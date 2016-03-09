package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ChatLoginView;
import view.ChatUserListView;

public class ChatLoginController {

	private ChatLoginView _view;
	
	public ChatLoginController(ChatLoginView view)
	{
		this._view = view;
		initActionListeners();
		this._view.setVisible(true);
	}
	
	public void initActionListeners()
	{ 
		this._view.setActionListenerbtnLoginButton(actionListenerbtnLoginButton);	
	}
	
	ActionListener actionListenerbtnLoginButton = new ActionListener()
	{

		public void actionPerformed(ActionEvent arg0) {
			/*TODO add condition where login fails - if connection refuses or not possible at all*/
			if(true)
			{
				ChatUserListController chatUserList = new ChatUserListController(new ChatUserListView(), _view.getTextfieldLoginUserName());
			_view.dispose();
			}
		}

	};
}
