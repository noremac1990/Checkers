package net.cameronedwards.checkers.game;

public class Board {
	private final int width = 8;
	private final int height = 8;
	
	private final Square[][] squares = new Square[width][height];
	
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
		
	}
	
	public void addChecker(Checker checker, Location where) {
		
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
	
	public void executeMove(Move m) {
		
		moveChecker(m.getOrigin(), m.getDestination());
		
		if(m.isJump()) {
			removeChecker(m.getJumpedLocation());
		}
		
	}
}
