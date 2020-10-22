package chess;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alay Shah
 * @author Anshika Khare
 */
public class Bishop extends Piece {



	/**
	 * Creates new Bishop. Calls Piece constructor to set fields
	  * @param team Team of the piece
	 * @param curLoc Current Location of the Piece
	 * @see Piece
	 */
	public Bishop(Team team, Location curLoc) {
		super(PieceType.BISHOP, team, curLoc);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return team == Team.BLACK ? "bB" : "wB";
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

		return moves;
	}

	@Override
	public Piece clone() {
		Bishop b = new Bishop(team, curLoc);
		b.hasMoved = hasMoved;
		return b;
	}

}
