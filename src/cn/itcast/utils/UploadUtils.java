package cn.itcast.utils;

import java.util.UUID;

/**
 * 文件上传的工具类
 * @author Administrator
 */
public class UploadUtils {
	
	/**
	 * 传入文件的名称，返回唯一的名称
	 * 例如：传入的是gril 返回sjflsdjf.jpg
	 * @param filename
	 * @return
	 */
	public static String getUUIDName(String filename){
		// 从后向前查找，查找 . 
		int index = filename.lastIndexOf(".");
		// 截取字符串 .jpg 
		String lastname = filename.substring(index);
		// 生成唯一字符串 sdjf-klsd-
		String uuid = UUID.randomUUID().toString().replace("-", "");
		// 拼接
		return uuid+lastname;
	}
	
	public static void main(String[] args) {
		String uuidName = getUUIDName("gril.jpg");
		System.out.println(uuidName);
	}

}
