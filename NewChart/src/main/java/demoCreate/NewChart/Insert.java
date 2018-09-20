package demoCreate.NewChart;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert extends GetConnection{
	public static String insert(Connection conn, String sql){
		Statement st = null;
		conn = getConnection();
		String inserted = "inserted new value";
		
		try {
			String sql1 = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (1,'李佈和',1001,20)";  // 定义插入值的SQL语句
			String sql1a = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (2,'彤乐格',1001,20)";
			String sql1b = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (3,'聂珂',1001,20)";
			String sql1c = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (4,'张琳雪',1001,20)";
 		    String sql2 = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (5,'聂珂',1002,20)";
			String sql2a = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (6,'李佈和',1002,20)";
			String sql2b = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (7,'崔智英',1002,20)";
			String sql3 = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (8,'彤乐格',1003,10)";
			String sql3a = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (9,'薛敏',1003,10)";
			String sql4 = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (10,'彤乐格',1004,20)";
			String sql4a = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (11,'薛敏',1004,20)";
			String sql5 = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (12,'张琳雪',1005,20)";
			String sql6 = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (13,'李佈和',1006,20)";
			String sql7 = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (14,'崔智英',2001,10)";
			String sql7a = "insert into Module_Ballot (Numerical,Student_Name,Module_ID,Module_Credits) values (15,'薛敏',2001,10)";
			String sql8 = "insert into Module_Ballot (Numerical,Student_Name) values (16,'张三')";
			String sql9 = "insert into Module_Ballot (Numerical,Student_Name) values (17,'李四')";
			
			//1.表名不允许用中文 2.字段不允许用中文
			
			st = conn.createStatement();
			
			st.addBatch(sql1); // 批量执行命令
			st.addBatch(sql1a);
			st.addBatch(sql1b);
			st.addBatch(sql1c);
			st.addBatch(sql2);
			st.addBatch(sql2a);
			st.addBatch(sql2b);
			st.addBatch(sql3);
			st.addBatch(sql3a);
			st.addBatch(sql4);
			st.addBatch(sql4a);
			st.addBatch(sql5);
			st.addBatch(sql6);
			st.addBatch(sql7);
			st.addBatch(sql7a);
			st.addBatch(sql8);
			st.addBatch(sql9);
			
			st.executeBatch(); // 确认批量执行
			
		}catch(SQLException e){  
			e.printStackTrace();	
		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {               
			if (st != null) { 
				try {
					st.close();
					
				}catch(SQLException e){
					e.printStackTrace();
				}
				
			}
		}
		
		return inserted;
	}

}
