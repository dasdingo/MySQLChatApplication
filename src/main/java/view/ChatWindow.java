package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import controller.ChatController;
import model.ConversationReply;

public class ChatWindow extends JFrame {
    JButton sendMessage = new JButton("send Message");;
    JTextField messageBox;
    JTextArea chatBox;
    ChatController controller;
    String chatText = "";
    public final static int INTERVAL = 200;
	public ChatWindow(String dbUserSender, final String dbUserReceiver)
	{
		super("Chat Window");
		controller = new ChatController(dbUserSender, dbUserReceiver);
		//2. Optional: What happens when the frame closes?
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(600, 600));
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		chatBox = new JTextArea();
		chatBox.setMinimumSize(new Dimension(300, 300));
		chatBox.setPreferredSize(new Dimension(300, 300));
		List<ConversationReply> messages = controller.getAllMessages(dbUserReceiver);
		
		
		for(ConversationReply conv : messages)
		{
			System.out.println(conv.get_reply());
			chatText += ""+ conv.get_user_id_fk()+ ": " + conv.get_reply() + "\n";
		}
		chatBox.setText(chatText);
		chatBox.setEditable(false);
		container.add(chatBox);
		messageBox = new JTextField(10);
		container.add(chatBox);
		container.add(messageBox);
		sendMessage.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) {
						controller.sendMessage(messageBox.getText());
						
						
					}
			
				});
		container.add(sendMessage);
		setSize(325, 100);
		setVisible(true);
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				List<ConversationReply> newMessages = controller.getNewMessages(dbUserReceiver);
				
				
				for(ConversationReply conv : newMessages)
				{
					chatText += ""+ conv.get_user_id_fk()+ ": " + conv.get_reply() + "\n";
				}
				chatBox.setText(chatText);
			    }  
			
			});

			timer.start();
			
		
	}
	
	
}
