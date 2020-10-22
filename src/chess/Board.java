package chess;

/**
 * @author Alay Shah & Anshika Khare
*/
public class Board {
	
	/**
	 * 2D array of Pieces, represents the actual chess board
	 */
	public Piece[][] board;
	
	
	/**
	 * Creates new Board object with an empty 8 x 8 piece array.
	 */
	public Board() {
		board = new Piece [8][8];
	}
	
	/**
	 * Prints out the piece array, filling the empty square with the corresponding color.
	 */
	public void printBoard() {
		for( int i = 7; i>=0; i--) {
			for( int j = 0 ; j<=8; j++) {
				if(j==8) {
					System.out.println((i+1)+" ");
					continue;
				}
				if(board[i][j]!=null) {
					System.out.print(board[i][j].toString()+" ");
				} else {
					if(i%2!=0) {
						if(j%2==0) {
							System.out.print("   ");
						}else System.out.print("## ");
					}else {
						if(j%2==0) {
							System.out.print("## ");
						}else System.out.print("   ");
					}
				}
			}
		}
		System.out.println(" a  b  c  d  e  f  g  h \n");
	}
}
