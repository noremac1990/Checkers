package net.cameronedwards.checkers.game;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testReset() {
		Board board = new Board();

		board.moveChecker(new Location(1, 2), new Location(2, 1));

		board.reset();

		assertTrue(board.getSquare(new Location(1, 2)).isOccupied());

	}

	@Test
	public void testAddChecker() {
		
		Board board = new Board();
		
		Checker c = mock(Checker.class, "checker");	
		Location l = mock(Location.class);
	
		assertFalse(board.getSquare(l).isOccupied());
		
		board.addChecker(c, l);

		assertTrue(board.getSquare(l).isOccupied());
		
	}

	@Test
	public void testMoveChecker() {
		Board board = new Board();
		Checker c = mock(Checker.class);
		
		board.addChecker(c, new Location(1, 1));
		
		assertTrue(board.getSquare(new Location(1, 1)).isOccupied());
		assertFalse(board.getSquare(new Location(2, 2)).isOccupied());
		
		board.moveChecker(new Location(1, 1), new Location(2, 2));

		assertTrue(board.getSquare(new Location(2, 2)).isOccupied());
		assertFalse(board.getSquare(new Location(1, 1)).isOccupied());
	}

	@Test
	public void testRemoveChecker() {
		Board board = new Board();
		
		Checker c = mock(Checker.class);
		
		assertFalse(board.getSquare(new Location(1, 1)).isOccupied());
		board.addChecker(c, new Location(1, 1));
		assertTrue(board.getSquare(new Location(1, 1)).isOccupied());
		
		board.removeChecker(new Location(1, 1));
		assertFalse(board.getSquare(new Location(1, 1)).isOccupied());
	}

	@Test
	public void testInBounds() {
		Board board = new Board();
		
		assertFalse(board.inBounds(new Location(-1, -1)));
		assertFalse(board.inBounds(new Location(1, -1)));
		assertFalse(board.inBounds(new Location(-1, 1)));
		assertFalse(board.inBounds(new Location(board.getWidth(), -1)));
		assertFalse(board.inBounds(new Location(board.getWidth(), 5)));
		
		assertTrue(board.inBounds(new Location(3, 3)));
		
	}

	@Test
	public void testExecuteMove() {
		Board board = new Board();
		Move m = mock(Move.class);
		
		when(m.getDestination()).thenReturn(new Location(2, 2));
		when(m.getOrigin()).thenReturn(new Location(1, 1));
		
		board.addChecker(mock(Checker.class), new Location(1, 1));
		
		assertTrue(board.getSquare(new Location(1,1)).isOccupied());
		assertFalse(board.getSquare(new Location(2,2)).isOccupied());
		board.executeMove(m);
		assertTrue(board.getSquare(new Location(2,2)).isOccupied());
		assertFalse(board.getSquare(new Location(1,1)).isOccupied());
		
	}

}
