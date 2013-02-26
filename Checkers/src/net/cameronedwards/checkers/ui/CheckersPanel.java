package net.cameronedwards.checkers.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.cameronedwards.checkers.game.Board;
import net.cameronedwards.checkers.game.Checker;
import net.cameronedwards.checkers.game.GameController;
import net.cameronedwards.checkers.game.Location;
import net.cameronedwards.checkers.game.Square;

public class CheckersPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -639668968480451845L;

	private final GameController gc;
	
	private BufferedImage blackChecker;
	private BufferedImage redChecker;
	private BufferedImage selectedSquare;
	private BufferedImage blackCheckerKing;
	private BufferedImage redCheckerKing;
	private BufferedImage possibleMoveSquare;
	
	private JLabel turnLabel;
	
	CheckersPanel(GameController gc) {
		this.gc = gc;
		setPreferredSize(new Dimension(630, 512));

		loadImages();
		
		init();
		
		repaint();
	}
	
	private void init() {
		
		setLayout(null);
		
		JButton newGame = new JButton(new AbstractAction("New Game") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4874711829599058010L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gc.newGame();
				repaint();

			}

		});
		Dimension newGameSize = newGame.getPreferredSize();
		newGame.setBounds(520, 10, newGameSize.width, newGameSize.height);
		add(newGame);
		
		turnLabel = new JLabel();
		turnLabel.setBounds(520, 40, newGameSize.width, 20);
		turnLabel.setHorizontalAlignment(JButton.CENTER);
		add(turnLabel);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getX() < 0
						|| e.getX() >= gc.getBoard().getWidth() * 64
						|| e.getY() < 0
						|| e.getY() >= gc.getBoard().getHeight() * 64)
					return;
				
				gc.go(new Location(e.getX() / 64, e.getY() / 64));
				
				if(!gc.hasMoves(Checker.Color.RED)) {
					JOptionPane.showMessageDialog(CheckersPanel.this, "Black Wins!");
					gc.newGame();
				}
				
				if(!gc.hasMoves(Checker.Color.BLACK)) {
					JOptionPane.showMessageDialog(CheckersPanel.this, "Red Wins!");
					gc.newGame();
				}
				
				
				repaint();
			}
		});
		
		
		
	}

	private void loadImages() {
		try {
			blackChecker = ImageIO.read(new File("resources/blackChecker.png"));
			redChecker = ImageIO.read(new File("resources/redChecker.png"));
			selectedSquare = ImageIO.read(new File("resources/selectedSquare.png"));
			redCheckerKing = ImageIO.read(new File("resources/redCheckerKing.png"));
			blackCheckerKing = ImageIO.read(new File("resources/blackCheckerKing.png"));
			possibleMoveSquare = ImageIO.read(new File("resources/possibleMoveSquare.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(getParent(), e, "Exception", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.clearRect(0, 0, getWidth(), getHeight());
		
		drawBoard(g);
		
		drawCheckers(g);
		
		drawSelection(g);
		
		drawPossibleMoves(g);
		
		turnLabel.setText(gc.getTurn() + "'s turn.");
	}

	private void drawPossibleMoves(Graphics g) {
		for(Location l : gc.getPossibleMoveLocations()) {
			g.drawImage(possibleMoveSquare, l.getX() * 64, l.getY() * 64, null);
		}
		
	}

	private void drawSelection(Graphics g) {
		
		for(Square square : gc.getSelectedSquares()) {
			g.drawImage(selectedSquare, square.getLocation().getX() * 64, square.getLocation().getY() * 64, null);
		}
		
	}

	private void drawCheckers(Graphics g) {
		Board b = gc.getBoard();
		for(int y = 0; y < b.getHeight(); y++) {
			for(int x = 0; x < b.getWidth(); x++) {
				Square square = b.getSquare(new Location(x, y));
				BufferedImage checkerImage;
				
				if(!square.isOccupied())
					continue;
				
				Checker c = square.getChecker();
				
				if(c.getColor() == Checker.Color.BLACK && !c.isKing())
					checkerImage = blackChecker;
				else if(c.getColor() == Checker.Color.RED && !c.isKing())
					checkerImage = redChecker;
				else if(c.getColor() == Checker.Color.BLACK && c.isKing())
					checkerImage = blackCheckerKing;
				else
					checkerImage = redCheckerKing;
				
				g.drawImage(checkerImage, x * 64, y * 64, null);
				
			}
		}
	}

	private void drawBoard(Graphics g) {
		Board b = gc.getBoard();
		for(int y = 0; y < b.getHeight(); y++) {
			for(int x = 0; x < b.getWidth(); x++) {
				
				if(b.getSquare(new Location(x, y)).getColor() == Square.Color.BLACK)
					g.setColor(Color.BLACK);
				else
					g.setColor(Color.WHITE);
				
				g.fillRect(x * 64, y * 64, 64, 64);
				
			}
		}
		
	}
	
}
