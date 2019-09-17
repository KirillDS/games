package brickBreaker;

//Necessary imports for the class
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	/*
	 * Unique identifier for classes
	 * Generated via default
	 */
	private static final long serialVersionUID = 1L;
	/* play 		  = All movements
	 * playerScore	  = Tracks player's score
	 * totalBrick 	  = Displays the amount of bricks, links to playerScore
	 * time 		  = tracks the game duration
	 * delay 		  = ball movement speed
	 * playerX 		  = The player's X-axis
	 * ballPositionX  = Ball's X-axis position
	 * ballPositionY  = Ball's Y-axis position
	 * ballDirectionY = Ball's direction for the Y-axis
	 * ballDirectionX = Ball's direction for the X-axis
	 * */
	private boolean play = false;
	private int playerScore = 0;
	private int totalBricks = 64;
	private Timer time;
	private int delay = 5;
	private int playerX = 310;
	private int ballPositionX = 120;
	private int ballPositionY = 350;
	private int ballDirectionY = -1;
	private int ballDirectionX = -2;
	private MapGenerator map;
	
	//This method is used/called within the Main class
	public Gameplay() {
		map = new MapGenerator(8, 8);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
	}
	//Designs the graphic visuals for the game
	public void paint(Graphics graphic) {
		//Background
		graphic.setColor(Color.darkGray);
		graphic.fillRect(1, 1, 700, 600);
		
		//Drawing map
		map.draw((Graphics2D)graphic);
		
		//Borders
		graphic.setColor(Color.darkGray);
		graphic.fillRect(0, 0, 3, 592);
		graphic.fillRect(6, 9, 692, 3);
		graphic.fillRect(691, 0, 3, 592);
		
		//Score/Score board
		graphic.setColor(Color.white);
		graphic.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		graphic.drawString("Score: "+playerScore, 579, 30);
		
		//Player platform
		graphic.setColor(Color.cyan);
		graphic.fillRect(playerX, 550, 100, 8);
		
		//Ball
		graphic.setColor(Color.orange);
		graphic.fillOval(ballPositionX, ballPositionY, 16, 16);
		
		if((totalBricks == 0) && (playerScore == 64)) {
			play = false;
			ballDirectionX = 0;
			ballDirectionY = 0;
			graphic.setColor(Color.green);
			graphic.setFont(new Font("Lucida Grande", Font.BOLD, 30));
			graphic.drawString("You Won", 296, 250);
			
			graphic.setColor(Color.green);
			graphic.setFont(new Font("Lucida Grande", Font.BOLD, 27));
			graphic.drawString("Your Score: "+playerScore, 275, 300);
			
			graphic.setColor(Color.green);
			graphic.setFont(new Font("Lucida Grande", Font.BOLD, 23));
			graphic.drawString("Press the 'Return' key to Restart",182, 350);
			
			graphic.setColor(Color.green);
			graphic.setFont(new Font("Lucida Grande", Font.BOLD, 23));
			graphic.drawString("Press '2' for Next Level",200, 400);
		}
		if((totalBricks == 0) && (playerScore == 145)) {
			play = false;
			ballDirectionX = 0;
			ballDirectionY = 0;
			graphic.setColor(Color.green);
			graphic.setFont(new Font("Lucida Grande", Font.BOLD, 30));
			graphic.drawString("You Won", 296, 250);
			
			graphic.setColor(Color.green);
			graphic.setFont(new Font("Lucida Grande", Font.BOLD, 27));
			graphic.drawString("Your Score: "+playerScore, 275, 300);
			
			graphic.setColor(Color.green);
			graphic.setFont(new Font("Lucida Grande", Font.BOLD, 23));
			graphic.drawString("Press the 'Return' key to Restart",182, 350);
			
			graphic.setColor(Color.green);
			graphic.setFont(new Font("Lucida Grande", Font.BOLD, 23));
			graphic.drawString("Press '3' for Next Level",200, 400);
		}
		if((totalBricks <= 0) && (playerScore == 245)) {
			play = false;
			ballDirectionX = 0;
			ballDirectionY = 0;
			graphic.setColor(Color.green);
			graphic.setFont(new Font("Lucida Grande", Font.BOLD, 30));
			graphic.drawString("You Complete the Game!", 296, 300);
			
			graphic.setColor(Color.green);
			graphic.setFont(new Font("Lucida Grande", Font.BOLD, 27));
			graphic.drawString("Your Score: "+playerScore, 275, 350);
			
			graphic.setColor(Color.green);
			graphic.setFont(new Font("Lucida Grande", Font.BOLD, 23));
			graphic.drawString("Press 'N' key to Start Again",182, 400);
		}
		if(ballPositionY > 570) {
			play = false;
			ballDirectionX = 0;
			ballDirectionY = 0;
			graphic.setColor(Color.red);
			graphic.setFont(new Font("Lucida Grande", Font.BOLD, 30));
			graphic.drawString("Game Over", 280, 300);
			
			graphic.setColor(Color.red);
			graphic.setFont(new Font("Lucida Grande", Font.BOLD, 27));
			graphic.drawString("Your Score: "+playerScore, 275, 350);
			
			graphic.setColor(Color.red);
			graphic.setFont(new Font("Lucida Grande", Font.BOLD, 23));
			graphic.drawString("Press the 'Return' key to Restart",	190, 400);
		}
		//Gets rid of all the graphic objects 
		graphic.dispose();
	}
	//The main interaction between the ball, player, bricks, also starts the timer and player score
	public void actionPerformed(ActionEvent e) {
		time.start();
		if(play) {
			if(new Rectangle(ballPositionX, ballPositionY, 18, 18).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballDirectionY = -ballDirectionY;
			}
			A: for(int i = 0; i<map.mapArray.length; ++i) {
				for(int j = 0; j<map.mapArray[0].length; ++j) {
					if(map.mapArray[i][j] > 0) {
						int brickX = j*map.brickWidth + 80;
						int brickY = i*map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						//Variable that is used for the intersection between the bricks and the ball.
						Rectangle rec = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRec = new Rectangle(ballPositionX, ballPositionY, 16, 16);
						Rectangle brickRec = rec;
						
						//What happens if ball intersects a brick.
						if(ballRec.intersects(brickRec)) {
							map.setBrickValue(0, i, j);
							--totalBricks;
							playerScore += 1;
							
							if(ballPositionX + 19 <= brickRec.x || ballPositionX + 1 >= brickRec.x + brickRec.width) {
								ballDirectionX = -ballDirectionX;
							}
							else {
								ballDirectionY = -ballDirectionY;
							}
							/* Takes the complier out of the loop and bring it to the beginning,
							 * takes the program to the beginning of this for loop marked with 'A:'*/
							break A;
						}
					}
				}
			}
			//The position of the ball increments
			ballPositionX += ballDirectionX;
			ballPositionY += ballDirectionY;
			//For the Left border
			if(ballPositionX < 0) {
				ballDirectionX = -ballDirectionX;
			}
			//For the Top border
			if(ballPositionY < 0) {
				ballDirectionY = -ballDirectionY;
			}
			//For the Right border
			if(ballPositionX > 670) {
				ballDirectionX = -ballDirectionX;
			}
		}
		repaint();
	}
	//Not used
	public void keyTyped(KeyEvent e) {}

	//Method used to create commands via pressing keys.
	public void keyPressed(KeyEvent e) {
		//Restricts the player movement to the right.
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600) {
				playerX = 600;
			}
			else {
				moveRight();
			}
		}
		//Restricts the player movement to the left.
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX = 10;
			}
			else {
				moveLeft();
			}
		}
		//Restarts the game if the 'Return' key is pressed
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballPositionX = 100;
				ballPositionY = 300;
				ballDirectionY = -1;
				ballDirectionX = -2;
				playerX = 310;
				playerScore = 0;
				totalBricks = 64;
				map = new MapGenerator(8,8);
				
				repaint();
			}
		}
		//Level 2 Generator
		if(e.getKeyCode() == KeyEvent.VK_2) {
			if(!play && playerScore == 64) {
				play = true;
				ballPositionX = 120;
				ballPositionY = 340;
				ballDirectionY = -1;
				ballDirectionX = -2;
				playerX = 310;
				playerScore = 64;
				totalBricks = 81;
				map = new MapGenerator(9,9);
				
				repaint();
			}
		}
		//Level 3 Generator
		if(e.getKeyCode() == KeyEvent.VK_3) {
			if(!play && playerScore == 145) {
				play = true;
				ballPositionX = 90;
				ballPositionY = 60;
				ballDirectionY = -1;
				ballDirectionX = -2;
				playerX = 310;
				playerScore = 145;
				totalBricks = 100;
				map = new MapGenerator(10,10);
				
				repaint();
			}
		}
		//Pause function
		if(e.getKeyCode() == KeyEvent.VK_P) {
			play = false;
		}
	}
	//Speed of the Player's platform to the Right
	public void moveRight() {
		play = true;
		playerX += 27;
	}
	//Speed of the Player's platform to the Left
	public void moveLeft() {
		play = true;
		playerX -= 27;
	}
	//Not used
	public void keyReleased(KeyEvent e) {}

}
