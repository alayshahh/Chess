package chess;

public class Location {
	private int file;
	private int rank;
	public Location(String loc) {
		int f = loc.charAt(0);
		int r = loc.charAt(1);
		this.file=f-97;
		this.rank=r-49;
	}
	int getRank() {
		return rank;
	}
	int getFile() {
		return file;
	}
	void locationOnBoard() {
		System.out.println(rank+ " "+ file);
	}
}
