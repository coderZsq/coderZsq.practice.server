package cn.wolfcode.wolf2w.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 攻略内容
 */
@Setter
@Getter
@TableName("strategy_content")
public class StrategyContent implements Serializable {
    private Long id;  //因为攻略内容id不是自动增长
    private String content;
}
