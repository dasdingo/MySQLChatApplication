package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import controller.ChatController;
import model.DBUser;
import model.DBUserService;

public class ChatUserListView extends JFrame{

	JLabel label_userList =  new JLabel("User Chatlist:");
	JList jList_userNames;
	JButton button_startChat = new JButton("Start Chat");
	String disp = "";
	DBUserService dBUserservice;

	public ChatUserListView(final String dBUserSender)
	{
		super("Userlist");
		this.setMinimumSize(new Dimension(600, 600));
		
		List<DBUser> dBUserList = new ArrayList<DBUser>();
		dBUserservice = new DBUserService();
		//2. Optional: What happens when the frame closes?
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		dBUserList = dBUserservice.findAll();
		jList_userNames = new JList(dBUserList.toArray());
		container.add(label_userList);
		container.add(jList_userNames);
		button_startChat.addActionListener(new ActionListener()
				{

					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String dBUserReceiver = jList_userNames.getSelectedValue().toString();
						
						ChatWindowByGuiBuilderView window = new ChatWindowByGuiBuilderView(dBUserSender, dBUserReceiver);
					}
			
				});
		container.add(button_startChat);
		//jtfText1.addActionListener(handler);
		//jtfUneditableText.addActionListener(handler);
		this.pack();
		this.setLocationRelativeTo (null);
		setSize(325, 100);
		setVisible(true);
	}
}
