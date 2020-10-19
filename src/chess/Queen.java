package chess;

import java.util.List;

/**
*@author Alay Shah & Anshika Khare
*/


public class Queen extends Piece {

	public Queen(Team team, Location curLoc) {
		super(PieceType.QUEEN, team, curLoc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return team==Team.BLACK ? "bQ":"wQ";
	}

	@Override
	public List<Move> allPossibleMoves(Board gameBoard) {
		// TODO Auto-generated method stub
		return null;
	}

}
