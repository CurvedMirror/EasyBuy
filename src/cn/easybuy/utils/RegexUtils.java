package cn.easybuy.utils;

public class RegexUtils {

	/**
	 * 邮箱
	 */
	public final static String EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	/**
	 *  定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
	 */
	public final static String IDENTITYCODE = "^\\d{15}|\\d{18}$";

	/**
	 * 判断是否是正确的邮箱地址
	 * 
	 * @param email
	 * @return boolean true,通过，false，没通过
	 */
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email))
			return false;
		return email.matches(EMAIL);
	}
	
	public static boolean isIdentityCode(String identityCode) {
		if (null == identityCode || "".equals(identityCode))
			return false;
		return identityCode.matches(IDENTITYCODE);
	}
	/**
	 * 判断是否是手机号码
	 * 
	 * @param phoneNumber
	 *            手机号码
	 * @return boolean true,通过，false，没通过
	 */
	public static boolean isPhoneNumber(String phoneNumber) {
		if (null == phoneNumber || "".equals(phoneNumber))
			return false;
		String regex = "^1[3|4|5|8][0-9]\\d{8}$";
		return phoneNumber.matches(regex);
	}
}