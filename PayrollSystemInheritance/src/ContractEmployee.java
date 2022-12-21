/*
 * ContractEmployee Is-A Employee
 * ContractEmployee has additional fields of:
 * Wage (double)
 * hoursWorked (double)
 * 
 * Overrides Employee's calculateMonthlySalary() and displaySalary(); 
 */
public class ContractEmployee extends Employee{
	protected double wage;
	protected double hoursWorked;

	public ContractEmployee(int EmpID, String name, double wage, double hoursWorked, int subgroup) {
		super(EmpID, name, subgroup);
		this.wage = wage;
		this.hoursWorked = hoursWorked;
		calculateMonthlySalary();
	}
	
	@Override
	public void calculateMonthlySalary() {
		//Contract Employee's salary formula is wage * hours worked
		setSalary(wage*hoursWorked);
	}
	
	@Override
	public void displaySalary() {
		String format = "%-20s%s%n";
		System.out.printf(format, "Employee ID: ", getEmployeeID());
		System.out.printf(format, "Employee Name: ", getEmployeeName());
		System.out.printf(format, "Employee Type: ", "Contract Employee");
		System.out.printf(format, "Employee's Subgroup: ", getSubgroup());
		System.out.printf(format, "Employee's salary: ", "$" + String.format("%.0f", getSalary()) + "\n");
		
	}

	public double getWage() {
		return wage;
	}

	public void setWage(double wage) {
		this.wage = wage;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

}
