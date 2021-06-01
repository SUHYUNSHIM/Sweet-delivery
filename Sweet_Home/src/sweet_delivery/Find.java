
package sweet_delivery;
//아이디, 비밀번호 찾기.
import java.awt.EventQueue;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Find extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtPhone;
	
	private JTextField txtId1;
	private JTextField txtName1;
	private JTextField txtPhone1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Find frame = new Find();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Find() {
		setTitle("아이디 찾기/비밀번호 찾기 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 533);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		Image bgimg = new ImageIcon(Find.class.getResource("/image/findbg3.png")).getImage();
		JPanel p = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(bgimg, 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		p.setBounds(0, 0, 878, 495);
		p.setLayout(null);
		contentPane.add(p);
		
		txtName = new JTextField();
		txtName.setBounds(147, 127, 116, 24);
		txtName.setColumns(10);
		p.add(txtName);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(147, 163, 116, 24);
		txtPhone.setColumns(10);
		p.add(txtPhone);
		
		txtId1 = new JTextField();
		txtId1.setBounds(147, 294, 116, 24);
		txtId1.setColumns(10);
		p.add(txtId1);
		
		txtName1 = new JTextField();
		txtName1.setBounds(147, 330, 116, 24);
		txtName1.setColumns(10);
		p.add(txtName1);
		
		txtPhone1 = new JTextField();
		txtPhone1.setBounds(147, 369, 116, 24);
		txtPhone1.setColumns(10);
		p.add(txtPhone1);
		
		JLabel lbName = new JLabel("NAME");
		lbName.setBounds(55, 130, 39, 18);
		lbName.setFont(new Font("나눔스퀘어OTF Light", Font.BOLD, 15));
		p.add(lbName);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(55, 166, 47, 18);
		lblPhone.setFont(new Font("나눔스퀘어OTF Light", Font.BOLD, 15));
		p.add(lblPhone);
		
		JLabel lbId1 = new JLabel("ID");
		lbId1.setBounds(55, 297, 16, 18);
		lbId1.setFont(new Font("나눔스퀘어OTF Light", Font.BOLD, 15));
		p.add(lbId1);
		
		JLabel lbName1 = new JLabel("NAME");
		lbName1.setBounds(55, 336, 39, 18);
		lbName1.setFont(new Font("나눔스퀘어OTF Light", Font.BOLD, 15));
		p.add(lbName1);
		
		JLabel lblPhone1 = new JLabel("PHONE");
		lblPhone1.setBounds(55, 372, 48, 18);
		lblPhone1.setFont(new Font("나눔스퀘어OTF Light", Font.BOLD, 15));
		p.add(lblPhone1);
		
		JButton idFindBtn = new JButton("\uC544\uC774\uB514 \uD78C\uD2B8");
		idFindBtn.setBounds(54, 215, 124, 29);
		idFindBtn.setBackground(new Color(230, 230, 250));
		idFindBtn.setFont(new Font("나눔스퀘어OTF Light", Font.BOLD, 17));
		p.add(idFindBtn);
		
		JButton pwFindBtn = new JButton("\uBE44\uBC00\uBC88\uD638 \uD78C\uD2B8");
		pwFindBtn.setBounds(55, 412, 150, 29);
		pwFindBtn.setBackground(new Color(230, 230, 250));
		pwFindBtn.setFont(new Font("나눔스퀘어OTF Light", Font.BOLD, 17));
		p.add(pwFindBtn);
		
		JButton btnReturn = new JButton("");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				new Login().setVisible(true);	
			}
		});
		btnReturn.setBackground(Color.WHITE);
		btnReturn.setIcon(new ImageIcon(Find.class.getResource("/image/return.png")));
		btnReturn.setBounds(258, 31, 48, 41);
		p.add(btnReturn);
		
		//아이디 찾기
		idFindBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtName.getText().trim();
				String phone = txtPhone.getText().trim();
				FileReader fr = null;
				BufferedReader br = null;
				
				try {
					fr = new FileReader(".\\src\\resource\\FindId\\"+name+".txt");
					br = new BufferedReader(fr);
					
					String read = br.readLine();
					String bea[] = read.split(",");
					
					if(bea[1].equals(name) && bea[2].equals(phone)) {
						String id = bea[0];
						String hint = id.substring(0,2);
						String hintShow = hint.concat("****");
						JOptionPane.showMessageDialog(null, "힌트 : "+hintShow);
					}else {
						JOptionPane.showMessageDialog(null, "이름 또는 전화번호를 다시 확인 하세요");
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "이름을 넣어주세요");
				} finally {
					try {
						if(br != null) {br.close();}
						if(fr != null) {fr.close();}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});//아이디 찾기
		
		//비밀번호 찾기
		pwFindBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId1.getText().trim();
				String name = txtName1.getText().trim();
				String phone = txtPhone1.getText().trim();
				FileReader fr = null;
				BufferedReader br = null;
				
				try {
					fr = new FileReader(".\\src\\resource\\MemberJoin\\"+id+".txt");
					br = new BufferedReader(fr);
					
					String read = br.readLine();
					String[] bea = read.split(",");
					if(bea[0].equals(id) && bea[1].equals(name) && bea[3].equals(phone)) {
						String pw = bea[2];
						System.out.println(pw);
						
						String pwHint = pw.substring(0,2);
						String HintShow = pwHint.concat("****");
						JOptionPane.showMessageDialog(null, "힌트 : "+HintShow);
					}else {
						JOptionPane.showMessageDialog(null, "등록된 정보가 일치하지 않습니다.");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "등록된 아이디가 없습니다.");
				} finally {
					try {
						if(br != null) {br.close();}
						if(fr != null) {fr.close();}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});	
		
		setLocationRelativeTo(null);
	}//생성자
	

}
