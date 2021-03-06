package com.seemygo.shop.cloud.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeMsg implements Serializable {

    private String code;
    private String msg;

    public static final CodeMsg PARAM_ERROR = new CodeMsg("A0002", "参数错误");
}
