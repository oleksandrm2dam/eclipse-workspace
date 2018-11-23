package entities;

import java.util.ArrayList;

public class Phonebook {
	
	private ArrayList<Contact> contacts;

	// START Constructors
	public Phonebook() {
		
	}
	
	public Phonebook(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
	// END Constructors
	
	// START Getters and Setters
	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
	// END Getters and Setters
	
	@Override
	public String toString() {
		return "Phonebook [contacts=" + contacts + "]";
	}
	
	// START Search methods
	public ArrayList<Contact> searchContactByName(String wantedName) {
		ArrayList<Contact> foundContacts = new ArrayList<Contact>();
		
		for(Contact currentContact : contacts) {
			if(currentContact.getName().equals(wantedName)) {
				foundContacts.add(currentContact);
			}
		}
		
		return foundContacts;
	}
	
	public ArrayList<Contact> searchContactByLastName(String wantedLastName) {
		ArrayList<Contact> foundContacts = new ArrayList<Contact>();
		
		for(Contact currentContact : contacts) {
			if(currentContact.getLastName().equals(wantedLastName)) {
				foundContacts.add(currentContact);
			}
		}
		
		return foundContacts;
	}
	
	public ArrayList<Contact> searchContactByAlias(String wantedAlias) {
		ArrayList<Contact> foundContacts = new ArrayList<Contact>();
		
		for(Contact currentContact : contacts) {
			if(currentContact.getAlias().equals(wantedAlias)) {
				foundContacts.add(currentContact);
			}
		}
		
		return foundContacts;
	}
	// END Search methods
		
}
