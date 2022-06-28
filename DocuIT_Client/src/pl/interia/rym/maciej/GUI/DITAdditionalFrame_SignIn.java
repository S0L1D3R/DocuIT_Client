package pl.interia.rym.maciej.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import pl.interia.rym.maciej.DITClientApp;

public class DITAdditionalFrame_SignIn extends JFrame {
	private JTextField textField;
	private JTextField textField_1;

	public DITAdditionalFrame_SignIn() {
		JFrame thisFrame = this;
		setTitle("DocuIT - panel logowania");
		setBounds(0, 0, 500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setText(DITClientApp.getOptions().getUserName());
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setText(DITClientApp.getOptions().getPassword());
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Anuluj");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnNewButton);
		
		JButton btnZaloguj = new JButton("Zaloguj");
		btnZaloguj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DITClientApp.connection.sendAuthenticationRequest();
				//Waits 5 seconds for connection with server!
				for (int i = 0; i < 5; i++) {
					try {
						if(DITClientApp.connection.getListener().isAuthenticated()) {
							System.out.println("Authenticated!");
							JOptionPane.showMessageDialog(thisFrame, "User Authenticated");
							dispose();
							return;
						}
						System.out.println("Not authenticated " + (i+1) + "x");
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				System.exit(0);
			}
		});
		getContentPane().add(btnZaloguj);
		
		
		
		
		setVisible(true);
	}
	
}
