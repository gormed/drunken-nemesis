/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * arkaneud Project (c) 2013 by Hans Ferchland
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * 
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * GNU Public License
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License 3 as published by
 * the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * Email me for any questions: hans.ferchland[at]gmx.de
 *
 * Project: arkaneud
 * File: Start.java
 * Type: Start
 *
 * Documentation created: 10.03.2013 - 14:02:44 by Hans Ferchland
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package gui;

import TUIO.TuioClient;

/**
 * The Class Start contains the main method.
 */
public class StartPrototype {

	/**
	 * Instantiates a new start.
	 */
	private StartPrototype() {

	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// create a game window
		GameWindow game = new GameWindow();
		
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