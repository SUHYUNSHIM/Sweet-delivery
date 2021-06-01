package MainPackage; //MainPackage는 mainpage 메인 화면에 관련된 클래스들을 갖고 있다.
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;

public class Coupons extends JFrame {

	private JPanel contentPane;	
	private JTextField txtOnlyProduct;
	private JTextField txtDiscountPrice;
	JComboBox <CouponSetting> cmbCouponlist;
	JButton btnUseCoupon;
	
	String path = ".\\src\\resource\\rewards\\coupon.txt";
	String couponPath = ".\\src\\resource\\rewards\\SelectedCoupon.txt";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Coupons frame = new Coupons(0,"","");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Coupons(){}
	public Coupons(int defaultPrice, String option, String total) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("쿠폰 조회");
		setBounds(100, 100, 544, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		
	
		//상품 가격
		JLabel lblOnlyProduct = new JLabel("상품 가격");
		lblOnlyProduct.setFont(new Font("나눔바른고딕", Font.PLAIN, 12));
		lblOnlyProduct.setBounds(24, 40, 62, 18);
		contentPane.add(lblOnlyProduct);
		
		txtOnlyProduct = new JTextField();
		txtOnlyProduct.setEditable(false);
		txtOnlyProduct.setBounds(86, 36, 116, 24);
		contentPane.add(txtOnlyProduct);
		txtOnlyProduct.setColumns(10);
		txtOnlyProduct.setText(String.valueOf(defaultPrice));	
		
		//쿠폰 조회
		JLabel lblCouponlist = new JLabel("적용 쿠폰");
		lblCouponlist.setFont(new Font("나눔바른고딕", Font.PLAIN, 12));
		lblCouponlist.setBounds(24, 100, 62, 18);
		contentPane.add(lblCouponlist);		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(195, 166, 119, -77);
		contentPane.add(scrollPane);
		
		JComboBox <CouponSetting> cmbCouponlist = new JComboBox();
		cmbCouponlist.setBackground(new Color(230, 230, 250));
		cmbCouponlist.setBounds(86, 96, 228, 24);
		contentPane.add(cmbCouponlist);
		
		//string 배열. 
		ArrayList<CouponSetting> cs = new ArrayList<CouponSetting>();			
		
		try {
				DataInputStream dis = new DataInputStream(new FileInputStream(path));
				String[] tempArray;	
				while(true) {
					String text = dis.readLine();								
					tempArray = text.split(",");
					if(text == null) {break;} 
					if(text.isEmpty())continue;
					cs.add(new CouponSetting(Double.parseDouble(tempArray[0]),tempArray[1])); 			
				}dis.close();
			}catch(Exception e) {
				e.printStackTrace();
			}	
		
		for(int i=0;i<cs.size();i++) {
			System.out.println(cs.get(i).getCouponName()+":"+cs.get(i).getDiscount_percent());
		}
		for(int i=0;i<cs.size();i++) {
			//cmbCouponlist.addItem(cs.get(i).getCouponName()+"-"+String.valueOf((cs.get(i).getDiscount_percent()*100)+"% 할인 쿠폰"));
			cmbCouponlist.addItem(new CouponSetting(cs.get(i).getDiscount_percent(),cs.get(i).getCouponName()));
		}
			
		cmbCouponlist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()== cmbCouponlist) {
					//할인 금액 계산
					//선택된 값 그대로 뽑는 방법.
					//txtDiscountPrice.setText(cmbCouponlist.getSelectedItem().toString());
					//System.out.println(cmbCouponlist.getSelectedItem());					
					int productPrice = Integer.parseInt(txtOnlyProduct.getText()); //상품 가격
					
					String[] setting = cmbCouponlist.getSelectedItem().toString().split(":");
					String percent_s = setting[1].substring(0,2); // 20%할인 -> 20을 추출.
				    double percent  = Integer.parseInt(percent_s)/100.00;
					
					int discountPrice = (int)(productPrice * percent);
					txtDiscountPrice.setText(String.valueOf(discountPrice)); //할인되는 가격. 원가 * 할인 퍼센트.					
				}				
			}			
		});		
		
		//할인 가격 계산
		JLabel lblDiscountPrice = new JLabel("원    할인 적용");
		lblDiscountPrice.setFont(new Font("나눔바른고딕", Font.PLAIN, 12));
		lblDiscountPrice.setBounds(209, 145, 92, 18);
		contentPane.add(lblDiscountPrice);
		
		txtDiscountPrice = new JTextField();
		txtDiscountPrice.setEditable(false);
		txtDiscountPrice.setBounds(115, 141, 87, 24);
		contentPane.add(txtDiscountPrice);
		txtDiscountPrice.setColumns(10);
		
		//쿠폰 사용 -> 텍스트에 사용하는 쿠폰 쓰기, 쓴 쿠폰 지우기, 
		btnUseCoupon = new JButton("사용하기");
		btnUseCoupon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("쿠폰으로 차감할 구매비용은:"+txtDiscountPrice.getText());
				System.out.println("선택된 쿠폰은:"+cmbCouponlist.getSelectedItem().toString());
				
				//선택한 쿠폰을 SelectedCoupon.txt에 쓴다.
				FileWriter writer = null;
				String message = cmbCouponlist.getSelectedItem().toString()+","+ txtDiscountPrice.getText(); //"선택된 쿠폰이름,할인된 가격"을 쓴다.
				try {
					//true는 기존 내용에 이어서 쓰는 것, 기존내용을 없애고 새로 쓰려면 false .-> 매번 값을 받을 때마다 갱신된다.
					writer = new FileWriter(couponPath,false); 
					writer.write(message+"\n");					
					writer.flush();					
					System.out.println("쓰기 성공하였습니다.");
				}catch(IOException e1) {
					e1.printStackTrace();
				}finally {
					try {
						if(writer !=null) writer.close();
					}catch(IOException e2) {
						e2.printStackTrace();
					}
				}
				
				//사용한 쿠폰을 coupon.txt에서 지운다.
				String[] setting = cmbCouponlist.getSelectedItem().toString().split(":");
				String percent_s = setting[1].substring(0,2); // 20%DC -> 20을 추출.
			    double percent  = Integer.parseInt(percent_s)/100.00; //-> 0.20
			    System.out.println("없애려는 항목의 할인율 소수표현은 "+percent);
			    String valueBack = String.valueOf(percent) +","+setting[0];
			    System.out.println("없애려는 항목은 "+valueBack);	
				
				
				String tempPath = ".\\src\\resource\\rewards\\temp.txt";
				File file = new File(tempPath);
				String dummy="";
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
					String line;
					while(true) {
						line = br.readLine(); //삭제하는 위치 이전까지는 이동하면서 dummy에 저장한다.
						dummy += (line +"\r\n");					
						if(line.equals(valueBack)){ //지워야 하는 문장에 도달하면 멈춘다.							
							break;
						}						
					}
					String delData = br.readLine();
					System.out.println("지울 것을 찾았습니다."+delData);
					while((line = br.readLine()) !=null) {
								dummy +=(line +"\r\n");						
							}
						FileWriter fw = new FileWriter(tempPath);
						fw.write(dummy);
						fw.close();
						br.close();
					}catch(Exception e) {
							e.printStackTrace();
					}											
					
				dispose();
				setVisible(false);			
				
			}
		});
		btnUseCoupon.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnUseCoupon.setBackground(new Color(230, 230, 250));
		btnUseCoupon.setBounds(195, 191, 87, 25);
		contentPane.add(btnUseCoupon);
		
		
		
	}
}
