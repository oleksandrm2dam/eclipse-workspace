package rurales;

import java.util.ArrayList;

public class Accommodation {
	
	private String name;
	private String address;
	private String phoneNumber;
	private ArrayList<Room> rooms;
	
	public Accommodation(String name, String address, String phoneNumber, ArrayList<Room> rooms) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.rooms = rooms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "Accommodation [name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + ", rooms="
				+ rooms + "]";
	}
	
}
