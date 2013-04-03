package net.cameronedwards.checkers.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Move {

	private final Location origin;
	private final Location destination;
	private final Location jumpedLocation;

	// TODO Double jumps
	private final List<Move> nextMoves = new ArrayList<Move>();

	private Move(Location origin, Location destination, Location jumpedLocation) {
		this.origin = origin;
		this.destination = destination;
		this.jumpedLocation = jumpedLocation;
	}

	public static List<Move> calculateMoves(Board board, Square sq, Checker ch) {
		return calculateMoves(board, ch, false,
				sq.getLocation(), new HashSet<Location>());
	}

	private static List<Move> calculatMovesDirection(Board board,
			Checker checker, boolean jumpsOnly, Location origin, int dirX,
			int dirY, Set<Location> visited) {

		List<Move> moves = new ArrayList<Move>();

		// big ugly move calculations
		visited.add(origin);
		
		int x = origin.getX();
		int y = origin.getY();
		int distance;
		Location prev = null;
		Location cur = null;

		do {
			x += dirX;
			y += dirY;
			distance = Math.abs(x - origin.getX());
			cur = new Location(x, y);

			if (!board.inBounds(cur))
				break;

			if (!jumpsOnly && distance == 1
					&& !board.getSquare(cur).isOccupied()) {
				if (!visited.contains(cur))
					moves.add(new Move(origin, cur, null));
			}

			if (distance == 2
					&& board.getSquare(prev).isOccupied()
					&& board.getSquare(prev).getChecker().getColor() != checker
							.getColor()) {
				if (!visited.contains(cur) && !board.getSquare(cur).isOccupied())
					moves.add(new Move(origin, cur, prev)); // add jump
			}

			prev = new Location(x, y);
		} while (distance < 2);

		return moves;
	}

	private static List<Move> calculateMoves(Board board, Checker checker,
			boolean jumpsOnly, Location origin, Set<Location> visited) {

		List<Move> moves = new ArrayList<Move>();

		// check north moves

		if (checker.canMoveNorth()) {

			moves.addAll(calculatMovesDirection(board, checker, jumpsOnly,
					origin, -1, -1, visited));
			moves.addAll(calculatMovesDirection(board, checker, jumpsOnly,
					origin, 1, -1, visited));

		}
		// check south moves

		if (checker.canMoveSouth()) {

			moves.addAll(calculatMovesDirection(board, checker, jumpsOnly,
					origin, -1, 1, visited));
			moves.addAll(calculatMovesDirection(board, checker, jumpsOnly,
					origin, 1, 1, visited));

		}

		boolean hasJumps = false;
		// recursively calculate any double jumps
		for (Move move : moves) {
			if (move.isJump()) {

				move.nextMoves.addAll(calculateMoves(board, checker, true,
						move.destination, visited));

				hasJumps = true;
			}

		}

		// forced jump rule
		// if any jumps exist, they must be taken
		if (hasJumps) {
			Iterator<Move> iMove = moves.iterator();

			while (iMove.hasNext()) {
				Move m = iMove.next();
				if (!m.isJump())
					iMove.remove();
			}

		}

		return moves;
	}

	public Location getOrigin() {
		return origin;
	}

	public Location getDestination() {
		return destination;
	}

	public boolean isJump() {
		return jumpedLocation != null;
	}
	
	public int value() {
		return jumpedLocation == null ? 1 : 3;
	}

	public Location getJumpedLocation() {
		return jumpedLocation;
	}

	public List<Move> getNextMoves() {
		return Collections.unmodifiableList(nextMoves);
	}
	
	@Override
	public String toString() {
		
		return String.format("%s from %s to %s.", isJump() ? "Jump" : "Move", origin, destination);
	}

}
