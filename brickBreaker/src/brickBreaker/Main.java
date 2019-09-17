package brickBreaker;

//Necessary imports
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

//By Kirill David Sorokin
	
	/* The program runs from this Main class - 
	 * the Gameplay and MapGenerator classes are called into this
	 * class through the 'myFrame.add(gamePlay)',
	 * which was enabled via defining a new variable with the use of an extension;
	 * 'Gameplay gamePlay = new Gameplay();'.*/ 
	/* The class; apart from calling other classes, the main frame for the game
	 * is designed here and the properties were implemented here.*/
	/* Press 'P' to pause and Arrow keys to continue */
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, 
				"----------Brick Buster----------\nLeft Arrow Key      "
				+": Move Left/Unpause\nRight Arrow Key    : Move Right/Unpause\n"
				+"'P' Key to Pause");
		JFrame myFrame = new JFrame();
		Gameplay gamePlay = new Gameplay();
		myFrame.setBounds(10, 10, 700, 600);
		myFrame.add(gamePlay);
		myFrame.setTitle("Brick Breaker");
		myFrame.setResizable(false);
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
