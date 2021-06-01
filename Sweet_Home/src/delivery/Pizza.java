package delivery;
//피자 메뉴 선택 화면
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.Color;
import java.awt.Font;

public class Pizza extends JFrame {

	private JPanel contentPane;
	private JTextField txt1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pizza frame = new Pizza("아이디");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Pizza(String id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title1 = new JLabel("New label");
		title1.setIcon(new ImageIcon(SweetDelivery.class.getResource("/image3/1.png")));
		title1.setBounds(14, 8, 232, 40);
		contentPane.add(title1);
		
		JButton poket1 = new JButton("장바구니");
		poket1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		poket1.setBackground(new Color(230, 230, 250));
		poket1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				setVisible(false);
				new Poket1(id).setVisible(true);
			}
		});
		poket1.setBounds(441, 19, 117, 29);
		contentPane.add(poket1);
		
		
		JButton btn3 = new JButton("< 이전");
		btn3.setBackground(new Color(230, 230, 250));
		btn3.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				new SweetDelivery(id).setVisible(true);
			}
		});
		btn3.setBounds(26, 567, 117, 29);
		contentPane.add(btn3);
		
		JButton food1 = new JButton("치즈피자");
		food1.setIcon(new ImageIcon(Pizza.class.getResource("/image3/pizza1.jpg")));
		food1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				new Pizza1(14000, "0", "0",id).setVisible(true);
			}
		});
		food1.setBounds(44, 167, 117, 111);
		contentPane.add(food1);
		
		JButton food2 = new JButton("페퍼로니");
		food2.setIcon(new ImageIcon(Pizza.class.getResource("/image3/pizza3.jpg")));
		food2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				new Pizza2(14000, "0", "0",id).setVisible(true);
			}
		});
		food2.setBounds(210, 167, 117, 111);
		contentPane.add(food2);
		
		JButton food3 = new JButton("콤비네이션");
		food3.setIcon(new ImageIcon(Pizza.class.getResource("/image3/pizza2.jpg")));
		food3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				new Pizza3(15000, "0", "0",id).setVisible(true);
			}
		});
		food3.setBounds(377, 167, 117, 111);
		contentPane.add(food3);
		
		JButton food4 = new JButton("");
		food4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				new ChineseFood3(14000, "0", "0",id).setVisible(true);
			}
		});
		food4.setBounds(44, 290, 117, 111);
		contentPane.add(food4);
		
		JButton button_3 = new JButton("");
		button_3.setBounds(210, 290, 117, 111);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.setBounds(377, 290, 117, 111);
		contentPane.add(button_4);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(44, 80, 488, 75);
		contentPane.add(toolBar);
		
		JButton menu1 = new JButton("");
		menu1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				new Chicken(id).setVisible(true);
			}
		});
		menu1.setIcon(new ImageIcon(SweetDelivery.class.getResource("/image3/Chicken.png")));
		toolBar.add(menu1);
		
		JButton menu2 = new JButton("");
		menu2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				new Pizza(id).setVisible(true);
			}
		});
		menu2.setIcon(new ImageIcon(SweetDelivery.class.getResource("/image3/pizza.png")));
		toolBar.add(menu2);
		
		JButton menu3 = new JButton("");
		menu3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				new Hambuger(id).setVisible(true);
			}
		});
		menu3.setIcon(new ImageIcon(SweetDelivery.class.getResource("/image3/hambuger.png")));
		toolBar.add(menu3);
		
		JButton menu4 = new JButton("");
		menu4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				new SchoolFood(id).setVisible(true);
			}
		});
		menu4.setIcon(new ImageIcon(SweetDelivery.class.getResource("/image3/schoolfood.png")));
		toolBar.add(menu4);
		
		JButton menu5 = new JButton("");
		menu5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);
				new ChineseFood(id).setVisible(true);
			}
		});
		menu5.setIcon(new ImageIcon(SweetDelivery.class.getResource("/image3/chinesefood.png")));
		toolBar.add(menu5);
		

		
		JButton button_9 = new JButton("");
		button_9.setBounds(44, 413, 117, 111);
		contentPane.add(button_9);
		
		JButton button_10 = new JButton("");
		button_10.setBounds(210, 413, 117, 111);
		contentPane.add(button_10);
		
		JButton button_11 = new JButton("");
		button_11.setBounds(377, 413, 117, 111);
		contentPane.add(button_11);
	}
}
