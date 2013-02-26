package net.cameronedwards.checkers.game;

public class Square {
	public enum Color {
		BLACK,
		WHITE
	};
	
	private Checker checker;
	private final Color color;
	private final Location location;
	
	public Square(Color color, Location location) {
		this.color = color;
		this.location = location;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Checker getChecker() {
		return checker;
	}
	
	public void setChecker(Checker checker) {
		this.checker = checker;

	}
	
	public boolean isOccupied() {
		return checker != null;
	}
	
	public Location getLocation() {
		return location;
	}
}
