
import javax.swing.JFrame;



public class Carte extends JFrame {
	
	public Carte() {
		setBounds(10, 10, 800, 800);
		
		Monde monde = new Monde ();
		monde.charger();
		Panneau panneau = new Panneau (monde.getVilles());
		getContentPane().add(panneau);
		setVisible(true);
		
		
		
	}

}
