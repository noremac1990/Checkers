package net.cameronedwards.checkers.game;

import static org.junit.Assert.*;

import java.util.List;


import org.junit.Before;
import org.junit.Test;

public class MoveTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalculateMoves() {
		Board board = new Board();
		
		board.addChecker(new RedChecker(), new Location(0, 0));
		board.addChecker(new BlackChecker(), new Location(1, 1));
		
		List<Move> moves = Move.calculateMoves(board, board.getSquare(new Location(0, 0)), board.getSquare(new Location(0, 0)).getChecker());
		
		// should only be 1 moves, a jump, and no double jumps either
		
		assertTrue("one move", moves.size() == 1);
		assertTrue("move is jump", moves.get(0).isJump());
		assertTrue("no doubles", moves.get(0).getNextMoves().size() == 0);
		
		board.reset();
		// test double jumps
		board.addChecker(new RedChecker(),  new Location(0, 0));
		board.addChecker(new BlackChecker(), new Location(1, 1));
		board.addChecker(new BlackChecker(), new Location(3, 3));
		
		moves = Move.calculateMoves(board, board.getSquare(new Location(0, 0)), board.getSquare(new Location(0, 0)).getChecker());
		
		assertTrue("one move", moves.size() == 1);
		assertTrue("move is jump", moves.get(0).isJump());
		assertTrue("double jump availible", moves.get(0).getNextMoves().size() > 0);
		
		board.reset();
		// test recursion with kings
		board.addChecker(new BlackChecker(),  new Location(5, 5));
		
		board.getSquare(new Location(5, 5)).getChecker().promote();
		
		board.addChecker(new RedChecker(), new Location(2, 2));
		board.addChecker(new RedChecker(), new Location(4, 4));
		
		moves = Move.calculateMoves(board, board.getSquare(new Location(5, 5)), board.getSquare(new Location(5, 5)).getChecker());
		
		assertTrue("one move", moves.size() == 1);
		assertTrue("move is jump", moves.get(0).isJump());
		assertTrue("double jump availible", moves.get(0).getNextMoves().size() > 0);
		
	}
}
