package sweet_delivery;
//지점 선택
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import MainPackage.*;

public class Branch extends JFrame {

	private JPanel cp;
	private JTextField textName;
	JLabel branchLb;
	JComboBox cbSeoul;
	JComboBox cbKyungki;
	JComboBox cbPusan;

	String localchoice;
	String localchoice1;
	JTextField txtChoice;

	String[] local = {"원하시는 지역을 선택하세요", "================서울=================", "================경기=================", "================부산================="};
	
	ImageIcon img[] = new ImageIcon[4];
	int imgId;
	private JLabel lbAd;
	private JLabel lblAd;
	private JTextField txtlocal;;
	
	
	JLabel lbad2;
	
	ImageIcon[] adimg = {
			new ImageIcon(Branch.class.getResource("/image/kimbab.png")),
			new ImageIcon(Branch.class.getResource("/image/dish.png")),
			new ImageIcon(Branch.class.getResource("/image/cookie.png")),
	};
	
	JButton btnNext;
	
	private JComboBox branchCb;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Branch frame = new Branch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Branch() {}
	
	public Branch(String id, String name, String pw, String phone, String address) {
//	public Branch() {
		
		System.out.println("회원정보 : "+id+","+name+","+pw+","+phone+","+address);
		
		setTitle("지점선택");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 609);
		cp = new JPanel();
//		cp.setBackground();
		cp.setBorder(new EmptyBorder(5, 5, 5, 5));
		cp.setLayout(null);
		setContentPane(cp);
		cp.setLayout(null);
		
		JLabel logoLB = new JLabel("");
		logoLB.setIcon(new ImageIcon(Branch.class.getResource("/image/logo1.png")));
		logoLB.setBounds(52, 12, 234, 57);
		cp.add(logoLB);
		
		//지역 이미지 
		ImageIcon[] img1 = {
				new ImageIcon(Branch.class.getResource("/image/choice.png")),
				new ImageIcon(Branch.class.getResource("/image/seoul.png")),
				new ImageIcon(Branch.class.getResource("/image/kyungki.png")),
				new ImageIcon(Branch.class.getResource("/image/pusan.png")),
		};
		
		branchLb = new JLabel(img1[0]);
		branchLb.setBounds(382, 154, 156, 131);
		cp.add(branchLb);
		
		txtlocal = new JTextField();
		txtlocal.setFont(new Font("나눔스퀘어OTF Light", Font.BOLD, 17));
		txtlocal.setBounds(443, 312, 121, 33);
		cp.add(txtlocal);
		txtlocal.setColumns(10);
		
		
		cbSeoul = new JComboBox();
		cbSeoul.setFont(new Font("나눔스퀘어OTF Light", Font.BOLD, 17));
		//cbSeoul.setModel(new DefaultComboBoxModel(new String[] {"\uC885\uB85C3\uAC00\uC810", "\uAC15\uB0A8\uC810", "\uB178\uB7C9\uC9C4\uC810"}));
		cbSeoul.setModel(new DefaultComboBoxModel(new String[] {"종로 3가점", "강남점", "노량진점"}));
		
		
		//하단 콤보박스 서울 
		cbSeoul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
				String str = (String)cb.getSelectedItem();
				
				txtChoice.setText(localchoice);
				txtlocal.setText(str); 
				txtlocal.setVisible(true);
				txtChoice.setVisible(true);
				
				localchoice1 = (String)cbSeoul.getSelectedItem();
				
				btnNext.setEnabled(true);
				
			}
		});
		cbSeoul.setBounds(552, 154, 168, 34);
		cp.add(cbSeoul);
		cbSeoul.setVisible(false);
		cbKyungki = new JComboBox();
		cbKyungki.setFont(new Font("나눔스퀘어OTF Light", Font.BOLD, 17));
		//cbKyungki.setModel(new DefaultComboBoxModel(new String[] {"\uBD80\uCC9C\uC810", "\uAD11\uBA85\uC810"}));
		cbKyungki.setModel(new DefaultComboBoxModel(new String[] {"부천점", "광명점"}));
		
		//하단 콤보박스 경기 
		cbKyungki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String str = (String)cb.getSelectedItem();
				
				txtlocal.setText(str); 
				txtlocal.setVisible(true);
				txtChoice.setText(localchoice);
				txtChoice.setVisible(true);
				
				localchoice1 = (String)cbKyungki.getSelectedItem();
				btnNext.setEnabled(true);
			}
		});
		cbKyungki.setBounds(552, 200, 168, 34);
		cp.add(cbKyungki);
		cbKyungki.setVisible(false);
		
		cbPusan = new JComboBox();
		cbPusan.setFont(new Font("나눔스퀘어OTF Light", Font.BOLD, 17));		
		//cbPusan.setModel(new DefaultComboBoxModel(new String[] {"\uC911\uAD6C\uC810", "\uC11C\uAD6C\uC810"}));
		cbPusan.setModel(new DefaultComboBoxModel(new String[] {"중구", "서구"}));
		
		//하단 콤보박스 부산
		cbPusan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String str = (String)cb.getSelectedItem();
				txtlocal.setText(str); 
				txtlocal.setVisible(true);
				
				txtChoice.setText(localchoice);
				txtChoice.setVisible(true);
				
				localchoice1 = (String)cbPusan.getSelectedItem();
				btnNext.setEnabled(true);
			}
		});
		cbPusan.setBounds(552, 246, 168, 33);
		cbPusan.setVisible(false);
		cp.add(cbPusan);
		

		//서울, 경기, 부산 지역 고르기	
		branchCb = new JComboBox();
		branchCb.setFont(new Font("굴림", Font.BOLD, 17));
		branchCb.setModel(new DefaultComboBoxModel(new String[] {"원하시는 지역을 선택하세요", "================서울=================", "================경기=================", "================부산================="}));
		branchCb.setBounds(380, 98, 340, 33);
		cp.add(branchCb);
		branchCb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox obj = (JComboBox)arg0.getSource();
				int idx = obj.getSelectedIndex();
				branchLb.setIcon(img1[idx]);
				
				String str = (String)obj.getSelectedItem();
				
				if(str.equals(local[1])) {
					txtChoice.setVisible(false);
					txtlocal.setVisible(false);
					cbSeoul.setVisible(true);
					cbKyungki.setVisible(false);
					cbPusan.setVisible(false);
					
					localchoice = "서울 / ";
					
					btnNext.setEnabled(false);
				}else if(str.equals(local[2])) {
					txtChoice.setVisible(false);
					txtlocal.setVisible(false);
					cbKyungki.setVisible(true);
					cbSeoul.setVisible(false);
					cbPusan.setVisible(false);
					localchoice = "경기 / ";
					
					btnNext.setEnabled(false);
				}else if(str.equals(local[3])) {
					txtChoice.setVisible(false);
					txtlocal.setVisible(false);
					cbPusan.setVisible(true);
					cbKyungki.setVisible(false);
					cbSeoul.setVisible(false);
					localchoice = "부산 / ";
					
					btnNext.setEnabled(false);
				}
			}
		});
		
		txtChoice = new JTextField();
		txtChoice.setFont(new Font("나눔스퀘어OTF Light", Font.BOLD, 17));
		txtChoice.setBounds(377, 312, 65, 33);
		cp.add(txtChoice);
		txtChoice.setColumns(10);
		
		//로그아웃
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setBackground(new Color(230, 230, 250));
		btnLogout.setFont(new Font("나눔스퀘어OTF Light", Font.BOLD, 17));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				dispose();
				setVisible(false);
				new Login().setVisible(true);
			}
		});
		btnLogout.setBounds(603, 42, 105, 27);
		cp.add(btnLogout);
		
		//상위 광고 이미지 
		for(int i=0; i<img.length; i++){
			img[i] = new ImageIcon(Branch.class.getResource("/image/ad_"+(i+1)+".png"));
		}
		lbAd = new JLabel("");
		lbAd.setIcon(img[0]);
		
		lbAd.setBounds(52, 139, 245, 170);
		cp.add(lbAd);
		
		lblAd = new JLabel("\uAD11\uACE0");
		lblAd.setFont(new Font("Dialog", Font.BOLD, 21));
		lblAd.setBounds(52, 100, 72, 27);
		cp.add(lblAd);
		
		txtChoice.setVisible(false);
		txtlocal.setVisible(false);
		
		JLabel lblogin = new JLabel("");
		lblogin.setIcon(new ImageIcon(Branch.class.getResource("/image/login1.png")));
		lblogin.setBounds(570, 22, 44, 47);
		cp.add(lblogin);
		
		//상위 광고 왼쪽 버튼 
		JButton btnLeft = new JButton("");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgId--; //이전 이미지
				imgId += img.length;// 0인 경우를 대비
				imgId %= img.length;
				lbAd.setIcon(img[imgId]);//0,1,2,3
			
			}
		});
		btnLeft.setBackground(Color.WHITE);
		btnLeft.setIcon(new ImageIcon(Branch.class.getResource("/image/left3.png")));
		btnLeft.setBounds(133, 308, 30, 27);
		cp.add(btnLeft);
		
		//상위 광고 오른쪽 버튼
		JButton btnright = new JButton("");
		btnright.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgId++;//다음 이미지 아이디	
				imgId %= img.length;//4
				lbAd.setIcon(img[imgId]);//0,1,2,3
			}
		});
		btnright.setIcon(new ImageIcon(Branch.class.getResource("/image/right3.png")));
		btnright.setForeground(Color.WHITE);
		btnright.setBackground(Color.WHITE);
		btnright.setBounds(177, 308, 30, 27);
		cp.add(btnright);
		
		lbad2 = new JLabel("");
		lbad2.setBounds(51, 406, 683, 97);
		cp.add(lbad2);
		
		btnNext = new JButton("");
		
	
		btnNext.setIcon(new ImageIcon(Branch.class.getResource("/image/next1.png")));
		btnNext.setBounds(667, 298, 53, 47);
		cp.add(btnNext);
		btnNext.setEnabled(false);	
	
		
		
		//***메인페이지로 넘어가는 부분 추가.------//
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); //지금창 해제, 프레임 닫는 것
				setVisible(false); //지금창 안보이게

			
				try {
//					new MainPage(id,name,pw,phone,address,localchoice,localchoice1).setVisible(true);
					new MainPage(id,name,pw,phone,address).setVisible(true);
										
					//**이중에 필요한 정보를 지금 넘기도록 한다. 
					String branch = ".\\src\\resource\\MemberJoin\\branch.txt";
					FileWriter writer = null;
					String message = localchoice+","+localchoice1; // 지역,지점
					
					try {
						//true는 기존 내용에 이어서 쓰는 것, 기존내용을 없애고 새로 쓰려면 false .-> 매번 값을 받을 때마다 갱신된다.
						writer = new FileWriter(branch,false); 
						writer.append(message+"\r\n");
						writer.flush();
						
						System.out.println("지역,지점 정보 저장 완료");
					}catch(IOException e1) {
						e1.printStackTrace();
					}finally {
						try {
							if(writer !=null) writer.close();
						}catch(IOException e2) {
							e2.printStackTrace();
						}
					}
					
					
				} catch (IOException e1) {					
					e1.printStackTrace();
				}
			}
		});		
			
		//하단 광고 배너 바뀌는 쓰레드
		MyThread mt = new MyThread();
		mt.start();		

		setLocationRelativeTo(null);
	}
	
	class MyThread extends Thread{
		
		public void run() {
			while(true) {
				int rn = (int)(Math.random()*3);
				
				lbad2.setIcon(adimg[rn]);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}			
				
			}
		}
	}
}
