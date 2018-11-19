package ejercicios5Serializable;

import java.io.Serializable;

public class Contact implements Serializable {
	
	private String name;
	private String lastName;
	private String address;
	private String email;
	private String phoneNumber;
	
	public Contact(String name, String lastName, String address, String email, String phoneNumber) {
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", lastName=" + lastName + ", address=" + address + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	
	
	
}
