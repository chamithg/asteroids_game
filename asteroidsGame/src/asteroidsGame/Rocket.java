package asteroidsGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Rocket {
	
	public double x,y;	// current position coordinates
	public double dx,dy;	// idx & idy computed from canon direction
	
	public Rocket (double ix, double iy, double idx, double idy) { // constructor 
		x=ix; y=iy; dx=idx; dy=idy;
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
		g.setColor(Color.red);		// draw self: red circle inside
		g.fillOval((int) x, (int) y, 5, 5);		// a 5 by 5 bounding rectangle
	}											// (x, y) is the upper left corner
}
