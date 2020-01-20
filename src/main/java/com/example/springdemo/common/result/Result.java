package com.example.springdemo.common.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 统一返回格式
 * @Description:
 * @Auther: wjc
 * @Date: 2019/11/28 14:03
 */
@Data
@Builder
@NoArgsConstructor
public class Result<T> {

    private int code;

    private String message;

    private T data;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void success(T data) {
        this.code = 0;
        this.message = "成功";
        this.data = data;
    }

}