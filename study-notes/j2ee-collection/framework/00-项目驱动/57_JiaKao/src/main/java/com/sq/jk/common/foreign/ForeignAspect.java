package com.sq.jk.common.foreign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.foreign.anno.ForeignCascade;
import com.sq.jk.common.foreign.anno.ForeignField;
import com.sq.jk.common.foreign.anno.ForeignField.ForeignFields;
import com.sq.jk.common.foreign.info.ForeignFieldInfo;
import com.sq.jk.common.foreign.info.ForeignTableInfo;
import com.sq.jk.common.util.Classes;
import com.sq.jk.common.util.Rs;
import com.sq.jk.common.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Aspect
@Component
public class ForeignAspect implements InitializingBean, ApplicationContextAware {
    private static final String FOREIGN_SCAN = "classpath*:com/sq/jk/pojo/po/**/*.class";
    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private ResourceLoader resourceLoader;

    @Around("execution(* com.sq.jk.service..*.remove*(..))")
    public Object handleRemove(ProceedingJoinPoint point) throws Throwable {
        Object target = point.getTarget();
        if (!(target instanceof IService)) return point.proceed();

        // 获取Service对象
        Class<?> poCls = ((IService<?>) target).getEntityClass();

        // 表格
        ForeignTableInfo table = ForeignTableInfo.get(poCls);
        if (table == null) return point.proceed();

        // 主键
        ForeignFieldInfo mainField = table.getMainField();
        if (mainField == null) return point.proceed();

        // 获取外键约束
        List<ForeignFieldInfo> subFields = mainField.getSubFields();
        if (CollectionUtils.isEmpty(subFields)) return point.proceed();

        // 获取参数值
        Object arg = point.getArgs()[0];
        List<Object> ids;
        if (arg instanceof List) {
            ids = (List<Object>) arg;
        } else {
            ids = new ArrayList<>();
            ids.add(arg);
        }

        for (ForeignFieldInfo subField : subFields) {
            ForeignTableInfo subTable = subField.getTable();
            BaseMapper<Class<?>> mapper = getMapper(subTable.getCls());
            QueryWrapper<Class<?>> wrapper = new QueryWrapper<>();
            wrapper.in(subField.getColumn(), ids);
            if (mainField.getCascade() == ForeignCascade.DEFAULT) { // 默认
                Integer count = mapper.selectCount(wrapper);
                if (count == 0) continue;
                Rs.raise(String.format("有%d条【%s】数据相关联，无法删除！", count, subTable.getTable()));
            } else { // 删除关联数据
                mapper.delete(wrapper);
            }
        }
        return point.proceed();
    }

    @Around("execution(* com.sq.jk.service..*.save*(..)) || execution(* com.sq.jk.service..*.update*(..)) ")
    public Object handleSaveOrUpdate(ProceedingJoinPoint point) throws Throwable {
        Object target = point.getTarget();
        if (!(target instanceof IService)) return point.proceed();

        // 获取Service对象
        Class<?> poCls = ((IService<?>) target).getEntityClass();

        // 表格
        ForeignTableInfo table = ForeignTableInfo.get(poCls);
        if (table == null) return point.proceed();

        // 获取外键约束
        Collection<ForeignFieldInfo> subFields = table.getSubFields().values();
        if (CollectionUtils.isEmpty(subFields)) return point.proceed();

        // 参数
        Object model = point.getArgs()[0];
        if (model.getClass() != poCls) {
            return point.proceed();
        }

        // 遍历外键约束
        for (ForeignFieldInfo subField : subFields) {
            List<ForeignFieldInfo> mainFields = subField.getMainFields();
            if (CollectionUtils.isEmpty(mainFields)) continue;
            // 引用的主键超过1个，无法智能处理，需要手动处理
            if (mainFields.size() > 1) continue;

            Object subValue = subField.getField().get(model);
            // 跳过空值（代表此字段不进行更新）
            if (subValue == null) continue;

            // 唯一的一个主键
            ForeignFieldInfo mainField = mainFields.get(0);
            BaseMapper<Class<?>> mapper = getMapper(mainField.getTable().getCls());
            QueryWrapper<Class<?>> wrapper = new QueryWrapper<>();
            wrapper.eq(mainField.getColumn(), subValue);
            if (mapper.selectCount(wrapper) == 0) {
                Rs.raise(String.format("%s=%s不存在", subField.getColumn(), subValue));
            }
        }
        return point.proceed();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        Resource[] rs = resolver.getResources(FOREIGN_SCAN);
        if (rs.length == 0) {
            Rs.raise("FOREIGN_SCAN配置错误，找不到任何类信息");
        }

        MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourceLoader);
        for (Resource r : rs) {
            parseCls(readerFactory.getMetadataReader(r).getClassMetadata().getClassName());
        }
    }

    private BaseMapper<Class<?>> getMapper(Class<?> poCls) {
        return (BaseMapper<Class<?>>) ctx.getBean(Strings.firstLetterLowercase(poCls.getSimpleName()) + "Mapper");
    }

    private void parseCls(String clsName) throws Exception {
        Class<?> subCls = Class.forName(clsName);
        ForeignTableInfo subTable = ForeignTableInfo.get(subCls, true);
        Classes.enumerateFields(subCls, (subField, curCls) -> {
            ForeignField ff = subField.getAnnotation(ForeignField.class);
            parseForeignField(subTable, subField, ff);

            ForeignFields ffs = subField.getAnnotation(ForeignFields.class);
            if (ffs == null) return null;
            for (ForeignField subFf : ffs.value()) {
                parseForeignField(subTable, subField, subFf);
            }
            return null;
        });
    }

    private void parseForeignField(ForeignTableInfo subTable,
                                   Field subField,
                                   ForeignField ff) throws Exception {
        // 跳过没有ForeignField注解的属性
        if (ff == null) return;
        // 主表的类
        Class<?> mainCls = Classes.notObject(ff.mainTable(), ff.value());
        // 说明ForeignField注解的是主键属性（因为缺乏mainCls）
        if (mainCls == null || mainCls.equals(Object.class)) return;

        // 主表中的主键属性
        Field mainField = Classes.getField(mainCls, ff.mainField());
        // 跳过错误（找不到）的主键属性
        if (mainField == null) return;

        // 存储到缓存中
        ForeignTableInfo mainTable = ForeignTableInfo.get(mainCls, true);
        ForeignFieldInfo subFieldInfo = subTable.getSubField(subField);
        ForeignFieldInfo mainFieldInfo = mainTable.getMainField(mainField);

        // 对象之间的关系
        subFieldInfo.addMainField(mainFieldInfo);
        mainFieldInfo.addSubField(subFieldInfo);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
