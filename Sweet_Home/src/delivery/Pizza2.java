package delivery;
//페퍼로니 추가 옵션
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MainPackage.Payment;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;

public class Pizza2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	File file = new File(".\\src\\resource\\Text\\Cost.txt");
	File file2 = new File(".\\src\\resource\\Text\\Product.txt");
	File file3 = new File(".\\src\\resource\\Text\\Total.txt");
	String product = "페퍼로니";
	String price = "14000";
	int op1;
	int op2;
	int op3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pizza2 frame = new Pizza2(0, "0", "0","아이디");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Pizza2(int defaultPrice, String option, String total,String id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Chicken1.class.getResource("/image3/1.png")));
		lblNewLabel.setBounds(165, 18, 221, 44);
		contentPane.add(lblNewLabel);
		
		JButton button = new JButton("장바구니");
		button.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		button.setBackground(new Color(230, 230, 250));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				setVisible(false);
				new Poket1(id).setVisible(true);
			}
		});
		button.setBounds(400, 52, 117, 29);
		contentPane.add(button);
		
		JLabel chicken1 = new JLabel("페퍼로니");
		chicken1.setIcon(new ImageIcon(Pizza2.class.getResource("/image3/pizza3.jpg")));
		chicken1.setBackground(Color.WHITE);
		chicken1.setBounds(65, 108, 150, 150);
		contentPane.add(chicken1);
		

		
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
		button_2.setBounds(29, 312, 117, 29);
		contentPane.add(button_2);
		
		JLabel lblNewLabel_1 = new JLabel("페퍼로니");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel_1.setBounds(233, 108, 98, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("14000");
		lblNewLabel_2.setBounds(343, 108, 53, 29);
		contentPane.add(lblNewLabel_2);
	
		
		JButton btnNewButton = new JButton("구매하기");
		btnNewButton.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnNewButton.setBackground(new Color(230, 230, 250));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedWriter writer = null;
				try {
					writer = new BufferedWriter(new FileWriter(file, true));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					writer.write(price+"\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				BufferedWriter writer2 = null;
				try {
					writer2 = new BufferedWriter(new FileWriter(file2, true));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					writer2.write(product+"\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					writer2.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Total tt = new Total(14000);
				tt.WriteToFile(14000);

				if(op1==1) {
					BufferedWriter bw1 = null;
					try {
						bw1 = new BufferedWriter(new FileWriter(file, true));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						bw1.write("500"+"\n");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						bw1.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					BufferedWriter bw2 = null;
					try {
						bw2 = new BufferedWriter(new FileWriter(file2, true));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						bw2.write("+갈릭디핑소스"+"\n");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						bw2.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				
					
					tt.WriteToFile(500);
				}
				 if(op2==1) {
					BufferedWriter bw3 = null;
					try {
						bw3 = new BufferedWriter(new FileWriter(file, true));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						bw3.write("1500"+"\n");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						bw3.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					BufferedWriter bw4 = null;
					try {
						bw4 = new BufferedWriter(new FileWriter(file2, true));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						bw4.write("+콜라 1.25L"+"\n");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						bw4.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				
					
					tt.WriteToFile(1500);
				}
				 if(op3==1) {
					BufferedWriter bw5 = null;
					try {
						bw5 = new BufferedWriter(new FileWriter(file, true));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						bw5.write("4000"+"\n");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						bw5.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					BufferedWriter bw6 = null;
					try {
						bw6 = new BufferedWriter(new FileWriter(file2, true));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						bw6.write("+스파게티"+"\n");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						bw6.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				
				 }
				
					tt.WriteToFile(4000);
				BufferedReader bf=null;
				try {
					bf = new BufferedReader(new FileReader(file3));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String line=null;
				try {
					line=bf.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				setVisible(false);
				try {
					new Payment(Integer.parseInt(line), "0", "0",id).setVisible(true);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(186, 278, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("장바구니");
		btnNewButton_1.setBackground(new Color(230, 230, 250));
		btnNewButton_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton_1)
					if(btnNewButton_1.getText().equals("장바구니")) {
						BufferedWriter writer = null;
						try {
							writer = new BufferedWriter(new FileWriter(file, true));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							writer.write(price+"\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							writer.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
						BufferedWriter writer2 = null;
						try {
							writer2 = new BufferedWriter(new FileWriter(file2, true));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							writer2.write(product+"\n");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							writer2.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						Total tt = new Total(14000);
						tt.WriteToFile(14000);
						if(op1==1) {
							BufferedWriter bw1 = null;
							try {
								bw1 = new BufferedWriter(new FileWriter(file, true));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								bw1.write("500"+"\n");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								bw1.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
							BufferedWriter bw2 = null;
							try {
								bw2 = new BufferedWriter(new FileWriter(file2, true));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								bw2.write("+갈릭디핑소스"+"\n");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								bw2.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
						
							
							tt.WriteToFile(500);
						}
						 if(op2==1) {
							BufferedWriter bw3 = null;
							try {
								bw3 = new BufferedWriter(new FileWriter(file, true));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								bw3.write("1500"+"\n");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								bw3.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
							BufferedWriter bw4 = null;
							try {
								bw4 = new BufferedWriter(new FileWriter(file2, true));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								bw4.write("+콜라 1.25L"+"\n");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								bw4.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
						
							
							tt.WriteToFile(1500);
						}
						 if(op3==1) {
							BufferedWriter bw1 = null;
							try {
								bw1 = new BufferedWriter(new FileWriter(file, true));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								bw1.write("4000"+"\n");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								bw1.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
							BufferedWriter bw2 = null;
							try {
								bw2 = new BufferedWriter(new FileWriter(file2, true));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								bw2.write("+스파게티"+"\n");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								bw2.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
						
							
							tt.WriteToFile(4000);
						 }
					}
			}
		});
		btnNewButton_1.setBounds(315, 278, 117, 29);
		contentPane.add(btnNewButton_1);
		
		JCheckBox Box1 = new JCheckBox("갈릭디핑소스 +500");
		Box1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					op1 = 1;
					
				}else {
					op1 = 0;
				}
				}
		});
		Box1.setBounds(243, 149, 143, 23);
		contentPane.add(Box1);
		
		JCheckBox Box2 = new JCheckBox("콜라 1.25L +1500");
		Box2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					op2 = 1;
					
				}else {
					op2 = 0;
				}
				}
		});
		Box2.setBounds(243, 173, 143, 23);
		contentPane.add(Box2);
		
		JCheckBox Box3 = new JCheckBox("스파게티 +4000");
		Box3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					op3 = 1;
					
				}else {
					op3 = 0;
				}
				}
		});
		Box3.setBounds(243, 199, 128, 23);
		contentPane.add(Box3);
	}
}

