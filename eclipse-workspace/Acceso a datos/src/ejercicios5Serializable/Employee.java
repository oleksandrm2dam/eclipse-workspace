package ejercicios5Serializable;

public class Employee extends Contact {

	private int employeeNumber;
	private float salary;
	
	public Employee(String name, String lastName, String address, String email, String phoneNumber, int employeeNumber, float salary) {
		super(name, lastName, address, email, phoneNumber);
		this.employeeNumber = employeeNumber;
		this.salary = salary;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Employee [employeeNumber=" + employeeNumber + ", salary=" + salary + "]";
	}
	
}
