package chess;


/**
 * @author Alay Shah & Anshika Khare
*/

public enum Team {
	WHITE("White"), BLACK("Black");
	private String name;
	private Team(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
