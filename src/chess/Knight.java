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
		int rnk = getRank();
		int fle = getFile();
		int [] [] knightPos = {{rnk+1, fle+2},{rnk+2,fle+1},{rnk-2, fle+1}, {rnk-1, fle+2},{rnk-2, fle-1}, {rnk-1,fle-2}, {rnk+1,fle-2}, {rnk+2,fle-1}};
		for( int [] loc: knightPos) {
			if(loc[0]>-1 && loc[0]<8 && loc[1]>-1 && loc[1]<8) {
				if(board[loc[0]][loc[1]]==null || board[loc[0]][loc[1]].team==opponent) {
					moves.add(new Move(curLoc, new Location(loc[0], loc[1])));
				}
			}
		}
		return moves;
	}

	@Override
	public Piece clone() {
		Knight k = new Knight(team, curLoc);
		k.hasMoved = hasMoved;
		return k;
	}

}
