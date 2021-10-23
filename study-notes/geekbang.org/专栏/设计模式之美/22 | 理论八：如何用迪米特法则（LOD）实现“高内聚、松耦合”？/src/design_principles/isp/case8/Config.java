package design_principles.isp.case8;

import java.util.Map;

public interface Config {
    void update();
    String outputInPlainText();
    Map<String, String> output();
}