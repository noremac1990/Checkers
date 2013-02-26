package net.cameronedwards.checkers.game;

import static org.junit.Assert.*;

import net.cameronedwards.checkers.game.Checker;

import org.junit.Test;

public class CheckerTest {

	@Test
	public void testIsKing() {
		Checker checker = new Checker(Checker.Color.RED, Checker.Direction.NORTH);
		assertFalse(checker.isKing());
	}

	@Test
	public void testPromote() {
		Checker checker = new Checker(Checker.Color.RED, Checker.Direction.NORTH);
		assertFalse(checker.isKing());
		
		checker.promote();
		assertTrue(checker.isKing());
	}

	@Test
	public void testCanMoveSouth() {
		Checker checker = new Checker(Checker.Color.RED, Checker.Direction.NORTH);
		assertFalse(checker.canMoveSouth());
		
		checker.promote();
		assertTrue(checker.canMoveSouth());
		
		checker = new Checker(Checker.Color.RED, Checker.Direction.SOUTH);
		assertTrue(checker.canMoveSouth());
		
	}

	@Test
	public void testCanMoveNorth() {
		Checker checker = new Checker(Checker.Color.RED, Checker.Direction.NORTH);
		assertTrue(checker.canMoveNorth());
		
		checker = new Checker(Checker.Color.RED, Checker.Direction.SOUTH);
		assertFalse(checker.canMoveNorth());
		
		checker.promote();
		assertTrue(checker.canMoveNorth());
	}

}
