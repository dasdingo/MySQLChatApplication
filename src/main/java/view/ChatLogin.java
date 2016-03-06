package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.ChatController;

public class ChatLogin extends JFrame{

	JLabel label_userName =  new JLabel("Login Name:");
	JTextField textfield_userName;
	JButton button_loginButton = new JButton("Login");
	String disp = "";
	public ChatLogin()
	{
		super("Login");
		//2. Optional: What happens when the frame closes?
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(600, 600));
		
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		textfield_userName = new JTextField("Schmittwilken");
		container.add(label_userName);
		container.add(textfield_userName);
		button_loginButton.addActionListener(new ActionListener()
				{

					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						ChatUserList chatUserList = new ChatUserList(textfield_userName.getText());
						
					}
			
				});
		container.add(button_loginButton);
		//jtfText1.addActionListener(handler);
		//jtfUneditableText.addActionListener(handler);
		
		setSize(325, 100);
		setVisible(true);
	}
}
