package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.Timer;

import model.ConversationReply;
import view.ChatWindowView;

public class ChatWindowController {

	private ChatWindowView _view;
	private ChatController controller;
	private String dbUserSender;
	private String dbUserReceiver;
	public final static int INTERVAL = 300;

	public ChatWindowController(ChatWindowView view, String dbUserSender, final String dbUserReceiver) {
		this.dbUserSender = dbUserSender;
		this.dbUserReceiver = dbUserReceiver;
		controller = new ChatController(dbUserSender, dbUserReceiver);
		this._view = view;
		initActionListeners();
		_view.setVisible(true);

		/*TODO: Replace Timer with long polling by Server */
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				List<ConversationReply> newMessages = controller.getNewMessages(dbUserReceiver);

				for (ConversationReply conv : newMessages) {
					_view.AppendjTextAreaTextArea("" + conv.get_user_id_fk() + "(" + (int) (conv.getTime() % 1000000)
							+ "): " + conv.get_reply() + "\n");
				}
			}

		});

		timer.start();

	}

	public void initActionListeners() {
		_view.setActionListenerSend(actionListenerbtnSend);
		_view.setKeyListenerSend(keyListenerSend);
	}

	public void initUIValues() {

	}

	ActionListener actionListenerbtnSend = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			controller.sendMessage(_view.getjTextFieldTextFieldText());

		}

	};

	KeyListener keyListenerSend = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				controller.sendMessage(_view.getjTextFieldTextFieldText());
			}
		}
	};

}
