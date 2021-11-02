package design_principles.isp.case9;

import java.util.Map;

public interface Viewer {
    String outputInPlainText();
    Map<String, String> output();
}