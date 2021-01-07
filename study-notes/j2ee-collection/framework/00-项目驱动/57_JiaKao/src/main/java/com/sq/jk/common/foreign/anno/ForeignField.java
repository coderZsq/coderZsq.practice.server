package com.sq.jk.common.foreign.anno;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(ForeignField.ForeignFields.class)
public @interface ForeignField {
    /**
     * 被引用的主表类
     */
    Class<?> value() default Object.class;

    /**
     * 被引用的主表类
     */
    Class<?> mainTable() default Object.class;

    /**
     * 被引用的属性名
     */
    String mainField() default "id";

    /**
     * 当前属性在数据库中的字段名
     */
    String column() default "";

    /**
     * 级联类型
     */
    ForeignCascade cascade() default ForeignCascade.DEFAULT;

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface ForeignFields {
        /**
         * 被引用的主表类
         */
        ForeignField[] value() default {};
    }
}
