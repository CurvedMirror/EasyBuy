package cn.easybuy.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public final class PrintUtil {
	public static void write(Object obj,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String json=JSONObject.toJSONString(obj);
		PrintWriter writer=null;
		try {
			if(response!=null){
				writer=response.getWriter();
				writer.print(json);
				writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			writer.close();
		}
	}
}
