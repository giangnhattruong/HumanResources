import java.util.Objects;

/**
 * A deparment class to manage a department informations
 */
public class Department {
    private static int count = 1;
    private int id;
    private String name;
    private int totalStaff;

    /**
     * Department main constructor
     * @param name - department name
     */
    public Department(String name) {
        this.id = count;
        this.name = name;
        this.totalStaff = 0;
        count++;
    }

    /**
     * Override equals() from class Object
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && totalStaff == that.totalStaff && Objects.equals(name, that.name);
    }

    /**
     * Override toString() from class Object
     * @return
     */
    @Override
    public String toString() {
        System.out.println("------------------------------");
        if (totalStaff == 0) {
            return "Department " + name + " doesn't have any staff members.";
        } else if (totalStaff == 1) {
            return "Department " + name + " has only 1 staff member.";
        }
        return "Department " + name + " has " + totalStaff + " staff members.";
    }

    /**
     * Add 1 to total staff
     */
    public void addStaff() {
        totalStaff++;
    }

    //Getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTotalStaff() {
        return totalStaff;
    }
}
