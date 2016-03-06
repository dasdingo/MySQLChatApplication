package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import controller.ChatController;
import model.ConversationReply;

public class ChatWindowByGuiBuilderView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ChatController controller;
	private JTextArea textArea = new JTextArea();
	String chatText = "";
	public final static int INTERVAL = 3000;
	/**
	 * Create the frame.
	 */
	public ChatWindowByGuiBuilderView(String dbUserSender, final String dbUserReceiver) {
		
		controller = new ChatController(dbUserSender, dbUserReceiver);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				controller.sendMessage(textField.getText());
				
				
			}
	
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSend, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSend)))
		);
		
		
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
List<ConversationReply> messages = controller.getAllMessages(dbUserReceiver);
		
		
		for(ConversationReply conv : messages)
		{
			System.out.println(conv.get_reply());
			chatText += ""+ conv.get_user_id_fk()+ "("+ (int)(conv.getTime() % 1000000)+"): " + conv.get_reply() + "\n";
		}
		textArea.setText(chatText);
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
		this.setVisible(true);
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				List<ConversationReply> newMessages = controller.getNewMessages(dbUserReceiver);
				
				
				for(ConversationReply conv : newMessages)
				{
					chatText += ""+ conv.get_user_id_fk()+ ": " + conv.get_reply() + "\n";
				}
				
				textArea.setText(chatText);
				//scrollpane_chatbox.add(chatBox);
			    }  
			
			});

			timer.start();
	}
}
