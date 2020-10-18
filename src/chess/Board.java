package chess;

public class Board {
	public static Piece[][] board  = new Piece [8] [8];
	
	public void printBoard() {
		for( int i = 0; i<8; i++) {
			for( int j = 0 ; j<=8; j++) {
				if(j==8) {
					System.out.println((8-i)+" ");
					continue;
				}
				if(board[i][j]!=null) {
					System.out.print(board[i][j].toString()+" ");
				} else {
					if(i%2==0) {
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
		System.out.println(" a  b  c  d  e  f  g  h ");
	}
}
