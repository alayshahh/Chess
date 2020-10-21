package chess;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alay Shah & Anshika Khare
*/


public class Pawn extends Piece {
	
	
	boolean lastMoveDouble;
	
	public Pawn(Team team, Location curLoc) {
		super(PieceType.PAWN, team, curLoc);
		lastMoveDouble = false;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return team == Team.BLACK ? "bp" : "wp" ;
	}


	@Override
	public List<Move> allPossibleMoves(Board gameBoard) {
		if(team==Team.WHITE) {
			return whitePawn(gameBoard.board);
		}else return blackPawn(gameBoard.board);
	}
	
	private List<Move> blackPawn(Piece [] [] board){
		List<Move> moves = new ArrayList<>();
		int rnk = getRank();
		int fle = getFile();
		if(!hasMoved) {
			if(board[rnk-2][fle]==null) {  //double on first move
				moves.add(new Move(curLoc, new Location(rnk-2,fle), MoveType.DOUBLEMOVE));
			}
		}
		if(board[rnk-1][fle]==null) { //normal one forward
			if(rnk==1) {
				moves.add(new Move(curLoc,new Location(rnk-1,fle), PieceType.QUEEN));
			}else moves.add(new Move(curLoc, new Location(rnk-1,fle)));
		}
		if(fle!=0) {
			if(board[rnk-1][fle-1]!=null&&board[rnk-1][fle-1].team==Team.WHITE) {
				if(rnk==1) {
					moves.add(new Move(curLoc, new Location(rnk-1,fle-1), PieceType.QUEEN));
				}else moves.add(new Move(curLoc, new Location(rnk-1,fle-1)));
			}
		}
		if(fle!=7) {
			if(board[rnk-1][fle+1]!=null&&board[rnk-1][fle+1].team==Team.WHITE) {
				if(rnk==1) {
					moves.add(new Move(curLoc, new Location(rnk-1,fle+1), PieceType.QUEEN));
				}else moves.add(new Move(curLoc, new Location(rnk-1,fle+1)));
			}
		}
		//enPassant
		if(rnk==3) {
			if(fle!=0) {
				if(board[3][fle-1]!=null&&board[3][fle-1].type==PieceType.PAWN&&board[4][fle-1].team==Team.WHITE) {
					Pawn p = (Pawn)board[3][fle-1];
					if(p.lastMoveDouble) {
						moves.add(new Move(curLoc,new Location(2,fle-1), p.curLoc)); //enPassant move
					}
				}
			}
			if(fle!=7) {
				if(board[3][fle+1]!=null&&board[3][fle+1].type==PieceType.PAWN&&board[4][fle-1].team==Team.WHITE) {
					Pawn p = (Pawn)board[3][fle+1];
					if(p.lastMoveDouble) {
						moves.add(new Move(curLoc,new Location(2,fle+1), p.curLoc)); //enPassant move
					}
				}

			}
		}
		for (Move m: moves) {
			System.out.println(m);
		}

		return moves;
	}
	private List<Move> whitePawn(Piece [] [] board){
		List<Move> moves = new ArrayList<>();
		int rnk = getRank();
		int fle = getFile();
		if(!hasMoved) {
			if(board[rnk+2][fle]==null) {  //double on first move
				moves.add(new Move(curLoc, new Location(rnk+2,fle), MoveType.DOUBLEMOVE));
			}
		}
		if(board[rnk+1][fle]==null) { //normal one forward
			if(rnk==7) {
				moves.add(new Move(curLoc,new Location(rnk+1,fle), PieceType.QUEEN));
			}else moves.add(new Move(curLoc, new Location(rnk+1,fle)));
		}
		if(fle!=0) {
			if(board[rnk+1][fle-1]!=null&&board[rnk+1][fle-1].team==Team.BLACK) {
				if(rnk==7) {
					moves.add(new Move(curLoc, new Location(rnk+1,fle-1), PieceType.QUEEN));
				}else moves.add(new Move(curLoc, new Location(rnk+1,fle-1)));
			}
		}
		if(fle!=7) {
			if(board[rnk+1][fle+1]!=null&&board[rnk+1][fle+1].team==Team.BLACK) {
				if(rnk==7) {
					moves.add(new Move(curLoc, new Location(rnk+1,fle+1), PieceType.QUEEN));
				}else moves.add(new Move(curLoc, new Location(rnk+1,fle+1)));
			}
		}
		if(rnk==4) {
			if(fle!=0) {
				if(board[4][fle-1]!=null&&board[4][fle-1].type==PieceType.PAWN&&board[4][fle-1].team==Team.BLACK) {
					Pawn p = (Pawn)board[4][fle-1];
					if(p.lastMoveDouble) {
						moves.add(new Move(curLoc,new Location(5,fle-1), p.curLoc)); //enPassant move
					}
				}
			}
			if(fle!=7) {
				if(board[4][fle+1]!=null&&board[4][fle+1].type==PieceType.PAWN&&board[4][fle-1].team==Team.BLACK) {
					Pawn p = (Pawn)board[4][fle+1];
					if(p.lastMoveDouble) {
						moves.add(new Move(curLoc,new Location(5,fle+1), p.curLoc)); //enPassant move
					}
				}
			}
		}
		for (Move m: moves) {
			System.out.println(m);
		}

		return moves;
	}

	
	
}
