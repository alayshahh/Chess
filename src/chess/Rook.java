package chess;

import java.util.List;

/**
 * @author Alay Shah & Anshika Khare
*/

public class Rook extends Piece{
	
	boolean hasMoved;

	public Rook(Team team, Location curLoc) {
		super(PieceType.ROOK, team, curLoc);
		hasMoved = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return team == Team.BLACK ? "bR": "wR";
	}

	@Override
	public List<Move> allPossibleMoves(Board gameBoard) {
		// TODO Auto-generated method stub
		return null;
	}

}
