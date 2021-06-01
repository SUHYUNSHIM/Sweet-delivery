package MainPackage;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import delivery.SweetDelivery;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import java.awt.Font;
import java.awt.SystemColor;
//결제 페이지. coupons, points, pocket1(장바구니) 클래스와 연동 된다.
public class Payment extends JFrame {

	private JPanel contentPane;
	private JTextArea txtOption2;
	private JTextField txtFirstValue2;
	private JTextField txtCoupon;
	private JTextField txtPoint;
	private JTextField txtUserName;
	private JTextField txtTotalPayment;
	
	private JLabel lblBaedal;
	private JTextField txtBaedal;	
	
	String name; 
	String pw; 
	String phone; 
	String address;
	
	String userName; //유저 이름도 표시되어야 한다.	
	private JTextField txtPhone;
	private JTextField txtName;
	JTextArea txt_address;

	JPanel panel_pay; //현금, 카드 결제의 라디오 그룹이 들어가는 패널.
	String couponPath = ".\\src\\resource\\rewards\\SelectedCoupon.txt";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment(0,"0","0","아이디");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Payment() {}
	public Payment(int defaultPrice, String option, String total,String id) throws NullPointerException { //총 결제 금액이 넘어온 것.defaultPrice	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 590);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//기본 가격
		JLabel lbl_firstValue2 = new JLabel("기본가격");
		lbl_firstValue2.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lbl_firstValue2.setBounds(62, 62, 62, 18);
		contentPane.add(lbl_firstValue2);		
		
		txtFirstValue2 = new JTextField();
		txtFirstValue2.setEditable(false);
		txtFirstValue2.setBounds(142, 62, 116, 24);
		contentPane.add(txtFirstValue2);
		txtFirstValue2.setText(String.valueOf(defaultPrice)+"원");
		txtFirstValue2.setColumns(10);
		
		//전체 메뉴
		JLabel lblOption2 = new JLabel("전체 메뉴");
		lblOption2.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblOption2.setBounds(62, 106, 62, 18);
		contentPane.add(lblOption2);	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(142, 105, 170, 50);
		contentPane.add(scrollPane);
		
		txtOption2 = new JTextArea();
		scrollPane.setViewportView(txtOption2);
		
		BufferedReader bw1=null;
		try {
			bw1 = new BufferedReader(new FileReader(".\\src\\resource\\Text\\Product.txt"));
		} catch (FileNotFoundException e2) {			
			e2.printStackTrace();
		}
		String line=null;
		
			try {
				while((line=bw1.readLine())!=null){
					txtOption2.append(line+"\n");
				}
			} catch (IOException e2) {				
				e2.printStackTrace();
			}
		
		//할인 쿠폰----------------------------------------//
		JLabel lblCoupon = new JLabel("할인 쿠폰");
		lblCoupon.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblCoupon.setBounds(62, 294, 62, 24);
		contentPane.add(lblCoupon);	
		
		txtCoupon = new JTextField();
		txtCoupon.setEditable(false);
		txtCoupon.setBounds(127, 294, 160, 24);
		contentPane.add(txtCoupon);
		txtCoupon.setColumns(10);
		txtCoupon.setText("적용하지 않음"); //초기상태.----------------> 어떤 쿠폰을 적용했는지.	
		
		
		//쿠폰 조회 버튼
		JButton btnCoupon = new JButton("조회");
		btnCoupon.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
		btnCoupon.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new Coupons(defaultPrice, option, total).setVisible(true);
			}
		});
		btnCoupon.setBounds(297, 296, 62, 20);
		btnCoupon.setBackground(new Color(230, 230, 250));
		contentPane.add(btnCoupon);	
		
		//포인트 	--------------------------------------------//	
		JLabel lblPoint = new JLabel("포인트");
		lblPoint.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblPoint.setBounds(62, 258, 55, 24);
		contentPane.add(lblPoint);		
						
		//포인트 조회 및 사용-> 새 창이 뜬다.
		//포인트 사용액이 주문액을 넘지 않기 위해 
		JButton btnPoint= new JButton("조회");
		btnPoint.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
		btnPoint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//배송비를 포함한 총금액 계산
				txtBaedal.setText("1500원");
				String temp = txtBaedal.getText();
				String onlyValue = temp.substring(0,4); // "1500원" -> "1500"
				int baedal = Integer.parseInt(onlyValue); //1500
				
				int realTotal=0;				
				int totalValue = defaultPrice;
				realTotal += totalValue+ baedal; //총액 잘못받은 것 수정.				
				System.out.println("넘겨준 총액은:"+realTotal);
				new Points(realTotal).setVisible(true);				
			}			
		});
		btnPoint.setBounds(297,260,62,20);
		btnPoint.setBackground(new Color(230, 230, 250));
		contentPane.add(btnPoint);	
		
		txtPoint = new JTextField(); //값이 들어가는 영역
		txtPoint.setEditable(false);
		txtPoint.setBounds(127, 258, 91, 24);
		contentPane.add(txtPoint);
		txtPoint.setColumns(10);
		txtPoint.setText("0"); //초기값을 0으로 해두었다.
		
		//배송비-----------------------------------------------------// --> 메뉴 선택에서 받아온 총금액에서 배달비를 더해야 한다.
		lblBaedal = new JLabel("배달비");
		lblBaedal.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblBaedal.setBounds(62, 167, 55, 24);
		contentPane.add(lblBaedal);
		
		txtBaedal = new JTextField();
		txtBaedal.setEditable(false);
		txtBaedal.setBounds(142, 167, 116, 24);
		txtBaedal.setText("1500원"); //보유한 쿠폰에 따라 배달비가 제외될 수 있다.
		contentPane.add(txtBaedal);
		txtBaedal.setColumns(10);
		
		//배송비를 포함한 총금액 계산
		String temp = txtBaedal.getText();
		String onlyValue = temp.substring(0,4); // "1500원" -> "1500"
		int baedal = Integer.parseInt(onlyValue); //1500
		System.out.println("배달비는"+baedal);
		int realTotal=0;		
		int totalValue = defaultPrice;
		realTotal += totalValue+ baedal; //총액 잘못받은 것 수정.			
		System.out.println("총액은:"+realTotal);
				
		//총액 
		JLabel lblTotal = new JLabel("총 결제 금액");
		lblTotal.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		lblTotal.setBounds(268, 381, 116, 24);
		contentPane.add(lblTotal);		

		txtTotalPayment = new JTextField();
		txtTotalPayment.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtTotalPayment.setEditable(false);
		txtTotalPayment.setBounds(389, 382, 116, 24);
		txtTotalPayment.setText(String.valueOf(realTotal));
		contentPane.add(txtTotalPayment);
		txtTotalPayment.setColumns(10);
						
		JButton btnBackPay = new JButton("");
		btnBackPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); //지금창 해제, 프레임 닫는 것
				setVisible(false); //지금창 안보이게
				new SweetDelivery(id).setVisible(true);//메뉴 선택 페이지로 되돌아간다. 
			}
		});
		btnBackPay.setIcon(new ImageIcon(Payment.class.getResource("/resource/back-button.png")));
		btnBackPay.setBounds(12, 391, 98, 74);
		btnBackPay.setBorderPainted(false);
		btnBackPay.setContentAreaFilled(false);
		btnBackPay.setFocusPainted(false);
		contentPane.add(btnBackPay);
		
		//라디오 버튼 체크 여부에 따라 최종 구매목록에 나타날 예정.
		//결제수단-----------------------------------------------------------------------------------//
		JLabel lblHowtoPay = new JLabel("결제 수단(현장)");
		lblHowtoPay.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblHowtoPay.setBounds(268, 417, 115, 18);
		contentPane.add(lblHowtoPay);			
		
		JRadioButton rb1 = new JRadioButton("현금"); 
		JRadioButton rb2 = new JRadioButton("카드");
		ButtonGroup group = new ButtonGroup();
		group.add(rb1);
		group.add(rb2);
		
		panel_pay = new JPanel();
		panel_pay.setBounds(389, 425, 160, 60);
		contentPane.add(panel_pay);			
		
		panel_pay.add(rb1);
		panel_pay.add(rb2);		
		
		//-------------------------------------------------------------------------------------------------//
			
		JButton btnRefresh = new JButton("적용"); //리워드 적용된 것을 새로고침 하는 용도 ---------------------//
		btnRefresh.setHorizontalAlignment(SwingConstants.LEADING);
		btnRefresh.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
		btnRefresh.setBackground(new Color(230, 230, 250));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				//포인트 파일 읽어오기 
				//보유 포인트 - 텍스트 파일에서 읽어오기 -> 사용하기 전 포인트
				String path =".\\src\\resource\\rewards\\point.txt";
				File file = new File(path);
				int existingPoint=0;
				
				ArrayList<String> list = new ArrayList<String>();
				BufferedReader br = null;
					try {
					 	//DataInputStream dis = new DataInputStream(new FileInputStream(path));					 	
					 	br = new BufferedReader(new FileReader(path));
					 	String text;
					 	while((text = br.readLine()) !=null) {
							list.add(text);
					 	}
					 	br.close();
					}catch (IOException e1) {
					    System.err.println(e1);
					   }finally{
					    try { if( br != null ) { br.close(); } } catch(Exception ex) { }
				    }					
				existingPoint = Integer.parseInt(list.get(0).toString()); //파일에 있는 포인트 값이 정수화된 것.
				System.out.println("payment에서 조회한 현재 보유 포인트"+existingPoint);
				System.out.println("현재 list의 길이는"+list.size());
				if(list.size()==1) { //포인트를 사용하지 않고, 적용 버튼을 눌렀을 때.
					System.out.println("현재는 0포인트 사용");
					JOptionPane.showMessageDialog(null, "포인트 및 쿠폰을 조회 후 눌러야 합니다."+"\r\n"+"조회 버튼을 눌러 할인 혜택을 적용한 뒤 사용해보세요");								
				}				 								 	
				
				String nowPoint = String.valueOf(existingPoint);
				
				//포인트 조회를 통해 포인트를 사용해와서 기존값, 잔여값을 받았기 때문에 길이가 2인 것이다.
				list.removeAll(Arrays.asList("",null)); //공백,null 제거
				
				boolean flag = true;
				if(list.size()==2 && flag==true) { //정상적으로 기존값, 잔여값 순으로 list가 들어갔을 때.	
					
					int leftPoint = Integer.parseInt(list.get(1).toString()); //사용한 값
					int result = existingPoint - leftPoint;
					System.out.println("화면에 표시될 사용한 포인트는 "+result);
					String resultPoint = String.valueOf(result);
					txtPoint.setText(resultPoint);
									 		
					int totalPay = Integer.parseInt(txtTotalPayment.getText()) - result;
					System.out.println("화면에 표시될 총구매액은 "+totalPay);
					String totalPayment = String.valueOf(totalPay);
					txtTotalPayment.setText(totalPayment);
					flag = false;					
											
					//refresh -> 잔여액을 표시한 이후에는 다시 텍스트를 잔여액만 남겨둬야 한다.
					list.remove(0); //첫 번째 값인 사용 전 금액을 삭제한다.	
					System.out.println("삭제되는 값은:"+list.get(0).toString());
					if(list.size()==1) {
						System.out.println("성공적으로 제거되었습니다.");
					}
					//현재 보유액만 텍스트 파일에 저장되게 한다.
					FileWriter writer = null;
					String refresh = list.get(0).toString();
					System.out.println("현재 텍스트 파일에 남겨둘 포인트 보유액은"+refresh);
					
					try {
						//true는 기존 내용에 이어서 쓰는 것, 기존내용을 없애고 새로 쓰려면 false .-> 매번 값을 받을 때마다 갱신된다.
						writer = new FileWriter(path,false); 
						writer.write(refresh+"\n");
						writer.flush();					
						System.out.println("새로 저장 성공");
					}catch(IOException e1) {
						e1.printStackTrace();
					}finally {
						try {
							if(writer !=null) writer.close();
						}catch(IOException e2) {
							e2.printStackTrace();
						}
					}
	
					}
		//선택된 쿠폰 정보 읽어오기------------------------------------------------------//
		boolean flag2 = true;
		
		if(flag2== true) {
			File cpfile = new File(couponPath);			
			 try {
				 	DataInputStream dis = new DataInputStream(new FileInputStream(couponPath));
				 	while(true) {
				 		String text = dis.readLine();
				 		if(text == null) {
					 			break;
					 	}
					 	String value = text; //읽은 값을 문자열로 저장		 	
						System.out.println(value);
						String[] values = value.split(","); //,를 기준으로 쿠폰이름과 할인된 가격으로 쪼갠다.
						txtCoupon.setText(values[0]); //쿠폰 이름 써넣는다.
						int beforeCoupon  = Integer.parseInt(txtTotalPayment.getText());
						int afterCoupon = beforeCoupon - Integer.parseInt(values[1]); //총금액에서 쿠폰 할인가를 뺀금액
						txtTotalPayment.setText(String.valueOf(afterCoupon));	
						flag2 = false;
						}
						//br.close();
					}catch (Exception e3) {
						e3.printStackTrace();
					}				
			}
			if(flag2==false) {
				//selectedCoupon의 파일 내용을 지운다. -> 적용하기 했을 때 더 이상 못지우게 한 번 눌렀을 경우 selected를 지운다.
				FileWriter writer = null;				
				
				try {
					//기존내용을 없애고 새로 쓰려면 false 
					writer = new FileWriter(couponPath,false); 
					writer.append("");
					writer.flush();
					
					System.out.println("Done");
				}catch(IOException e3) {
					e3.printStackTrace();
				}finally {
					try {
						if(writer !=null) writer.close();
					}catch(IOException e4) {
						e4.printStackTrace();
					}
				}
			}	
		
		}
			
			
		});
		btnRefresh.setBounds(371, 258, 62, 60);
		contentPane.add(btnRefresh);
		btnRefresh.setBorderPainted(false);		
		btnRefresh.setFocusPainted(false);		
		
		JButton btnComplete = new JButton("주문하기");
		btnComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//수단
				Enumeration<AbstractButton> enums = group.getElements();
				String gibonSelect=null;
				while(enums.hasMoreElements()) {                 // hasMoreElements() Enum에 더 꺼낼 개체가 있는지 체크.
				    AbstractButton ab = enums.nextElement();    
				    JRadioButton jb = (JRadioButton)ab;         // 형변환. 물론 윗줄과 이줄을 합쳐서 바로 형변환 해서 받아도 된다.
				 
				    if(jb.isSelected())                         // 받아낸 라디오버튼의 체크 상태를 확인한다. 체크되었을경우 true 반환.
				    	gibonSelect = jb.getText().trim(); //getText() 메소드로 문자열 받아낸다. 카드를 선택했으면 "카드" 문자열 값을 가지고 있다.		    
				}	
							
				
				dispose();
				setVisible(false);
				new Order_History(id,gibonSelect).setVisible(true);
			}
		});
		btnComplete.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnComplete.setBackground(new Color(230, 230, 250));
		btnComplete.setBounds(610, 412, 116, 28);
		contentPane.add(btnComplete);
		
		//배송 정보------------------------------------------------------//
		
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
		
		JLabel lblAddress = new JLabel("배송 주소");
		lblAddress.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblAddress.setBounds(504, 106, 55, 18);
		contentPane.add(lblAddress);
		
		txt_address = new JTextArea();
		txt_address.setBounds(550, 124, 210, 43);
		contentPane.add(txt_address);
		//
		txt_address.setText(address);
		
		JLabel lblPhone = new JLabel("휴대폰");
		lblPhone.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblPhone.setBounds(504, 188, 55, 18);
		contentPane.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(550, 187, 136, 24);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);
		//
		txtPhone.setText(phone);
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblNewLabel.setBounds(504, 62, 55, 18);
		contentPane.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setBounds(548, 60, 114, 24);
		contentPane.add(txtName);
		txtName.setColumns(10);
		//
		txtName.setText(name);
		//------------------------------------------------------------//
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(450, 57, 1, 290);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(30, 349, 792, 1);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("상품 정보");
		lblNewLabel_1.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		lblNewLabel_1.setBounds(30, 31, 91, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("배송 정보");
		lblNewLabel_2.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		lblNewLabel_2.setBounds(465, 31, 84, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("혜택 적용");
		lblNewLabel_3.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		lblNewLabel_3.setBounds(30, 228, 70, 18);
		contentPane.add(lblNewLabel_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(30, 206, 421, 1);
		contentPane.add(panel_2);
		
		
	}
}
