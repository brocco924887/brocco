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
	private static Connection conn = null;// �������Ӷ���
	
	
	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		ConnectUtil.conn = conn;
	}

	// ���ݿ�����
	public static Connection getConnection() {
		String user="root"; 
		String pass="123qwe";
		String driver = "com.mysql.jdbc.Driver";// ������������
		String url = "jdbc:mysql://localhost:3306/test?" // ���ݿ�URL
				+ "useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC";// ��ֹ����
		try {
			Class.forName(driver);// ע��(����)��������
			conn = DriverManager.getConnection(url, user, pass);// ��ȡ���ݿ�����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// ��ѯ���ݣ������query����
	public static String query(Connection conn,String sql) {
		try {
			Statement stmt = conn.createStatement(); // Ҳ����ʹ��PreparedStatement����
			ResultSet rs = stmt.executeQuery(sql);// ִ��sql��䲢��������

			while (rs.next()) {// ��������� ������һ��
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
			Statement stmt = conn.createStatement(); // Ҳ����ʹ��PreparedStatement����
			ResultSet rs = stmt.executeQuery(sql);// ִ��sql��䲢��������

			while (rs.next()) {// ��������� ������һ��
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

