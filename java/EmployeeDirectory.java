import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDirectory {

   private List<Employee> employeeMap = new ArrayList<>();

    public List<Employee> findEmployeeByExperiencee(int experience) {
        return employeeMap
                .stream()
                .filter(employee -> employee.getExperience() == experience)
                .collect(Collectors.toList());
    }

    public List<Integer> findPhoneNumberByName(String name) {
        return employeeMap
                .stream()
                .filter(employee -> employee.getName().equalsIgnoreCase(name))
                .map(Employee::getPhoneNumber)
                .collect(Collectors.toList());
    }

    public Employee findEmployeeByPersonelNumber(int personelNumber) {
        return employeeMap
                .stream()
                .filter(employee -> employee.getPersonelNumber() == personelNumber)
                .findFirst().get();
    }

    public void addEmployee(Employee employee) {
        employeeMap.add(employee);
    }


}
