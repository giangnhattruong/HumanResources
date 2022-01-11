import java.time.LocalDate;

/**
 * An abstract class to manage a staff information
 * inherited by class Employee and class Manager
 */
public abstract class Staff {
    protected String id;
    protected String name;
    protected int age;
    protected double coefficient;
    protected int vacationDays;
    protected LocalDate startDay;
    protected Department department;

    /**
     * Abstract class for displaying staff information as string
     */
    public abstract void displayInformation();

    // Setter
    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public Department getDepartment() {
        return department;
    }

    public abstract int calculateSalary();
}
