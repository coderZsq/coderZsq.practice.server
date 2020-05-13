package com.coderZsq.login_app.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult {
    private boolean success=true;//操作成功与否
    private String msg;//失败信息
    public static PageResult mark(String msg){
        return new PageResult(false,msg);
    }
    public static PageResult success(){
        return new PageResult(true,"");
    }
}
