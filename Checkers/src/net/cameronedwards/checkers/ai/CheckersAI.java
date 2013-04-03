package net.cameronedwards.checkers.ai;

import java.util.List;

import net.cameronedwards.checkers.game.Board;
import net.cameronedwards.checkers.game.Checker;
import net.cameronedwards.checkers.game.Location;
import net.cameronedwards.checkers.game.Move;
import net.cameronedwards.checkers.game.Square;

public class CheckersAI {

	
	
	public CheckersAI() {
		
	}
	
	public Move CalculateMove(Board b) {
		
		Move bestMove = null;
		
		for (int x = 0; x < b.getWidth(); x++) {
			for (int y = 0; y < b.getHeight(); y++) {
				Square sq = b.getSquare(new Location(x, y));
				if (!sq.isOccupied())
					continue;

				if (sq.getChecker().getColor() == Checker.Color.RED) {
					List<Move> moves = Move.calculateMoves(b, sq,
							sq.getChecker());

					for (Move m : moves) {
						if (bestMove == null)
							bestMove = m;
						else if (m.value() > bestMove.value())
							bestMove = m;

					}
				}

			}
		}

		return bestMove;
	}

}
