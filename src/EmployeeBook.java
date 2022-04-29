import java.util.ArrayList;

public class EmployeeBook {

    private final ArrayList<Employee> employeeBook;

    public EmployeeBook(){
        employeeBook = new ArrayList<>();
    }

    public void add(Employee e){
        employeeBook.add(e);
    }

    public Employee getById(int id){
        for(Employee e : employeeBook) {
            if(e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public Employee getByIndex(int i){
        return employeeBook.get(i);
    }

    public int size(){
        return employeeBook.size();
    }

    public void removeById(int id){
        for(int i = 0; i < employeeBook.size(); i++){
            if(employeeBook.get(i).getId() == id) {
                employeeBook.remove(i);
                break;
            }
        }
    }

    public void removeByFullName(String name, String middleName, String secondName){
        for (int i = 0; i < employeeBook.size(); i++) {
            Employee e = employeeBook.get(i);
            if(e.getName().equals(name) && e.getMiddleName().equals(middleName) && e.getSecondName().equals(secondName)) {
                employeeBook.remove(i);
                break;
            }
        }
    }

    public void print(){
        System.out.println("Список сотрудников: ");
        for(Employee e : employeeBook)
            System.out.println(e);
    }

    public void print(int departmentId){
        if(!Main.depBook.isIdExist(departmentId))
            throw new IllegalArgumentException("Wrong departmentId argument in EmployeeBook.print() method");
        System.out.println("Список сотрудников [" + Main.depBook.findNameById(departmentId) + "]: ");
        for(Employee e : employeeBook)
            if(e.getDepartment() == departmentId)
                System.out.println(e);
    }

    public void toArray(Employee[] employeeArray){
        if(employeeArray.length != employeeBook.size()) {
            throw new IllegalArgumentException("Wrong array size in EmployeeBook.toArray argument");
        }
        for(int i = 0; i < employeeBook.size(); i++)
            employeeArray[i] = employeeBook.get(i);
    }

    public double calculateOverallSalary(){
        double sum = 0;
        for(Employee e : employeeBook)
            sum += e.getSalary();
        return sum;
    }

    public double calculateOverallSalary(int departmentId){
        if(!Main.depBook.isIdExist(departmentId))
            throw new IllegalArgumentException("Wrong argument departmentId in method EmployeeBook.calculateOverallSalary()");
        double sum = 0;
        for(Employee e : employeeBook)
            if(e.getDepartment() == departmentId)
                sum += e.getSalary();
        return sum;
    }

    public Employee findEmployeeWithMinimumSalary(){
        Employee eMin = employeeBook.get(0);
        for(int i = 1; i < employeeBook.size(); i++){
            if(employeeBook.get(i).getSalary() < eMin.getSalary())
                eMin = employeeBook.get(i);
        }
        return eMin;
    }

    public Employee findEmployeeWithMinimumSalary(int departmentId){
        if(!Main.depBook.isIdExist(departmentId))
            throw new IllegalArgumentException("Wrong argument departmentId in method EmployeeBook.findEmployeeWithMinimumSalary()");
        double minSalary = this.findEmployeeWithMaximumSalary().getSalary();
        int index = -1;
        for(int i = 0; i < employeeBook.size(); i++){
            if(employeeBook.get(i).getSalary() < minSalary && employeeBook.get(i).getDepartment() == departmentId) {
                minSalary = employeeBook.get(i).getSalary();
                index = i;
            }
        }
        if( index >= 0 )
            return employeeBook.get(index);
        else
            throw new RuntimeException("Minimum salary in [" + Main.depBook.findNameById(departmentId) + "] not found");
    }

    public Employee findEmployeeWithMaximumSalary(){
        Employee eMax = employeeBook.get(0);
        for(int i = 1; i < employeeBook.size(); i++){
            if(employeeBook.get(i).getSalary() > eMax.getSalary())
                eMax = employeeBook.get(i);
        }
        return eMax;
    }

    public Employee findEmployeeWithMaximumSalary(int departmentId){
        if(!Main.depBook.isIdExist(departmentId))
            throw new IllegalArgumentException("Wrong argument departmentId in method EmployeeBook.findEmployeeWithMaximumSalary()");
        double maxSalary = this.findEmployeeWithMinimumSalary().getSalary();
        int index = -1;
        for(int i = 0; i < employeeBook.size(); i++){
            if(employeeBook.get(i).getSalary() > maxSalary && employeeBook.get(i).getDepartment() == departmentId) {
                maxSalary = employeeBook.get(i).getSalary();
                index = i;
            }
        }
        if( index >= 0 )
            return employeeBook.get(index);
        else
            throw new RuntimeException("Maximum salary in [" + Main.depBook.findNameById(departmentId) + "] not found");
    }

    public void salaryIndexation(int percent){ // percent should be integer >= 0
        double ratio = 1 + (double) percent / 100;
        for(Employee e : employeeBook){
            e.setSalary(e.getSalary() * ratio);
        }
    }

    public void salaryIndexation(int percent, int departmentId){ // percent should be integer >= 0
        if(!Main.depBook.isIdExist(departmentId))
            throw new IllegalArgumentException("Wrong argument departmentId in method EmployeeBook.salaryIndexation()");
        double ratio = 1 + (double) percent / 100;
        for(Employee e : employeeBook){
            if(e.getDepartment() == departmentId)
                e.setSalary(e.getSalary() * ratio);
        }
    }

    public int calculateNumberOfEmployees(int departmentId) {
        if(!Main.depBook.isIdExist(departmentId))
            throw new IllegalArgumentException("Wrong argument departmentId in method EmployeeBook.calculateNumberOfEmployees()");
        int sum = 0;
        for(Employee e : employeeBook)
            if(e.getDepartment() == departmentId)
                sum++;
        return sum;
    }

    public ArrayList<Employee> findAllEmployeesWithSalaryGreaterThan(double minSalary){

        ArrayList<Employee> employeeList = new ArrayList<>();

        for(Employee e : employeeBook)
            if(e.getSalary() >= minSalary)
                employeeList.add(e);

        return employeeList;

    }

    public ArrayList<Employee> findAllEmployeesWithSalaryLessThan(double maxSalary){

        ArrayList<Employee> employeeList = new ArrayList<>();

        for(Employee e : employeeBook)
            if(e.getSalary() <= maxSalary)
                employeeList.add(e);

        return employeeList;

    }

    public void changeSalaryByFullName(String name, String middleName, String secondName, double newSalary){
        for (Employee e : employeeBook) {
            if (e.getName().equals(name) && e.getMiddleName().equals(middleName) && e.getSecondName().equals(secondName))
                e.setSalary(newSalary);
        }
    }

    public void changeSalaryById(int id, double newSalary){
        for (Employee employee : employeeBook) {
            if (employee.getId() == id)
                employee.setSalary(newSalary);
        }
    }

    public void changeDepartmentByFullName(String name, String middleName, String secondName, int newDepartmentId){
        if(!Main.depBook.isIdExist(newDepartmentId))
            throw new IllegalArgumentException("Wrong argument newDepartmentId in method EmployeeBook.changeDepartmentByFullName()");
        for (Employee e : employeeBook) {
            if (e.getName().equals(name) && e.getMiddleName().equals(middleName) && e.getSecondName().equals(secondName))
                e.setDepartment(newDepartmentId);
        }
    }

    public void changeDepartmentById(int id, int newDepartmentId){
        if(!Main.depBook.isIdExist(newDepartmentId))
            throw new IllegalArgumentException("Wrong argument newDepartmentId in method EmployeeBook.changeDepartmentById()");
        for (Employee employee : employeeBook) {
            if (employee.getId() == id)
                employee.setDepartment(newDepartmentId);
        }
    }

    public EmployeeBook duplicate(){
        EmployeeBook newEmployeeBook = new EmployeeBook();
        for(Employee e : employeeBook) {
            Employee e1 = new Employee(e.getId(), e.getName(), e.getMiddleName(), e.getSecondName(), e.getDepartment(), e.getSalary());
            newEmployeeBook.add(e1);
        }
        return newEmployeeBook;
    }

}
