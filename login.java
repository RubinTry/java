package javas;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;

public class login extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
    public login(){
    	setVisible(true);
    	setBounds(100,100,500,400);
    	getContentPane().setLayout(new GridLayout(4, 1));
    	
    	JPanel panel_1 = new JPanel();
    	getContentPane().add(panel_1);
    	
    	JPanel panel_2 = new JPanel();
    	getContentPane().add(panel_2);
    	
    	JLabel label = new JLabel("�û�����");
    	panel_2.add(label);
    	
    	textField = new JTextField();
    	panel_2.add(textField);
    	textField.setColumns(10);
    	
    	JPanel panel_3 = new JPanel();
    	getContentPane().add(panel_3);
    	
    	JLabel label_1 = new JLabel("���룺");
    	panel_3.add(label_1);
    	
    	textField_1 = new JTextField();
    	panel_3.add(textField_1);
    	textField_1.setColumns(10);
    	
    	JPanel panel = new JPanel();
    	getContentPane().add(panel);
    	
    	JButton button = new JButton("��¼");
    	panel.add(button);
//    	��¼����
    	button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename","root","root");
					Statement st=con.createStatement();
					String name=textField.getText();
					String pwd=textField_1.getText();
					String sql="select * from student where pwd='"+pwd+"'";
					ResultSet rs=st.executeQuery(sql);
					if (rs.next()) {
						if (rs.getString("pwd").equals(pwd)) {
							JOptionPane.showMessageDialog(null, "��¼�ɹ�");
						}
						else{
							JOptionPane.showMessageDialog(null,"��¼ʧ��");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "�û���������");
					}

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
    	
    	JButton button_1 = new JButton("ȡ��");
    	panel.add(button_1);
//    	��ȡ������ť����
    	button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
    	
//    	���ر�
    	setDefaultCloseOperation(login.EXIT_ON_CLOSE);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new login();
	}

}
