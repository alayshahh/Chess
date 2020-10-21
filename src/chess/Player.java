package chess;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alay Shah & Anshika Khare
 *
 */
public class Player {
	

	/**
	 * @return
	 */
	public Team getTeam() {
		return team;
	}

	private final Team team;
	public final King king;
	private List<Piece> pieces;
	private List<Move> possibleMoves = new ArrayList<>();
	private List<Move> validMoves = new ArrayList<>();
	
	
	public Player(Team team, King king) {
		this.team = team;
		this.king = king;
	}
	
	public void getPossibleMoves(Board b) {
		possibleMoves.clear();
		for(Piece p: pieces) {
			possibleMoves.addAll(p.allPossibleMoves(b));
		}
	}
	
	/**
	 * @param b
	 * 
	 */
	public void validateMoves(Board b) {
		
		for(Move m: possibleMoves) {
			Piece [][] board = b.board.clone();
			board[m.next.getRank()][m.next.getFile()] = board[m.cur.getRank()][m.cur.getFile()];
			board[m.cur.getRank()][m.cur.getFile()] = null;
			if(m.type==MoveType.ENPASSANT) {
				board[m.other.cur.getRank()][m.other.cur.getFile()] = null;
			}
			if(m.type==MoveType.CASTLING) {
				board[m.other.next.getRank()][m.other.next.getFile()]= board[m.other.cur.getRank()][m.other.cur.getFile()];
				board[m.other.cur.getRank()][m.other.cur.getFile()] = null;
			}
			if(!inCheck(board, m.next)) {
				validMoves.add(m);
			}
		}
		
	}
	
	/**
	 * @param b
	 */
	public void getPieces(Board b){

		List<Piece> pieces = new ArrayList<>();

		for( int i =0; i<8; i++) {
			for(int j =0; j<8; j++) {
				if(b.board[i][j]==null) {
					continue;
				}
				if(b.board[i][j].getTeam()==team) {
					if(b.board[i][j].type == PieceType.PAWN) {
						//set last move doubleto false
						Pawn pwn = (Pawn)b.board[i][j];
						pwn.lastMoveDouble = false;
						pieces.add(pwn);
					}else {
						pieces.add(b.board[i][j]);
					}
				}
			}
		}
		this.pieces = pieces;
		//clear the possible moves and valid moves
		possibleMoves.clear();
		validMoves.clear();

	}
	
	/**
	 * @param b
	 * @param m takes in a move
	 * @return if King is in check because of the move, then it will return true.
	 */
	public boolean inCheck(Piece[][] b, Location next) {
		Team op;
		op = team==Team.WHITE? Team.BLACK : Team.WHITE;
		Location kingLoc;
		if(b[next.getRank()][next.getRank()].type == PieceType.KING && b[next.getRank()][next.getFile()].team == team) {
			kingLoc = next;
		} else kingLoc = king.curLoc;
		int rnk = kingLoc.getRank();
		int fle = kingLoc.getRank();
		
		
		//check up for rook & Queen && check for king on the first square up
		for (int i =rnk+1; i<8; i++) {
			if(b[i][fle]!=null) {
				if(b[i][fle].team==op) {
					if(i==rnk+1 && b[i][fle].type==PieceType.KING) {
						return true;
					}
					if(b[i][fle].type==PieceType.QUEEN||b[i][fle].type==PieceType.ROOK) {
						return true;
					}
				}else break; //same team
			}
		}
		
		//check down for rook and Queen && check for king on the first square down
		for(int i = rnk-1; i>-1; i--) {
			if(b[i][fle]!=null) {
				if(b[i][fle].team==op) {
					if(i==rnk-1 && b[i][fle].type==PieceType.KING) {
						return true;
					}
					if(b[i][fle].type==PieceType.QUEEN||b[i][fle].type==PieceType.ROOK) {
						return true;
					}
				}else break; //same team
			}
		}
		//check right for rook and Queen && check for king on the first square right
		for(int i = fle+1; i<8; i++) {
			if(b[rnk][i]!=null) {
				if(b[rnk][i].team==op) {
					if(i==fle+1 && b[rnk][i].type==PieceType.KING) {
						return true;
					}
					if(b[rnk][i].type==PieceType.QUEEN||b[rnk][i].type == PieceType.ROOK) {
						return true;
					}
					
				}else break;
			}
		}
		//check left for rook and Queen && check for king on the first square left
		for(int i = fle-1; i>-1; i--) {
			if(b[rnk][i]!=null) {
				if(b[rnk][i].team==op) {
					if(i==fle-1 && b[rnk][i].type==PieceType.KING) {
						return true;
					}
					if(b[rnk][i].type==PieceType.QUEEN||b[rnk][i].type == PieceType.ROOK) {
						return true;
					}
					
				}else break;
			}
		}
		
		//check up left
		//check up rihgt
		//check down left
		//check down right
		//check 
			
		
		
		
		
		
		
		
		
		
		
		
		return false;
	}
	
	
	
	

}
