package cn.wolfcode.wolf2w.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 游记内容
 */
@Setter
@Getter
@TableName("travel_content")
public class TravelContent implements Serializable {
    private Long id;  //因为游记内容id不是自动增长
    private String content;
}
