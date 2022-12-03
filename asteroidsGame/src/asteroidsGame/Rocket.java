package asteroidsGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

public class Rocket {
	
	public double x,y;	// current position coordinates
	public double dx,dy;	// idx & idy computed from canon direction
	
	public Rocket (double ix, double iy, double idx, double idy) { // constructor 
		x=ix; y=iy; dx=idx; dy=idy;
	}
	
	public boolean checkInFrame() {   // check if the rocket is in the frame (width 500, height 400)
		return (y < 0 || x > 500 || x < 0);
	}
	
	
	public void move ( ArrayList<Asteroid> asteroids ) {
		x+= dx; y+= dy;		// move ‘this’ rocket
		//TODO  << start with the beginning of the list of asteroids >>
		int rockIndex = 0;
		while(asteroids.size()>0 && rockIndex < asteroids.size()) {
			Asteroid rock = (Asteroid)asteroids.get(rockIndex);
			if (rock.nearTo(x, y)) {
				rock.hit( );  // hit if too close
			}
			
			rockIndex +=1;
			
		}
		
		
	}
	
	public void paint(Graphics g) {
		Toolkit tool=Toolkit.getDefaultToolkit();
		Image img=tool.getImage("rocket.png");
		g.drawImage(img,(int) x, (int) y, 8, 8,null);
		
//		g.setColor(Color.red);		// draw self: red circle inside
//		g.fillOval((int) x, (int) y, 5, 5);		// a 5 by 5 bounding rectangle
	}											// (x, y) is the upper left corner
}
