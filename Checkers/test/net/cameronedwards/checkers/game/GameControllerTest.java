package net.cameronedwards.checkers.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNewGame() {
		GameController gc = new GameController();
		
		gc.newGame(false);

		assertTrue("Pieces should be in starting position", gc.getBoard().getSquare(new Location(0, 5)).isOccupied());
		
	}

	@Test
	public void testGo() {
		
		GameController gc = new GameController();
		
		gc.newGame(false);
		
		gc.go(new Location(0, 5));
		gc.go(new Location(1, 4));
		
		assertTrue("Black should have moved", gc.getBoard().getSquare(new Location(1, 4)).isOccupied());
	}

	@Test
	public void testGetTurn() {
		GameController gc = new GameController();
		
		gc.newGame(false);
		
		assertEquals("Black moves first", GameController.Turn.BLACK, gc.getTurn());
		
		gc.go(new Location(0, 5));
		gc.go(new Location(1, 4));
		
		assertEquals("Red moves after black", GameController.Turn.RED, gc.getTurn());
		
	}

	@Test
	public void testGetSelectedSquares() {
		GameController gc = new GameController();
		
		gc.newGame(false);
		
		gc.go(new Location(0, 5));
		
		assertTrue("square 0, 5 should be selected", gc.getSelectedSquares().get(0).getLocation().equals(new Location(0, 5)));
		
	}

}
