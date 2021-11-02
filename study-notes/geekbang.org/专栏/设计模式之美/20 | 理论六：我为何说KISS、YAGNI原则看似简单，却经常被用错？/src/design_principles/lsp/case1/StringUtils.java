package design_principles.lsp.case1;

public class StringUtils {
    public static boolean isNotBlank(String appToken) {
        return appToken != null && appToken.length() != 0;
    }

    public static boolean isBlank(String appId) {
        return appId == null || appId.length() == 0;
    }

    public static String[] split(String ipAddress, char c) {
        return null;
    }
}
