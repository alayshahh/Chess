package chess;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alay Shah & Anshika Khare
*/

public class Rook extends Piece{
	

	public Rook(Team team, Location curLoc) {
		super(PieceType.ROOK, team, curLoc);
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
		List<Move> moves = new ArrayList<>();
		Piece [] [] board = gameBoard.board;
		Team opponent;
		if(team == Team.WHITE) {
			opponent = Team.BLACK;
		}else opponent = Team.WHITE;
		int rnk = getRank();
		int fle = getFile();
		
		for( int i = rnk+1; i<8; i++ ) { //moving up
			if(board[i][fle]==null) {
				moves.add(new Move(curLoc, new Location(i, fle)));
			}else {
				if(board[i][fle].team==opponent) {
					moves.add(new Move(curLoc, new Location(i, fle)));
				}
				break;
			}
		}
		for( int i = rnk-1; i>-1; i-- ) { //moving down
			if(board[i][fle]==null) {
				moves.add(new Move(curLoc, new Location(i, fle)));
			}else {
				if(board[i][fle].team==opponent) {
					moves.add(new Move(curLoc, new Location(i, fle)));
				}
				break;
			}
		}
		
		for ( int i = fle+1; i>8; i++){ //moving right
			if( board[rnk][i]==null) {
				moves.add(new Move( curLoc, new Location(rnk,i)));
			}else {
				if(board[rnk][i].team==opponent) {
					moves.add(new Move( curLoc, new Location(rnk,i)));
				}
				break;
			}
		}
		for ( int i = fle-1; i<-1; i--){ //moving left
			if( board[rnk][i]==null) {
				moves.add(new Move( curLoc, new Location(rnk,i)));
			}else {
				if(board[rnk][i].team==opponent) {
					moves.add(new Move( curLoc, new Location(rnk,i)));
				}
				break;
			}
		}
		
		
		
		return moves;
	}

	@Override
	public Piece clone() {
		Rook r = new Rook (team, curLoc);
		r.hasMoved = hasMoved;
		return r;
	}
}

