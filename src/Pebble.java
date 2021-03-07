import java.awt.Color;


public class Pebble {
	private Color color;
	private Players p;
	private int x,y;
	
	public Pebble(int x , int y, Players p) {
		this.p  = p;
		this.x = x;
		this.y = y;
		
		if(this.p == Players.p1) {color = Color.BLACK;}
		else if (this.p == Players.p2){	color = Color.WHITE;}
		
	} 
	
	
	public void setCoord(int x,int y) {this.x = x;this.y = y;}
	public int getX() {return this.x;}
	public int getY() {return this.y;}
	public Players getPlayer() {return this.p;}
	public Color getColor() {return this.color;}
	
	
}
