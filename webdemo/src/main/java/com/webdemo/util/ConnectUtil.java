package com.webdemo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ConnectUtil {
	private static Connection conn = null;// 声明连接对象
	
	
	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		ConnectUtil.conn = conn;
	}

	// 数据库连接
	public static Connection getConnection() {
		String user="root"; 
		String pass="123qwe";
		String driver = "com.mysql.jdbc.Driver";// 驱动程序类名
		String url = "jdbc:mysql://localhost:3306/test?" // 数据库URL
				+ "useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC";// 防止乱码
		try {
			Class.forName(driver);// 注册(加载)驱动程序
			conn = DriverManager.getConnection(url, user, pass);// 获取数据库连接
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 查询数据，定义的query方法
	public static String query(Connection conn,String sql) {
		try {
			Statement stmt = conn.createStatement(); // 也可以使用PreparedStatement来做
			ResultSet rs = stmt.executeQuery(sql);// 执行sql语句并返还结束

			while (rs.next()) {// 遍历结果集 ，向下一行
				return rs.getString("image");
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static InputStream queryImg(Connection conn,String sql) {
		try {
			Statement stmt = conn.createStatement(); // 也可以使用PreparedStatement来做
			ResultSet rs = stmt.executeQuery(sql);// 执行sql语句并返还结束

			while (rs.next()) {// 遍历结果集 ，向下一行
				 	Blob blob = rs.getBlob("image");
					InputStream is = blob.getBinaryStream();
					return is;
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

