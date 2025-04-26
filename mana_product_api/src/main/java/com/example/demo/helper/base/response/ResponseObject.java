package com.example.demo.helper.base.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseObject <T> {
    private String message;
    private T data;
}