package net.cameronedwards.checkers.game;

public class Location {
	private final int x;
	private final int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return String.format("{x: %d, y: %d}", x, y);
	}
	
	@Override
	public int hashCode() {
		return x + y * 17;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null)
			return false;
		
		if(obj == this)
			return true;
		
		if(!(obj instanceof Location))
			return false;
		
		Location location = (Location) obj;
		
		if(location.x == x && location.y == y)
			return true;
		
		return false;
		
	}
}
