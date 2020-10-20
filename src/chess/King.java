package chess;

import java.util.ArrayList;
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
	public List<Move> allPossibleMoves( Board gameBoard) {
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
			if(board[rank+1][file]==null||board[rank+1][file].team==opponent) { //up
				moves.add(new Move(curLoc, new Location(rank+1, file)));
			}
			if(left) {
				if(board[rank+1][file-1]==null||board[rank+1][file-1].team==opponent) { //upright
					moves.add(new Move(curLoc, new Location(rank+1, file-1)));
				}
			}
			if(right) {
				if(board[rank+1][file+1]==null||board[rank+1][file+1].team==opponent) { //upleft
					moves.add(new Move(curLoc, new Location(rank+1, file+1)));
				}
			}
		}
		if (down) {
			if(board[rank-1][file]==null||board[rank-1][file].team==opponent) { //down
				moves.add(new Move(curLoc, new Location(rank-1, file)));
			}
			if(left) {
				if(board[rank-1][file-1]==null||board[rank-1][file-1].team==opponent) { //down right
					moves.add(new Move(curLoc, new Location(rank-1, file-1)));
				}
			}
			if(right) {
				if(board[rank-1][file+1]==null||board[rank-1][file+1].team==opponent) { //down left
					moves.add(new Move(curLoc, new Location(rank-1, file+1)));
				}
			}
		}
		if(left) {
			if(board[rank][file-1]==null||board[rank][file-1].team==opponent) { //down right
				moves.add(new Move(curLoc, new Location(rank, file-1)));
			}
		}
		if(right) {
			if(board[rank][file+1]==null||board[rank][file+1].team==opponent) { //down right
				moves.add(new Move(curLoc, new Location(rank, file+1)));
			}
		}
		if(!hasMoved) {
			List<Move> m = castling(board);
			moves.addAll(m);
		}
		
		
		return moves;
	}
	
	
	private List<Move> castling(Piece [][] board){
		List<Move> m = new ArrayList<>();
		int rnk = getRank();
		if(board[rnk][7]!=null&&board[rnk][7].type==PieceType.ROOK&& !board[rnk][7].hasMoved && board[rnk][7].team== team) {
			if(board[rnk][6]==null&&board[rnk][5]==null) {
				m.add(new Move(curLoc, new Location(rnk, 6), new Move(new Location(rnk,7), new Location(rnk,5))));
			}
		}
		if(board[rnk][0]!=null&&board[rnk][0].type==PieceType.ROOK&& !board[rnk][0].hasMoved && board[rnk][7].team==team) {
			if(board[rnk][1]==null&&board[rnk][2]==null&&board[rnk][3]==null) {
				m.add(new Move(curLoc, new Location(rnk,2), new Move(new Location(rnk,0), new Location(rnk,3))));
			}
		}
		return m;
	}


}
