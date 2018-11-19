package rurales;

public class Room {
	
	private String type;
	private boolean bathroom;
	private float price;
	
	public Room(String type, boolean bathroom, float price) {
		this.type = type;
		this.bathroom = bathroom;
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isBathroom() {
		return bathroom;
	}

	public void setBathroom(boolean bathroom) {
		this.bathroom = bathroom;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Room [type=" + type + ", bathroom=" + bathroom + ", price=" + price + "]";
	}
	
}
