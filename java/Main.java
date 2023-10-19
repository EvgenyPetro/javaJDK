public class Main {
    static EmployeeDirectory employeeDirectory = generateDir();


    public static void main(String[] args) {
        employeeDirectory.findEmployeeByExperiencee(5);
        employeeDirectory.findEmployeeByPersonelNumber(4);
        employeeDirectory.findPhoneNumberByName("Mary");
    }

    private static EmployeeDirectory generateDir() {
        employeeDirectory = new EmployeeDirectory();
        Employee employee1 = new Employee(1, 1234121, "John", 3);
        Employee employee2 = new Employee(2, 1314124, "Mary", 5);
        Employee employee3 = new Employee(3, 2342343, "Scot", 5);
        Employee employee4 = new Employee(4, 3453466, "Mark", 2);
        Employee employee5 = new Employee(5, 2123156, "Adam", 1);
        Employee employee6 = new Employee(6, 1233333, "Mary", 6);
        Employee employee7 = new Employee(7, 3434345, "Nikol", 7);

        employeeDirectory.addEmployee(employee1);
        employeeDirectory.addEmployee(employee2);
        employeeDirectory.addEmployee(employee3);
        employeeDirectory.addEmployee(employee4);
        employeeDirectory.addEmployee(employee5);
        employeeDirectory.addEmployee(employee6);
        employeeDirectory.addEmployee(employee7);

        return employeeDirectory;
    }
}
