///**
// * Calculate staff salary
// * implement ICalculator interface
// */
//public class SalaryCalculator implements ICalculator {
//    private int employeeBaseSalary;
//    private int employeeOvertimeWage;
//    private double employeeOvertimeHours;
//    private double coefficient;
//    private int managerBaseSalary;
//    private String managerTitle;
//    private int businessLeaderBonus;
//    private int projectLeaderBonus;
//    private int technicalLeaderBonus;
//
//    /**
//     * Constructor for calculating manager salary
//     * @param managerTitle - manager title
//     * @param coefficient - manager salary coefficient
//     */
//    public SalaryCalculator(String managerTitle, double coefficient) {
//        if (coefficient <= 0) throw new IllegalArgumentException("Coefficient must be greater than 0.");
//
//        this.managerBaseSalary = 5_000_000;
//        this.businessLeaderBonus = 8_000_000;
//        this.projectLeaderBonus = 5_000_000;
//        this.technicalLeaderBonus = 6_000_000;
//        this.managerTitle = managerTitle;
//        this.coefficient = coefficient;
//    }
//
//    /**
//     * Constructor for calculating employee salary
//     * @param employeeOvertimeHours - employee overtime hours
//     * @param coefficient - employee coefficient
//     */
//    public SalaryCalculator(double employeeOvertimeHours, double coefficient) {
//        this.employeeBaseSalary = 3_000_000;
//        this.employeeOvertimeWage = 200_000;
//        this.employeeOvertimeHours = employeeOvertimeHours;
//        this.coefficient = coefficient;
//    }
//
//    /**
//     * Override method from ICalculator interface
//     * @return {int}
//     */
//    @Override
//    public int calculateManagerSalary() {
//        return (int) (coefficient * managerBaseSalary + getManagerBonus());
//    }
//
//    /**
//     * Override method from ICalculator interface
//     * @return {int}
//     */
//    @Override
//    public int calculateEmployeeSalary() {
//        return (int) (coefficient * employeeBaseSalary + getEmployeeOverTimeWage());
//    }
//
//    /**
//     * Get manager additional bonus/salarr base on manager title
//     * return {int}
//     */
//    private int getManagerBonus() {
//        String title = managerTitle.trim().toLowerCase();
//        return switch (title) {
//            case "business leader", "businessleader" -> businessLeaderBonus;
//            case "project leader", "projectleader" -> projectLeaderBonus;
//            case "technical leader", "technicalleader" -> technicalLeaderBonus;
//            default -> 0;
//        };
//    }
//
//    /**
//     * Get employee additional wage base on overtime hours
//     * @return {int}
//     */
//    private int getEmployeeOverTimeWage() {
//        return (int) (employeeOvertimeWage * employeeOvertimeHours);
//    }
//}
