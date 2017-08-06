package com.yc.weixin.utils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文字处理类</br>
 * @author zy <br />
 */
public final class StringUtil {

    /** 字符编码*/
    private static final String CHAR_ENCODE = "utf-8";
    /** 全角文字长度*/
    private static final int WCHAR_WIDTH = 2;

    /**
     * appendWithPaddingのオプション(空白を後ろに追加)
     */
    private static final int APPEND_AFTER = 0;
    /**
     * appendWithPaddingのオプション(空白を前に追加)
     */
    private static final int APPEND_BEFORE = 1;
    /**
     * 半角空白
     */
    private static final String HALF_WHITESPACE = " ";
    /**
     * 半角空白(char)
     */
    private static final char PADC = HALF_WHITESPACE.charAt(0); //半角空白

    /**
     * 构造方法私有化.
     */
    private StringUtil() {
    }

    /**
     * 截取文字。</br>
     * {@link java.lang.String#substring(int)}
     * @param   strArg     目标字符串
     * @param   beginIndex 开始索引
     * @return             目标字符串的一部分
     */
    public static String subString(String strArg, int beginIndex) {
        if (strArg == null) {
            return "";
        }
        return subString(strArg, beginIndex, strArg.length());
    }

    /**
     * 截取文字。</br>
     * {@link java.lang.String#substring(int, int)}
     * と同じ動作をしますが、文字が足りない場合に
     * 例外を発生させません。
     * 
      * @param   strArg     目标字符串
     * @param   beginIndex 开始索引
     * @param endIndex 结束索引
     * @return 目标字符串的一部分
     */
    public static String subString(String strArg, int beginIndex, int endIndex) {
        if (strArg == null) {
            return "";
        }
        if (strArg.length() <= beginIndex) {
            return "";
        }
        if (strArg.length() < endIndex) {
            return strArg.substring(beginIndex);
        } else {
            return strArg.substring(beginIndex, endIndex);
        }
    }

    /**
     * 空白(null)判定。</br>
     * 
     * @param  strArg 目标文字
     * @return 
     */
    public static boolean isBlank(Object strArg) {
        return (strArg == null || strArg.toString().trim().equals(""));
    }

    /**
     * 数値判定。</br>
     * 
     * @param  strArg 目标文字 
     * @return true
     */
    public static boolean isNumeric(String strArg) {
        if (strArg == null) {
            return false;
        }
        try {
            Long.parseLong(strArg);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * 判断字符串是否为正数。</br>
     * 
     * @param  目标文字 
     * @return 
     */
    public static boolean isNumericPositive(String strArg) {
        boolean isnumeric = isNumeric(strArg);
        if (!isnumeric) {
            return false;
        }
        return (Long.parseLong(strArg) >= 0);
    }

    /**
     * 获取字符串的字节数组长度
     * @param strArg 目标字符串
     * @return 
     */
    public static long getByteLength(String strArg) {
        if (strArg == null) {
            return 0;
        }
        return getBytes(strArg).length;
    }
    /**
     * 获取字符串的字节数组码
     * @param strArg 目标字符串
     * @return 字节数组序列
     */
    public static byte[] getBytes(String strArg) {
        if (strArg == null) {
            return new byte[] {};
        }
        try {
            return strArg.getBytes(CHAR_ENCODE);
        } catch (UnsupportedEncodingException e) {
            return new byte[] {};
        }
    }

  /**
   * 字符串转长整型
   * @param strArg
   * @return
   */
    public static long parseLong(String strArg) {
        if (strArg == null) {
            return 0;
        }
        try {
            return Long.parseLong(strArg);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    /**
     * 字符串转整型</br>
     * @param  strArg  
     * @return 数値変換
     */
    public static int parseInt(String strArg) {
        if (strArg == null) {
            return 0;
        }
        try {
            return Integer.parseInt(strArg);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    /**
     * 字符串转double</br>
     * @param  strArg  
     * @return 数値変換
     */
    public static double parseDouble(String strArg) {
        if (strArg == null) {
            return 0;
        }
        try {
            return Double.parseDouble(strArg);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    /**
     * 判断字符是全角，半角，还是混合.
     * @param strArg
     * @return   -1 半角   0 混合   1　全角
     */
    public static int isCharType(String strArg){
    	if( getBytes(strArg).length==strArg.length()){
    		return -1;
    	}else if(getBytes( strArg).length>strArg.length()&& getBytes( strArg).length!=strArg.length()){
    		return 0;
    	}else if( getBytes(strArg).length==strArg.length()*2){
    		return 1;
    	}
    	return -2;
    }

    /**
     * 半角文字列判定。</br>
     * @param  strArg  
     * @return 
     */
    public static boolean isHankaku(String strArg) {
        return (strArg.length() == getBytes(strArg).length);
    }
    /**
     * 全て全角文字かどうかの判定。</br>
     * @param  strArg 対象の文字列 
     * @return 全て全角文字列の場合、true
     */
    public static boolean isAllZenkaku(String strArg) {
        return ((strArg.length() * WCHAR_WIDTH) == getBytes(strArg).length);
    }

    /**
     * 获取日期。<br/>
     * {@link java.text.SimpleDateFormat}
     * <pre>
     * 【例】获取当前时间 (格式: yyyy/MM/dd HH:mm:ss)
     * dateFormat(new Date(), "yyyy/MM/dd HH:mm:ss");
     * </pre>
     * @param date 
     * @param pattern {@link java.text.SimpleDateFormat}的定义
     * @return 格式化后的日期字符串
     */
    public static String dateFormat(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * 数值数据的格式化操作。<br/>
     * {@link java.text.DecimalFormat}。
     * @param num 対象数値
     * @param pattern {@link java.text.DecimalFormat}的定义
     * @return 格式化后的文字列
     */
    public static String decimalFormat(double num, String pattern) {
        DecimalFormat formatter = new DecimalFormat(pattern);
        return formatter.format(num);
    }

    /**
     * 数值数据的格式化操作。<br/>
     * {@link java.text.DecimalFormat}。
     * @param num 対象数値
     * @param pattern {@link java.text.DecimalFormat}的定义
     * @return 格式化后的文字列
     */
    public static String decimalFormat(String num, String pattern) {
        DecimalFormat formatter = new DecimalFormat(pattern);
        return formatter.format(num);
    }

    /**
     * 空白文字列作成。<br/>
     * 
     * @param size 空白サイズ
     * @return 空白文字列
     */
    public static String space(int size) {
        StringBuffer buf = new StringBuffer(size);
        for (int i = 0; i < size; i++) {
            buf.append(" ");
        }

        return buf.toString();
    }

    /**
     * 文字列の左埋め。<br/>
     * 
     * @param strTarget 対象の文字列
     * @param ch 埋められる文字列
     * @param size サイズ
     * @return 左埋め後の文字列
     */
    public static String fillLeft(String strTarget, String ch, int size) {
        StringBuffer buf = new StringBuffer(strTarget);

        while (buf.length() < size) {
            buf.insert(0, ch);
        }

        return buf.toString();
    }

    /**
     * 文字列の左埋め。（パディング文字をcharで指定）<br/>
     * 
     * @param strTarget 対象の文字列
     * @param ch 埋められるキャラクタ
     * @param size サイズ
     * @return 左埋め後の文字列
     */
    public static String fillLeft(String strTarget, char ch, int size) {
        StringBuffer buf = new StringBuffer(strTarget);

        while (buf.length() < size) {
            buf.insert(0, ch);
        }

        return buf.toString();
    }

    /**
     * 文字列の右埋め。<br/>
     * 
     * @param strTarget 対象の文字列
     * @param ch 埋められる文字列
     * @param size サイズ
     * @return 右埋め後の文字列
     */
    public static String fillRight(String strTarget, String ch, int size) {
        StringBuffer buf = new StringBuffer(strTarget);

        while (buf.length() < size) {
            buf.append(ch);
        }

        return buf.toString();
    }
    /**
     * 文字列の右埋め。（パディング文字をcharで指定）<br/>
     * 
     * @param strTarget 対象の文字列
     * @param ch 埋められる文字列
     * @param size サイズ
     * @return 右埋め後の文字列
     */
    public static String fillRight(String strTarget, char ch, int size) {
        StringBuffer buf = new StringBuffer(strTarget);

        while (buf.length() < size) {
            buf.append(ch);
        }

        return buf.toString();
    }

    /**
     * 文字数の先頭から指定バイト数分を取得する
     * 元の長さがが指定に満たない場合は半角空白を追加した文字列を返す
     * @param anAppendee 追加する文字列
     * @param aByteLength 項目桁数
     * @return String 編集後の文字列
     */
    public static String getLengthedString(String anAppendee, int aByteLength) {

        return getLengthedStringGeneral(anAppendee, aByteLength, APPEND_AFTER);
    }

    /**
     * 文字列が桁数指定に満たない場合は空白を追加した文字列を返す
     * @param anAppendee 追加する文字列
     * @param aByteLength 項目桁数
     * @param anAppendOption 空白追加方法
     * @return String 編集後の文字列
     */
    public static String getLengthedStringGeneral(
        String anAppendee,
        int aByteLength,
        int anAppendOption) {

        //引数のチェック
        if (aByteLength < 1) {
            throw new IllegalArgumentException("桁数には1以上の値を指定してください。");
        }
        if (!(anAppendOption == APPEND_AFTER || anAppendOption == APPEND_BEFORE)) {
            throw new IllegalArgumentException("オプション指定が誤っています。");
        }
        //長さの判定
        byte[] appendeeByte = null;
        int appendeeLength = 0;
        if (anAppendee != null) {
            appendeeByte = getBytes(anAppendee);
            appendeeLength = appendeeByte.length;
        }
        //すでに指定の長さとなっている場合はそのまま返す
        if (appendeeLength == aByteLength) {
            return anAppendee;
        }
        //編集用バッファの作成
        StringBuffer buffer = new StringBuffer(aByteLength);
        //編集文字列の作成
        //指定桁数よりも長い場合は後ろをカットする
        if (appendeeLength > aByteLength) {
            StringBuffer temp = new StringBuffer(anAppendee);
            while (true) {
                if (temp.length() == 0) {
                    break;
                }
                temp.deleteCharAt(temp.length() - 1);
                byte[] cutbyte = getBytes(temp.toString());
                if (cutbyte.length <= aByteLength) {
                    anAppendee = temp.toString();
                    appendeeLength = cutbyte.length;
                    break;
                }
            }
        }
        //空白の追加
        if (anAppendOption == APPEND_BEFORE) {
            for (int i = appendeeLength; i < aByteLength; i++) {
                buffer.append(PADC);
            }
        }
        if (anAppendee != null) {
            buffer.append(anAppendee);
        }
        if (anAppendOption == APPEND_AFTER) {
            for (int i = appendeeLength; i < aByteLength; i++) {
                buffer.append(PADC);
            }
        }

        return buffer.toString();
    }

    /**
     * 文字列末尾の全角空白も削除するrtrim
     * @param src 元の文字列
     * @return String
     */
    public static String nrtrim(String src) {
        if (src == null || src.trim().length() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer(src);
        for (int i = sb.length() - 1; i >= 0; --i) {
            char ch = sb.charAt(i);
            if (Character.isWhitespace(ch)) {
                sb.deleteCharAt(i);
            } else {
                break;
            }
        }

        return sb.toString();
    }
}
