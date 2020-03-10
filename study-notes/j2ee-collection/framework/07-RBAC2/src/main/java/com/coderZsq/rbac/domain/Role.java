package com.coderZsq.rbac.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class Role {
    private Long id;

    private String name;

    private String sn;

    //role关联permissions
    private List<Permission> permissions;
}