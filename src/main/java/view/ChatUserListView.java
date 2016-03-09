package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import model.DBUser;
import model.DBUserService;

public class ChatUserListView extends JFrame {

	private JPanel contentPane;
	private JList jListUserlist;
	private JLabel lblUserlist = new JLabel("Userlist");
	private JScrollPane scrollPane;
	private JButton btnStartChatButton;

	/**
	 * Create the frame.
	 */
	public ChatUserListView() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		btnStartChatButton = new JButton("Chat");
		btnStartChatButton.setFont(new Font("Tahoma", Font.PLAIN, 64));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 167,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(btnStartChatButton, GroupLayout.DEFAULT_SIZE, 219,
												Short.MAX_VALUE))
								.addComponent(lblUserlist))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_contentPane.createSequentialGroup().addContainerGap().addComponent(lblUserlist).addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnStartChatButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197,
										Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197,
										Short.MAX_VALUE))
						.addContainerGap()));

		jListUserlist = new JList();
		scrollPane.setViewportView(jListUserlist);
		contentPane.setLayout(gl_contentPane);
	}

	public void setActionListenerbtnStartChatButton(ActionListener actionListener) {
		btnStartChatButton.addActionListener(actionListener);
	}

	public void setJListUserlist(Object[] listData) {
		jListUserlist.setListData(listData);
	}

	public String getJListUserListSelectedValue() {
		return jListUserlist.getSelectedValue().toString();
	}
}
