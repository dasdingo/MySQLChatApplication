package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChatLoginView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLoginUserName;
	private JLabel lblUserName = new JLabel("Username: ");
	private JButton btnLoginButton = new JButton("Login");
	/**
	 * Create the frame.
	 */
	public ChatLoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		textFieldLoginUserName = new JTextField();
		textFieldLoginUserName.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(64)
					.addComponent(lblUserName)
					.addGap(18)
					.addComponent(textFieldLoginUserName, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLoginButton)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(106)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoginButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textFieldLoginUserName)
						.addComponent(lblUserName))
					.addGap(122))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setActionListenerbtnLoginButton(ActionListener actionListener)
	{
		btnLoginButton.addActionListener(actionListener);
	}
	
	public String getTextfieldLoginUserName()
	{
		return textFieldLoginUserName.getText();
	}
}
