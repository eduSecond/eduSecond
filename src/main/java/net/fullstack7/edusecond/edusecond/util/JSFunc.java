package net.fullstack7.edusecond.edusecond.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JSFunc {
	public static void alertLocation(String msg, String url, HttpServletResponse response) {
		try {
			PrintWriter writer = response.getWriter();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String code = "<script>"
					+ "alert('" + msg + "');"
					+ "location.href='" + url + "';"
					+ "</script>";
			writer.print(code);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static void back(HttpServletResponse response) {
		try {
			PrintWriter writer = response.getWriter();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String code = "<script>"
					+ "history.back();"
					+ "</script>";
			writer.print(code);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static void alertBack(String msg, HttpServletResponse response) {
		try {
			PrintWriter writer = response.getWriter();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String code = "<script>"
					+ "alert('" + msg + "');"
					+ "history.back();"
					+ "</script>";
			writer.print(code);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static void alert(String msg, HttpServletResponse response) {
		try {
			PrintWriter writer = response.getWriter();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String code = "<script>"
					+ "alert('" + msg + "');"
					+ "</script>";
			writer.print(code);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
