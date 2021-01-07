package com.sq.jk.common.foreign.info;

import com.sq.jk.common.foreign.anno.ForeignCascade;
import com.sq.jk.common.foreign.anno.ForeignField;
import com.sq.jk.common.foreign.anno.ForeignTable;
import com.sq.jk.common.util.Strings;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ForeignTableInfo {
    private static final Map<Class<?>, ForeignTableInfo> cache = new HashMap<>();

    private Class<?> cls;
    private String table;
    private ForeignFieldInfo mainField;
    /**
     * key是field的属性名
     */
    private Map<String, ForeignFieldInfo> subFields = new HashMap<>();

    public static ForeignTableInfo get(Class<?> tableCls) {
        return get(tableCls, false);
    }

    /**
     * 从缓存中取出table
     * @param newIfAbsent 如果找不到就新建一个
     */
    public static ForeignTableInfo get(Class<?> tableCls, boolean newIfAbsent) {
        if (!newIfAbsent) return cache.get(tableCls);
        return cache.computeIfAbsent(tableCls, k -> {
            ForeignTableInfo table = new ForeignTableInfo();
            // 类
            table.setCls(tableCls);

            // 表名
            ForeignTable tableAnno = tableCls.getAnnotation(ForeignTable.class);
            String tableName;
            if (tableAnno != null) {
                tableName = Strings.notEmpty(tableAnno.name(), tableAnno.value());
            } else {
                tableName = Strings.camel2underline(tableCls.getSimpleName());
            }
            table.setTable(tableName);
            return table;
        });
    }

    public ForeignFieldInfo getMainField(Field field) {
        if (mainField == null) {
            mainField = new ForeignFieldInfo();
            mainField.setTable(this);
            mainField.setField(field);
            mainField.setColumn(getFieldColumn(field));
            ForeignField ff = field.getAnnotation(ForeignField.class);
            if (ff != null) {
                mainField.setCascade(ff.cascade());
            } else {
                mainField.setCascade(ForeignCascade.DEFAULT);
            }
        }
        return mainField;
    }

    public ForeignFieldInfo getSubField(Field field) {
        String fieldName = field.getName();
        return subFields.computeIfAbsent(fieldName, k -> {
            ForeignFieldInfo subField = new ForeignFieldInfo();
            subField.setTable(this);
            subField.setField(field);
            subField.setColumn(getFieldColumn(field));
            return subField;
        });
    }

    private String getFieldColumn(Field field) {
        ForeignField ff = field.getAnnotation(ForeignField.class);
        if (ff != null) {
            String column = ff.column();
            if (!Strings.isEmpty(column)) return column;
        }
        return Strings.camel2underline(field.getName());
    }
}
