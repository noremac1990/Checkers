package net.cameronedwards.checkers.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
	private final int width = 8;
	private final int height = 8;
	
	private final Square[][] squares = new Square[width][height];
	
	private final List<Checker> redCheckers = new ArrayList<Checker>();
	private final List<Checker> blackCheckers = new ArrayList<Checker>();
	
	public Board() {
		
		reset();
	}
	
	public void reset() {
		for(int y = 0; y < squares.length; y++) {
			for(int x = 0; x < squares[y].length; x++) {
				Square newSquare;
				
				if(((x + y) & 1) == 1)
					newSquare = new Square(Square.Color.WHITE, new Location(x, y));
				else
					newSquare = new Square(Square.Color.BLACK, new Location(x, y));
				
				squares[x][y] = newSquare;
			}
		}
		
		redCheckers.clear();
		blackCheckers.clear();

		
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < squares[y].length; x++) {
				if(((x + y) & 1) == 1)
					addChecker(new Checker(Checker.Color.RED, Checker.Direction.SOUTH), new Location(x, y));
			}
		}
		
		for(int y = squares.length - 1; y > 4; y--) {
			for(int x = 0; x < squares[y].length; x++) {
				if(((x + y) & 1) == 1)
					addChecker(new Checker(Checker.Color.BLACK, Checker.Direction.NORTH), new Location(x, y));
			}
		}
		
	}
	
	public void addChecker(Checker checker, Location where) {
		if(checker.getColor() == Checker.Color.RED)
			redCheckers.add(checker);
		else
			blackCheckers.add(checker);
		
		getSquare(where).setChecker(checker);
	}
	
	public void moveChecker(Location origin, Location dest) {
		Checker c = getSquare(origin).getChecker();
		
		getSquare(dest).setChecker(c);
		getSquare(origin).setChecker(null);
		
		if(c.getDirection() == Checker.Direction.NORTH && dest.getY() == 0)
			c.promote();
		
		if(c.getDirection() == Checker.Direction.SOUTH && dest.getY() == getHeight() - 1)
			c.promote();
		
	}
	
	public void removeChecker(Location where) {

		Checker c = getSquare(where).getChecker();
		
		if(c.getColor() == Checker.Color.RED)
			redCheckers.remove(c);
		else
			blackCheckers.remove(c);
		
		getSquare(where).setChecker(null);
		
	}
	
	public boolean inBounds(Location location) {
		if(location.getX() < getWidth() && location.getX() >= 0 &&
				location.getY() < getHeight() && location.getY() >= 0)
			return true;
		else
			return false;
	}
	
	public Square getSquare(Location location) {
		return squares[location.getX()][location.getY()];
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public List<Checker> getRedCheckers() {
		return Collections.unmodifiableList(redCheckers);
	}

	public List<Checker> getBlackCheckers() {
		return Collections.unmodifiableList(blackCheckers);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public void executeMove(Move m) {
		
		moveChecker(m.getOrigin(), m.getDestination());
		
		if(m.isJump()) {
			removeChecker(m.getJumpedLocation());
		}
		
	}
}
