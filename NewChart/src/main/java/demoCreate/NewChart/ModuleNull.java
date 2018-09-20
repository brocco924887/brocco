package demoCreate.NewChart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModuleNull extends GetConnection{
	public static String moduleNull(Connection conn, String sql) {
		Statement st = null;
		conn = getConnection();
		String selected = "selected columns";  //定义返回值
		
		//定义一个方法查询没有选课的学生
		try {
			sql = "SELECT Student_Name "+
		          "FROM module_ballot "+
				  "WHERE Module_ID "+
				  "is null";  
		
		st = conn.createStatement();
		
		ResultSet rs =  st.executeQuery(sql);  // 执行更改
		while(rs.next()){
			
			System.out.println(rs.getString("Student_Name"));
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
