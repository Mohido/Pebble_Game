import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class PebbleGame {
	
	private JFrame frame;
	private PebbleBoardGUI game;
	
	private final int INITIAL_BOARD_SIZE = 3;
	
	public PebbleGame() {
		frame = new JFrame("Color clicker");
		frame.setFocusable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game = new PebbleBoardGUI(INITIAL_BOARD_SIZE);
        frame.getContentPane().add(game.getPanel(), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFocusable(false);
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setFocusable(false);
        menuBar.add(gameMenu);
        JMenu newMenu = new JMenu("New");
        newMenu.setFocusable(false);
        gameMenu.add(newMenu); 
        int[] boardSizes = new int[]{3, 4, 6};
        for (int boardSize : boardSizes) {
            JMenuItem sizeMenuItem = new JMenuItem(boardSize + "x" + boardSize);
            sizeMenuItem.setFocusable(false);
            newMenu.add(sizeMenuItem);
            sizeMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.getContentPane().remove(game.getPanel());
                    game = new PebbleBoardGUI(boardSize);
                    frame.getContentPane().add(game.getPanel(), BorderLayout.CENTER);
                    game.getPanel().requestFocus();
                    frame.pack();
				}
            });
        }
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setFocusable(false);
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		PebbleGame g = new PebbleGame();

	}

}
