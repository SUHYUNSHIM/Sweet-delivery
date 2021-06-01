package EventPackage;
//가위바위보 이전 화면
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Event;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Event8 extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Event8 frame = new Event8("아이디","이름","비밀번호","폰번호","주소");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Event8(String id, String name, String pw, String phone, String address) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("가위바위보 하기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				new Event77(id,name,pw,phone,address).setVisible(true);
			}
		});
		
		JLabel lblNewLabel = new JLabel("가위바위보로 운을 시험해봐!!!");
		lblNewLabel.setForeground(new Color(245, 255, 250));
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(339, 90, 568, 48);
		contentPane.add(lblNewLabel);
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 28));
		btnNewButton.setBounds(473, 453, 302, 70);
		contentPane.add(btnNewButton);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Event8.class.getResource("/image2/어플문구.png")));
		label_1.setBounds(17, 601, 229, 57);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("회사 : sweet deilvery");
		label_2.setFont(new Font("굴림", Font.BOLD, 13));
		label_2.setBounds(263, 580, 196, 43);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("전화번호 : 02-123-4567");
		label_3.setFont(new Font("굴림", Font.BOLD, 13));
		label_3.setBounds(263, 615, 196, 43);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("이메일 : deliverysweet@naver.com");
		label_4.setFont(new Font("굴림", Font.BOLD, 13));
		label_4.setBounds(263, 651, 306, 43);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("주소 : ㅇㅇㅇㅇㅇㅇㅇ");
		label_5.setFont(new Font("굴림", Font.BOLD, 13));
		label_5.setBounds(454, 615, 345, 43);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("사업자 번호 : 1234-15566-1245");
		label_6.setFont(new Font("굴림", Font.BOLD, 13));
		label_6.setBounds(444, 580, 266, 43);
		contentPane.add(label_6);
		
		JButton button = new JButton("돌아가기");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();;
				setVisible(false);
				new Event2(id,name,pw,phone,address).setVisible(true);
			}
		});
		button.setForeground(new Color(240, 255, 255));
		button.setFont(new Font("굴림", Font.BOLD, 22));
		button.setBackground(new Color(135, 206, 250));
		button.setBounds(44, 43, 164, 43);
		contentPane.add(button);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(Event8.class.getResource("/image2/딜리버리 이벤트.jpg")));
		label.setBounds(263, 55, 722, 112);
		contentPane.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Event8.class.getResource("/image2/가위바위보.jpg")));
		lblNewLabel_1.setBounds(354, 187, 528, 236);
		contentPane.add(lblNewLabel_1);
	}
}
