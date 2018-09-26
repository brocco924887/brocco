package controller;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.ConnectUtil;

public class GetTableController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws UnsupportedEncodingException {
		Connection conn = ConnectUtil.getConnection();
		request.setCharacterEncoding("UTF-8");
		Enumeration<String> Names = request.getParameterNames();
		while(Names.hasMoreElements()) {
			String str = (String)Names.nextElement();
			String[] parameterlist = request.getParameterValues(str);
			for (int i = 0;parameterlist!=null&&i < parameterlist.length; i++) {
                System.out.println(str+":"+parameterlist[i]+"\t");
            }
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws UnsupportedEncodingException {
		doGet(request,response);
	}

}
