package cn.wolfcode.wolf2w.vo;

import cn.wolfcode.wolf2w.domain.StrategyCatalog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CatalogVO {
    private String destName;
    private List<StrategyCatalog> catalogList = new ArrayList<>();
}
