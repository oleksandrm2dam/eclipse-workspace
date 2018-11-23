package entities;

import java.util.ArrayList;

public class Contact {
	
	private String name;					// Required
	private String lastName;				// Required
	private String alias;					// Can be empty
	private ArrayList<String> email;		// Can be empty or unlimited
	private String address;					// Can be empty
	private String landlinePhoneNumber;		// Can be empty
	private ArrayList<String> phoneNumber;	// Can be empty or unlimited
	
	// START Constructors
	public Contact() {
		
	}
	
	public Contact(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
	}
	
	public Contact(String name, String lastName, String alias, ArrayList<String> email, String address,
			String landlinePhoneNumber, ArrayList<String> phoneNumber) {
		this.name = name;
		this.lastName = lastName;
		this.alias = alias;
		this.email = email;
		this.address = address;
		this.landlinePhoneNumber = landlinePhoneNumber;
		this.phoneNumber = phoneNumber;
	}
	// END Constructors
	
	// START Getters and Setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public ArrayList<String> getEmail() {
		return email;
	}
	
	public void setEmail(ArrayList<String> email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getLandlinePhoneNumber() {
		return landlinePhoneNumber;
	}
	
	public void setLandlinePhoneNumber(String landlinePhoneNumber) {
		this.landlinePhoneNumber = landlinePhoneNumber;
	}
	
	public ArrayList<String> getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(ArrayList<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	// END Getters and Setters

	@Override
	public String toString() {
		return "Contact [name=" + name + ", lastName=" + lastName + ", alias=" + alias + ", email=" + email
				+ ", address=" + address + ", landlinePhoneNumber=" + landlinePhoneNumber + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
}
