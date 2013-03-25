package com.reactplane.gui;

import TUIO.TuioClient;

public class Reactplane {
	/**
	 * Instantiates a new start.
	 */
	private Reactplane() {

	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// create a game window
		GameWindow game = GameWindow.getInstance();

		TuioClient client = null;

		switch (args.length) {
			case 1:
				try { 
					client = new TuioClient( Integer.parseInt(args[0])); 
				} catch (Exception e) {
					System.out.println("usage: java reactable_prototype [port]");
					System.exit(0);
				}
				break;
			case 0:
				client = new TuioClient();
				break;
			default: 
				System.out.println("usage: java reactable_prototype [port]");
				System.exit(0);
				break;
		}

		if (client!=null) {
			client.addTuioListener(game.getTuioInputListener());
			client.connect();
		} else {
			System.out.println("usage: java reactable_prototype [port]");
			System.exit(0);
		}

		// and make it visible
		game.setVisible(true);

	}
}
