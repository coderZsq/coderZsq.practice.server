package cn.wolfcode.netty.demo;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author Leon
 * @date 2021/4/23
 */
public enum Base64Util {

    /**
     * Base64 工具类单例对象
     */
    ENCODER;

    private static final char[] TO_BASE64 = {
            'Q', 'j', '6', 'y', 'q', 'B', 'H', 'l',
            'b', 'i', '7', 'z', 'r', 'V', 'G', 'U',
            'd', 'I', '0', '1', '9', 'X', 'D', 'T',
            'c', 'k', 's', 't', '8', 'C', 'F', 'Y',
            '2', 'm', 'u', 'e', 'Z', '+', 'o', 'R',
            'f', 'n', 'v', '3', '/', 'L', 'A', 'E',
            'g', 'S', 'w', 'M', '4', 'K', 'P', 'W',
            'h', 'p', 'x', '5', 'N', 'J', 'O', 'a'
    };

    private static final String HEX_PREFIX = "0x";

    public static void main(String[] args) {
        // 获取报警码
        String[] alarmCode = Base64Util.ENCODER.getAlarmCode("A1");
        System.out.println(Arrays.toString(alarmCode));
    }

    /**
     * Base64 编码转 String
     *
     * @param src
     * @return
     */
    public String base64ToString(String src) {
        return base64ToString(src.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Base64 编码转 String
     *
     * @param src
     * @return
     */
    public String base64ToString(byte[] src) {
        byte[] encoded = toBase64(src);
        return new String(encoded, 0, 0, encoded.length);
    }

    /**
     * 获取报警码
     *
     * @param src
     * @return
     */
    public String[] getAlarmCode(String src) {
        String encrypt = sortAndReverse(base64ToString(src));

        String[] ret = new String[encrypt.length()];

        for (int i = 0; i < encrypt.length(); i++) {
            int ch = (int) encrypt.charAt(i);
            String hexStr = Integer.toHexString(ch);

            ret[i] = HEX_PREFIX + hexStr;
        }

        return ret;
    }

    /**
     * base64 编码
     *
     * @param src 源数组
     * @return byte 数组
     */
    public byte[] toBase64(byte[] src) {
        int len = outLength(src.length);
        byte[] dst = new byte[len];
        int ret = encode0(src, 0, src.length, dst);
        if (ret != dst.length) {
            return Arrays.copyOf(dst, ret);
        }
        return dst;
    }

    /**
     * 排序、倒序以及截取前6位
     *
     * @param encrypt
     * @return
     */
    private static String sortAndReverse(String encrypt) {
        char[] chars = encrypt.toCharArray();
        Arrays.sort(chars);

        StringBuilder builder = new StringBuilder(chars.length);
        builder.append(chars).reverse();

        String finalStr = builder.toString();

        if (builder.length() <= 6) {
            return finalStr;
        }

        return builder.toString().substring(0, 6);
    }

    private int outLength(int srcLen) {
        return 4 * ((srcLen + 2) / 3);
    }

    /**
     * base64 编码
     *
     * @param src 源数据
     * @param off 编码开始位置
     * @param end 编码结束位置
     * @param dst 编码的输出位置
     * @return
     */
    private int encode0(byte[] src, int off, int end, byte[] dst) {
        char[] base64 = TO_BASE64;
        int sp = off;
        int slen = (end - off) / 3 * 3;
        int sl = off + slen;
        int dp = 0;
        while (sp < sl) {
            int sl0 = Math.min(sp + slen, sl);
            for (int sp0 = sp, dp0 = dp; sp0 < sl0; ) {
                int bits = (src[sp0++] & 0xff) << 16 |
                        (src[sp0++] & 0xff) << 8 |
                        (src[sp0++] & 0xff);
                dst[dp0++] = (byte) base64[(bits >>> 18) & 0x3f];
                dst[dp0++] = (byte) base64[(bits >>> 12) & 0x3f];
                dst[dp0++] = (byte) base64[(bits >>> 6) & 0x3f];
                dst[dp0++] = (byte) base64[bits & 0x3f];
            }
            int dlen = (sl0 - sp) / 3 * 4;
            dp += dlen;
            sp = sl0;
        }
        // 1 or 2 leftover bytes
        if (sp < end) {
            int b0 = src[sp++] & 0xff;
            dst[dp++] = (byte) base64[b0 >> 2];
            if (sp == end) {
                dst[dp++] = (byte) base64[(b0 << 4) & 0x3f];
                dst[dp++] = '=';
                dst[dp++] = '=';
            } else {
                int b1 = src[sp++] & 0xff;
                dst[dp++] = (byte) base64[(b0 << 4) & 0x3f | (b1 >> 4)];
                dst[dp++] = (byte) base64[(b1 << 2) & 0x3f];
                dst[dp++] = '=';
            }
        }
        return dp;
    }
}
