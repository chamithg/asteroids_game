package asteroidsGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Station {
	
	public Station (double ix, double iy) { 
		x = ix; y = iy; 
	}
	
	private double angle = Math.PI / 2.0; 	// public static final double PI 3.141592653589793d 
	private int hits = 0; 					// So, angle is initialized to 90 degrees
	private final double x;
	private final double y;
	
	
	public void moveLeft( ) { 
		angle = angle + 0.1; 
	} 
	public void moveRight( ) { 
		angle = angle - 0.1; 
	}
	
	public void fire (ArrayList <Rocket>rockets) { 
		double cosAngle = Math.cos(angle);
		double sinAngle = Math.sin(angle);
		// rocket goes same direction as gun is pointing
		// length of Rocket Launcher is 20; size of rocket is 5; (20 – 5 = 15)
		Rocket r = new Rocket(x + 15 * cosAngle, y - 15 * sinAngle, 5 * cosAngle, - 5 * sinAngle);
		
		rockets.add(r);
	}
	
	// check if asteroid (rock) hit me (station) then hits is incremented by asteroid size 
	public void checkHit (Asteroid rock) {
		if (rock.nearTo((double) x, (double) y)) {
			hits += rock.size;
		}
			
	}
	
	public void paint (Graphics g) {
		// paint rocket launcher (length 20 pixels)
		g.setColor (Color.red);
		double lv = 20 * Math.sin(angle); 
		double lh = 20 * Math.cos(angle);
		// launcher tip vertical coordinate 
		// launcher tip horizontal coordinate
		// (x, y) is launcher base, (x+lh, y-lv) is tip of launcher
		g.drawLine((int) x, (int) y, (int) (x + lh), (int) (y - lv));
		// display updated score
		g.drawString("hits: " + hits, (int) (x + 10), (int) (y - 5)); 
	}


}
