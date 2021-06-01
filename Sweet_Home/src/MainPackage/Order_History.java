package MainPackage;
//주문 내역
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Order_History extends JFrame {

	private JPanel contentPane;
	String name; 
	String pw; 
	String phone; 
	String address;
	private JTextField txtBranchName;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Order_History frame = new Order_History("아이디","현금/카드");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Order_History(String id, String howToPay) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
						
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Order_History.class.getResource("/resource/delivery-man.png")));
		lblNewLabel_3.setBounds(261, 303, 88, 90);
		contentPane.add(lblNewLabel_3);
		//시간---------------------------------------------------------
		JLabel lblTime = new JLabel("주문 완료 시간 텍스트");
		lblTime.setForeground(new Color(0, 128, 128));
		lblTime.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		lblTime.setBounds(63, 349, 260, 24);
		contentPane.add(lblTime);
		
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		String order_time = format.format (System.currentTimeMillis());
		lblTime.setText(order_time);
		
		JLabel lblNewLabel_5 = new JLabel("주문 완료 시간");
		lblNewLabel_5.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(63, 331, 113, 18);
		contentPane.add(lblNewLabel_5);
		//-----------------------------------------------------------
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Order_History.class.getResource("/projectimage/어플문구.png")));
		lblNewLabel.setBounds(37, 12, 232, 67);
		contentPane.add(lblNewLabel);
		
		JLabel lblHowtoPay = new JLabel("현금/카드");
		lblHowtoPay.setForeground(new Color(0, 128, 128));
		lblHowtoPay.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		lblHowtoPay.setBounds(206, 187, 81, 18);
		contentPane.add(lblHowtoPay);
		lblHowtoPay.setText(howToPay);  //결제 페이지에서 선택한 현금 또는 카드 결제 방식 값이 들어온다.
		
		
		JLabel lblNewLabel_2 = new JLabel("택하신 현장 결제 방식인");
		lblNewLabel_2.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(63, 188, 169, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("님");
		lblNewLabel_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(159, 106, 62, 18);
		contentPane.add(lblNewLabel_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
		textArea_1.setText("지정하신 주소로 곧 따뜻한 음식 도착합니다.");
		textArea_1.setBounds(63, 123, 300, 34);
		contentPane.add(textArea_1);
		
		JLabel lblUserName = new JLabel("유저 이름");
		lblUserName.setForeground(new Color(0, 128, 128));
		lblUserName.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 16));
		lblUserName.setBounds(64, 105, 88, 18);
		contentPane.add(lblUserName);
		lblUserName.setText(id);
		
		JLabel lblNewLabel_4 = new JLabel("미리 준비해주세요.");
		lblNewLabel_4.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(63, 207, 146, 18);
		contentPane.add(lblNewLabel_4);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(37, 63, 352, 330);
		contentPane.add(textArea);
		
		//메인으로 가기 위해서는 유저정보를 가져와야 한다. 생성자로 넘겨줘야 하기 때문.
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(".\\src\\resource\\MemberJoin\\"+id+".txt"); 
			br = new BufferedReader(fr); //생성자로 넘겨받은 id 값을 바탕으로 유저 텍스트 파일을 연다.
		
			String readMember = br.readLine();
			String[] bea = readMember.split(",");
			name = bea[1]; 
			pw = bea[2]; 
			phone = bea[3]; 
			address = bea[4];
			//System.out.println(name+"+"+pw+"+"+phone+"+"+address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		JButton btnNewButton = new JButton("메인 홈으로 가기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				try {
					new MainPage(id,name,pw,phone,address).setVisible(true);
				} catch (IOException e1) {					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(new Color(230, 230, 250));
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnNewButton.setBounds(141, 418, 169, 27);
		contentPane.add(btnNewButton);
	}
}
