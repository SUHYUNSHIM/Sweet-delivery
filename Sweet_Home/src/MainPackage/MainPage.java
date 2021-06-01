package MainPackage;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import EventPackage.Event1;
import delivery.SweetDelivery;
import sweet_delivery.Login;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
//메인 페이지. branch 지점 선택 페이지에서 넘어온다.
public class MainPage extends JFrame {

	private JPanel contentPane;
	private JLabel lblUserName;
	private JTable table;
	//private JPanel panel;
	private static JLabel lblBanner;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public MainPage() {};
	public MainPage(String id, String name, String pw, String phone, String address) throws IOException {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1048, 1000);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUserName = new JLabel("유저 이름");  //가입한 유저의 이름을 가져와야 한다.
		lblUserName.setFont(new Font("함초롬돋움", Font.PLAIN, 13));
		lblUserName.setBounds(907, 58, 62, 18);
		contentPane.add(lblUserName);
		
		//Login에서 사용자의 이름 가져오기.------------//		
		lblUserName.setText(name);
		System.out.println(lblUserName.getText());
		
		JLabel lblProfile = new JLabel("");
		lblProfile.setIcon(new ImageIcon(MainPage.class.getResource("/resource/user_s.png")));
		lblProfile.setBounds(912, -1, 68, 65);
		contentPane.add(lblProfile);
		
		JLabel lblLogo = new JLabel(""); //logo
		lblLogo.setIcon(new ImageIcon(MainPage.class.getResource("/resource/logo.png")));
		lblLogo.setBounds(14, 13, 253, 64);
		contentPane.add(lblLogo);
		
		System.out.println(id+name+pw+phone+address);
		
		JButton btnMypage = new JButton("My page"); //마이 페이지, 고객 정보 ----------------//
		btnMypage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose(); //지금창 해제, 프레임 닫는 것
				setVisible(false); //지금창 안보이게
				new UserInfo(id).setVisible(true);
			}
		});
		btnMypage.setBackground(Color.WHITE);
		btnMypage.setOpaque(true);
		btnMypage.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		btnMypage.setBounds(772, 12, 111, 65);
		contentPane.add(btnMypage);
		
		JButton btnToOrder = new JButton("주문하기"); // 메뉴 선택 화면
		btnToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose(); //지금창 해제, 프레임 닫는 것
				setVisible(false); //지금창 안보이게
				new SweetDelivery(id).setVisible(true);
				//new Payment(name);
			}
		});
		btnToOrder.setBackground(Color.WHITE);
		btnToOrder.setOpaque(true);
		btnToOrder.setFont(new Font("배달의민족 주아", Font.PLAIN, 22));
		btnToOrder.setBounds(528, 12, 105, 65);
		contentPane.add(btnToOrder);
		
		JButton btnEvent = new JButton("Event"); //이벤트 페이지-------------------------------//
		btnEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); //지금창 해제, 프레임 닫는 것
				setVisible(false); //지금창 안보이게
				new Event1(id,name,pw,phone,address).setVisible(true); //이벤트 페이지. 
			}
		});
		btnEvent.setBackground(Color.WHITE);
		btnEvent.setOpaque(true);
		btnEvent.setFont(new Font("배달의민족 주아", Font.PLAIN, 22));
		btnEvent.setBounds(647, 12, 111, 65);
		contentPane.add(btnEvent);
		
		JLabel lblToolbar = new JLabel("");
		lblToolbar.setOpaque(true);
		lblToolbar.setForeground(SystemColor.inactiveCaption);
		lblToolbar.setBackground(Color.WHITE);
		lblToolbar.setBounds(14, 12, 1002, 65);
		contentPane.add(lblToolbar);
		
		lblBanner = new JLabel(""); //배너.
		lblBanner.setIcon(new ImageIcon(MainPage.class.getResource("/resource/banner/B1.png")));
		
		lblBanner.setBounds(39, 100, 977, 338);
		contentPane.add(lblBanner);

		String header[] = {"분류","제목","날짜"}; //공지사항 테이블 형태로 표시된다.
		String contents[][] = {
				{"시스템 점검","시스템 점검 안내 6월 6일(일) 오전 0시~5시","2021-05-24"},
				{"이벤트","[오픈 이벤트] 핫치킨 종로3가 1호점 오픈 안내","2021-05-11"},
				{"이벤트","[공지] 4월 행운의 주인공을 찾아라 당첨자 발표","2021-05-05"},
				{"서비스 안내","[안내] 5월 카드사 무이자 할부 안내","2021-05-01"},
				{"서비스 안내","[안내] 스위트 배달 메인 홈 개편","2021-04-15"}
				
		};	
		
		table = new JTable(contents,header);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int column= table.getSelectedColumn();
				int row = table.getSelectedRow();
				if(column==1 && row == 0 ) {
					try {
						dispose(); //지금창 해제, 프레임 닫는 것
						setVisible(false); //지금창 안보이게						
						new SystemMessage(id,name,pw, phone, address).setVisible(true); //시스템 점검 행을 누를 경우 나타나는 창.
					} catch (IOException e) {						
						e.printStackTrace();
					};
				}
			}
		});
		table.setShowVerticalLines(false);
		table.setFont(new Font("나눔스퀘어OTF Light", Font.PLAIN, 18));
				
		table.getColumn("분류").setPreferredWidth(30);
		table.getColumn("제목").setPreferredWidth(100);
		table.getColumn("날짜").setPreferredWidth(100);
		
		table.setBounds(39, 465, 921, 251);		
		table.setRowHeight(50);
		contentPane.add(table);
		
		JLabel lblNotice = new JLabel("공지사항");
		lblNotice.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		lblNotice.setBounds(39, 435, 62, 18);
		contentPane.add(lblNotice);
	
		DefaultTableCellRenderer celAlign = new DefaultTableCellRenderer();
		celAlign.setHorizontalAlignment(SwingConstants.LEFT);
		TableColumnModel alignTable = table.getColumnModel();	
		
		for(int i=0;i<alignTable.getColumnCount();i++) {
			alignTable.getColumn(i).setCellRenderer(celAlign);
		}	
		resizeColumnWidth(table);		
		
		JLabel lblEvent = new JLabel("Daily 이벤트");
		lblEvent.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		lblEvent.setBounds(39, 747, 121, 23);
		contentPane.add(lblEvent);
		
		JButton btnLuckyBox = new JButton("★오늘의 럭키 박스★");
		btnLuckyBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); //지금창 해제, 프레임 닫는 것
				setVisible(false); //지금창 안보이게
				new EventPage(id,name,pw,phone,address).setVisible(true); //럭키 박스 페이지(포인트, 쿠폰 지급)
			}
		});
		btnLuckyBox.setBackground(new Color(255, 160, 122));
		btnLuckyBox.setFont(new Font("타이포_크레파스 M", Font.PLAIN, 23));
		btnLuckyBox.setIcon(new ImageIcon(MainPage.class.getResource("/resource/qmark.png")));
		
		btnLuckyBox.setBounds(39, 782, 325, 137);
		btnLuckyBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(btnLuckyBox);
		
		JLabel label = new JLabel("첫 방문 고객 혜택");
		label.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		label.setBounds(566, 749, 137, 18);
		contentPane.add(label);
		
		JButton btnFirst = new JButton("오직 첫구매만!");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); //지금창 해제, 프레임 닫는 것
				setVisible(false); //지금창 안보이게
				new FirstBenefit(id,name,pw,phone,address).setVisible(true); //임시 이벤트 페이지
			}
		});
		btnFirst.setForeground(new Color(255, 235, 205));
		btnFirst.setBackground(new Color(65, 105, 225));
		btnFirst.setIcon(new ImageIcon(MainPage.class.getResource("/resource/ribbon.png")));
		btnFirst.setFont(new Font("타이포_크레파스 M", Font.PLAIN, 26));
		btnFirst.setBounds(566, 781, 325, 137);
		btnFirst.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(btnFirst);
		
		Banner bn = new Banner();
		bn.start();		

		setLocationRelativeTo(null);
	}
		
		
	//} // 설계도 class 끝.
	
	//문장 길이별로 셀 너비 조절하는 함수. 공지사항 테이블에 적용.
	public void resizeColumnWidth(JTable table) { 
		final TableColumnModel columnModel = table.getColumnModel(); 
		   for (int column = 0; column < table.getColumnCount(); column++) { 
				int width = 50; // Min width 
				for (int row = 0; row < table.getRowCount(); row++) { 
					TableCellRenderer renderer = table.getCellRenderer(row, column); 
					Component comp = table.prepareRenderer(renderer, row, column);
					width = Math.max(comp.getPreferredSize().width +1 , width); 
				} columnModel.getColumn(column).setPreferredWidth(width); 
			} 
		}
	
	//배너-----------------------------------------------------------------------//
	class Banner extends Thread{
		@Override
		public void run() {		
				int num = 0;
				int P=0;
				
				Calendar cal=Calendar.getInstance();
				String sec = null;
				String[] picture = {"B1","B2","B3"};
				
				while(true) {
					try {
						cal = Calendar.getInstance();
						sec = (cal.get(Calendar.SECOND)< 10?"0":"")+cal.get(Calendar.SECOND);
						Thread.sleep(1000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					num++;
					if(num%3==0) {
						if(P<3) {
							P++;
							if(P==3) {
								P=0;
							}
							lblBanner.setIcon(new ImageIcon(MainPage.class.getResource("/resource/banner/"+picture[P]+".png")));
						}
					}
				}
		}	
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage("","","","",""); //임시
					System.out.println(frame.getName());
					frame.setVisible(true);								
										
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
