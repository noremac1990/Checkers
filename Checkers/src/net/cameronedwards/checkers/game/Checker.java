package net.cameronedwards.checkers.game;

public class Checker {
	public enum Color {
		RED,
		BLACK
	}
	
	public enum Direction {
		NORTH,
		SOUTH
	}
	
	private final Color color;
	private final Direction direction;
	private boolean king = false;
	
	public Checker(Color color, Direction direction) {
		this.color = color;
		this.direction = direction;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public boolean isKing() {
		return king;
	}
	
	public void promote() {
		king = true;
	}
	
	public boolean canMoveSouth() {
		if(king || direction == Direction.SOUTH)
			return true;
		
		return false;
	}
	
	public boolean canMoveNorth() {
		if(king || direction == Direction.NORTH)
			return true;
		
		return false;
	}
	
}
