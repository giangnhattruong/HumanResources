import java.text.NumberFormat;
import java.time.LocalDate;

/**
 * An employee class to manage an employee informations
 * inherit from class Staff
 */
public class Employee extends Staff implements ICalculator {
    private static int count = 1;
    private double overtimeHours;
    private int employeeBaseSalary;
    private int employeeOvertimeWage;

    /**
     * Employee main constructor
     * @param name - employee name
     * @param age - employee age
     * @param coefficient - employee salary coefficient
     * @param vacationDays - employee vacation days
     * @param startDay - employee start day
     * @param department - employee department
     * @param overtimeHours - employee overtime hour
     */
    public Employee(
            String name,
            int age,
            double coefficient,
            int vacationDays,
            LocalDate startDay,
            Department department,
            double overtimeHours) {
        if (
            age <= 0 ||
            age >= 100 ||
            coefficient <= 0 ||
            vacationDays < 0 ||
            overtimeHours < 0 ||
            name.length() < 3)  throw new IllegalArgumentException();

        this.id = "emp" + count++;
        this.name = name;
        this.age = age;
        this.coefficient = coefficient;
        this.vacationDays = vacationDays;
        this.startDay = startDay;
        this.department = department;
        this.overtimeHours = overtimeHours;
        this.employeeBaseSalary = 3_000_000;
        this.employeeOvertimeWage = 200_000;
    }

    /**
     * Override displayInformation() from class Staff
     */
    @Override
    public void displayInformation() {
        String salary = NumberFormat.getInstance().format(calculateSalary()) + " vnd";

        System.out.println("---------------------------");
        System.out.println("Employee name\t:\t" + name);
        System.out.println("Age\t\t\t\t:\t" + age);
        System.out.println("Salary\t\t\t:\t" + salary);
        System.out.println("Vacation days\t:\t" + vacationDays);
        System.out.println("Worked since\t:\t" + startDay);
        System.out.println("Department\t\t:\t" + department.getName());
    }

    /**
     * Setter for overtime hours
     * @param overtimeHours
     */
    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    /**
     * Getter for overtime hours
     * @return {double}
     */
    public double getOvertimeHours() {
        return overtimeHours;
    }

    @Override
    public int calculateSalary() {
        return (int) (coefficient *  employeeBaseSalary + overtimeHours * employeeOvertimeWage);
    }
}
