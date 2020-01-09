package com.coderZsq.rbac.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Role {
    private Long id;//角色id
    private String sn;//角色编码
    private String name;//角色名称
}
