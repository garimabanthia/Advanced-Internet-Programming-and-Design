package weatherData;

public class Location {
	private String city;
	private String state;

	public Location(String city, String state) {
		this.city = city;
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public boolean equals(Object cmpObj) {
		Location cmpLoc;

		if (!(cmpObj instanceof Location)) {
			return false;
		}

		cmpLoc = (Location) cmpObj;
		if (city.equals(cmpLoc.city) && state.equals(cmpLoc.state)) {
			return true;
		}

		return false;
	}

	public String toString() {
		return city + ", " + state;
	}

	// Return null if everything is OK, otherwise returns a String error message
	static public String validateData(String city, String state) {
		int i;
		char tstChar;

		if (city == null) {
			return "City is not valid";
		}

		if (state == null) {
			return "State is not valid";
		}

		if (state.length() != 2) {
			return "The state must be a two letter abbreviation";
		}

		for (i = 0; i < city.length(); i++) {
			tstChar = city.charAt(i);
			if (!Character.isLetter(tstChar) && tstChar != ' ') {
				return "Invalid character in city";
			}
		}

		for (i = 0; i < state.length(); i++) {
			tstChar = state.charAt(i);
			if (!Character.isLetter(tstChar)) {
				return "Invalid character in state";
			}
		}

		return null; // Everything is OK
	}
}