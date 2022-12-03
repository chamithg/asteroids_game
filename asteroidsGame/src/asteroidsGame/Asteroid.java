package asteroidsGame;


import java.awt.*;
import java.awt.Graphics;

public class Asteroid {
	public double x,y;    // current position of ‘this’ asteroid
	public double dx,dy;	// displacement (delta) dx & dy for next position
	public int size = 20;   // initial size 20 pixels
	
	// holds data if hit the station or not
	public boolean hitStation = false;
	
	public Asteroid (double ix, double iy, double idx, double idy) { // constructor
		x=ix; y=iy; dx=idx; dy=idy;
	} 
	
	public void move() {	//move to next position
		x+=dx; y+=dy;
	}
	
	public boolean checkInFrame() {   // check if the asteroid is in the frame (width 500, height 400)
		return (y > 400 || x > 500 || x < 0);
	}
	
	public void paint(Graphics g) {  
		
		// draw asteroid image
		Toolkit tool=Toolkit.getDefaultToolkit();
		Image img=tool.getImage("asteroid.png");  
		g.drawImage(img,(int) x, (int) y, size, size,null);
		
		// if asteroid hit station, then make visible an explosive effect.
		if (hitStation) {
			Image img1=tool.getImage("explotion.png");
			g.drawImage(img1,(int) x-2, (int) y-2, size +4, size+4,null);			
		}
		
	}
	
	public void hit ( ) {  // shrink size when hit
		if(size >=4) {
			size = size - 4; 	
		}
		
	}
	
	
	// this will check if asteroid get hit by a rocket
	public boolean nearTo(double tx, double ty) {  // use Pythagorean theorem to determine distance between points
		double distance = Math.sqrt( (x - tx) * (x - tx) + (y - ty) * (y - ty) );
		return distance <10;
		
		// Is (tx, ty) too close to ‘this’? 
		// x, y are coordinates of ‘this’
		// tx, ty are ‘other sender’ coords
	}
	
	
	// this will figure out if station got hit by the asteroid.
	public boolean nearToStation(double tx, double ty) {  // use Pythagorean theorem to determine distance between points
		double distance = Math.sqrt( (x - tx) * (x - tx) + (y - ty) * (y - ty) );
		return distance <25;
		
		// Is (tx, ty) too close to ‘this’? 
		// x, y are coordinates of ‘this’
		// tx, ty are ‘other sender’ coords
	}


}
