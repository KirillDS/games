package brickBreaker;

//Necessary imports
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	//Global variables
	public int mapArray[][];
	public int brickWidth;
	public int brickHeight;
	
	//Used to generate the bricks with the map
	public MapGenerator(int row, int col) {
		mapArray = new int[row][col];
		for(int i = 0; i < mapArray.length; ++i) {
			for(int j=0; j<mapArray[0].length;++j) {
				mapArray[i][j] = 1;
			}
		}
		brickWidth = 540/col;
		brickHeight = 150/row;	
	}
	//Creates the bricks - size, color and position
	public void draw(Graphics2D graphic) {
		for(int i = 0; i<mapArray.length; ++i) {
			for(int j=0;j<mapArray[0].length;++j) {
				if(mapArray[i][j] > 0) {
					graphic.setColor(Color.yellow);
					graphic.fillRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
					
					graphic.setStroke(new BasicStroke(3));
					graphic.setColor(Color.darkGray);
					graphic.drawRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight);
				}
			}
		}
	}
	//Setting the value for the bricks
	public void setBrickValue(int value, int row, int col) {
		mapArray[row][col] = value;
	}

}
