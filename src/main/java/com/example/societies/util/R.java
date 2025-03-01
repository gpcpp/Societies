package com.example.societies.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class R {
    //状态码
    private String code;

    //提示信息
    private String msg;

    //返回的数据
    private Object data;
}
