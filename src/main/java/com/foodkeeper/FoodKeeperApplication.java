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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    private User getUser(){
        User user = new User();
        user.setUserId("admin");
        return user;
    }

    private List<Sku> getSkuList(){
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        List skuList = Lists.newArrayList();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Sku sku = Sku.builder().name("요구르트").barcode("801056836011").price(1000).expiredAt(df.format(cal.getTime())).build();
        skuList.add(sku);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, 2);
        Sku sku2 = Sku.builder().name("요구르트2").barcode("801056836012").price(2000).expiredAt(df.format(cal2.getTime())).build();
        skuList.add(sku2);

        Calendar cal3 = Calendar.getInstance();
        cal3.add(Calendar.DATE, 3);
        Sku sku3 = Sku.builder().name("요구르트3").barcode("801056836013").price(3000).expiredAt(df.format(cal3.getTime())).build();
        skuList.add(sku3);

        Calendar cal4 = Calendar.getInstance();
        cal4.add(Calendar.DATE, 4);
        Sku sku4 = Sku.builder().name("요구르트4").barcode("801056836014").price(4000).expiredAt(df.format(cal4.getTime())).build();
        skuList.add(sku4);

        return skuList;
    }
}
