/*
 * PermanentEmployee Is-A Employee
 * PermanentEmployee has additional fields of:
 * Basic Pay (double)
 * HRA (double)
 * Experience (double) (in years)
 * Overrides Employee's calculateMonthlySalary() and displaySalary(); 
 */
public class PermanentEmployee extends Employee {
	protected double basicPay;
	protected double hra;
	protected double experience;

	public PermanentEmployee(int empID, String name, double basicPay, double hra, double experience, int subgroup) {
		super(empID, name, subgroup);
		this.basicPay = basicPay;
		this.hra = hra;
		this.experience = experience;
		calculateMonthlySalary();
	}
	
	@Override
	public void calculateMonthlySalary() {
		double varComp = 0.0;		//variable comp based on years of experiene
		
		//<3 years 0%
		//3 and below 4 years 5%
		//5 and below 10 years 7%
		//10 and above 12%
		if (experience < 3) {
			varComp = 0 * basicPay;
		} else if (experience >= 3 && experience < 5) {
			varComp = 0.05 * basicPay;
		} else if (experience >= 5 && experience < 10) {
			varComp = 0.07 * basicPay;
		} else if (experience >= 10) {
			varComp = 0.12 * basicPay;
		}
		//Permanent Employees' salary formula is Basic Pay + HRA + Variable Comp
		//rounded to nearest whole number as required by the original question statement
		setSalary(Math.round(basicPay + hra + varComp));
	}
	
	@Override
	public void displaySalary() {
		String format = "%-20s%s%n";
		System.out.printf(format, "Employee ID: ", getEmployeeID());
		System.out.printf(format, "Employee name: ", getEmployeeName());
		System.out.printf(format, "Employee Type: ", "Permanent Employee");
		System.out.printf(format, "Employee's Subgroup: ", getSubgroup());
		System.out.printf(format, "Employee's salary: ", "$" + String.format("%.0f", getSalary()) + "\n");
	}

	public double getBasicPay() {
		return basicPay;
	}

	public void setBasicPay(double basicPay) {
		this.basicPay = basicPay;
	}

	public double getHra() {
		return hra;
	}

	public void setHra(double hra) {
		this.hra = hra;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(float experience) {
		this.experience = experience;
	}

}
