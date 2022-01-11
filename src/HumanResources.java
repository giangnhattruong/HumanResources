import java.time.LocalDate;
import java.util.*;

/**
 * Main class which manage the company human resources
 */
public class HumanResources {
    /**
     * List of all staff members
     */
    private static ArrayList<Staff> staffs = new ArrayList<Staff>();
    /**
     * List of all departments
     */
    private static ArrayList<Department> departments = new ArrayList<Department>();
    /**
     * Scanner is declared for taking input later on
     */
    private static Scanner scanner;
    /**
     * This control the main program loops
     */
    private static boolean isProgramClose;

    /**
     * Main methods that run the program
     * @param args
     */
    public static void main(String[] args) {
        addSeeds();
        printIntroduce();
        showChoices();
    }

    /**
     * Print program first time introduction
     */
    private static void printIntroduce() {
        System.out.println("This is a program to manage human resources in the company.");
    }

    /**
     * Print guide lines and choices to user
     */
    private static void printGuide() {
        System.out.println();
        System.out.println("===========================");
        System.out.println("Please choose:");
        System.out.println("1. sa\t: show all staff members");
        System.out.println("2. sd\t: show all departments");
        System.out.println("3. sdm\t: show staff members in each department");
        System.out.println("4. sadm\t: show staff members in one department");
        System.out.println("5. a\t: add a new staff member");
        System.out.println("6. f\t: find a staff member by ID or name");
        System.out.println("7. sds\t: show all staff members by descending salary");
        System.out.println("8. sas\t: show all staff members by ascending salary");
        System.out.println("9. q\t: quit the program");
        System.out.print("Enter\t: ");
    }

    /**
     * Show choices
     * User can enter a number or abbreviation to select
     */
    private static void showChoices() {
        while (isProgramClose == false) {
            printGuide();
            scanner = new Scanner(System.in);
            String input = scanner.next().trim().toLowerCase();
            if (input.equals("sa") || input.equals("1")) {
                showAllStaffMembers();
            } else if (input.equals("sd") || input.equals("2")) {
                showAllDepartments();
            } else if (input.equals("sdm") || input.equals("3")) {
                showStaffMembersByEachDepartment();
            } else if (input.equals("sadm") || input.equals("4")) {
                showStaffMembersInOneDepartment();
            } else if (input.equals("a") || input.equals("5")) {
                addNewStaffMember();
            } else if (input.equals("f") || input.equals("6")) {
                findAStaffMember();
            } else if (input.equals("sds") || input.equals("7")) {
                showStaffMembersByDescendingSalary();
            } else if (input.equals("sas") || input.equals("8")) {
                showStaffMembersByAscendingSalary();
            } else if (input.equals("q") || input.equals("9")) {
                isProgramClose = true;
            } else {
                printInputInvalidError(input);
            }
        }
    }

    /**
     * Print error if user enter a invalid input from guide lines
     * @param input - the invalid input user just enter
     */
    private static void printInputInvalidError(String input) {
        System.out.println();
        System.out.println(input + " is not a valid input!");
    }

    /**
     * Show all staff members in the program
     * with displayStaffs() method
     */
    private static void showAllStaffMembers() {
        System.out.println();
        System.out.println("List of all staff members:");
        displayStaffs(staffs);
        System.out.println("------------------------------");
    }

    /**
     * Show all departments in the program
     */
    private static void showAllDepartments() {
        System.out.println();
        System.out.println("List of all departments:");
        for (Department department: departments) {
            System.out.println(department);
        }
        System.out.println("------------------------------");
    }

    /**
     * Show all staff members grouped by department
     * loop and display each department with displayStaffMembersInDepartment() method
     */
    private static void showStaffMembersByEachDepartment() {
        System.out.println();
        System.out.println("List of all staff members by each department:");
        for (Department department: departments) {
            displayStaffMembersInDepartment(department);
        }
    }

    /**
     * Show all staff members in 1 selected department
     * prompt for department name
     * find a department base on name
     * if there is a department, show all staff members in that department
     * with displayStaffMembersInDepartment() method
     */
    private static void showStaffMembersInOneDepartment() {
        while (true) {
            System.out.println();
            String text = readString("Department name (or enter b to go back)", 1).trim().toLowerCase();
            if (text.equals("b")) break;

            Department department = findDepartment(text);
            if (department != null) {
                displayStaffMembersInDepartment(department);
            } else {
                System.out.println("No department found. Please try again.");
            }
        }
    }

    /**
     * Show all staff members in an existed department
     * @param department - an existed department
     */
    private static void displayStaffMembersInDepartment(Department department) {
        System.out.println();
        System.out.println(department.toString());
        for (Staff staff: staffs) {
            if (staff.department.equals(department)) {
                staff.displayInformation();
            }
        }
    }

    /**
     * Show all staff members by descending salary
     * create a descending Comparator
     * clone the original list of staff members and sort by Comparator
     * show all staff members by a new sorted list with displayStaffs() method
     */
    private static void showStaffMembersByDescendingSalary() {
        ArrayList<Staff> descSalaryList = (ArrayList<Staff>) staffs.clone();
//        var sortList = staffs.stream().sorted(descendingSalary).toList();

        descSalaryList.sort((Staff s1, Staff s2) -> s2.calculateSalary() - s1.calculateSalary());
        displayStaffs(descSalaryList);
    }

    /**
     * Show all staff members by ascending salary
     * create a ascending Comparator
     * clone the original list of staff members and sort by Comparator
     * show all staff members by a new sorted list with displayStaffs() method
     */
    private static void showStaffMembersByAscendingSalary() {
        ArrayList<Staff> ascSalaryList = (ArrayList<Staff>) staffs.clone();
        ascSalaryList.sort((Staff s1, Staff s2) -> s1.calculateSalary() - s2.calculateSalary());
        displayStaffs(ascSalaryList);
    }

    /**
     * Add a new staff member
     * loop to add new staff member until user choose to go back
     * let user choose to add a manager or an employee
     * get and validate all necessary input for instantiate a new employee or manager
     * get department name and find a existed department or
     *   if department exists, add 1 to department
     *   otherwise instantiate a new department
     * inject SalaryCalculator which implements ICalculator interface when instantiate a staff
     */
    private static void addNewStaffMember() {
        while (true) {
            scanner = new Scanner(System.in);
            System.out.println("------------------------------");
            System.out.println("Please choose:");
            System.out.println("1 to add new manager");
            System.out.println("2 to add new employee");
            System.out.println("b to go back");
            System.out.print("Enter: ");
            String input = scanner.next().trim().toLowerCase();
            if (input.equals("b")) {
                break;
            } else if (input.equals("1")) {
                // Get and validate manager information
                String name = readString("Name",3);
                int age = readInteger("Age", 0, 100);
                double coefficient = readNumber("Coefficient");
                int vacationDays = readInteger("Vacation days");
                LocalDate startDay = readStartDay();
                String title = readString("Title",3);
                Department department = getOrCreateDepartment();
                department.addStaff();

                staffs.add(new Manager(name, age, coefficient, vacationDays, startDay, department, title));
            } else if (input.equals("2")) {
                // Get and validate employee information
                String name = readString("Name",3);
                int age = readInteger("Age", 0, 100);
                double coefficient = readNumber("Coefficient");
                int vacationDays = readInteger("Vacation days");
                LocalDate startDay = readStartDay();
                double overtimeHours = readNumber("Overtime hours");
                Department department = getOrCreateDepartment();
                department.addStaff();

                staffs.add(new Employee(name, age, coefficient, vacationDays, startDay, department, overtimeHours));
            }
        }
    }

    /**
     * Find all staff members base on name or ID
     * loop until user choose to go back
     * get and trim as well as convert input string to lower case
     * use findStaffByNameOrID() method to find all staffs which have the same name or match the ID
     *   if there are staffs, display all with displayStaffs() method
     *   otherwise let user try again
     */
    private static void findAStaffMember() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please enter staff name or ID (enter b to go back): ");
            String staffNameOrID = scanner.nextLine().trim().toLowerCase();
            if (staffNameOrID.equals("b")) {
                break;
            } else {
                ArrayList<Staff> list = findStaffByNameOrID(staffNameOrID);
                if (list.size() != 0) {
                    displayStaffs(list);
                } else {
                    System.out.println("No staff with name or ID \"" + staffNameOrID + "\" found, please try " +
                            "again.");
                }
            }
        }
    }

    /**
     * Find all staffs which have the same name or match the ID
     * @param staffNameOrID - staff member name or ID
     * @return {ArrayList<Staff>}
     */
    private static ArrayList<Staff> findStaffByNameOrID(String staffNameOrID) {
        ArrayList<Staff> list = new ArrayList<Staff>();
        for (Staff staff: staffs) {
            String id = staff.getId();
            String name = staff.getName().toLowerCase();
            if (id.equals(staffNameOrID) || name.contains(staffNameOrID)) list.add(staff);
        }
        return list;
    }

    /**
     * Display all staff members in selected list
     * @param list - list of staff members
     */
    private static void displayStaffs(List<Staff> list) {
        for (Staff staff : list) {
            staff.displayInformation();
        }
    }

    /**
     * Get an existed department in list or create a new one if the department can't be found
     * get and validate department name
     * find department with findDepartment() method
     * if the department can't be found, create a new one
     * @return {Department}
     */
    private static Department getOrCreateDepartment() {
        String departmentName = readString("Department name",1);
        Department department = findDepartment(departmentName);

        if (department == null) {
            department = new Department(departmentName);
            departments.add(department);
        }

        return department;
    }

    /**
     * Find and return an existed department with department name
     * @param name - department name
     * @return {Department}
     */
    private static Department findDepartment(String name) {
        name = name.trim().toLowerCase();
        for (Department department: departments) {
            String departmentName = department.getName().toLowerCase();
            if (departmentName.equals(name)) return department;
        }
        return null;
    }

    /**
     * Get and validate date object
     * loop until user enter valid date between 2000-01-01 and 3 month after today
     * @return {LocalDate}
     */
    private static LocalDate readStartDay() {
        System.out.println("Start day:");
        LocalDate date;
        LocalDate maxDate = LocalDate.now().plusMonths(3);
        LocalDate minDate = LocalDate.of(2000, 1, 1);
        while (true) {
            int day = readInteger("\tDay", 1, 31);
            int month = readInteger("\tMonth", 1, 12);
            int year = readInteger("\tYear");
            date = LocalDate.of(year, month, day);
            if (date.isEqual(maxDate) || (date.isAfter(minDate) && date.isBefore(maxDate))) break;
            System.out.println("Invalid date! Date must after 2000-01-01 and not after " + maxDate +".");
        }
        return date;
    }

    /**
     * Get and validate string with minimum length required
     * @param prompt - prompt message for input
     * @param min - minimum length required
     * @return {String}
     */
    private static String readString(String prompt, int min) {
        scanner = new Scanner(System.in);
        String name;
        while (true) {
            System.out.print(prompt + ": ");
            name = scanner.nextLine();
            if (name.length() >= min) break;
            System.out.println("Invalid name! Name must have more than " + min + " characters.");
        }
        return name;
    }

    /**
     * Get and validate integer between min and max
     * @param prompt - prompt message for input
     * @param min - minimum integer
     * @param max - maximum integer
     * @return {int}
     */
    private static int readInteger(String prompt, int min, int max) {
        scanner = new Scanner(System.in);
        int number;
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.next();
            if (isInteger(input)) {
                number = Integer.parseInt(input);
                if (number >= min && number <= max) break;
            }

            System.out.println("Invalid number! Number must be between " + min + " and " + max + " (inclusive).");
        }
        return number;
    }

    /**
     * Get and validate an integer greater than or equal to 0
     * get input from string and validate if it's an integer with isInteger() method
     * @param prompt - prompt message for input
     * @return {int}
     */
    private static int readInteger(String prompt) {
        scanner = new Scanner(System.in);
        int number;
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.next();
            if (isInteger(input)) {
                number = Integer.parseInt(input);
                if (number >= 0) break;
            }
            System.out.println("Invalid number! Number must be greater than or equal to zero.");
        }
        return number;
    }

    /**
     * Get and validate a real number greater than or equal to 0
     * get input from string and validate if it's a real number with isNumber() method
     * @param prompt - prompt message for input
     * @return {double}
     */
    private static double readNumber(String prompt) {
        scanner = new Scanner(System.in);
        double number;
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.next();
            if (isNumber(input)) {
                number = Double.parseDouble(input);
                if (number >= 0) break;
            }
            System.out.println("Invalid number! Number must be greater than or equal to zero.");
        }
        return number;
    }

    /**
     * Check if a string can be converted into integer
     * @param s - string
     * @return {boolean}
     */
    private static boolean isInteger(String s) {
        for (int i = 0; i < s.length(); i++) {
            if ((int) s.charAt(i) < 48 || (int) s.charAt(i) > 57) return false;
        }
        return true;
    }

    /**
     * Check if a string can be converted into real number
     * @param s - string
     * @return {boolean}
     */
    private static boolean isNumber(String s) {
        if (s.indexOf(".") != s.lastIndexOf(".")) {
            return false;
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '.' &&
                        ((int) s.charAt(i) < 48 || (int) s.charAt(i) > 57)) return false;
            }
            return true;
        }
    }

    /**
     * Add seeds
     */
    private static void addSeeds() {
        var dept1 = new Department("AAA");
        var dept2 = new Department("BBB");
        departments.add(dept1);
        departments.add(dept2);

        var emp1 = new Employee(
                "Bill Stack",
                26,
                2,
                12,
                LocalDate.of(2021,07,25),
                dept1,
                12);
        var emp2 = new Employee(
                "James Rivers",
                21,
                3,
                12,
                LocalDate.of(2020,1,15),
                dept1,
                12);
        var emp3 = new Employee(
                "John Will",
                23,
                2.5,
                12,
                LocalDate.of(2021,11,5),
                dept2,
                12);
        var man1 = new Manager(
                "Steve Mckey",
                23,
                2.5,
                12,
                LocalDate.of(2021,11,5),
                dept2,
                "Business Leader");

        Collections.addAll(staffs, emp1, emp2, emp3, man1);
        dept1.addStaff();
        dept1.addStaff();
        dept2.addStaff();
        dept2.addStaff();
    }
}
