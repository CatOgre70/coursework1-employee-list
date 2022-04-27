import java.util.Formatter;

public class Employee {

    private static int idIndex = 0;
    private int id;
    private String name;
    private String middleName;
    private String secondName;
    private int department;
    private double salary;

    public Employee(String name, String middleName, String secondName, int department, double salary){
        // Input data check
        if(name == null || secondName == null || !Main.depBook.isIdExist(department) || salary <= 0){
            throw new IllegalArgumentException("Wrong argument(s) in Employee class constructor");
        }
        this.name = name;
        this.middleName = middleName;
        this.secondName = secondName;
        this.department = department;
        this.salary = salary;
        this.id = idIndex++;
    }

    // Constructor for coping Employee objects, don't touch the idIndex
    public Employee(int id, String name, String middleName, String secondName, int department, double salary){
        if(name == null || secondName == null || !Main.depBook.isIdExist(department) || salary <= 0){
            throw new IllegalArgumentException("Wrong argument(s) in Employee class constructor");
        }
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.secondName = secondName;
        this.department = department;
        this.salary = salary;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getMiddleName(){
        return middleName;
    }

    public String getSecondName(){
        return secondName;
    }

    public int getDepartment() { return department; }

    public double getSalary() { return salary; }

    public void setSalary(double newSalary) {
        if(newSalary > 0){
            salary = newSalary;
        } else {
            throw new IllegalArgumentException("Wrong argument newSalary in Employee class setSalary setter");
        }
    }

    public void setDepartment(int newDepartment){
        if(Main.depBook.isIdExist(newDepartment)) {
            department = newDepartment;
        } else {
            throw new IllegalArgumentException("Wrong argument newDepartment in Employee class setDepartment setter");
        }
    }

    public String toString(){
        Formatter f = new Formatter();
        f.format("%3d : %-15s : %-15s : %-20s : %-30s : %11.2f", this.id, this.secondName, this.name,
                this.middleName, Main.depBook.findNameById(this.department), this.salary);
        return f.toString();
    }

}
