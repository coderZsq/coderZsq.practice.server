package cn.wolfcode.netty.demo;


import java.util.*;

/**
 * @ClassName CodeUtils
 * @Description TODO
 * @Author Joey
 * @Date 2021-04-22 18:41
 **/
public class CodeUtils {

    private static final Map<Integer,String> BASE64_ENCODE_TABLE = new HashMap();

    private CodeUtils(){}

    public static void encode(){

        Base64.Encoder encoder = Base64.getEncoder();

        byte[] bytes = "A1B2".getBytes();

        String s = encoder.encodeToString(bytes);
        System.out.println(s);
    }


    public static void main(String[] args) {

        String str = "A1B2";


        String[] strings = toArrays(str,3);
        System.out.println(strings);


        List<StringBuilder> list = new ArrayList<>();

        for (String string : strings) {
            StringBuilder stringBuilder = new StringBuilder();

            String[] c = string.split("");

            for (String s : c) {
                stringBuilder.append(strToBinary(s));
            }
            list.add(stringBuilder);

        }
        System.out.println(list);

        List<Integer> list1 = new ArrayList<>();

        //每6位分组；
        for (StringBuilder stringBuilder : list) {

            String[] strings1 = toArrays(stringBuilder.toString(), 6);

            //不足六位的后面加0
            for (int i = 0; i < strings1.length; i++) {
                String s = strings1[i];
                if (s.length()<6){
                    int num = 6 - s.length();
                    for (int i1 = 0; i1 < num; i1++) {
                        s+="0";
                    }
                    strings1[i] = s;
                }
            }

            for (int i = 0; i < strings1.length; i++) {
                String s = "00" + strings1[i];
                Integer integer = Integer.valueOf(s,2);
                list1.add(integer);
            }
        }

        System.out.println(list1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list1.size(); i++) {
            Integer key = list1.get(i);
            String s = BASE64_ENCODE_TABLE.get(key);
            sb.append(s);
        }
        if (sb.length()!=8){
            int i = 8 - sb.length();
            for (int i1 = 0; i1 < i; i1++) {
                sb.append("=");
            }
        }
        System.out.println(sb.toString());

    }

    // 将字符串转换成二进制字符串，以空格相隔
    private static StringBuilder strToBinary(String str) {
        char[] chars = str.toCharArray();
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < chars.length; i++) {
            result.append(Integer.toBinaryString(chars[i]) + "");
        }
        if (result.length()> 8 || result.length() == 0){
            throw new RuntimeException("生成二进制长度不合法");
        }
        if (result.length()<8){
            int i = 8 - result.length();
            for (int i1 = 0; i1 < i; i1++) {
                result.insert(0,"0");
            }
        }
        return result;
    }

    /**
     * 字符串按三个进行分组
     * @param str
     * @return
     */
    private static String[] toArrays(String str, int length){

        char[] chars = str.toCharArray();

        int size;

        if (chars.length % length == 0){
            size = chars.length / length;
        }else {
            size = chars.length / length + 1;
        }
        String[] strings = new String[size];

        StringBuilder sb = new StringBuilder();
        int index = 0;
        //能装满个数倍数
        int length1 = chars.length;
        int i1 = length1 / length;
        //能装整的最大个数
        int i2 = length * i1;

        for (int i = 0; i < length1; i++) {

            sb.append(chars[i]);

            if (i2 > i){
                if (sb.length()==length){
                    strings[index] = sb.toString();
                    sb.delete(0,sb.length());
                    ++index;
                }
            } else {
                if (i == length1-1){
                    strings[index] = sb.toString();
                }
            }
        }

        return strings;
    }

    /**
     * 定义Base64编码索引表
     */
    static {

        BASE64_ENCODE_TABLE.put(0,"Q");
        BASE64_ENCODE_TABLE.put(1,"j");
        BASE64_ENCODE_TABLE.put(2,"6");
        BASE64_ENCODE_TABLE.put(3,"y");
        BASE64_ENCODE_TABLE.put(4,"q");
        BASE64_ENCODE_TABLE.put(5,"B");
        BASE64_ENCODE_TABLE.put(6,"H");
        BASE64_ENCODE_TABLE.put(7,"l");

        BASE64_ENCODE_TABLE.put(8,"b");
        BASE64_ENCODE_TABLE.put(9,"i");
        BASE64_ENCODE_TABLE.put(10,"7");
        BASE64_ENCODE_TABLE.put(11,"z");
        BASE64_ENCODE_TABLE.put(12,"r");
        BASE64_ENCODE_TABLE.put(13,"V");
        BASE64_ENCODE_TABLE.put(14,"G");
        BASE64_ENCODE_TABLE.put(15,"U");

        BASE64_ENCODE_TABLE.put(16,"d");
        BASE64_ENCODE_TABLE.put(17,"I");
        BASE64_ENCODE_TABLE.put(18,"O");
        BASE64_ENCODE_TABLE.put(19,"1");
        BASE64_ENCODE_TABLE.put(20,"9");
        BASE64_ENCODE_TABLE.put(21,"X");
        BASE64_ENCODE_TABLE.put(22,"D");
        BASE64_ENCODE_TABLE.put(23,"T");

        BASE64_ENCODE_TABLE.put(24,"c");
        BASE64_ENCODE_TABLE.put(25,"k");
        BASE64_ENCODE_TABLE.put(26,"s");
        BASE64_ENCODE_TABLE.put(27,"t");
        BASE64_ENCODE_TABLE.put(28,"8");
        BASE64_ENCODE_TABLE.put(29,"C");
        BASE64_ENCODE_TABLE.put(30,"F");
        BASE64_ENCODE_TABLE.put(31,"Y");

        BASE64_ENCODE_TABLE.put(32,"2");
        BASE64_ENCODE_TABLE.put(33,"m");
        BASE64_ENCODE_TABLE.put(34,"u");
        BASE64_ENCODE_TABLE.put(35,"e");
        BASE64_ENCODE_TABLE.put(36,"Z");
        BASE64_ENCODE_TABLE.put(37,"+");
        BASE64_ENCODE_TABLE.put(38,"o");
        BASE64_ENCODE_TABLE.put(39,"R");

        BASE64_ENCODE_TABLE.put(40,"f");
        BASE64_ENCODE_TABLE.put(41,"n");
        BASE64_ENCODE_TABLE.put(42,"v");
        BASE64_ENCODE_TABLE.put(43,"3");
        BASE64_ENCODE_TABLE.put(44,"/");
        BASE64_ENCODE_TABLE.put(45,"L");
        BASE64_ENCODE_TABLE.put(46,"A");
        BASE64_ENCODE_TABLE.put(47,"E");

        BASE64_ENCODE_TABLE.put(48,"g");
        BASE64_ENCODE_TABLE.put(49,"S");
        BASE64_ENCODE_TABLE.put(50,"w");
        BASE64_ENCODE_TABLE.put(51,"M");
        BASE64_ENCODE_TABLE.put(52,"4");
        BASE64_ENCODE_TABLE.put(53,"K");
        BASE64_ENCODE_TABLE.put(54,"P");
        BASE64_ENCODE_TABLE.put(55,"W");

        BASE64_ENCODE_TABLE.put(56,"h");
        BASE64_ENCODE_TABLE.put(57,"p");
        BASE64_ENCODE_TABLE.put(58,"x");
        BASE64_ENCODE_TABLE.put(59,"5");
        BASE64_ENCODE_TABLE.put(60,"N");
        BASE64_ENCODE_TABLE.put(61,"J");
        BASE64_ENCODE_TABLE.put(62,"O");
        BASE64_ENCODE_TABLE.put(63,"a");

    }

}
