import java.text.NumberFormat;
import java.time.LocalDate;

/**
 * An manager class to manage an manager information
 * inherit from class Staff
 */
public class Manager extends Staff implements ICalculator {
    private static int count = 1;
    private String title;
    private int managerBaseSalary;
    private int businessLeaderBonus;
    private int projectLeaderBonus;
    private int technicalLeaderBonus;

    /**
     * Manager main constructor
     * @param name - manager name
     * @param age - manager age
     * @param coefficient - manager salary coefficient
     * @param vacationDays - manager vacation days
     * @param startDay - manager start day
     * @param department - manager department
     * @param title - manager title
     */
    public Manager(
            String name,
            int age,
            double coefficient,
            int vacationDays,
            LocalDate startDay,
            Department department,
            String title) {
        if (
            age < 0 ||
            age > 100 ||
            coefficient <= 0 ||
            vacationDays < 0 ||
            name.length() < 3) throw new IllegalArgumentException();

        this.id = "man" + count++;
        this.name = name;
        this.age = age;
        this.coefficient = coefficient;
        this.vacationDays = vacationDays;
        this.startDay = startDay;
        this.department = department;
        this.title = title;
        this.managerBaseSalary = 5_000_000;
        this.businessLeaderBonus = 8_000_000;
        this.projectLeaderBonus = 5_000_000;
        this.technicalLeaderBonus = 6_000_000;
    }

    /**
     * Override displayInformation() from class Staff
     */
    @Override
    public void displayInformation() {
        String salary = NumberFormat.getInstance().format(calculateSalary()) + " vnd";

        System.out.println("---------------------------");
        System.out.println("Manager name\t:\t" + name);
        System.out.println("Title\t\t\t:\t" + title);
        System.out.println("Age\t\t\t\t:\t" + age);
        System.out.println("Salary\t\t\t:\t" + salary);
        System.out.println("Vacation days\t:\t" + vacationDays);
        System.out.println("Worked since\t:\t" + startDay);
        System.out.println("Department\t\t:\t" + department.getName());
    }

    /**
     * Setter for manager title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for manager title
     * @return {String}
     */
    public String getTitle() {
        return title;
    }

    @Override
    public int calculateSalary() {
        return (int) (coefficient * managerBaseSalary + getManagerBonus());
    }

    /**
     * Get manager additional bonus/salarr base on manager title
     * return {int}
     */
    private int getManagerBonus() {
        String formattedTitle = title.trim().toLowerCase();
        return switch (formattedTitle) {
            case "business leader", "businessleader" -> businessLeaderBonus;
            case "project leader", "projectleader" -> projectLeaderBonus;
            case "technical leader", "technicalleader" -> technicalLeaderBonus;
            default -> 0;
        };
    }
}
