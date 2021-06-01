package delivery;
//장바구니 화면. 
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MainPackage.Payment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.SystemColor;

public class Poket1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	File file = new File(".\\src\\resource\\Text\\Cost.txt");  //선택한 메뉴들의 각 가격이 줄단위로 써진다.
	File file2 = new File(".\\src\\resource\\Text\\Product.txt"); //선택한 메뉴들의 이름 목록이 써진다.
	File file3 = new File(".\\src\\resource\\Text\\Total.txt");  //장바구니에 담긴 총금액이 저장된다.

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Poket1 frame = new Poket1("아이디");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Poket1(String id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 701);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlightText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Poket1.class.getResource("/image3/1.png")));
		label.setBounds(14, 9, 224, 39);
		contentPane.add(label);
		
		JButton button = new JButton("장바구니");
		button.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		button.setBackground(new Color(230, 230, 250));
		button.setBounds(351, 83, 103, 29);
		contentPane.add(button);
		
		JButton button_2 = new JButton("< 이전으로");
		button_2.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		button_2.setBackground(new Color(230, 230, 250));
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				setVisible(false);
				new SweetDelivery(id).setVisible(true);
			}
		});
		button_2.setBounds(52, 351, 103, 29);
		contentPane.add(button_2);
		
		JLabel lblNewLabel = new JLabel("총 금액");
		lblNewLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		lblNewLabel.setBounds(295, 313, 61, 16);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(351, 308, 101, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		BufferedReader bw3 = null;
		try {
			bw3 = new BufferedReader(new FileReader(file3));
		} catch (FileNotFoundException e2) {			
			e2.printStackTrace();
		}
		String line3 = null;
		try {
			line3 = bw3.readLine();
		} catch (IOException e2) {			
			e2.printStackTrace();
		}
		textField_1.setText(line3);		
		
		
		JButton btnNewButton = new JButton("주문하기");
		btnNewButton.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnNewButton.setBackground(new Color(230, 230, 250));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				BufferedReader bw4 = null;
				try {
					bw4 = new BufferedReader(new FileReader(file3));
				} catch (FileNotFoundException e1) {					
					e1.printStackTrace();
				}
				String value = null;
				try {
					value = bw4.readLine();
				} catch (IOException e1) {					
					e1.printStackTrace();
				}
				try {
					bw4.close();
				} catch (IOException e1) {					
					e1.printStackTrace();
				}
				
				String defaultPrice = value;
				System.out.println(defaultPrice);
				
					 
			
				dispose();
				setVisible(false);
				try {
					new Payment(Integer.parseInt(defaultPrice),"0", "0",id).setVisible(true);
				} catch (NumberFormatException e1) {					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(351, 351, 103, 29);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(212, 84, 101, 174);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea(); //장바구니에 담은 선택한 메뉴들이 써진다.
		textArea.setEditable(false);
		scrollPane_1.setViewportView(textArea);
		textArea.setLineWrap(true);
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {			
			e1.printStackTrace();
		}
		String line;

		try {
			while((line=br.readLine())!=null) {
				textArea.append(line+"\n");
			}
				
		} catch (IOException e1) {			
			e1.printStackTrace();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(110, 84, 101, 174);
		contentPane.add(scrollPane);		
		
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		scrollPane.setViewportView(textArea_1);
		textArea_1.setLineWrap(true);		
	
		
		JButton btnNewButton_1 = new JButton("삭제하기");  //장바구니에 담긴 메뉴들을 삭제한다.
		btnNewButton_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnNewButton_1.setBackground(new Color(230, 230, 250));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(file, false)); //가격 목록 삭제
					bw.close();
				} catch (IOException e1) {					
					e1.printStackTrace();
				}
					try {
						BufferedWriter bw2 = new BufferedWriter(new FileWriter(file2, false)); //목록 삭제
						bw2.close();
					} catch (IOException e1) {						
						e1.printStackTrace();
					}
					try {
						BufferedWriter bw3 = new BufferedWriter(new FileWriter(file3, false)); //총가격 삭제. 0으로 값 대신 넣는다.
						bw3.write("0");
						bw3.close();
					} catch (IOException e1) {						
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(textArea, "삭제되었습니다. 메뉴 다시 선택해주세요.");
					dispose();
					setVisible(false);		
					new SweetDelivery(id).setVisible(true); //메뉴 선택화면으로 돌아간다.
			}
		});
		btnNewButton_1.setBounds(351, 124, 103, 29);
		contentPane.add(btnNewButton_1);
		
		BufferedReader br2 = null;
		
			try {
				br2 = new BufferedReader(new FileReader(file2));
			} catch (FileNotFoundException e1) {				
				e1.printStackTrace();
			}
			String line2;
	
			try {
				while((line2=br2.readLine())!=null) {
					textArea_1.append(line2+"\n");
				}
					
			} catch (IOException e1) {				
				e1.printStackTrace();
			}		
	}
}
