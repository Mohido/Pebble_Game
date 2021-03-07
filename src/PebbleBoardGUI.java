import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PebbleBoardGUI {
	private JButton[][] buttons;
	private JPanel gameBoard;
	private int size;
	private PebbleBoard board;
	
	
	public PebbleBoardGUI(int size) {
		
		this.size = size;
		this.board = new PebbleBoard(size);
		///GUI elements
		buttons = new JButton[size][size];
		gameBoard = new JPanel();
		this.gameBoard.setFocusable(true);
		this.gameBoard.grabFocus();
		gameBoard.addKeyListener(new KeyAction());
		gameBoard.setLayout(new GridLayout(size,size));
		for(int i = 0 ; i < size ; i++) {
			for(int j = 0 ; j < size ; j++) {
				JButton btn = new JButton();
				btn.setFocusable(false);
				btn.setPreferredSize(new Dimension(50,50));
				btn.addActionListener(new ButtonListener(i,j));
				if(board.getPebble(i,j) != null) { 
					board.getPebble(i,j).setCoord(i,j);
					btn.setEnabled(true);
					btn.setBackground(board.getPebble(i,j).getColor());
					buttons[i][j] = btn;
				}else {
					btn.setEnabled(false);
					buttons[i][j] = btn;
				}
			}
		} 
		for(int i = 0 ; i < size ; i++) {
			for(int j = 0 ; j < size ; j++) {
				this.gameBoard.add(buttons[i][j]);
			}
		}

	}
	
	
	public void render() {
		for(int i = 0 ; i < size ; i++) {
			for(int j = 0 ; j < size ; j++) {
				if(board.getPebble(i,j) != null) { ///initiating the first 2 rows with pebbles information.
					board.getPebble(i,j).setCoord(i,j);
					buttons[i][j].setBackground(board.getPebble(i,j).getColor());
					buttons[i][j].setEnabled(true);
				}else {
					buttons[i][j].setEnabled(false);
					buttons[i][j].setBackground(null);
				}
			}
		}
		checkGameOver();
	}

	private void checkGameOver() {
		if(board.getWinner() == Players.p1) {
			JOptionPane.showMessageDialog(this.gameBoard,
					"Player 1 won",
					"CONGRATS",
					JOptionPane.INFORMATION_MESSAGE);
			this.board = new PebbleBoard(this.size);
			this.render();
		}else if(board.getWinner() == Players.p2) {
			JOptionPane.showMessageDialog(this.gameBoard,
					"Player 2 won",
					"CONGRATS",
					JOptionPane.INFORMATION_MESSAGE);
			this.board = new PebbleBoard(this.size);
			this.render();
		}else if(board.getWinner() == Players.both) {
			JOptionPane.showMessageDialog(this.gameBoard,
					"It is a Tie.",
					"DRAW",
					JOptionPane.INFORMATION_MESSAGE);
			this.board = new PebbleBoard(this.size);
			this.render();
		}
	}

	///getters and setters area _____________
	public JPanel getPanel() {return this.gameBoard;}
	
	///private Classes Area.
		class ButtonListener implements ActionListener {
			int x,y;
			Pebble cur;
			ButtonListener(int x, int y){
				this.x = x ; this.y = y;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked");
				cur = board.getPebble(x, y); 
				if(cur == null) {
					return;
				}
				board.setChosenPebble(null);
				if(cur.getPlayer() == board.getCurrentPlayer()) {///validation if the Right player clicked on his pebble
					board.setChosenPebble(cur);
				}
			}
		}
		
		class KeyAction implements KeyListener{
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("key");
				if(board.getChosenPebble() != null) {
					if(e.getKeyCode() == KeyEvent.VK_UP) {
						board.movePebbles(Directions.N);
					}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
						board.movePebbles(Directions.S);
					}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						board.movePebbles(Directions.E);
					}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
						board.movePebbles(Directions.W);
					}
					board.setChosenPebble(null);
					board.nextTurn();
					render();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {}
		}
}
