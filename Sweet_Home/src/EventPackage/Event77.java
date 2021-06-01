package EventPackage;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MainPackage.Rewards;
import javax.swing.SwingConstants;

public class Event77 extends JFrame {

	private JPanel contentPane;
	
	ImageIcon [] img = {
			new ImageIcon(Event77.class.getResource("/img/kawi.jpg")),
			new ImageIcon(Event77.class.getResource("/img/bawi.jpg")),
			new ImageIcon(Event77.class.getResource("/img/bo.jpg")),
		
	};
		//세개의 버튼중에 하나를 선택하려면 Panel에 버튼을 넣어줘야 한다.
	SelectPanel select = new SelectPanel();
	ResultDisplay resultDisplay = new ResultDisplay();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Event77 frame = new Event77("아이디","이름","비밀번호","폰번호","주소");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Event77(String id, String name, String pw, String phone, String address) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		contentPane.add(resultDisplay, "Center" );
		contentPane.add(select, "South");
		
		
		JButton btn1 = new JButton("");
		btn1.setIcon(new ImageIcon(Event77.class.getResource("/resource/back-button.png")));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				new Event8(id,name,pw,phone,address).setVisible(true);
			}
		});
		//contentPane.setLayout(null);
		btn1.setBounds(14,12,97,64);
		contentPane.add(btn1);
		
	}
	
	class SelectPanel extends JPanel{
		JButton [] btn = new JButton[3];
		//JButton btn1 = new JButton("돌아가기");		
		
		public SelectPanel(){
			setBackground(Color.gray);
			for(int i=0; i<img.length; i++){
				btn[i] = new JButton(img[i]);
				this.add(btn[i]);
				
				btn[i].addActionListener(new EventHandler());
			}
		}		
	}
	class ResultDisplay extends JPanel{
		
		
		
		
		JLabel userlb = new JLabel("you");
		JLabel com = new JLabel("computer");
		JLabel result = new JLabel("winner");
		
		public ResultDisplay() {
			setBackground(Color.WHITE);
			add(userlb);
			add(result);
			add(com);
		}
		
		public void output(Icon img, Icon comImage, String res){
			userlb.setIcon(img);
			userlb.setHorizontalTextPosition(JLabel.LEFT);
			com.setIcon(comImage);
			result.setText(res);
			result.setForeground(Color.red);
			result.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		}
	}//ResultDisplay
	
	
	class EventHandler implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			JButton btnSrc = (JButton)ae.getSource();
			int selCom = (int)(Math.random()*3); // 0: 가위 , 1: 바위, 2: 보
			
			String res = "";
			
			// user 가 이기는 경우
			if(btnSrc.getIcon() == img[0] && selCom ==2 ||
			   btnSrc.getIcon() == img[1] && selCom ==0 ||
			   btnSrc.getIcon() == img[2] && selCom ==1 ) {
				res = "당신이 이겼습니다. 25% 할인 쿠폰이 지급됩니다";
				Rewards rw = new Rewards(0.25,"이벤트 당첨");
				rw.WriteToFile(0.25,"Game-winner");
			}
				
			
			else if(btnSrc.getIcon() == img[0] && selCom == 0 ||
					btnSrc.getIcon() == img[1] && selCom == 1 ||
					btnSrc.getIcon() == img[2] && selCom == 2 
					) {
				res = "비겼습니다!!!";
			}
				
			else 
				res = "졌습니다!! 다음기회에!!!";
			
			resultDisplay.output(btnSrc.getIcon(), img[selCom], res);
		}
	}

}