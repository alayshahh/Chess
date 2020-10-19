package chess;

import java.util.List;

/**
 * @author Alay Shah & Anshika Khare
*/

public class King extends Piece {
	
	public King(Team team, Location curLoc) {
		super(PieceType.KING, team, curLoc);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return team == Team.BLACK ? "bK" : "wK";
	}



	@Override
	public List<Move> allPossibleMoves(Board gameBoard) {
		// TODO Auto-generated method stub
		return null;
	}

}
