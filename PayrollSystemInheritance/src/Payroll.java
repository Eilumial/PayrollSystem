/*
 *	Payroll class
 *	Handles logic for displaying and paying salaries of employees
 *	pay() is overloaded to take either 
 *	a. single Employee object, 
 *	b. ArrayList of Employee objects belonging to same subgroup
 *	c. HashMap of all Employee objects
 *	
 *	
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Payroll {
	Scanner sc = new Scanner(System.in);

	HashMap<Integer, Employee> empMap = populateMap(); // Record of all Employees, using this in place of a database
	ArrayList<Integer> empPaidList = new ArrayList<>(); // Record of Employee IDs who have already been paid

	// Pre-populate hashmap in place of DB
	public static HashMap<Integer, Employee> populateMap() {
		HashMap<Integer, Employee> empMap = new HashMap<Integer, Employee>();

		empMap.put(1, new ContractEmployee(1, "John Smith", 20, 160, 1));
		empMap.put(2, new ContractEmployee(2, "James Carter", 15, 200, 1));
		empMap.put(3, new PermanentEmployee(3, "Mary Sue", 2000, 1000, 5.0f, 1));
		empMap.put(4, new PermanentEmployee(4, "Gary Stu", 4000, 1500, 12.5f, 2));
		empMap.put(5, new ContractEmployee(5, "Jimmy Lee", 5, 500, 2));
		empMap.put(6, new PermanentEmployee(6, "Yura Soup", 10000, 4500, 30.85f, 3));

		return empMap;
	}

	// Display menu options and take input for user selection
	int menuOption() {
		System.out.println("\n=Please select salary processing option=");
		System.out.println("1. Process single employee salary");
		System.out.println("2. Process sub-group employee salary");
		System.out.println("3. Process salary for entire company");
		System.out.println("4. Create new Employee");
		System.out.println("5. Exit system");
		int select = 0;
		try {
			if (sc.hasNextLine()) {
				select = Integer.parseInt(sc.nextLine());
			}
		} catch (Exception e) {
			System.out.println("Invalid Input.");
		}
		return select;
	}

	// Switch case branching based on user input in main menu
	boolean processMenuSelection() {
		int select = menuOption();
		boolean exit = false;
		try {
			switch (select) {

			// Single Employee
			case 1:
				int empID = empIDInput();
				// check for valid empID input (between 1 and current total employee count)
				// do not have Delete or Update functions so empID will always be 1 and above
				// will have to change the hardcoded empID > 0 if those functions are introduced
				if (empID > 0 && empID <= empMap.size()) {
					Employee emp = empMap.get(empID);
					if (emp != null) {
						pay(emp); // overloaded
					} else {
						System.out.println("Invalid Employee ID");
					}
				} else {
					System.out.println("Invalid input");
				}

				break;

			// Sub-group Employee
			case 2:
				// retrieve Employee objects belonging to user inputed subgroup into ArrList for
				// processing
				ArrayList<Employee> empList = empSubgrpInput(empMap);
				if (empList.size() > 0) {
					pay(empList); // overloaded
				} else {
					// if empList's size is 0 and below that means all have been paid
					System.out.println("All Employees of Subgroup has already been paid.");
				}
				break;

			// Entire company
			case 3:
				pay(empMap);
				break;

			// Create new Employee
			case 4:
				displayCreateEmp();
				break;
			// Exit
			case 5:
				exit = true;
				System.out.println("Exiting system.");
				break;
			// Wrong input
			default:
				System.out.println("Unauthorized access, contacting security");
				exit = true;
				break;
			}
		} catch (Exception e) {
			System.out.println("Invalid Input.");
		}

		return exit;
	}

//	Request user input for Employee's ID
	int empIDInput() {

		System.out.println("\n=Please enter Employee ID to pay=");
		int empID = 0;
		if (sc.hasNextLine()) {
			empID = Integer.parseInt(sc.nextLine());
		}

		return empID;
	}

	// Request user input for Subgroup number
	ArrayList<Employee> empSubgrpInput(HashMap<Integer, Employee> empMap) {

		System.out.println("\n=Please enter Employee Sub-group to pay=");

		int subgrp = 0;
		if (sc.hasNextLine()) {
			subgrp = Integer.parseInt(sc.nextLine());
		}
		ArrayList<Employee> empList = empFilterBySubgrp(subgrp, empMap); // filter employee objects based on subgrp
																			// input
		return empList;
	}

	// filter employee objects based on subgrp input and add to ArrList
	ArrayList<Employee> empFilterBySubgrp(int subgrp, HashMap<Integer, Employee> empMap) {

		ArrayList<Employee> empList = new ArrayList<>();
		// Iterate map and compared employee object's subGroup attribute vs subgrp
		for (HashMap.Entry<Integer, Employee> set : empMap.entrySet()) {
			if (set.getValue().getSubgroup() == subgrp) {
				empList.add(set.getValue());
			}
		}
		return empList;

	}

	// basic pay() for single Employee
	void pay(Employee ref) {
		if (!checkPaid(ref.getEmployeeID())) {

			System.out.println("=Processing single Employee salary=");
			ref.displaySalary();
			updatePaid(ref.getEmployeeID()); // add to arrList of EmpIDs of paid employees
		} else {
			displayEmpPaid(ref);
		}

	}

	// Overloaded pay() to accept ArrayList of Employee objects
	// 2. Process sub-group employee salary
	void pay(ArrayList<Employee> empList) {

		System.out.println("\n=Processing group Employee salary=\n");

		int count = 1;
		// Iterate list of employees in subgroup
		for (Employee ref : empList) {
			// if not in paid arrList (i.e. unpaid)
			if (!checkPaid(ref.getEmployeeID())) {
				System.out
						.println("Processing Unpaid Employee #" + count++ + " of subgroup #" + ref.getSubgroup() + "*");
				ref.displaySalary();
				updatePaid(ref.getEmployeeID());
			} else {
				displayEmpPaid(ref);
			}

		}
	}

	// Overloaded pay() to accept HashMap of all Employee objects
	// 3. Process salary for entire company
	void pay(HashMap<Integer, Employee> empMap) {

		System.out.println("=Processing group Employee salary=\n");

		// Iterate entire map of Employees, pay everyone who is not already in paid
		// arrList
		for (int i = 1; i <= empMap.size(); i++) {
			if (!checkPaid(empMap.get(i).getEmployeeID())) {
				System.out.println("*Employee #" + i + " of group size " + empMap.size() + "*");
				empMap.get(i).displaySalary();
				updatePaid(empMap.get(i).getEmployeeID());
			} else {
				displayEmpPaid(empMap.get(i));
			}
		}
	}

	// 4. Create new Employee record/object, place into Map
	void displayCreateEmp() {
		// PermanentEmployee(int empID, String name, double basicPay, double hra, double
		// experience, int subgroup)
		// ContractEmployee(int EmpID, String name, double wage, double hoursWorked, int
		// subgroup)
		try {
			System.out.println("=Create New Employee=\n");
			System.out.println("Please enter Employee's name:");
			String name = sc.nextLine();

			System.out.println("Please select Employee type:");
			System.out.println("Enter 1 for Permanent Employee");
			System.out.println("Enter 2 for Contract Employee");
			int input = Integer.parseInt(sc.nextLine());

			int subgroup;

			// Branches based on Employee type
			switch (input) {
			case 1:
				// Create Permanent Employee record
				System.out.println("Please enter Employee's Basic Pay:");
				double basicPay = Double.parseDouble(sc.nextLine());

				System.out.println("Please enter Employee's HRA:");
				double hra = Double.parseDouble(sc.nextLine());

				System.out.println("Please enter Employee's Experience in years:");
				double experience = Double.parseDouble(sc.nextLine());

				System.out.println("Please enter Employee's subgroup:");
				subgroup = Integer.parseInt(sc.nextLine());

				createPermEmp(name, basicPay, hra, experience, subgroup);
				break;
			case 2:
				// Create Contract Employee record
				System.out.println("Please enter Employee's Wage:");
				double wage = Double.parseDouble(sc.nextLine());

				System.out.println("Please enter Employee's Hours Worked:");
				double hoursWorked = Double.parseDouble(sc.nextLine());

				System.out.println("Please enter Employee's subgroup:");
				subgroup = Integer.parseInt(sc.nextLine());

				createConEmp(name, wage, hoursWorked, subgroup);
				break;
			default:
				System.out.println("Invalid input.");
				break;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// Creates permEmp obj and put into Map
	void createPermEmp(String name, double basicPay, double hra, double experience, int subgroup) {
		// PermanentEmployee(int empID, String name, double basicPay, double hra, double
		// experience, int subgroup)
		int empIDCurr = empMap.size() + 1;
		empMap.put((empIDCurr), new PermanentEmployee(empIDCurr, name, basicPay, hra, experience, subgroup));
		System.out.println("Permenant Employee " + name + " (Employee ID: " + empIDCurr + ") created");
	}

	// Creates contEmp obj and put into Map
	void createConEmp(String name, double wage, double hoursWorked, int subgroup) {
		// ContractEmployee(int EmpID, String name, double wage, double hoursWorked, int
		// subgroup)
		int empIDCurr = empMap.size() + 1;
		empMap.put((empIDCurr), new ContractEmployee(empIDCurr, name, wage, hoursWorked, subgroup));
		System.out.println("Contract Employee" + name + " (Employee ID: " + empIDCurr + ") created");
	}

	// When a employee is paid, his Employee ID is added to the empPaidList
	void updatePaid(int empID) {
		if (!empPaidList.contains(empID)) {
			empPaidList.add(empID);
		}
	}

	// Checks whether empPaidList contains the received employee ID
	boolean checkPaid(int empID) {
		return empPaidList.contains(empID);
	}

	// Displays message that the selected Employee has already been paid
	void displayEmpPaid(Employee ref) {

		System.out.println("Employee " + ref.getEmployeeName() + " (Employee ID: " + ref.getEmployeeID()
				+ ") has already been paid.\n");

	}

}
