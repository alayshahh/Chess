package chess;

import java.util.ArrayList;
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
				if(board[rank+1][file-1]==null||board[rank+1][file-1].team==opponent) { //upright
					moves.add(new Move(curLoc, new Location(rank+1, file-1)));
				}
			}
			if (up) {
				if(board[rank+1][file]==null||board[rank+1][file].team==opponent) { //up
					moves.add(new Move(curLoc, new Location(rank+1, file)));
				}
			}
			if(right) {
				if(board[rank+1][file+1]==null||board[rank+1][file+1].team==opponent) { //upleft
					moves.add(new Move(curLoc, new Location(rank+1, file+1)));
				}
			}
		}
		if (down) {
			if(left) {
				if(board[rank-1][file-1]==null||board[rank-1][file-1].team==opponent) { //down right
					moves.add(new Move(curLoc, new Location(rank-1, file-1)));
				}
			}
			if (down) {
				if(board[rank-1][file]==null||board[rank-1][file].team==opponent) { //down
					moves.add(new Move(curLoc, new Location(rank-1, file)));
				}
			}
			if(right) {
				if(board[rank-1][file+1]==null||board[rank-1][file+1].team==opponent) { //down left
					moves.add(new Move(curLoc, new Location(rank-1, file+1)));
				}
			}
		}
		if(left) {
			if(board[rank][file-1]==null||board[rank][file-1].team==opponent) { // left
				moves.add(new Move(curLoc, new Location(rank, file-1)));
			}
		}
		if(right) {
			if(board[rank][file+1]==null||board[rank][file+1].team==opponent) { // right
				moves.add(new Move(curLoc, new Location(rank, file+1)));
			}
		}
		return moves;
	}
}
