package libManager;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;import com.mysql.jdbc.CachedResultSetMetaData;
import com.mysql.jdbc.Statement;

import javax.swing.JButton;

public class Pg_Donation extends JFrame{
	
	private int width;
	private int height;
	private int winWidth;
	private int winHeight;
	private JTextField bookname_txt;
	private JTextField press_txt;
	private JTextField presstime_txt;
	private JTextField status_txt;
	public DB_Helper db_Helper;
	public static boolean isBackToManager;
    public Pg_Donation() {
    	setTitle("捐书：出版时间请按照yyyy-mm-dd格式输入");
		// TODO Auto-generated constructor stub
    	Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
		width=(int) screensize.getWidth();
		height=(int)screensize.getHeight();	
		winWidth=465;	
		winHeight=282;
		isBackToManager=false;
		init();
		setBounds((width-winWidth)/2,(height-winHeight)/2,winWidth,winHeight);
    	setVisible(true);
    	
	}
    public void init() {
getContentPane().setLayout(null);
		
		JLabel bookname = new JLabel("书名：");
		bookname.setBounds(47, 54, 54, 15);
		getContentPane().add(bookname);
		
		JLabel press = new JLabel("出版社：");
		press.setBounds(47, 92, 54, 15);
		getContentPane().add(press);
		
		JLabel presstime = new JLabel("出版时间：");
		presstime.setBounds(47, 132, 68, 15);
		getContentPane().add(presstime);
		
		JLabel status = new JLabel("状态：");
		status.setBounds(47, 173, 54, 15);
		getContentPane().add(status);
		
		bookname_txt = new JTextField();
		bookname_txt.setBounds(144, 52, 186, 18);
		getContentPane().add(bookname_txt);
		bookname_txt.setColumns(10);
		
		press_txt = new JTextField();
		press_txt.setColumns(10);
		press_txt.setBounds(144, 89, 186, 18);
		getContentPane().add(press_txt);
		
		presstime_txt = new JTextField();
		presstime_txt.setColumns(10);
		presstime_txt.setBounds(144, 129, 186, 18);
		getContentPane().add(presstime_txt);
		
		status_txt = new JTextField();
		status_txt.setColumns(10);
		status_txt.setText("未借出");
		status_txt.setEditable(false);
		status_txt.setBounds(144, 170, 186, 18);
		getContentPane().add(status_txt);
		
		JButton yes = new JButton("确定");
		yes.setBounds(80, 211, 93, 23);
		getContentPane().add(yes);
		
		JButton cancel = new JButton("取消");
		cancel.setBounds(237, 211, 93, 23);
		getContentPane().add(cancel);
		yes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					isBackToManager=true;
					String bookname=bookname_txt.getText();
					String press=press_txt.getText();
					
					//获取系统时间
//					long time = System.currentTimeMillis();
//					java.sql.Date presstime = new java.sql.Date(time);
				
					String time=presstime_txt.getText();
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date d = null; 
					 try { 
					  d = format.parse(time); 
					 } catch (Exception e) { 
					  e.printStackTrace(); 
					 } 
					 java.sql.Date presstime = new java.sql.Date(d.getTime()); 
					
			
				
					
					String status=status_txt.getText();

			        String sql="insert into book(Bookname,Press,PressTime,Status) value('"+bookname+"','"+press+"','"+presstime+"','"+status+"')";
					
			        //int Upd=st.executeUpdate("insert into book(Bookname,Press,PressTime,Status) value('"+bookname+"','"+press+"','"+presstime+"','"+status+"')");
			        db_Helper=new DB_Helper();
			        db_Helper.Connect_insertBook(sql);
			        JOptionPane.showMessageDialog(null, "捐赠成功");
					close();
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// TODO Auto-generated method stub
				
				close();
				
			}
		});
    }
    public void close() {
    	
    	this.dispose();
    	new Pg_Manager();
    }
}
