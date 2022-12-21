/*
 * Parent class Employee contains:
 * Employee ID (int), starting at 1
 * Employee's Name (String)
 * Subgroup (int)
 * abstract methods calculateMonthlySalary() and displaySalary() to be implemented in child classes 
 */
abstract public class Employee {
	protected int employeeID;
	protected String employeeName;
	protected double salary;
	protected int subgroup;

	Employee(int employeeID, String employeeName, int subgroup) {
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.subgroup = subgroup;
	}

	abstract public void calculateMonthlySalary();

	abstract public void displaySalary();

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getSubgroup() {
		return subgroup;
	}

	public void setSubgroup(int subgroup) {
		this.subgroup = subgroup;
	}

}
