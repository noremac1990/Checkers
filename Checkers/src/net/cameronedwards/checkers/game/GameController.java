package net.cameronedwards.checkers.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameController {
	public enum Turn {
		BLACK,
		RED
	}
	
	private Turn turn = Turn.BLACK;
	
	private LinkedList<Move> moveQueue = new LinkedList<Move>();
	
	private LinkedList<Square> selectedSquares = new LinkedList<Square>();
	
	private Board board = new Board();
	
	public GameController() {

	}
	
	public void newGame() {
		turn = Turn.BLACK;
		moveQueue.clear();
		selectedSquares.clear();
		board = new Board();
		
	}
	
	public void go(Location location) {

		if (selectedSquares.size() == 0) {
			if (board.getSquare(location).isOccupied()) {

				if(board.getSquare(location).getChecker().getColor() == Checker.Color.RED && turn == Turn.RED) {
					selectedSquares.push(board.getSquare(location));
				} else if(board.getSquare(location).getChecker().getColor() == Checker.Color.BLACK && turn == Turn.BLACK) {
					selectedSquares.push(board.getSquare(location));
				}
				
			}

		} else {

			boolean jumpForced = isJumpForced();
			boolean found = false;
			for (Move m : Move.calculateMoves(board, selectedSquares.peek(), selectedSquares.getLast().getChecker())) {

				if (jumpForced && !m.isJump())
					continue;

				if (m.getDestination().equals(location)) {
					found = true;
					moveQueue.addLast(m);

					if (m.getNextMoves().size() == 0) {
						doTurn();
					} else {
						selectedSquares.push(board.getSquare(m.getDestination()));
					}

					break;

				}

			}
			
			if (!found) {
				selectedSquares.clear();
				moveQueue.clear();
			}

		}
	}
	
	public List<Location> getPossibleMoveLocations() {
		List<Location> possibleMoveLocations = new ArrayList<>();

		boolean jumpForced = isJumpForced();

		if (selectedSquares.size() == 0)
			return possibleMoveLocations;

		for (Move move : Move.calculateMoves(board, selectedSquares.peek(),
				selectedSquares.getLast().getChecker())) {
			if(jumpForced && !move.isJump())
				continue;
			
			possibleMoveLocations.add(move.getDestination());
		}
			

		return possibleMoveLocations;
	}
	
	private void doTurn() {
		while(moveQueue.size() > 0) {
			board.executeMove(moveQueue.pop());
		}
		
		if(turn == Turn.BLACK)
			turn = Turn.RED;
		else
			turn = Turn.BLACK;
		
		selectedSquares.clear();
	}

	private boolean isJumpForced() {
		// fucking ugly
		for(int y = 0; y < board.getHeight(); y++)
			for(int x = 0; x < board.getHeight(); x++)
				if(board.getSquare(new Location(x, y)).isOccupied())
					if((board.getSquare(new Location(x, y)).getChecker().getColor() == Checker.Color.RED && turn == Turn.RED) || 
							(board.getSquare(new Location(x, y)).getChecker().getColor() == Checker.Color.BLACK && turn == Turn.BLACK))
						for(Move move : Move.calculateMoves(board, board.getSquare(new Location(x, y)), board.getSquare(new Location(x, y)).getChecker()))
								if(move.isJump())
									return true;
		return false;
	}
	
	public boolean hasMoves(Checker.Color color) {
		
		for(int y = 0; y < board.getHeight(); y++)
			for(int x = 0; x < board.getHeight(); x++)
				if(board.getSquare(new Location(x, y)).isOccupied())
					if((board.getSquare(new Location(x, y)).getChecker().getColor() == color))
						if(Move.calculateMoves(board, board.getSquare(new Location(x, y)), board.getSquare(new Location(x, y)).getChecker()).size() > 0)
								return true;
		
		return false;
	}

	public Turn getTurn() {
		return turn;
	}

	public List<Square> getSelectedSquares() {
		return selectedSquares;
	}

	public Board getBoard() {
		return board;
	}
	
	
}
