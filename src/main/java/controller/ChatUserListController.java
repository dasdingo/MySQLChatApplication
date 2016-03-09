package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

import model.DBUser;
import model.DBUserService;
import view.ChatUserListView;
import view.ChatWindowView;

public class ChatUserListController {

	private ChatUserListView _view;
	private String dBUserSender;
	private DBUserService dBUserservice;
	

	public ChatUserListController(ChatUserListView view, String dBUserSender)
	{
	this._view = view;	
	this.dBUserSender = dBUserSender;
	initActionListeners();
	initUIValues();
	this._view.setVisible(true);
	}
	
	
	public void initActionListeners()
	{
	_view.setActionListenerbtnStartChatButton(actionListenerbtnStartChatButton);	
	}
	
	public void initUIValues()
	{
		List<DBUser> dBUserList = new ArrayList<DBUser>();
		dBUserservice = new DBUserService();
		dBUserList = dBUserservice.findAll();
		_view.setJListUserlist(dBUserList.toArray());
	}

	
	ActionListener actionListenerbtnStartChatButton = new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
						String dBUserReceiver = _view.getJListUserListSelectedValue();
						ChatWindowController controller = new ChatWindowController(new ChatWindowView(),dBUserSender, dBUserReceiver);
						
			
		}
		
	};
}
