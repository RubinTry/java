package libManager;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Pg_Register extends JFrame{
	public static final String url="jdbc:mysql://localhost:3306/libmanager";
	public static final String name="com.mysql.jdbc.Driver";
	public static final String username="root";
	public static final String pwd="root";
	public To_md5 to_md5;
	private int width;
	private int height;
	private int winWidth;
	private int winHeight;
	private JTextField username_txt;
	private JPasswordField pwd_txt;
	public DB_Helper db_Helper=new DB_Helper();
	public ResultSet rs;
	public Statement st;
     public Pg_Register() {
    	 Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
 		width=(int) screensize.getWidth();
 		height=(int)screensize.getHeight();	
 		winWidth=465;	
		winHeight=282;
		setBounds((width-winWidth)/2,(height-winHeight)/2,winWidth,winHeight);
		init();
		// TODO Auto-generated constructor stub
    	 setVisible(true);
	}
     public void init() {
    	 getContentPane().setLayout(null);
 		
 		JLabel usname = new JLabel("用户名：");
 		usname.setBounds(92, 69, 54, 15);
 		getContentPane().add(usname);
 		
 		JLabel password = new JLabel("密码：");
 		password.setBounds(92, 129, 54, 15);
 		getContentPane().add(password);
 		
 		username_txt = new JTextField();
 		username_txt.setBounds(183, 66, 166, 21);
 		getContentPane().add(username_txt);
 		username_txt.setColumns(10);
 		
 		pwd_txt = new JPasswordField();
 		pwd_txt.setColumns(10);
 		pwd_txt.setBounds(183, 126, 166, 21);
 		getContentPane().add(pwd_txt);
 		
 		JButton register = new JButton("注册");
 		register.setBounds(106, 187, 93, 23);
 		getContentPane().add(register);
 		
 		JButton cancel = new JButton("取消");
 		cancel.setBounds(244, 187, 93, 23);
 		getContentPane().add(cancel);
 		
 		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// TODO Auto-generated method stub
				register();
			}
		});
 		
		username_txt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					register();
				}
				if(e.getKeyChar()==KeyEvent.VK_ESCAPE) {
					close();
				}
			}
		});
		pwd_txt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					register();
				}
				if(e.getKeyChar()==KeyEvent.VK_ESCAPE) {
					close();
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
     public void register() {
    	 try {
				to_md5=new To_md5();
				String usn=username_txt.getText();
				String passwd=pwd_txt.getText();	
				
				//注册时做MD5加密处理
				String passwd_md5=to_md5.toMd5(pwd_txt.getText());
				st=db_Helper.Connection_book();
				String sql="select * from user where username='"+usn+"'";
				rs=st.executeQuery(sql);
				if(rs.next()) {
					JOptionPane.showMessageDialog(null, "用户名已存在");
					
					
				}else {
					//JOptionPane.showMessageDialog(null, "0");
					if(!usn.equals("")) {
						if(passwd.length()>=6) {
							//获取系统时间
							Date day=new Date();    

							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	                        String currenttime=df.format(day);
							System.out.println(currenttime);   
							int Upd=st.executeUpdate("insert into user(username,pwd,registerTime) value('"+usn+"','"+passwd_md5+"','"+currenttime+"')");
						    JOptionPane.showMessageDialog(null, "注册成功");
						    close();
						}
						else {
							JOptionPane.showMessageDialog(null, "您的密码长度不足6位，请重新输入");
						}	
						 
						
					}else {
						JOptionPane.showMessageDialog(null, "请输入用户名");
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
     }
     public void close() {
    	 this.dispose();
    	 Pg_Login pg_Login=new Pg_Login();
    	 pg_Login.setVisible(true);
     }
}
