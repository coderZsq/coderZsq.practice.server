package com.sq.jk.common.foreign.info;

import com.sq.jk.common.foreign.anno.ForeignCascade;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ForeignFieldInfo {
    /**
     * 属性名
     */
    private Field field;
    /**
     * 列名
     */
    private String column;
    /**
     * 表
     */
    private ForeignTableInfo table;
    /**
     * 自己引用着的一些属性
     */
    private List<ForeignFieldInfo> mainFields;
    /**
     * 引用着自己的一些属性
     */
    private List<ForeignFieldInfo> subFields;
    /**
     * 级联类型
     */
    private ForeignCascade cascade;

    public void setField(Field field) {
        field.setAccessible(true);
        this.field = field;
    }

    public void addSubField(ForeignFieldInfo subField) {
        if (subFields == null) {
            subFields = new ArrayList<>();
        } else if (subFields.contains(subField)) {
            return;
        }
        subFields.add(subField);
    }

    public void addMainField(ForeignFieldInfo mainField) {
        if (mainFields == null) {
            mainFields = new ArrayList<>();
        } else if (mainFields.contains(mainField)) {
            return;
        }
        mainFields.add(mainField);
    }
}
