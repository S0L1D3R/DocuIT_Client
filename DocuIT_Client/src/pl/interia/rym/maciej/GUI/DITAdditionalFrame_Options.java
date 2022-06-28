package pl.interia.rym.maciej.GUI;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import pl.interia.rym.maciej.DITClientOptions;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DITAdditionalFrame_Options extends JFrame {
	private JLabel lblNewLabel;
	private JTextField txtTest;
	private JLabel lblNewLabel_1;
	private JTextField txtTest_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtTest_2;
	private JTextField txtTest_3;
	private JButton btnNewButton;
	DITClientOptions options;
	private JLabel lblNewLabel_4;
	private JTextField textField;
	private JButton btnNewButton_1;

	public DITAdditionalFrame_Options(DITClientOptions optionsToBeLoaded, int x, int y) {
		options = optionsToBeLoaded;
		setTitle("DocuIT - opcje klienta");
		setBounds(x, y, 500, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(6, 0, 0, 0));
		
		lblNewLabel_1 = new JLabel("Adres ip serwera");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_1);
		
		txtTest = new JTextField(options.getServerIP());
		txtTest.setToolTipText("");
		getContentPane().add(txtTest);
		txtTest.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Port serwera");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_2);
		
		txtTest_1 = new JTextField(Integer.toString(options.getPort()));
		txtTest_1.setToolTipText("");
		getContentPane().add(txtTest_1);
		txtTest_1.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Folder recznego zapisu projektow");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_3);
		
		txtTest_2 = new JTextField(options.getBackupFolder());
		txtTest_2.setToolTipText("");
		getContentPane().add(txtTest_2);
		txtTest_2.setColumns(10);
		
		lblNewLabel = new JLabel("Nazwa u\u017Cytkownika");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel);
		
		txtTest_3 = new JTextField();
		txtTest_3.setText(options.getUserName());
		getContentPane().add(txtTest_3);
		txtTest_3.setColumns(10);
		
		btnNewButton = new JButton("Zapisz zmiany");
		btnNewButton.addActionListener(createSaveButtonListerner(this));
		
		lblNewLabel_4 = new JLabel("Has\u0142o_Mas\u0142o");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setText(options.getPassword());
		textField.setColumns(10);
		getContentPane().add(textField);
		getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Wyjdz");
		btnNewButton_1.addActionListener(createCancelButtonListener(this));
		getContentPane().add(btnNewButton_1);
		setVisible(true);
	}
	
	private ActionListener createCancelButtonListener(DITAdditionalFrame_Options frameToClose) {
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frameToClose.dispose();
				frameToClose.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		};
		return listener;
	}
	
	private ActionListener createSaveButtonListerner(DITAdditionalFrame_Options frameToClose) {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				options.setServerIP(txtTest.getText());
				options.setPort(Integer.parseInt(txtTest_1.getText()));
				options.setBackupFolder(txtTest_2.getText());
				options.setUserName(txtTest_3.getText());
				options.setPassword(textField.getText());
				options.saveData();
				frameToClose.dispose();
			}
		};
		return listener;
	}
	
}
