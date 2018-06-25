package com.thinvent.zhhd.common.util;


import com.thinvent.zhhd.common.exception.ApplicationException;
import com.thinvent.zhhd.common.exception.ThinventBaseException;

public class StringHelper {
	/**
	 * 空的字符串
	 */
	public static final String Empty = "";
	
	/**
	 * 判断一个字符串是否为空
	 */
	public static boolean isNullOrEmpty(String str) {
		if(str == null || str.length() == 0)
			return true;
		else
			return false;
	}
	
	/**
	 * 判断字符串是否为Null或是全部为空格
	 */
	public static boolean isNullOrWhitespace(String str) {
		if(str != null) {
			int num;
			for(int i = 0, l = str.length(); i < l; i++) {
				num = str.charAt(i);
				if(num != 32)
					return false;
			}
		}
		return true;
	}
	
	public static boolean equal(String str1, String str2) {
		if(str1 == null && str2 == null) {
			return true;
		}
		if(str1 != null && str2 != null) {
			return str1.equals(str2);
		} else {
			return false;
		}
	}
	
	/**
	 * 字符串截取，同javascript slice方法
	 * @param start 参数可以为负数
	 */
	private static String slice(String str, int start) {
		if(isNullOrEmpty(str))
			return Empty;
		int length = str.length();
		while(start < 0)
			start = length + start;
		if(start >= length)
			return Empty;
		return str.substring(start);
	}
	
	/**
	 * 字符串截取，同javascript slice方法
	 * @param start 参数可以为负数
	 * @param end 参数可以为负数
	 */
	private static String slice(String str, int start, int end) {
		if(isNullOrEmpty(str))
			return Empty;
		int length = str.length();
		while(start < 0)
			start = length + start;
		if(start >= length)
			return Empty;
		
		while(end < 0)
			end = length + end;
		if(end > length)
			end = length - 1;
		if(end < start)
			return Empty;
		return str.substring(start, end);
		
	}
	
	/**
	 * 字符串格式化 format("He{0}{0}o W{1}rld", "l", "o") = Hello World
	 */
	public static String format(String format, Object... args) {
		if (isNullOrEmpty(format) || args == null || args.length == 0) {
            return format;
        }
		StringBuilder builder = new StringBuilder();
		int i = 0;
		while(true) {
			int open = format.indexOf('{', i);
			int close = format.indexOf('}', i);
			if ((open < 0) && (close < 0)) {
				builder.append(slice(format, i));
                break;
            }
			if ((close > 0) && ((close < open) || (open < 0))) {
                if (format.charAt(close + 1) != '}') {
                    throw new ApplicationException("如果想加入{}字符需成双出现，如{{ 或者 }}!");
                }
                builder.append(slice(format, i, close + 1));
                i = close + 2;
                continue;
            }
			builder.append(slice(format, i, open));
			i = open + 1;
            if (format.charAt(i) == '{') {
                builder.append('{');
                i++;
                continue;
            }
            if (close < 0)
                throw new ApplicationException("格式化字符串缺少闭合标记，不符合规则!");
            String brace = format.substring(i, close);
            int index = 0;
            try {
            	index = Integer.parseInt(brace, 10);
            } catch(NumberFormatException nfe) {
            	throw new ApplicationException("占位符必须为数字!");
            }
            Object arg = args[index];
            if(arg == null)
            	arg = Empty;
            builder.append(arg);
            i = close + 1;
		}
		return builder.toString();
	}
}
