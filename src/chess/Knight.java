package chess;

import java.util.List;

/**
 * @author Alay Shah & Anshika Khare
*/

public class Knight extends Piece {
	
	
	public Knight(Team team, Location curLoc) {
		super(PieceType.KNIGHT, team, curLoc);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return team == Team.BLACK ? "bN":"wN" ;
	}

	@Override
	public List<Move> allPossibleMoves(Board gameBoard) {
		// TODO Auto-generated method stub
		return null;
	}

}
