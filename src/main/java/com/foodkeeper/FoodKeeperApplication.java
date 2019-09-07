package com.foodkeeper;

import com.foodkeeper.domain.OrderItemESDto;
import com.foodkeeper.domain.Sku;
import com.foodkeeper.domain.User;
import com.foodkeeper.repository.OrderItemESRepository;
import com.foodkeeper.repository.SkuRepository;
import com.foodkeeper.repository.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@EnableBatchProcessing
@EnableScheduling
@EnableElasticsearchRepositories
@SpringBootApplication
public class FoodKeeperApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private OrderItemESRepository orderItemESRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(FoodKeeperApplication.class, args);
    }

    @Override
    public void run(String... args) {
        userRepository.saveAll(getUserList());
        skuRepository.saveAll(getSkuList());
//        orderItemESRepository.save(getOrderItemESDto());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private List<User> getUserList() {
        List<User> userList = Lists.newArrayList();
        User user = new User();
        user.setUserId("admin");
        user.setCustNo("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setToken(getRandomHexString(50));
        userList.add(user);

        User user2 = new User();
        user2.setUserId("lth");
        user2.setCustNo("01050180048");
        user2.setPassword(passwordEncoder.encode("lth"));
        user2.setToken(getRandomHexString(50));
        userList.add(user2);

        User user3 = new User();
        user3.setUserId("ajh");
        user3.setPassword(passwordEncoder.encode("ajh"));
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
        cal3.add(Calendar.DATE, 10);
        Sku sku3 = Sku.builder()
                .name("꿀홍삼")
                .barcode("8801382127130")
                .price(3000)
                .expiredAt(cal3.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku3);

        Calendar cal4 = Calendar.getInstance();
        cal4.add(Calendar.DATE, 365);
        Sku sku4 = Sku.builder()
                .name("칠성사이다")
                .barcode("8801056070892")
                .price(4000)
                .expiredAt(cal4.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku4);

        Calendar cal5 = Calendar.getInstance();
        cal5.add(Calendar.DATE, 1000);
        Sku sku5 = Sku.builder()
                .name("잔치집식혜")
                .barcode("8801056063245")
                .price(4500)
                .expiredAt(cal5.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku5);

        Calendar cal6 = Calendar.getInstance();
        cal6.add(Calendar.DATE, -2);
        Sku sku6 = Sku.builder()
                .name("크런키")
                .barcode("8801062628476")
                .price(2700)
                .expiredAt(cal6.getTime())
                .imageUrl("https://eventusstorage.blob.core.windows.net/evs/Image/nhhackathon/9423/ProjectInfo/7ec0e6f9efdb482985db1b0135538b00.jpg")
                .build();
        skuList.add(sku6);

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
