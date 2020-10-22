package chess;


/**
 * @author Alay Shah & Anshika Khare
*/

public enum Team {
	WHITE("White"), BLACK("Black");
	private String teamName;
	
	
	/**
	 * @param name Gives the value a teamName attribute
	 */
	private Team(String teamName) {
		this.teamName = teamName;
	}
	
	public String toString() {
		return teamName;
	}
}
