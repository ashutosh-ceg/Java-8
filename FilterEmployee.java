package src.streamprogram.userdefineobj;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterEmployee {

    public static void main(String[] args) {

// 1. Foiond the all employee from a specific city and count all. 
        List<Employee> empList = Arrays.asList(
                new Employee("Ashutosh", 100000, 1, "Noida", "IT"),
                new Employee("Raja", 50000, 2, "Bengaluru", "HR"),
                new Employee("Nanda", 40000, 3, "Chennai", "Testing"),
                new Employee("Shashank", 18000, 4, "Gurugram", "Accounts"),
                new Employee("Shekhar", 10000, 5, "Pune", "Testing"),
                new Employee("Ranjan", 30000, 6, "Hydrabad", "IT"),
                new Employee("Raghaw", 60000, 7, "Bengaluru", "IT"),
                new Employee("Ranjeet", 40000, 2, "Noida", "HR")
        );

        Predicate<Employee> pred = emp -> emp.getCity().equals("Bengaluru");

        empList.stream().filter(emp -> emp.getCity().equals("Bengaluru")).forEach(System.out::println);
        long count = empList.stream().filter(emp -> emp.getCity().equals("Bengaluru")).count();

        System.out.println("Total number of employee from Bengaluru is " + count);

        // 2.  Find the all employee where salary is greater than a particular value.
        System.out.println();
        System.out.println("Find the all employee where salary is greater than a particular value.");
        empList.stream().filter(emp -> emp.getSalary() > 30000).forEach(System.out::println);

        // 3. print the all employees names only in upper case and alphabetical order.
        System.out.println();
        System.out.println("Print the all employees names only in upper case and alphabetical order.");
        Function<Employee, String> fun = emp -> emp.getEmpName();

        empList.stream().map(emp -> emp.getEmpName().toUpperCase()).sorted().forEach(System.out::println);

        //4. get the first employee where department is HR, if not available throw an exception.
        System.out.println();
        System.out.println("get the first employee where department is HR, if not available throw an exception.");
        System.out.println();
        Optional<Employee> optEmp = empList.stream().filter(emp -> emp.getDepartment().equals("HR")).findFirst();
        optEmp.ifPresent(System.out::println);

        if (optEmp.isPresent()) {
            Employee emp = optEmp.get();
            System.out.println(emp);
        }
        // Employee emp1 = empList.stream().filter(emp -> emp.getDepartment().equals("HRO")).findFirst()
        //         .orElseThrow(() -> new EmployeeNotFoundException("Employee not found."));

        // System.out.println(emp1);
        // 5. Get the total salary of all the employee in a specific department.
        System.out.println();
        double totalSalary = empList.stream().filter(emp -> emp.getDepartment().equals("IT"))
                // .mapToDouble(emp->emp.getSalary()) // by suing lambda expresiion 
                .mapToDouble(Employee::getSalary) // Method reference 
                .sum();
        System.out.println("Total salary of all the employee in the IT department is " + totalSalary);

        double summinVal = empList.stream().filter(emp -> emp.getDepartment().equals("Testing"))
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println();
        System.out.println("Total salary of all the employee in the Testing department is " + summinVal);

        // ................... Intermediate Problems ..........................................
        // 6. Check if all employess have a salary greater then the specific value.
        boolean isAllMatch = empList.stream().allMatch(emp -> emp.getSalary() >= 10000);
        System.out.println("Check if all employess have a salary greater then 10000 " + isAllMatch);

        // 7. get the sum of salary of all the employees and the average salary.
        // In this case we are converting stream data into premitive type and then calling the aggregate function.
        double sum = empList.stream().mapToDouble(Employee::getSalary).sum();
        System.out.println("Sum of salary of all the employees is " + sum);

        // In this case we are using the static method of collectors class. 
        sum = empList.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println("Sum of salary of all the employees is " + sum);

        OptionalDouble avg = empList.stream().mapToDouble(Employee::getSalary).average();

        avg.ifPresent(System.out::println);

        double avg1 = empList.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("Average of salary of all the employees is " + avg1);

        // 8. Get the map of employee name and their salary
        Map<String, Double> empMap = empList.stream().collect(Collectors.toMap(emp -> emp.getEmpName(), Employee::getSalary));
        System.out.println();
        System.out.println("Employee name and their salary in a Map");
        empMap.forEach((key, val) -> System.out.println(key + " " + val));

        // 9 . Print the employess details from highest to lowest salary.
        System.out.println();
        System.out.println("Print the employess details from highest to lowest salary.");
        System.out.println();

        // empList.stream().sorted((emp1, emp2) -> ((Double) emp2.getSalary())
        //         .compareTo(emp1.getSalary())).forEach(System.out::println);
        // Or
        empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).forEach(System.out::println);

        // 10. get the employee with the highest salary
        System.out.println();
        System.out.println("Get the employee with the highest salary");

        // Optional<Employee> emOptional = empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).findFirst();
        // if (emOptional.isPresent()) {
        //     Employee emp1 = emOptional.get();
        //     System.out.println(emp1);
        // }
        // Best way if there is only one employee having highest salary 
        Optional<Employee> maxSalEmp = empList.stream().max(Comparator.comparing(Employee::getSalary));
        maxSalEmp.ifPresent(System.out::println);

        // If we have more than one one highest slary employee 
        double maxSal = empList.stream().mapToDouble(Employee::getSalary).max().getAsDouble(); // get the highest salary

        // filter the highest salary 
        empList.stream().filter(emp -> emp.getSalary() == maxSal).forEach(System.out::println);

        // 11. get the employee with second highest salary
        System.out.println();
        System.out.println("get the employee with second highest salary");

        // Note -> OptionalDouble is a primitive stream, Optional<Double> is a stream of double object. 
        // In case of more than two second highest salary
        // First we find the second highest salary.
        double secondHighestSal = empList.stream().map(Employee::getSalary).sorted((sal1, sal2) -> sal2.compareTo(sal1)).skip(1).findFirst().get();

        // Now find the employee having second highest salary
        empList.stream().filter(emp -> emp.getSalary() == secondHighestSal).forEach(System.out::println);

        // 12. Get the employees wo are earning above the average salary
        // Get the average salary
        System.out.println();
        System.out.println("Get the employees wo are earning above the average salary");
        System.out.println();
        double avgSal = empList.stream().mapToDouble(Employee::getSalary).average().getAsDouble();
        System.out.println(avgSal);
        // or
        double avgSal1 = empList.stream().collect(Collectors.averagingDouble(Employee::getSalary));

        // List of employee having salary greater then average salary
        empList.stream().filter(emp -> emp.getSalary() > avgSal).forEach(System.out::println);

        // 13. Get the employee with longest name.
        System.out.println();
        System.out.println("Get the employee with longest name.");

        Employee empName1 = empList.stream().max(Comparator.comparing(Employee::getEmpName)).get();
        System.out.println(empName1);

        //........................ Advance Questions .............................................
        // 14. List all employees by department.
        System.out.println();
        System.out.println("List all employees by department.");
        System.out.println();
        Map<String, List<Employee>> empMap1 = empList.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        empMap1.forEach((dep, emp) -> {
            System.out.println("Department " + dep);
            System.out.println(emp);
        });

        // 15. Get the count of employees by department. 
        System.out.println();
        System.out.println("Get the count of employees by department.");
        System.out.println();
        empList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting())).
                forEach((dept, count1) -> System.out.println(dept + "\t" + count1));

        // 16. Get the sum of salaries of all the employees by department.
        System.out.println();
        System.out.println("Get the sum of salaries of all the employees by department.");
        System.out.println();

        empList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::getSalary)))
                .forEach((dept, salSum) -> System.out.println(dept + " " + salSum));

        // 17. Get the average salary of all the employees per department.
        System.out.println();
        System.out.println("Get the average salary of all the employees per department.");
        System.out.println();

        empList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
                .forEach((dept, avgSalary) -> System.out.println(dept + " " + avgSalary));

        // 18. Check if all employee belongs to a speicific department, ex- "IT".
        System.out.println();
        System.out.println("Check if all employee belongs to a speicific department.");
        System.out.println();

        boolean isBelongs = empList.stream().allMatch(emp -> emp.getDepartment().equals("IT"));

        System.out.println("Is all employees belongs to IT department ? " + isBelongs);

        isBelongs = empList.stream().anyMatch(emp -> emp.getDepartment().equals("IT"));

        System.out.println("Is any employees belongs to IT department ? " + isBelongs);

        // 19. List all employees grouping by citys but belongs to same department.
        System.out.println();
        System.out.println("List all employees grouping by citys but belongs to same department.");
        System.out.println();
        Map<String, List<Employee>> empWithDept = empList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        empWithDept.forEach((dept, emp) -> {
            System.out.println("Department " + dept);
            emp.stream().collect(Collectors.groupingBy(Employee::getCity)).forEach((city, emps) -> {
                System.out.println("City " + city);
                // emps.forEach(System.out::println);  // To print employee details 
                emps.forEach(em -> System.out.println("Emp Name " + em.getEmpName())); // To print only employee name
                System.out.println();
            });
        });

        // List all employees grouping by department but belongs to same  citys .
        System.out.println();
        System.out.println("List all employees grouping by department but belongs to same  citys .");
        System.out.println();
        Map<String, List<Employee>> empWithCity = empList.stream().collect(Collectors.groupingBy(Employee::getCity));
        empWithCity.forEach((city, emp) -> {
            System.out.println("City " + city);
            emp.stream().collect(Collectors.groupingBy(Employee::getDepartment)).forEach((dept, emps) -> {
                System.out.println("Department " + dept);
                // emps.forEach(System.out::println);  // To print employee details 
                emps.forEach(em -> System.out.println("Emp Name " + em.getEmpName())); // To print only employee name
                System.out.println();
            });
        });

        // 20. Find the department with highest average salary.
        System.out.println();
        System.out.println(" Find the department with highest average salary.");
        System.out.println();
        // First we are finding the average salary of every department. 
        Map<String, Double> deptSalMap = empList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

        System.out.println(deptSalMap);

        String key = deptSalMap.keySet().stream().max(Comparator.comparing(dept -> deptSalMap.get(dept))).get();

        double highest = deptSalMap.get(key);
        System.out.println(highest);

    }
}
