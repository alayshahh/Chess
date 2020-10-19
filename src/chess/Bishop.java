package chess;

import java.util.List;

/**
*@author Alay Shah & Anshika Khare
*/
public class Bishop extends Piece {

	
	
	public Bishop(Team team, Location curLoc) {
		super(PieceType.BISHOP, team, curLoc);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * @return String
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return team == Team.BLACK ? "bB" : "wB";
	}

	@Override
	public List<Move> allPossibleMoves(Board gameBoard) {
		// TODO Auto-generated method stub
		return null;
	}

}
