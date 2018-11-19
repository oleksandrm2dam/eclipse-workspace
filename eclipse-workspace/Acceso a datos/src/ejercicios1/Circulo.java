package ejercicios1;

public class Circulo {

	private int xCenter, yCenter, radius;
	
	public Circulo() {
		this.radius = 0;
		this.xCenter = 0;
		this.yCenter = 0;
	}
	
	public Circulo(int radius) {
		this.radius = radius;
	}
	
	public Circulo(int radius, int xCenter, int yCenter) {
		this.radius = radius;
		this.xCenter = xCenter;
		this.yCenter = yCenter;
	}
	
	public double calcArea() {
		return Math.PI * radius * radius;
	}
	
	public double calcLength() {
		return Math.PI * radius * 2;
	}

	public int getxCenter() {
		return xCenter;
	}

	public void setxCenter(int xCenter) {
		this.xCenter = xCenter;
	}

	public int getyCenter() {
		return yCenter;
	}

	public void setyCenter(int yCenter) {
		this.yCenter = yCenter;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
}
