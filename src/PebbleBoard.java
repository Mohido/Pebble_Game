import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PebbleBoard{
	private int turn_count;
	private Players turn = Players.p1; //player 1 to begin with
	private int size;
	private ArrayList<Pebble> pebbles;
	private Pebble chosenPebble;
	
	
	public PebbleBoard(int size) {
		this.size = size;
		this.turn_count = 0;
		pebbles = new ArrayList<Pebble>();
		
		for(int i = 0; i < size; i++) {
			for(int j = 0 ; j < size ; j++) {
				if(i == 0) {
					pebbles.add(new Pebble(0,0,Players.p1));///The Coordinates are temporary for now. They will be changed later during the buttons initialization
				}else if(i == 1) {
					pebbles.add(new Pebble(0,0,Players.p2));
				}else {
					pebbles.add(null);
				}
			}
		}
		Collections.shuffle(pebbles);
	}

	
	public void nextTurn() {
		if(turn == Players.p1) {turn = Players.p2;}
		else {turn = Players.p1;}
		this.turn_count++;
	}
	public void movePebbles(Directions d) {
		switch(d) {
		case N:
			{
				int x = this.chosenPebble.getY(); ///rows
				int y = this.chosenPebble.getX(); ///col
				this.pebbles.set((x) + (y)*size, null);
				while(y > 0 && this.chosenPebble != null) {
					Pebble temp = this.getPebble(y-1, x);
					this.chosenPebble.setCoord(y-1, x);
					this.pebbles.set((x) + (y-1)*size, this.chosenPebble);
					this.chosenPebble = temp;
					y--;
				}
			}
			break;
		case S:
			{
				int x = this.chosenPebble.getY(); ///rows
				int y = this.chosenPebble.getX(); ///col
				this.pebbles.set((x) + (y)*size, null);
				while(y < (size - 1 ) && this.chosenPebble != null) {
					Pebble temp = this.getPebble(y+1, x);
					this.chosenPebble.setCoord(y+1, x);
					this.pebbles.set((x) + (y+1)*size, this.chosenPebble);
					this.chosenPebble = temp;
					y++;
				}
			}
			break;
		case E:
			{
				int x = this.chosenPebble.getY(); ///rows
				int y = this.chosenPebble.getX(); ///col
				this.pebbles.set((x) + (y)*size, null);
				while(x < (size - 1 ) && this.chosenPebble != null) {
					Pebble temp = this.getPebble(y, x+1);
					this.chosenPebble.setCoord(y, x+1);
					this.pebbles.set((x+1) + (y)*size, this.chosenPebble);
					this.chosenPebble = temp;
					
					x++;
				}
			}
			break;
		case W:
			{
				int x = this.chosenPebble.getY(); ///rows
				int y = this.chosenPebble.getX(); ///col
				this.pebbles.set((x) + (y)*size, null);
				while(x > 0 && this.chosenPebble != null) {
					Pebble temp = this.getPebble(y, x-1);
					this.chosenPebble.setCoord(y, x-1);
					this.pebbles.set((x-1) + (y)*size, this.chosenPebble);
					this.chosenPebble = temp;
					
					x--;
				}
			}
		break;
		default:
			break;
		}
	}

///Getters Area
	public Players getWinner() {
		if(this.turn_count >= size * 5) { ///after passing the limit.
			int player1 = 0;
			int player2 = 0;
			while(this.pebbles.size() > 0) {
				Pebble temp = this.pebbles.remove(this.pebbles.size() - 1);
				if(temp == null) continue;
				if(temp.getPlayer() == Players.p1) {player1++;}
				else if(temp.getPlayer() == Players.p2) {player2++;}
			}
			if(player1 > player2) {
				return Players.p1;
			}else if(player1 < player2){
				return Players.p2;
			}
			return Players.both;
		}else if(this.turn_count < size * 5) {
			int player1 = 0;
			int player2 = 0;
			int i = -1;
			while(this.pebbles.size() - 1 - i > 0) {
				i++;
				Pebble temp = this.pebbles.get(i);
				if(temp == null) continue;
				if(temp.getPlayer() == Players.p1) {player1++;}
				else if(temp.getPlayer() == Players.p2) {player2++;}
			}
			if(player1 == 0 && player2 > 0) {
				return Players.p2;
			}else if(player2 == 0 && player1 > 0){
				return Players.p1;
			}
		}
		return null;
	}
	public Players getCurrentPlayer() {return this.turn;}
	public Pebble getPebble(int x, int y) { return this.pebbles.get(y + x * size);}
	public Pebble getChosenPebble() { return this.chosenPebble;}
	
	public void setChosenPebble(Pebble p) { this.chosenPebble = p;}
}