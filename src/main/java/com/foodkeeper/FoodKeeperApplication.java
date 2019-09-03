package com.foodkeeper;

import com.foodkeeper.domain.Sku;
import com.foodkeeper.domain.User;
import com.foodkeeper.repository.SkuRepository;
import com.foodkeeper.repository.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.List;

@SpringBootApplication
public class FoodKeeperApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkuRepository skuRepository;

    public static void main(String[] args) {
        SpringApplication.run(FoodKeeperApplication.class, args);
    }

    @Override
    public void run(String... args) {
        userRepository.save(getUser());
        skuRepository.saveAll(getSkuList());
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
        Sku sku = Sku.builder()
                .name("요구르트")
                .barcode("801056836011")
                .price(1000)
                .expiredAt(cal.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, 2);
        Sku sku2 = Sku.builder()
                .name("요구르트2")
                .barcode("801056836012")
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

        return skuList;
    }
}
