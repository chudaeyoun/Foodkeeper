package com.foodkeeper;

import com.foodkeeper.domain.Order;
import com.foodkeeper.domain.OrderItem;
import com.foodkeeper.domain.Sku;
import com.foodkeeper.domain.User;
import com.foodkeeper.repository.OrderItemRepository;
import com.foodkeeper.repository.OrderRepository;
import com.foodkeeper.repository.SkuRepository;
import com.foodkeeper.repository.UserRepository;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class FoodKeeperApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public static void main(String[] args) {
        SpringApplication.run(FoodKeeperApplication.class, args);
    }

    @Override
    public void run(String... args) {
        User user = userRepository.save(getUser());
        Iterable<Sku> skuList= skuRepository.saveAll(getSkuList());
        Order order = orderRepository.save(getOrder(user));
        saveOrderItem(order, Lists.newArrayList(skuList));
    }

    private User getUser() {
        User user = new User();
        user.setUserId("admin");
        return user;
    }

    private List<Sku> getSkuList() {
        List skuList = Lists.newArrayList();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Sku sku = Sku.builder().name("요구르트").barcode("801056836011").price(1000).expiredAt(cal.getTime()).build();
        skuList.add(sku);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, 2);
        Sku sku2 = Sku.builder().name("요구르트2").barcode("801056836012").price(2000).expiredAt(cal2.getTime()).build();
        skuList.add(sku2);

        Calendar cal3 = Calendar.getInstance();
        cal3.add(Calendar.DATE, 3);
        Sku sku3 = Sku.builder().name("요구르트3").barcode("801056836013").price(3000).expiredAt(cal3.getTime()).build();
        skuList.add(sku3);

        Calendar cal4 = Calendar.getInstance();
        cal4.add(Calendar.DATE, 4);
        Sku sku4 = Sku.builder().name("요구르트4").barcode("801056836014").price(4000).expiredAt(cal4.getTime()).build();
        skuList.add(sku4);

        return skuList;
    }

    private HashMap<Long, Integer> getCartItem(List<Sku> skuList) {
        // skuId, quantity
        HashMap<Long, Integer> cartItemMap = Maps.newHashMap();
        int quantity = 1;
        for (Sku sku : skuList) {
            cartItemMap.put(sku.getId(), quantity++);
        }

        return cartItemMap;
    }

    private Order getOrder(User user) {
        return Order.builder().user(user).totalPrice(0).build();
    }

    private void saveOrderItem(Order order, List<Sku> skuList) {
        int totalPrice = 0;
        HashMap<Long, Integer> cartItem = getCartItem(skuList);

        for (Long skuId : cartItem.keySet()) {
            Optional<Sku> sku = skuRepository.findById(skuId);
            if (sku.isPresent()) {
                int quantity = cartItem.get(skuId);
                totalPrice += sku.get().getPrice() * quantity;
                OrderItem orderItem = OrderItem.builder().order(order).user(order.getUser()).sku(sku.get()).quantity(quantity).noti_yn(true).build();
                orderItemRepository.save(orderItem);
            }
        }

        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
    }
}
