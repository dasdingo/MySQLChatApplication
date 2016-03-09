package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

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
import javax.swing.border.EmptyBorder;

public class ChatWindowView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea = new JTextArea();
	private JScrollPane scrollPane;
	private JButton btnSend = new JButton("Send");

	/**
	 * Create the frame.
	 */
	public ChatWindowView() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textField = new JTextField();
		textField.setColumns(10);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(scrollPane, Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, 323,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(btnSend, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)))
								.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(textField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSend))));

		textArea.setLineWrap(true);
		textArea.setEditable(false);

		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
	}

	public void AppendjTextAreaTextArea(String text) {
		textArea.append(text);
	}

	public void setActionListenerSend(ActionListener actionListener) {
		btnSend.addActionListener(actionListener);
	}

	public void setKeyListenerSend(KeyListener keyListener) {
		textField.addKeyListener(keyListener);
	}

	public String getjTextFieldTextFieldText() {
		return textField.getText();
	}
}
