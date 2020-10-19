package chess;

import java.util.List;

/**
 * @author Alay Shah & Anshika Khare
*/


public class Pawn extends Piece {
	
	


	boolean hasMoved; //will help for enPassant and for the init double move
	
	public Pawn(Team team, Location curLoc) {
		super(PieceType.PAWN, team, curLoc);
		hasMoved = false;
		
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return team == Team.BLACK ? "bp" : "wp" ;
	}


	@Override
	public List<Move> allPossibleMoves(Board gameBoard) {
		// TODO Auto-generated method stub
		return null;
	}

}
