public class LaunchPayroll {

	public static void main(String[] args) {
		boolean exit = false;
		Payroll p = new Payroll();
		
		//Loop until exit boolean is returned as true from Payroll
		while (!exit) {
			try {
				exit = p.processMenuSelection();
			} catch (Exception e) {
				System.out.println("Something went wrong in main:" + e.toString());
				//e.printStackTrace();
			}
		}

		/*
		 * Originally just created arrays/objects of ContractEmp and PermEmp
		 * and processed them with pay() in Payroll class
		 * single employee pay(Employee ref) and group pay(Employee[] empArr)
		 */
		
//		ContractEmployee cm2 = new ContractEmployee(2, "James Carter", 15, 200);
//		ContractEmployee cm3 = new ContractEmployee(5, "Jimmy Lee", 5, 500);
//		PermanentEmployee em1 = new PermanentEmployee(3, "Mary Sue", 2000, 1000, 5.0f);
//		PermanentEmployee em2 = new PermanentEmployee(4, "Gary Stu", 4000, 1500, 12.5f);
//		PermanentEmployee em3 = new PermanentEmployee(6, "Yura Soup", 10000, 4500, 30.85f);
// 		
//		cm: wage*hoursWorked
//		p.pay(cm1); // 20*160 = 3200
//		p.pay(cm2); // 15*200 = 3000
//
//		// em: Math.round(basicPay + hra + varComp)
//		p.pay(em1); // 2000 + 1000 + (0.07*2000) = 3140
//		p.pay(em2); // 4000 + 1500 + (0.12*4000) = 5980
//
//		// Overloaded pay() to accept array of Employee objects
//		p.pay(empArr);

	}

}
