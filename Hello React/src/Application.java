import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import TUIO.TuioCursor;
import TUIO.TuioListener;
import TUIO.TuioObject;
import TUIO.TuioTime;

/**
 * Application implements the TuioListener and reacts on TUIO events. It automatically connects to a local TuioSimulator via port 3333. 
 * When objects are moved in the Simulator, short Console messages are shown. Only listeners for Object Movements are implemented.
 * 
 * @author Simon Nestler
 *
 */
public class Application extends JFrame implements TuioListener  {

	private static final long serialVersionUID = 1L;
	private JScrollPane consolePane;
	private JTextArea console;

	public Application(String titel) {
		super(titel);

		console = new JTextArea();
		consolePane = new JScrollPane(console);
		add(consolePane);
		setSize(640, 480);
		
		System.setOut(new PrintStream(new Console(console)));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	@Override
	public void addTuioCursor(TuioCursor arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void addTuioObject(TuioObject arg0) {
		System.out.println("Objekt Nummer " + arg0.getSessionID() + " erscheint an (" + arg0.getX() + ", " + arg0.getY() + ")");
	}

	@Override
	public void refresh(TuioTime arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTuioCursor(TuioCursor arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTuioObject(TuioObject arg0) {
		System.out.println("Objekt Nummer " + arg0.getSessionID() + " wurde entfernt von (" + arg0.getX() + ", " + arg0.getY() + ")");
	}

	@Override
	public void updateTuioCursor(TuioCursor arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTuioObject(TuioObject arg0) {
		System.out.println("Objekt Nummer " + arg0.getSessionID() + " wurde bewegt nach (" + arg0.getX() + ", " + arg0.getY() + ")");
	}
	
}
