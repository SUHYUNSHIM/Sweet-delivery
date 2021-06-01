package MainPackage;
//마이페이지, 유저 정보.
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sweet_delivery.Branch;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class UserInfo extends JFrame {
	
	private JPanel contentPane;

	String name; 
	String pw; 
	String phone; 
	String address;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfo frame = new UserInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserInfo() {
		
	}
	
	public UserInfo(String id) {
//		public UserInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 476);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbLogo = new JLabel("");
		lbLogo.setIcon(new ImageIcon(UserInfo.class.getResource("/image/logo1.png")));
		lbLogo.setBounds(46, 31, 229, 50);
		contentPane.add(lbLogo);
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(".\\src\\resource\\MemberJoin\\"+id+".txt");
			br = new BufferedReader(fr);
		
			String readMember = br.readLine();
			String[] bea = readMember.split(",");
			name = bea[1]; 
			pw = bea[2]; 
			phone = bea[3]; 
			address = bea[4];
			System.out.println(name+"+"+pw+"+"+phone+"+"+address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String info= "                  USER INFORMAION   \n"+
					  "==================================================\n"+
				      "ID : "+id+"\n"+
				      "NAME : "+ name+"\n"+
				      "PASSWORD : *******\n"+
				      "PHONE : "+phone+"\n"+
					  "ADDRESS : \n"
				      +address+"\n"+
					  "==================================================\n";
		
				
		System.out.println(info);
		
		JTextArea textArea = new JTextArea(info);
		textArea.setFont(new Font("Monospaced", Font.BOLD, 21));
		textArea.setBounds(32, 99, 660, 265);
		contentPane.add(textArea);
		
		//다음 페이지로 넘어가는 버튼
		JButton btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				setVisible(false);
				try {
					new MainPage(id,name,pw,phone,address).setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				new Branch(id, name, pw, phone, address).setVisible(true);
			
			}
		});
		btnNext.setIcon(new ImageIcon(UserInfo.class.getResource("/image/next1.png")));
		btnNext.setBounds(617, 367, 56, 50);
		contentPane.add(btnNext);
	}
	

}
