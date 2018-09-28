package controller;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import utility.ConnectUtil;

public class GetTableController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8"); 
		// 创建枚举类，包含请求内的参数
		
		Enumeration<String> Names = request.getParameterNames();
		Connection conn = ConnectUtil.getConnection();
		List<String> array = new ArrayList<String>();
		String[] parameterlist = new String[4];
		// 开始遍历
		for (int p = 0; Names.hasMoreElements()&&p<4; p++) { 
			String str = (String)Names.nextElement();
			array.add(str);
			// 创建包含上传的参数的数组
			parameterlist[p] = request.getParameter(str);
		}
		
		String nameSpiltStr = StringUtils.join(array.toArray(),",");
		
		String sql2 = "INSERT INTO webdata " + 
				"("+nameSpiltStr+") " + 
				"VALUES "+"(?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql2);
			for(int i=0;i<parameterlist.length;i++) 
			{
			ps.setString((i+1), parameterlist[i]);
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws UnsupportedEncodingException {
		doGet(request,response);
	}

}
