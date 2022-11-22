package asteroidsGame;


import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import java.awt.Color;
import java.awt.Graphics;

public class Asteroid {
	public double x,y;    // current position of ‘this’ asteroid
	public double dx,dy;	// displacement (delta) dx & dy for next position
	public int size = 20;   // initial size 20 pixels
	
	public Asteroid (double ix, double iy, double idx, double idy) { // constructor
		x=ix; y=iy; dx=idx; dy=idy;
	} 
	
	public void move() {	//move to next position
		x+=dx; y+=dy;
	}
	
	public void paint(Graphics g) {  // paint asteroid as a blue circle
		g.setColor(Color.blue);
		g.drawOval((int) x, (int) y, size, size);   // set size and coordinates
	}
	
	public void hit ( ) {  // shrink size when hit
		size = size - 4; 
	}
	
	public boolean nearTo(double tx, double ty) {  // use Pythagorean theorem to determine distance between points
		double distance = Math.sqrt( (x - tx) * (x - tx) + (y - ty) * (y - ty) );
		return distance <10;
		
		// Is (tx, ty) too close to ‘this’? 
		// x, y are coordinates of ‘this’
		// tx, ty are ‘other sender’ coords
	}
	
	
	// find the potential hits


}
