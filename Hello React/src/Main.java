import TUIO.TuioClient;

/**
 * Short example Application for using the TUIO.  A new TuioClient is created and
 * a TuioListener is added to the Client.
 * 
 * @author Simon Nestler
 *
 */
public class Main {

	public static void main(String[] args) {		
		Application app = new Application("TUIO Beispiel");
		TuioClient client = new TuioClient();
		client.addTuioListener(app);
		client.connect();
	}

}
