package com.hesj.rbac.qo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {
    //是否成功,默认为true 成功
    private boolean success =  true;
    private String msg;//错误信息
}