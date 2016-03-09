package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingUtilities;

import model.DBUser;
import model.DBUserService;
import view.ChatUserListView;
import view.ChatWindowView;

/**
 * @author dave
 *
 */
public class ChatUserListController {

	private ChatUserListView _view;
	private String dBUserSender;
	private DBUserService dBUserservice;
	private List<DBUser> dBUserList;
	private SearchForUserListWorker worker;

	public ChatUserListController(ChatUserListView view, String dBUserSender) {
		dBUserList = new ArrayList<DBUser>();
		dBUserservice = new DBUserService();
		worker = new SearchForUserListWorker(dBUserList, dBUserservice);
		worker.addPropertyChangeListener(listener);
		worker.execute();
		// dBUserList = dBUserservice.findAll();
		this._view = view;
		this.dBUserSender = dBUserSender;
		initActionListeners();
		this._view.setVisible(true);
		initUIValues();
	}

	public void initActionListeners() {
		_view.setActionListenerbtnStartChatButton(actionListenerbtnStartChatButton);
	}

	public void initUIValues() {

	}
 /*DB Query for getting Userlist in Background*/
	PropertyChangeListener listener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent event) {

			if (worker.isDone()) {
				try {
					dBUserList = worker.get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				_view.setJListUserlist(dBUserList.toArray());
			}
		}
	};
	
	ActionListener actionListenerbtnStartChatButton = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			final String dBUserReceiver = _view.getJListUserListSelectedValue();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					ChatWindowController controller = new ChatWindowController(new ChatWindowView(), dBUserSender,
							dBUserReceiver);
				}
			});

		}

	};
}
