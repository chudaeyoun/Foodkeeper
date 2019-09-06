package com.foodkeeper.domain;

import lombok.Data;

@Data
public class FcmDto {
    //토큰
    private String token;
    //제목
    private String title;
    //body 내용
    private String body;
}
