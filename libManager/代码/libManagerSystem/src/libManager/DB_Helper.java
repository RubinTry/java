package libManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageTypeSpecifier;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class DB_Helper {
	public static final String url="jdbc:mysql://localhost:3306/libmanager?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
	public static final String name="com.mysql.jdbc.Driver";
	public static final String username="root";
	public static final String pwd="abc.1234";
	private int width;
	private int height;
	private int winWidth=1000;
	private int winHeight=800;
    public int isSuccess;
    public Connection connection;
    public Statement st;
	public ResultSet rs;
	public ResultSet rs1;
	public String currentLoginTime;
	public String lastLoginTime;
	public String registerTime;
	public DB_Helper() {
		
	}
	public ResultSet Connection(String sql) {
		
		
		try {
			
			Class.forName(name);
			connection= DriverManager.getConnection(url,username,pwd);
			st=connection.createStatement();
			rs=st.executeQuery(sql);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	public Statement Connection_book() {
      try {
			
			Class.forName(name);
			connection= DriverManager.getConnection(url,username,pwd);
			st=connection.createStatement();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return st;
	}
	public void Connect_insertBook(String sql) {
		//JOptionPane.showMessageDialog(null, "调用成功");
		try {
			Class.forName(name);
			Connection connection=DriverManager.getConnection(url,username,pwd);
			Statement st=(Statement) connection.createStatement();
			int upd=st.executeUpdate(sql);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public String returnLastTime(String sql,String usname) {
		try {
			rs1=Connection(sql);
		//	rs1=st.executeQuery();
			if(rs1.next()) {
				lastLoginTime=rs1.getString("currentLoginTime");
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		//JOptionPane.showMessageDialog(null, lastLoginTime);
		return lastLoginTime;
	}
	public String registerTime(String sql,String usname) {
		try {
			rs=Connection(sql);
			if(rs.next()) {
				registerTime=rs.getString("registerTime");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return registerTime;
	}
	 public void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		 
         try {
               if (rs != null)
                    rs.close();
               if (pstmt != null)
                    pstmt.close();
               if (con != null)
                    con.close();
        } catch (SQLException e) {
               // TODO: handle exception
              e.printStackTrace();
        }
  }
   
}
