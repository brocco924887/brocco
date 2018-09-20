package demoCreate.NewChart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MostModule extends GetConnection {
	
	public static String mostModule(Connection conn, String sql) {
		Statement st = null;
		conn = getConnection();
		String selected = "selected columns";  //定义返回值
		
		try {
			st = conn.createStatement();
			
			// 定义一个表名为new的表
			// 包含学生名及每人所选课程数量
			
			String sql1 = "CREATE table New( "+
				   "SELECT Student_Name, "+
				   "count( * ) AS '课程数' "+
				   "FROM Module_Ballot "+ 
				   "GROUP BY Student_Name "+ 
				   "ORDER BY count( * ) DESC)"; 
			st.executeUpdate(sql1);
			
			//查询选课最多的学生
			String sql2 = "SELECT * "+ 
					      "FROM new "+
					      "WHERE 课程数 = (SELECT MAX(课程数) FROM new)";   
		
		ResultSet rs =  st.executeQuery(sql2);  // 执行查询命令
		while(rs.next()){
			System.out.println(rs.getString("Student_Name"));
			System.out.println(rs.getString("课程数"));
		}
		
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
		return selected;	
	}

}
