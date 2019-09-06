package com.foodkeeper;

import com.foodkeeper.domain.OrderItemESDto;
import com.foodkeeper.domain.Sku;
import com.foodkeeper.domain.User;
import com.foodkeeper.repository.OrderItemESRepository;
import com.foodkeeper.repository.SkuRepository;
import com.foodkeeper.repository.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@EnableElasticsearchRepositories
@SpringBootApplication
public class FoodKeeperApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private OrderItemESRepository orderItemESRepository;

    public static void main(String[] args) {
        SpringApplication.run(FoodKeeperApplication.class, args);
    }

    @Override
    public void run(String... args) {
        userRepository.saveAll(getUserList());
        skuRepository.saveAll(getSkuList());
//        orderItemESRepository.save(getOrderItemESDto());
    }

    private List<User> getUserList() {
        List<User> userList = Lists.newArrayList();
        User user = new User();
        user.setUserId("admin");
        user.setCustNo("admin");
        user.setPassword(getRandomHexString(20));
        user.setToken(getRandomHexString(50));
        userList.add(user);

        User user2 = new User();
        user2.setUserId("lth");
        user2.setCustNo("01050180048");
        user2.setPassword(getRandomHexString(20));
        user2.setToken(getRandomHexString(50));
        userList.add(user2);

        User user3 = new User();
        user3.setUserId("ajh");
        user3.setPassword(getRandomHexString(20));
        user3.setCustNo("01076715951");
        user3.setToken(getRandomHexString(50));
        userList.add(user3);
        return userList;
    }

    private List<Sku> getSkuList() {
        List skuList = Lists.newArrayList();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Sku sku = Sku.builder()
                .name("스파클")
                .barcode("8809055546202")
                .price(1000)
                .expiredAt(cal.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, 2);
        Sku sku2 = Sku.builder()
                .name("페레로로쉐")
                .barcode("80050278")
                .price(2000)
                .expiredAt(cal2.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku2);

        Calendar cal3 = Calendar.getInstance();
        cal3.add(Calendar.DATE, 3);
        Sku sku3 = Sku.builder()
                .name("요구르트3")
                .barcode("801056836013")
                .price(3000)
                .expiredAt(cal3.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku3);

        Calendar cal4 = Calendar.getInstance();
        cal4.add(Calendar.DATE, 4);
        Sku sku4 = Sku.builder()
                .name("요구르트4")
                .barcode("801056836014")
                .price(4000)
                .expiredAt(cal4.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku4);

        Calendar cal5 = Calendar.getInstance();
        cal5.add(Calendar.DATE, 6);
        Sku sku5 = Sku.builder()
                .name("우유1")
                .barcode("801056836015")
                .price(4500)
                .expiredAt(cal5.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku5);

        Calendar cal6 = Calendar.getInstance();
        cal6.add(Calendar.DATE, 8);
        Sku sku6 = Sku.builder()
                .name("우유2")
                .barcode("801056836016")
                .price(2700)
                .expiredAt(cal6.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku6);

        Calendar cal7 = Calendar.getInstance();
        cal7.add(Calendar.DATE, 9);
        Sku sku7 = Sku.builder()
                .name("우유3")
                .barcode("801056836017")
                .price(1700)
                .expiredAt(cal7.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku7);

        Calendar cal8 = Calendar.getInstance();
        cal8.add(Calendar.DATE, 0);
        Sku sku8 = Sku.builder()
                .name("우유4")
                .barcode("801056836018")
                .price(1900)
                .expiredAt(cal8.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku8);

        Calendar cal9 = Calendar.getInstance();
        cal9.add(Calendar.DATE, 30);
        Sku sku9 = Sku.builder()
                .name("유통기한 +30")
                .barcode("801056836019")
                .price(1900)
                .expiredAt(cal9.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku9);

        Calendar cal10= Calendar.getInstance();
        cal10.add(Calendar.DATE, -2);
        Sku sku10 = Sku.builder()
                .name("유통기한 지난 상품")
                .barcode("801056836020")
                .price(1900)
                .expiredAt(cal10.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku10);


        return skuList;
    }

    private OrderItemESDto getOrderItemESDto(){
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        return OrderItemESDto.builder().id(1L).skuName("테스트상품").orderItemId(1L).skuImage("test").orderedAt(df.format(new Date())).expiredAt(df.format(new Date())).build();
    }

    private String getRandomHexString(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, numchars);
    }
}
