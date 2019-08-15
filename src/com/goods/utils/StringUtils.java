package com.goods.utils;

public class StringUtils {
    /**
     * 判断字符串是否不为空
     * @param str
     * @return  true：表示不为空，false：表示字符串为空
     */
    public static boolean isNotNull(String str){
        return str!=null && !"".equals(str);
    }

    /**
     * 将字符串转换为Integer
     * @param str       要转换的字符串
     * @param defValue  如果转换失败则返回的默认值
     * @return
     */

    public static Integer str2Int(String str, Integer defValue){
        // 1、判断字符串是否不为空
        if(isNotNull(str)){
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("抛出的异常" + e.getMessage());
            }
        }
        return defValue;
    }

    public static Double str2Double(String str, Double defValue) {
        if (isNotNull(str)) {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("抛出的异常"+ e.getMessage());
            }
        }
        return defValue;
    }

}
