package net.cameronedwards.checkers.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class SquareTest {

	@Test
	public void testIsOccupied()
	 throws Exception {
		Square square = new Square(Square.Color.WHITE, new Location(1,1));
		
		assertFalse(square.isOccupied());
		
		square.setChecker(new Checker(Checker.Color.BLACK, Checker.Direction.SOUTH));
		
		assertTrue(square.isOccupied());
		
		square.setChecker(null);
		
		assertFalse(square.isOccupied());
		
	}

}
