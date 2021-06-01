package MainPackage;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
//포인트 조회 버튼을 누르면 나오는 화면. 보유 포인트, 사용하는 포인트-> 입력받는다, 사용 후 잔여 포인트를 계산.
//
public class Points extends JFrame {

	private JPanel contentPane;

	private JTextField txtNow;
	private JTextField txtUse;
	private JTextArea textArea;
	private JButton btnTouse;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Points frame = new Points(10000); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Points(int total) {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("포인트 조회");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPoint = new JLabel("보유 포인트");
		lblPoint.setBounds(38, 53, 97, 18);
		contentPane.add(lblPoint);
		
		//보유 포인트 - 텍스트 파일에서 읽어오기
		String path =".\\src\\resource\\rewards\\point.txt";
		File file = new File(path);
		int existingValue=0; //=0을 지움
		 try {
			 	DataInputStream dis = new DataInputStream(new FileInputStream(path));
			 	while(true) {
					String text;
					
						text = dis.readLine();			 		
				 		existingValue = Integer.parseInt(text);				 		
				 		System.out.println("현존 포인트:"+existingValue);
				 		if(text == null) {
				 			break;				 		
					}		 		
			 	}			 	
			}catch (Exception e) {
				e.printStackTrace();
		}finally {
			//txtNow.setText(value);
		}
		System.out.println("현재 보유한 포인트:"+existingValue); //정수값
		String nowValue = String.valueOf(existingValue); //문자열값
		System.out.println("현재 보유한 포인트를 문자열로 바꾼 값:"+nowValue);
		 
		txtNow = new JTextField();
		txtNow.setEditable(false);
		txtNow.setBounds(180, 50, 116, 24);
		contentPane.add(txtNow);
		txtNow.setColumns(10);
		txtNow.setText(nowValue);
		System.out.println("화면에 나타난 포인트 값은"+txtNow.getText()); //화면에 나타난 포인트 값 
		//-------------------------------------------------
		btnTouse = new JButton("사용하기");
		btnTouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
														
				int use = Integer.parseInt(txtUse.getText());
				int now = Integer.parseInt(txtNow.getText());
				System.out.println("넘겨받은 총액은"+total);
				if(use>total) {
					JOptionPane.showMessageDialog(null,"총 금액보다 작은 값을 넣어주세요");	 //물품 가격보다 많은 포인트를 입력했을 경우 메시지창이 뜬다.
					txtUse.setText("0");
					textArea.setText(txtNow.getText()); //잔여 포인트도 제대로 되돌려준다.
				}
				if(use<0 || use>now ) {
					JOptionPane.showMessageDialog(null,"올바른 값을 넣어주세요");	
					txtUse.setText("0");
					textArea.setText(txtNow.getText());
				}	
				else if(use>=0 && use<=now && use<=total) {
					textArea.setText(String.valueOf(now-use)); //올바르게 입력된 경우, 포인트 잔액이 표시된다.
				}
				
			}
		});
		btnTouse.setFont(new Font("굴림", Font.PLAIN, 13));
		btnTouse.setBounds(26, 97, 97, 36);
		contentPane.add(btnTouse);		
		
		txtUse = new JTextField();   //쓰고 싶은 만큼 입력할 수 있음.사용액이 표시되는 부분.		
		txtUse.setBounds(180, 103, 116, 24);
		contentPane.add(txtUse);
		txtUse.setColumns(10);	
		txtUse.setText("0");
		
		//잔여포인트-----------------------------------------//
		JLabel lblNewLabel_1 = new JLabel("잔여 포인트");
		lblNewLabel_1.setBounds(38, 171, 97, 18);
		contentPane.add(lblNewLabel_1);	
				
		textArea = new JTextArea(); //잔여 포인트
		textArea.setEditable(false);
		textArea.setText(txtNow.getText());
		textArea.setBounds(180, 169, 116, 24);
		contentPane.add(textArea);		
		
		JButton btnBacktoPay = new JButton("확인");
		btnBacktoPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("사용할 포인트는:"+txtUse.getText());				
				//확인 버튼을 눌렀으면 포인트 잔여 포인트가 다시 텍스트 파일에 기록되어야 한다.
				FileWriter writer = null;
				String message = String.valueOf(textArea.getText()); //잔여액을 파일에 쓰기 위해 문자열로 바꾸었다.
				try {
					//true는 기존 내용에 이어서 쓰는 것, 기존내용을 없애고 새로 쓰려면 false .-> 매번 값을 받을 때마다 갱신된다.
					writer = new FileWriter(path,true); 
					writer.write(message+"\n");					
					writer.flush();					
					System.out.println("포인트 사용 후 잔여액으로 다시 저장.");
				}catch(IOException e1) {
					e1.printStackTrace();
				}finally {
					try {
						if(writer !=null) writer.close();
					}catch(IOException e2) {
						e2.printStackTrace();
					}
				}
				//그리고 창을 닫는다.
				dispose();
				setVisible(false); //지금창 안보이게						
			}
		});
		btnBacktoPay.setBounds(332, 214, 61, 27);
		contentPane.add(btnBacktoPay);			
	}

}
