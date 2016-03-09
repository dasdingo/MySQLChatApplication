package main;

import javax.swing.SwingUtilities;

import controller.ChatLoginController;
import view.ChatLoginView;

public class App {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		      public void run() {
		    	  ChatLoginController chatlogin =   new ChatLoginController(new ChatLoginView());
		      }
		    });
		
		
	}

}
