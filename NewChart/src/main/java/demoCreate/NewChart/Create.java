package demoCreate.NewChart;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Create extends GetConnection{
	public static String create(Connection conn, String sql) {
		Statement st = null;
		conn = getConnection();
		String created = "created new chart";  //定义返回值
		
		try {
		sql = "CREATE TABLE Module_Ballot"+
		"(Numerical INT,"+
		"Student_Name VARCHAR(9),"+
		"Module_ID INT,"+
		"Module_Credits INT,"+
		"PRIMARY KEY(Numerical))";   //定义建表的SQL语句
		
		st = conn.createStatement();
		st.executeUpdate(sql);  // 执行更改
		
		}catch(SQLException e){  //错误捕捉
			e.printStackTrace();	
		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {               //关闭方法
			if (st != null) { 
				try {
					st.close();
					
				}catch(SQLException e){
					e.printStackTrace();
				}
				
			}
		}
		return created;	
	}
}
