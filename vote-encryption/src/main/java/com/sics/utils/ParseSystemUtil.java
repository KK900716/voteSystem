package com.sics.utils;

/**
 * Hexadecimal conversion
 *
 * @author liangjc
 * @version 2022/07/26
 */
public class ParseSystemUtil {
    /**
     * 二进制转十六进制
     * @author liangjc
     * @param buf 二进制
     * @return 十六进制
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    public static byte[] hexStringToByte(String hex) {
        byte[] b = new byte[hex.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hex.charAt(j++);
            char c1 = hex.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }
    private static int parse(char c) {
        if (c >= 'a') {
            return (c - 'a' + 10) & 0x0f;
        }
        if (c >= 'A') {
            return (c - 'A' + 10) & 0x0f;
        }
        return (c - '0') & 0x0f;
    }
    /**
     * 十六进制转二进制
     * @author liangjc
     * @param hexStr 十六进制
     * @return 二进制
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
