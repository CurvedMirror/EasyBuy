package cn.easybuy.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtils {
	/**
	 * md5 加密
	 * @param value 要加密的值
	 * @return	加密后的值
	 */
	public static String md5Hex(String value) {
		return DigestUtils.md5Hex(value);
	}
	/**
	 * 三次md5操作
	 * @param value
	 * @return
	 */
	public static String md5Hex3(String value) {
		for (int i = 0; i <3; i++) {
			value=DigestUtils.md5Hex(value);
		}
		return value;
	}
	/**
	 * sha256加密
	 * @param value
	 * @return
	 */
	public static String sha256Hex(String value){
		return DigestUtils.sha256Hex(value);
	}
	public static String sha512Hex(String value){
		return DigestUtils.sha512Hex(value);
	}
	
}

