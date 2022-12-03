package asteroidsGame;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AsteroidGame extends Frame {
	private int FrameWidth = 500;
	private int FrameHeight = 400;
	
	// instantiate timer class
	Timer timer = new Timer();
	
	static public void main (String [ ] args){ 
		AsteroidGame world = new AsteroidGame(); 
		world. setVisible(true); 
		world.run();	
	}
	
	public AsteroidGame ( ) {
		setTitle("Asteroid Game"); 
		setSize(FrameWidth, FrameHeight); 
		setSize(500, 400);
		setBackground(Color.DARK_GRAY);
		addKeyListener (new keyDown( ));
//		addWindowListener(new CloseQuit());
	}
	
	public void run ( ) { // move pieces 
		while (true) {
			movePieces();
			repaint();
			try {						// pause 100 milliseconds in order
				Thread.sleep(100);		// to create animation illusion
			}catch(Exception e) {}		// must be in try-catch
		} 								// more details later...
	}
	
	private ArrayList <Asteroid> asteroids = new ArrayList<Asteroid> ( ); 
	private ArrayList <Rocket> rockets = new ArrayList<Rocket>( );
	
	// Station position middle of baseline
	private Station station = new Station (FrameWidth/2, FrameHeight-20);
	
	
	public void paint(Graphics g) {
		station.paint(g);
		int astoIndex = 0;
		while(asteroids.size()> 0 && astoIndex < asteroids.size() ) {
			Asteroid rock = (Asteroid)asteroids.get(astoIndex);
			rock.paint(g);
			astoIndex += 1;
		}
		
		int rocketIndex = 0;
		while(rockets.size()> 0 && rocketIndex < rockets.size() ) {
			Rocket rock = (Rocket)rockets.get(rocketIndex);
			rock.paint(g);
			rocketIndex += 1;
		}
		
		
	}
	
	private void movePieces() {
		// create a random new asteroid – 30% of the time
		if (Math.random( ) < 0.3) {
			Asteroid newRock = new Asteroid(FrameWidth * Math.random( ), 20,
					10 * Math.random( ) - 5, 3 + 3 * Math.random( ));
			
			asteroids.add(newRock);
			//<< add element(r) to asteroids container >>;
		}
		int astoIndex = 0;
		
		// move each asteroid in the asteroids in array list, check for hits,check for in frame
		while(asteroids.size()> 0 && astoIndex < asteroids.size() ) {
			Asteroid rock = (Asteroid)asteroids.get(astoIndex);
			// move the asteroid
			rock.move();
			
			// check for hits on station
			station.checkHit(rock);
			
			// if hits station, then initiate a timer to keep the asteroid
			// without removing.
			
			if (rock.hitStation) {
				final int toRemove = astoIndex;
				
				// remove asteroid after 99 mili seconds.
				// during the time explosive effect will be visible
				timer.schedule(new TimerTask() {
					  public void run() {
						  // remove asteroids
						  asteroids.remove(toRemove);
					  }
					},99);
				
			}
			
			// check if the game over.
			if(station.gameOver){
				
				// if game over, set timer to exit after three seconds.
				timer.schedule(new TimerTask() {
					  @Override
					  public void run() {
						  System.exit(0);
					  }
					},3000);
				
			}
			
			// check if the asteroid in frame
			if (rock.checkInFrame()){
				
				// remove the asteroids not in frame.
				asteroids.remove(astoIndex);
			}
			astoIndex += 1;
		}
		
		
	
		
		int rocketIndex = 0;
		
		// move each rocket in the rockets in array list, check for in frame
		while(rockets.size()> 0 && rocketIndex < rockets.size() ) {
			Rocket rock = (Rocket)rockets.get(rocketIndex);
			
			// move the rockets
			rock.move(asteroids);
			
			 //check if the rocket in frame
			if (rock.checkInFrame()){
				// eliminate if the rocket move out of the frame
				rockets.remove(rocketIndex);
			}
			rocketIndex += 1;
		}
		
		
	}
	
	private class keyDown extends KeyAdapter {  
		public void keyPressed (KeyEvent e) {
	
			char key = e.getKeyChar( ); 
			switch (key) {
				case 'j': 
					station.moveLeft( );	// turn left
					break; 
				case 'k': 
					station.moveRight( ); 
					break; 
				case ' ': 
					station.fire(rockets); 
					break; 
				case 'q': 
					System.exit(0);
					
			}
		}
	}
		 // turn right // space: fire // q: quit
	
	private class gameMover extends Thread {
		// override the run( ) method of Thread
		public void run ( ) { 
			while (true) {
				movePieces( ); 
				repaint( );
				try {
				sleep(100);
				} catch (Exception e) { }
			}
		}
	}
	
	

}
