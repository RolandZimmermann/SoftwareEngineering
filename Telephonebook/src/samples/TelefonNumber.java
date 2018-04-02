package samples;

public class TelefonNumber {
	private final String firstName;
	private final String lastName;
	private final String number;
	
	public TelefonNumber(String firstName, String lastName, String number) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getNumber() {
		return number;
	}
}
