package chess;

import java.util.ArrayList;
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
		List<Move> moves = new ArrayList<>();
		Piece [] [] board = gameBoard.board;
		Team opponent;
		if(team == Team.WHITE) {
			opponent = Team.BLACK;
		}else opponent = Team.WHITE;
		int rank = getRank();
		int file = getFile();
		boolean up = rank+1<8;
		boolean down = rank-1 > -1;
		boolean left = file -1> -1;
		boolean right = file+1< 8;
		if (up) {
			if(left) {
				if(board[rank+1][file-3]==null||board[rank+1][file-1].team==opponent) { //up left
					moves.add(new Move(curLoc, new Location(rank+1, file-1)));
				}
			}
			if(right) {
				if(board[rank+1][file+3]==null||board[rank+1][file+1].team==opponent) { //up right
					moves.add(new Move(curLoc, new Location(rank+1, file+1)));
				}
			}
		}
		if (down) {
			if(left) {
				if(board[rank-1][file-3]==null||board[rank-1][file-1].team==opponent) { //down left
					moves.add(new Move(curLoc, new Location(rank-1, file-1)));
				}
			}
			if(right) {
				if(board[rank-1][file+3]==null||board[rank-1][file+1].team==opponent) { //down right
					moves.add(new Move(curLoc, new Location(rank-1, file+1)));
				}
			}
		}
		return moves;
	}

}
