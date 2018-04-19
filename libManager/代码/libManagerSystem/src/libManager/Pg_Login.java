package libManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.security.auth.login.LoginContext;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class Pg_Login extends JFrame {
	private int width;
	private int height;
	private int winWidth;
	private int winHeight;
	private JTextField account_txt;
	private JPasswordField pwd_txt;
	private DB_Helper dbLoginHelper;
	public String currentUser;
	public ResultSet rs;
	public PreparedStatement pstmt=null;
	public static String CurrentUserName;
	public static boolean isLogin;
	public To_md5 to_md5;
	public Pg_Login() {
		setType(Type.POPUP);
		setTitle("登录");
		isLogin=false;
		//鍒濆鍖栨帶浠�
		init();
		winWidth=465;	
		winHeight=282;
        //绐楀彛鍩烘湰灞炴�х殑璁惧畾		
		setVisible(true);
		setBounds((width-winWidth)/2,(height-winHeight)/2,winWidth,winHeight);
		setDefaultCloseOperation(Pg_Login.EXIT_ON_CLOSE);
		
	}
	
	
	//鍒濆鍖栧悇鎺т欢
	public void init() {
		Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
		width=(int) screensize.getWidth();
		height=(int)screensize.getHeight();	
        getContentPane().setLayout(null);
		
		JLabel label = new JLabel("用户名：");
		label.setBounds(89, 70, 54, 15);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setBounds(89, 129, 54, 15);
		getContentPane().add(label_1);
		
		account_txt = new JTextField();
		account_txt.setBounds(196, 68, 151, 18);
		getContentPane().add(account_txt);
		account_txt.setColumns(10);
		
		pwd_txt = new JPasswordField();
		pwd_txt.setBounds(198, 126,151, 21);
		getContentPane().add(pwd_txt);
		pwd_txt.setColumns(10);
		
		JButton login_btn= new JButton("登录");
		login_btn.setBounds(186, 181, 63, 23);
		getContentPane().add(login_btn);
		
		JButton cancel_btn = new JButton("取消");
		cancel_btn.setBounds(284, 181, 63, 23);
		getContentPane().add(cancel_btn);
		
		JButton register = new JButton("注册");
		register.setBounds(89, 181, 63, 23);
		getContentPane().add(register);
		login_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(account_txt.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入用户名");
				}else {
					login();
				}
			}
		});
		cancel_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Pg_Register();
				hidewin();
			}
		});
		account_txt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					login();
				}
				if(e.getKeyChar()==KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		pwd_txt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					login();
				}
				if(e.getKeyChar()==KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
	}
	public void login() {
		isLogin=true;
		String username=account_txt.getText();
		String pwd=to_md5.toMd5(pwd_txt.getText());
		String sql="select * from user where username='"+username+"'";
	    dbLoginHelper=new DB_Helper();
	    rs=dbLoginHelper.Connection(sql);
	    
		try {
			if(rs.next()) {
				String UsPwd=rs.getString("pwd");
				if(UsPwd.equals(pwd)) {
					
					JOptionPane.showMessageDialog(null, "登录成功");
					//isSuccess=1;
					CurrentUserName=username;
					
					new Pg_Manager();
					close();
				}
				else {
					JOptionPane.showMessageDialog(null, "登录失败，请检查用户名和密码是否正确");
				}
			}else {
				JOptionPane.showMessageDialog(null, "用户名不存在");
			}
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //判断是否登录成功，若成功则跳转到下个页面
	    
	    
	}
	public String getUserName(String username) {
		String usname=account_txt.getText();
		return usname;
	}
	public void hidewin() {
		this.setVisible(false);
	}
	public void close() {
		this.dispose();
	}
	public static void main(String[] args) {
		
		new Pg_Login();
	}
}
