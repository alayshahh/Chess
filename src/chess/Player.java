package chess;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alay Shah
 * @author Anshika Khare
 *
 */
public class Player {
	

	

	private final Team team;
	public King king;
	private List<Piece> pieces;
	private List<Move> possibleMoves = new ArrayList<>();
	private List<Move> validMoves = new ArrayList<>();
	
	
	/**
	 * @return Team of Player (BLACK or WHITE)
	 * @see Team
	 */
	public Team getTeam() {
		return team;
	}
	
	/**
	 * @param team Team
	 * @param king King Piece that belongs to player
	 * @see King
	 * @see Team
	 */
	public Player(Team team, King king) {
		this.team = team;
		this.king = king;
	}
	
	/**
	 * @return List of all valid moves that can be executed by the player
	 * @see Move
	 */
	public List<Move> getMoves(){
		return validMoves;
		
	}
	
	
	/**
	 * Sets the List of Possible moves, by going through list of pieces for the player and getting allPossibleMoves
	 * @param b Game Board
	 * @see Piece
	 * @see Piece#allPossibleMoves(Board)
	 * @see #pieces
	 */
	private void getPossibleMoves(Board b) {
		possibleMoves.clear();
		for(Piece p: pieces) {
			possibleMoves.addAll(p.allPossibleMoves(b));
		}
	}
	
	/**
	 * Filters list of possible Moves and reduces it down to the moves that do not endager the king.
	 * @param b Board
	 * @see Move
	 * @see #possibleMoves
	 * @see #validMoves
	 * @see #inCheck(Piece[][], Location)
	 * 
	 */
	public void getValidMoves(Board b) {
		getPossibleMoves(b);
		for(Move m: possibleMoves) {
			if(inCheck(b.board,null)&&m.type==MoveType.CASTLING) {
				continue;
			}
			
			Piece [][] board = new Piece [8][8];
			for(int i =0;i<8;i++) {
				for(int j =0; j<8; j++) {
					if(b.board[i][j]!=null) {
						board[i][j] = b.board[i][j].clone();
					}else {
						board[i][j]=null;
					}
				}
			}
			if (m.type==MoveType.CASTLING) {
				Piece [] [] castlingCheck = new Piece[8][8]; 
				for(int i =0;i<8;i++) {
					for(int j =0; j<8; j++) {
						if(b.board[i][j]!=null) {
							castlingCheck[i][j] = b.board[i][j].clone();
						}else {
							castlingCheck[i][j]=null;
						}
					}
				}
				if(m.next.getFile() == 6) { //checks if the square the king passes through is under check
					castlingCheck[m.next.getRank()][m.next.getFile()-1] = castlingCheck[m.cur.getRank()][m.cur.getFile()];
					castlingCheck[m.cur.getRank()][m.cur.getFile()] = null;
					if(inCheck(castlingCheck,new Location(m.next.getRank(),m.next.getFile()-1))) {
						continue;
					}
				}else {
					castlingCheck[m.next.getRank()][m.next.getFile()+1] = castlingCheck[m.cur.getRank()][m.cur.getFile()];
					castlingCheck[m.cur.getRank()][m.cur.getFile()] = null;
					if(inCheck(castlingCheck,new Location(m.next.getRank(),m.next.getFile()+1))) {
						continue;
					}
				}
			}
			board[m.next.getRank()][m.next.getFile()] = board[m.cur.getRank()][m.cur.getFile()];
			board[m.cur.getRank()][m.cur.getFile()] = null;
			
			//dont need to check for promotion or doublemove bc they will not capture a piece or move another piece to block the king
			
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
	 * Gets all pieces from game board
	 * @param b Board
	 * @see Board
	 * @see Piece
	 * 
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
	 * Checks if king is in danger. If a move can put king in check or king is still in check after the move, it is invalid.
	 * @param b Board
	 * @param m takes in a Move
	 * @return True if king is in danger
	 * @see Board
	 * @see #king
	 * @see King
	 * @see Move
	 */
	public boolean inCheck(Piece[][] b, Location next) {
		Team op;
		op = team==Team.WHITE? Team.BLACK : Team.WHITE;
		Location kingLoc;
		if(next==null) {
			kingLoc = king.curLoc;
		}else {
			if(b[next.getRank()][next.getFile()]!=null && b[next.getRank()][next.getFile()].type == PieceType.KING && b[next.getRank()][next.getFile()].team == team) {
				kingLoc = next;
				
			} else kingLoc = king.curLoc;
		}
//		System.out.println(kingLoc);
		int rnk = kingLoc.getRank();
		int fle = kingLoc.getFile();
		
		
		//check up for rook & Queen && check for king on the first square up
		for (int i =rnk+1; i<8; i++) {
			if(b[i][fle]!=null) {
				if(b[i][fle].team==op) {
					if(i==rnk+1 && b[i][fle].type==PieceType.KING) {
//						System.out.println(b[i][fle]);
						return true;
					}
					if(b[i][fle].type==PieceType.QUEEN||b[i][fle].type==PieceType.ROOK) {
//						System.out.println(b[i][fle]);
						return true;
					}else break;
				}else break; //same team
			}
		}
		
		//check down for rook and Queen && check for king on the first square down
		for(int i = rnk-1; i>-1; i--) {
			if(b[i][fle]!=null) {
				if(b[i][fle].team==op) {
					if(i==rnk-1 && b[i][fle].type==PieceType.KING) {
//						System.out.println(b[i][fle]);
						return true;
					}
					if(b[i][fle].type==PieceType.QUEEN||b[i][fle].type==PieceType.ROOK) {
//						System.out.println(b[i][fle]);
						return true;
					}else break;
				}else break; //same team
			}
		}
		//check right for rook and Queen && check for king on the first square right
		for(int i = fle+1; i<8; i++) {
			if(b[rnk][i]!=null) {
				if(b[rnk][i].team==op) {
					if(i==fle+1 && b[rnk][i].type==PieceType.KING) {
//						System.out.println(b[rnk][i]);
						return true;
					}
					if(b[rnk][i].type==PieceType.QUEEN||b[rnk][i].type == PieceType.ROOK) {
//						System.out.println(b[rnk][i]);
						return true;
					}else break;
				}else break;
			}
		}
		//check left for rook and Queen && check for king on the first square left
		for(int i = fle-1; i>-1; i--) {
			if(b[rnk][i]!=null) {
				if(b[rnk][i].team==op) {
					if(i==fle-1 && b[rnk][i].type==PieceType.KING) {
//						System.out.println(b[rnk][i]);
						return true;
					}
					if(b[rnk][i].type==PieceType.QUEEN||b[rnk][i].type == PieceType.ROOK) {
//						System.out.println(b[rnk][i]);
						return true;
					}else break;
				}else break;
			}
		}
		
		
		//check up right
		for( int i = rnk+1, j = fle +1; i<8 && j<8; i++, j++) {
			if(b[i][j]!=null) {
				if(b[i][j].team==op) {
					if(i==rnk+1 && j == fle+1 && b[i][j].type==PieceType.KING) {
//						System.out.println(b[i][j]);
						return true;
					}
					if(b[i][j].type==PieceType.QUEEN||b[i][j].type==PieceType.BISHOP) {
//						System.out.println(b[i][j]);
						return true;
					}else break;
				}else break;
			}
		}
		//check up left
		for( int i = rnk+1, j = fle -1; i<8 && j>-1; i++, j--) {
			if(b[i][j]!=null) {
				if(b[i][j].team==op) {
					if(i==rnk+1 && j == fle-1 && b[i][j].type==PieceType.KING) {
						System.out.println(b[i][j]);
						return true;
					}
					if(b[i][j].type==PieceType.QUEEN||b[i][j].type==PieceType.BISHOP) {
//						System.out.println(b[i][j]);
						return true;
					}else break;
				}else break;
			}
		}
		//check down right
		for( int i = rnk-1, j = fle +1; i>-1 && j<8; i--, j++) {
			if(b[i][j]!=null) {
				if(b[i][j].team==op) {
					if(i==rnk-1 && j == fle+1 && b[i][j].type==PieceType.KING) {
//						System.out.println(b[i][j]);
						return true;
					}
					if(b[i][j].type==PieceType.QUEEN||b[i][j].type==PieceType.BISHOP) {
//						System.out.println(b[i][j]);
						return true;
					}else break;
				}else break;
			}
		}
		//check down left
		for( int i = rnk-1, j = fle -1; i>-1 && j>-1; i--, j--) {
			if(b[i][j]!=null) {
				if(b[i][j].team==op) {
					if(i==rnk-1 && j == fle-1 && b[i][j].type==PieceType.KING) {
//						System.out.println(b[i][j]);
						return true;
					}
					if(b[i][j].type==PieceType.QUEEN||b[i][j].type==PieceType.BISHOP) {
//						System.out.println(b[i][j]);
						return true;
					}else break;
				}else break;
			}
		}
		
		//check knight -> 8 postions
		
		int [] [] knightPos = {{rnk+1, fle+2},{rnk+2,fle+1},{rnk-2, fle+1}, {rnk-1, fle+2},{rnk-2, fle-1}, {rnk-1,fle-2}, {rnk+1,fle-2}, {rnk+2,fle-1}};
		for( int [] loc: knightPos) {
			if(loc[0]>-1 && loc[0]<8 && loc[1]>-1 && loc[1]<8) {
				if(b[loc[0]][loc[1]]!=null && b[loc[0]][loc[1]].team==op && b[loc[0]][loc[1]].type==PieceType.KNIGHT) {
					return true;
				}
			}
		}
		
		//check pawn -> done by team
		int [] [] pwnPos = {{rnk,fle-1},{rnk,fle+1}};
		if(team==Team.WHITE) { //pawns will have a higher rank because they can only move up
			pwnPos[0][0]++; 
			pwnPos[1][0]++;
		}else {
			pwnPos[0][0]--; //pawns will have a lower rank bc they can only move up
			pwnPos[1][0]--;
		}
		for(int [] loc: pwnPos) {
			if(loc[0]>-1 && loc[0]<8 && loc[1]>-1 && loc[1]<8) {
				if(b[loc[0]][loc[1]]!=null && b[loc[0]][loc[1]].team==op && b[loc[0]][loc[1]].type==PieceType.PAWN) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	
	

}
