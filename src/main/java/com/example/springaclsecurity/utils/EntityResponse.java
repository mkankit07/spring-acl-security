package com.example.springaclsecurity.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EntityResponse<T, M> implements Serializable {

    private int code;
    private T data;
    private String message;
    private M metaData;

    public static EntityResponse<Void,Void> setEntityResponse(int code, String message) {
        return EntityResponse.<Void,Void>builder()
                .code(code)
                .message(message)
                .build();
    }

    public static <T> EntityResponse<T,Void> setEntityResponse(int code, String message, T data) {
        return EntityResponse.<T,Void>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static <T,M> EntityResponse<T,M> setEntityResponse(int code, String message, T data, M metadata) {
        return EntityResponse.<T,M>builder()
                .code(code)
                .message(message)
                .data(data)
                .metaData(metadata)
                .build();
    }
}
