package chess;

import java.util.ArrayList;
import java.util.List;

/**
*@author Alay Shah
*@author Anshika Khare
*/


public class Queen extends Piece {

	/**
	 * Creates instance of Queen, utilizes Piece super constructor
	 * @param team Team of Queen
	 * @param curLoc Current Location of Queen
	 * @see Piece
	 * @see Location
	 */
	public Queen(Team team, Location curLoc) {
		super(PieceType.QUEEN, team, curLoc);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return team==Team.BLACK ? "bQ":"wQ";
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
		//up right
		for( int i = rnk+1, j = fle +1; i<8 && j<8; i++, j++) {  
			if(board[i][j]==null) {
				moves.add(new Move(curLoc, new Location(i,j)));
			}else {
				if(board[i][j].team==opponent) {
					moves.add(new Move(curLoc, new Location(i,j)));
				}
				break;
			}
		}
		//up left
		for( int i = rnk+1, j = fle -1; i<8 && j>-1; i++, j--) {
			if(board[i][j]==null) {
				moves.add(new Move(curLoc, new Location(i,j)));
			}else {
				if(board[i][j].team==opponent) {
					moves.add(new Move(curLoc, new Location(i,j)));
				}
				break;
			}
		}
		//down right
		for( int i = rnk-1, j = fle +1; i>-1 && j<8; i--, j++) {
			if(board[i][j]==null) {
				moves.add(new Move(curLoc, new Location(i,j)));
			}else {
				if(board[i][j].team==opponent) {
					moves.add(new Move(curLoc, new Location(i,j)));
				}
				break;
			}
		}
		//check down left
		for( int i = rnk-1, j = fle -1; i>-1 && j>-1; i--, j--) {
			if(board[i][j]==null) {
				moves.add(new Move(curLoc, new Location(i,j)));
			}else {
				if(board[i][j].team==opponent) {
					moves.add(new Move(curLoc, new Location(i,j)));
				}
				break;
			}
		}

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
		
		for ( int i = fle+1; i<8; i++){ //moving right
			if( board[rnk][i]==null) {
				moves.add(new Move( curLoc, new Location(rnk,i)));
			}else {
				if(board[rnk][i].team==opponent) {
					moves.add(new Move( curLoc, new Location(rnk,i)));
				}
				break;
			}
		}
		for ( int i = fle-1; i>-1; i--){ //moving left
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
		Queen q = new Queen (team, curLoc);
		q.hasMoved = hasMoved;
		return q;
	}
}
