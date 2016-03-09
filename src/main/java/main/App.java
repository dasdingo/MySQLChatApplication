package main;

import controller.ChatLoginController;
import view.ChatLoginView;

public class App {

	public static void main(String[] args) {

		ChatLoginController chatlogin = new ChatLoginController(new ChatLoginView());
	}

}
