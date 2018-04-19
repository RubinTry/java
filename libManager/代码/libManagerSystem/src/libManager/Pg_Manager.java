package libManager;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.channels.NonWritableChannelException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Pg_Manager extends JFrame{
	
    public DB_Helper db_Helper=new DB_Helper();
	private int width;
	private int height;
	private static int winWidth=1000;
	private static int winHeight=800;
	private JTextField borrows_txt;
	public ResultSet rs;
	private JTextField returnBook_txt;
	public Statement st;
	public String username;
	public String currentLoginTime;
	public String lastLoginTime;
	
	public String borrower;
//	Pg_Login pg_Login=new Pg_Login();
	public Pg_Manager() {
		
		username=Pg_Login.CurrentUserName;
		//System.out.println(Pg_Login.CurrentUserName);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		setTitle("图书管理系统");
		Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
		width=(int) screensize.getWidth();
		height=(int)screensize.getHeight();	

		refresh();
		// TODO Auto-generated constructor stub
		
		getContentPane().setLayout(null);
		
		borrows_txt = new JTextField();
		borrows_txt.setText("请输入图书编号");
		borrows_txt.setBounds(606, 69, 150, 23);
		getContentPane().add(borrows_txt);
		borrows_txt.setColumns(10);
		borrows_txt.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				refresh();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				borrows_txt.setText("");
			}
		});
		
		JButton borrows_btn = new JButton("借书");
		borrows_btn.setBounds(773, 67, 93, 23);
		getContentPane().add(borrows_btn);
		
		returnBook_txt = new JTextField();
		returnBook_txt.setText("请输入图书编号");
		returnBook_txt.setBounds(100, 69, 156, 23);
		getContentPane().add(returnBook_txt);
		returnBook_txt.setColumns(10);
		returnBook_txt.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				refresh();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				returnBook_txt.setText("");
			}
		});
		JButton returnBook_btn = new JButton("还书");
		returnBook_btn.setBounds(281, 67, 93, 23);
		getContentPane().add(returnBook_btn);
		
		JButton logout = new JButton("注销");
		logout.setBounds(449, 67, 93, 23);
		getContentPane().add(logout);
		
		JButton donationBook = new JButton("向此馆捐书");
		donationBook.setBounds(434, 27, 125, 23);
		getContentPane().add(donationBook);
		
		JLabel label1 = new JLabel("");
		label1.setBounds(100, 10, 156, 15);
		label1.setText("用户："+username);
		getContentPane().add(label1);
		
		JLabel lastLoginTime_label = new JLabel("上次登录：null");
		lastLoginTime_label.setBounds(213, 10, 237, 15);
		getContentPane().add(lastLoginTime_label);
		
		JLabel currentLoginTime_label = new JLabel("本次登录：null");
		currentLoginTime_label.setBounds(100, 31, 246, 15);
		getContentPane().add(currentLoginTime_label);
		
		JLabel registerTime_label = new JLabel("New label");
		registerTime_label.setBounds(648, 10, 195, 15);
		getContentPane().add(registerTime_label);
		
		String sqlr="select registerTime from user where username='"+username+"'";
			
			String registerTimeTxt=db_Helper.registerTime(sqlr, username);
			registerTime_label.setText("注册时间："+registerTimeTxt);
		
		
		if(Pg_Login.isLogin) {
			String sqls="select currentLoginTime from user where username='"+username+"'";
			lastLoginTime=db_Helper.returnLastTime(sqls,username);
			
			if(lastLoginTime.equals("")) {
				lastLoginTime_label.setText("上次登录：第一次登录");
				SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
				currentLoginTime = tempDate.format(new java.util.Date()); 
				
				String sql="update user set currentLoginTime='"+currentLoginTime+"' where username='"+username+"'";
				String sql1="update user set lastLoginTime='"+currentLoginTime+"' where username='"+username+"'";
			    currentLoginTime_label.setText("本次登录："+currentLoginTime);
				db_Helper.Connect_insertBook(sql);
				db_Helper.Connect_insertBook(sql1);
				lastLoginTime=currentLoginTime;
			}
			else {
				
				lastLoginTime_label.setText("上次登录："+lastLoginTime);
				String sql="update user set lastLoginTime='"+lastLoginTime+"' where username='"+username+"'";
				db_Helper.Connect_insertBook(sql);
				SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
				currentLoginTime = tempDate.format(new java.util.Date()); 
				lastLoginTime=currentLoginTime;
				currentLoginTime_label.setText("本次登录："+currentLoginTime);
				String sql2="update user set currentLoginTime='"+currentLoginTime+"' where username='"+username+"'";
				db_Helper.Connect_insertBook(sql2);
			}
		}

		
		
		borrows_txt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					borrows();
				}
			}
		});
		returnBook_txt.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					returns();
				}
			}
		});
		//借书功能的实现
		borrows_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			    
				// TODO Auto-generated method stub
				borrows();
			}
		});
		//还书功能的实现
		returnBook_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				returns();
			}
		});
		//注销
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logout();
			}
		});
		
        //捐赠书本的实现
		donationBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				donation();
				
//				if(Pg_Donation.isBackToManager==1) {
//					JOptionPane.showMessageDialog(null, "刷新成功");
//					refresh();
//				}
			}
		});
		setVisible(true);
		setBounds((width-winWidth)/2,(height-winHeight)/2,winWidth,winHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ESCAPE) {
					System.out.println("1111111111111");
					logout();
				}
			}
		});
	}
	public void borrows() {
		try {
			int id=Integer.parseInt(borrows_txt.getText());
			String bookname=borrows_txt.getText();
			String sql="select * from book where Id='"+id+"'";
			
    		st=(Statement) db_Helper.Connection_book();
    		rs=st.executeQuery(sql);
                if(rs.next()) {
    			
				String status=rs.getString("Status");
				if(status.equals("未借出"))
				{
					//System.out.println("2");
					JOptionPane.showMessageDialog(null, "借出成功");
					//这里写修改借书状态的语句
					int upd=st.executeUpdate("update book set Status='借出' where Id='"+id+"'");	
					int upde=st.executeUpdate("update book set Borrower='"+username+"' where Id='"+id+"'");
					
					refresh();
				}
				else {
					//System.out.println("3");
					JOptionPane.showMessageDialog(null, "对不起，本书已借出");
					
				}
				borrows_txt.setText("请输入图书编号");
			}
			else {
				JOptionPane.showMessageDialog(null, "对不起，这里没有您想要的书");
				borrows_txt.setText("请输入图书编号");
			}
			
			
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	public void returns() {
		try {
			int id=Integer.parseInt(returnBook_txt.getText());
			String sql="select * from book where Id='"+id+"'";
			st=(Statement) db_Helper.Connection_book();
			ResultSet rs=st.executeQuery(sql);
			
			if(rs.next()) {
				String status=rs.getString("Status");
				if(status.equals("借出")) {
					borrower=rs.getString("Borrower");
					
					
					if(username.equals(borrower)) {
						int upd=st.executeUpdate("update book set Status='未借出' where Id='"+id+"'");	
						int upde=st.executeUpdate("update book set Borrower='' where Id='"+id+"'");
						JOptionPane.showMessageDialog(null, "归还成功");
					}else {
						JOptionPane.showMessageDialog(null, "抱歉，用户名不符");
					}
					refresh();
				}
				else {
					JOptionPane.showMessageDialog(null, "归还失败，此书未借出");
				}
				returnBook_txt.setText("请输入图书编号");
			}
			else {
				JOptionPane.showMessageDialog(null, "抱歉，我们这并没这本书，何来借不借？");
				returnBook_txt.setText("请输入图书编号");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//捐书页面
	public void donation() {
		new Pg_Donation();
		this.dispose();
	}
	//注销
	public void logout() {
		this.dispose();
		
		new Pg_Login();
	}
	//刷新
	public  void refresh() {
		try {
			Vector columns=new Vector();
			//将字段加入到向量中
			columns.add("编号");
			columns.add("书名");
			columns.add("出版社");
			columns.add("出版日期");
			columns.add("状态");
			columns.add("借书人");
			Vector rowData=new Vector();
			String sql="select * from book where Bookname is not null";
			st=(Statement) db_Helper.Connection_book();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Vector hang=new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				rowData.add(hang);
			}
			JTable jt=new JTable(rowData,columns);
			jt.setEnabled(false);
			int tbWidth=800;
			int tbHeight=600;
			JScrollPane jScrollPane=new JScrollPane(jt);
			jScrollPane.setBounds((winWidth-tbWidth)/2,(winHeight-tbHeight)/2,tbWidth,tbHeight);
			
			getContentPane().add(jScrollPane);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
