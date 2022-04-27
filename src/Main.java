import java.util.ArrayList;

public class Main {

    // Definitions of public variables
    public static DepartmentBook depBook;

    public static void main(String[] args) {

        // Department book initialization
        String[] depNames = {"Отдел кадров", "Бухгалтерия", "Финансовый отдел", "Производственный отдел",
                            "Отдел логистики", "Отдел закупок", "Маркетинговый отдел", "Отдел продаж"};
        depBook = new DepartmentBook(depNames);
        depBook.print();

        // Employee book initialization
        EmployeeBook employeeBook = new EmployeeBook();
        employeeBook.add(new Employee("Иван", "Иванович", "Иванов", 0, 50_000));
        employeeBook.add(new Employee("Петр", "Иннокентьевич", "Петров", 0, 150_000));
        employeeBook.add(new Employee("Семен", "Семенович", "Горбунков", 1, 101_000));
        employeeBook.add(new Employee("Иннокентий", "Борисович", "Чулков", 1, 120_000));
        employeeBook.add(new Employee("Сергей", "Ануфриевич", "Ябеда", 2, 40_000));
        employeeBook.add(new Employee("Иосиф", "Вахтангович", "Гришин", 2, 91_000));
        employeeBook.add(new Employee("Иван", "Иванович", "Иванов", 3, 50_000));
        employeeBook.add(new Employee("Алибек", "Джунгарович", "Хомяков", 3, 42_000));
        employeeBook.add(new Employee("Рустам", "Ибрагимович", "Сулейбеков", 4, 22_000));
        employeeBook.add(new Employee("Зухра", "Петровна", "Джугашвилли", 4, 173_000));
        employeeBook.add(new Employee("Казбек", "Дмитриевич", "Светлый", 5, 17_500));
        employeeBook.add(new Employee("Сулейман", "Мыколович", "Беспамятный", 5, 23_700));
        employeeBook.add(new Employee("Михайло", "Дмитриевич", "Главко", 6, 110_300));
        employeeBook.add(new Employee("Елена", "Арменовна", "Акопян", 6, 199_999.99));
        employeeBook.add(new Employee("Гюльчатай", "Зурабовна", "Сухова", 7, 69_000));
        employeeBook.add(new Employee("Наталья", "Альбертовна", "Рабинович", 7, 29_794));

        employeeBook.print();

        // Part 1. Basic difficulty
        // ==========================================================================================================

        Employee[] employeeArray = new Employee[employeeBook.size()];
        employeeBook.toArray(employeeArray);

        print(employeeArray);
        System.out.printf("Общая сумма вылат по зарплатам: %,11.2f рублей в месяц", calculateOverallSalary(employeeArray));
        System.out.println("\n");
        System.out.println("Сотрудник с минимальной зарплатой: ");
        System.out.println(findEmployeeWithMinimumSalary(employeeArray));
        System.out.println();

        System.out.println("Сотрудник с максимальной зарплатой: ");
        System.out.println(findEmployeeWithMaximumSalary(employeeArray));
        System.out.println();

        System.out.printf("Средняя зарплата по предприятию: %,11.2f рублей в месяц", calculateOverallSalary(employeeArray) /
                employeeArray.length);
        System.out.println("\n");

        System.out.println("Список сотрудников (ФИО):");
        System.out.println("=========================");
        for(Employee e : employeeArray)
            System.out.println(e.getSecondName() + " " + e.getName() + " " + e.getMiddleName());
        System.out.println();

        // Part 2. Increased difficulty
        // ==========================================================================================================

        // Duplicate all employeeArray data into newEmployeeArray
        Employee[] newEmployeeArray = duplicateEmployeeArray(employeeArray);

        int percent = 12; // Percent of salary indexation

        salaryIndexation(newEmployeeArray, percent);
        System.out.println("Зарплаты сотрудников после повышения:");
        print(newEmployeeArray);
        System.out.println();

        int depId = 0;
        print(employeeArray, depId);
        depId = 1;
        print(employeeArray, depId);

        depId = 4;
        System.out.println("Сотрудник [" + depBook.findNameById(depId) +"] с минимальной зарплатой: ");
        System.out.println(findEmployeeWithMinimumSalary(employeeArray,depId));
        System.out.println();

        depId = 3;
        System.out.println("Сотрудник [" + depBook.findNameById(depId) +"] с максимальной зарплатой: ");
        System.out.println(findEmployeeWithMaximumSalary(employeeArray,depId));
        System.out.println();

        depId = 0;
        System.out.printf("Общая сумма вылат по зарплатам [%s]: %,11.2f рублей в месяц", depBook.findNameById(depId),
                calculateOverallSalary(employeeArray, depId));
        System.out.println("\n");

        System.out.printf("Средняя зарплата [%s]: %,11.2f рублей в месяц", depBook.findNameById(depId),
                calculateOverallSalary(employeeArray, depId) / calculateNumberOfEmployees(employeeArray,depId));
        System.out.println("\n");

        // Coping all employeeArray data into newEmployeeArray one more time
        newEmployeeArray = duplicateEmployeeArray(employeeArray);
        depId = 1;
        salaryIndexation(newEmployeeArray, percent, depId);
        System.out.println("Зарплаты сотрудников [" + depBook.findNameById(depId) + "] после повышения:");
        print(newEmployeeArray, depId);
        System.out.println();

        double minSalary = 100_000.00;
        Employee[] employeeList = findAllEmployeesWithSalaryGreaterThan(employeeArray, minSalary);
        if(employeeList != null){
            System.out.println("Сотрудники с зарплатой больше, чем " + minSalary + " рублей");
            print(employeeList);
        } else {
            System.out.println("Сотрудников с зарплатой больше, чем " + minSalary + " рублей не обнаружено");
        }

        double maxSalary = 10_000.00;
        employeeList = findAllEmployeesWithSalaryLessThan(employeeArray, maxSalary);
        if(employeeList != null){
            System.out.println("Сотрудники с зарплатой меньше, чем " + maxSalary + " рублей");
            print(employeeList);
        } else {
            System.out.println("Сотрудников с зарплатой меньше, чем " + maxSalary + " рублей не обнаружено");
        }
        System.out.println();

        // Part 3. Very difficult (ha-ha!)
        // ==========================================================================================================

        employeeBook.print();
        System.out.println();

        System.out.printf("Общая сумма вылат по зарплатам: %,11.2f рублей в месяц", employeeBook.calculateOverallSalary());
        System.out.println("\n");
        System.out.println("Сотрудник с минимальной зарплатой: ");
        System.out.println(employeeBook.findEmployeeWithMinimumSalary());
        System.out.println();

        System.out.println("Сотрудник с максимальной зарплатой: ");
        System.out.println(employeeBook.findEmployeeWithMaximumSalary());
        System.out.println();

        System.out.printf("Средняя зарплата по предприятию: %,11.2f рублей в месяц",
                employeeBook.calculateOverallSalary() / employeeBook.size());
        System.out.println("\n");

        System.out.println("Список сотрудников (ФИО):");
        System.out.println("=========================");
        for(int i = 0; i < employeeBook.size(); i++) {
            Employee e = employeeBook.getByIndex(i);
            System.out.println(e.getSecondName() + " " + e.getName() + " " + e.getMiddleName());
        }
        System.out.println();

        // Duplicate employee book
        EmployeeBook newEmployeeBook = employeeBook.duplicate();
        newEmployeeBook.salaryIndexation(percent);
        System.out.println("Зарплаты сотрудников после повышения:");
        newEmployeeBook.print();
        System.out.println();

        depId = 0;
        employeeBook.print(depId);
        System.out.println();
        depId = 7;
        employeeBook.print(depId);
        System.out.println();

        depId = 4;
        System.out.println("Сотрудник [" + depBook.findNameById(depId) +"] с минимальной зарплатой: ");
        System.out.println(employeeBook.findEmployeeWithMinimumSalary(depId));
        System.out.println();

        depId = 3;
        System.out.println("Сотрудник [" + depBook.findNameById(depId) +"] с максимальной зарплатой: ");
        System.out.println(employeeBook.findEmployeeWithMaximumSalary(depId));
        System.out.println();

        depId = 0;
        System.out.printf("Общая сумма вылат по зарплатам [%s]: %,11.2f рублей в месяц", depBook.findNameById(depId),
                employeeBook.calculateOverallSalary(depId));
        System.out.println("\n");

        System.out.printf("Средняя зарплата [%s]: %,11.2f рублей в месяц", depBook.findNameById(depId),
                employeeBook.calculateOverallSalary(depId) / employeeBook.calculateNumberOfEmployees(depId));
        System.out.println("\n");

        // Coping all employeeArray data into newEmployeeArray one more time
        newEmployeeBook = employeeBook.duplicate();
        depId = 1;
        newEmployeeBook.salaryIndexation(percent, depId);
        System.out.println("Зарплаты сотрудников [" + depBook.findNameById(depId) + "] после повышения:");
        newEmployeeBook.print(depId);
        System.out.println();

        minSalary = 100_000.00;
        ArrayList<Employee> employeeList1 = employeeBook.findAllEmployeesWithSalaryGreaterThan(minSalary);
        if(employeeList1.size() != 0){
            System.out.println("Сотрудники с зарплатой больше, чем " + minSalary + " рублей");
            for (Employee e : employeeList1) {
                System.out.println(e);
            }
        } else {
            System.out.println("Сотрудников с зарплатой больше, чем " + minSalary + " рублей не обнаружено");
        }
        System.out.println();

        maxSalary = 10_000.00;
        employeeList1 = employeeBook.findAllEmployeesWithSalaryLessThan(maxSalary);
        if(employeeList1.size() != 0){
            System.out.println("Сотрудники с зарплатой меньше, чем " + maxSalary + " рублей");
            for (Employee e : employeeList1) {
                System.out.println(e);
            }
        } else {
            System.out.println("Сотрудников с зарплатой меньше, чем " + maxSalary + " рублей не обнаружено");
        }
        System.out.println();

        // Add new employee to employeeBook
        employeeBook.add(new Employee("Ольга", "Игоревна", "Штольц", 7, 256_194));
        employeeBook.print();
        System.out.println();

        // Remove employee from employeeBook

        employeeBook.removeByFullName("Ольга", "Игоревна", "Штольц");
        employeeBook.print();
        System.out.println();

        employeeBook.removeById(15);
        employeeBook.print();
        System.out.println();

        employeeBook.changeSalaryById(14, 75_000.00);
        System.out.println(employeeBook.getById(14));
        employeeBook.changeSalaryByFullName("Гюльчатай", "Зурабовна", "Сухова", 85_000.00);
        System.out.println(employeeBook.getById(14));
        System.out.println();

        employeeBook.changeDepartmentById(8, 7);
        System.out.println(employeeBook.getById(8));
        employeeBook.changeDepartmentByFullName("Рустам", "Ибрагимович", "Сулейбеков", 4);
        System.out.println(employeeBook.getById(8));
        System.out.println();

        Department[] dep = new Department[depBook.size()];
        depBook.toArray(dep);
        for(Department d : dep){
            employeeBook.print(d.getId());
        }

    }

    public static void print(Employee[] employeeArray){
        System.out.println("Список всех сотрудников из массива employeeArray[" + employeeArray.length + "]:");
        for(Employee e : employeeArray)
            System.out.println(e);
        System.out.println();
    }

    public static void print(Employee[] employeeArray, int departmentId){
        if(!depBook.isIdExist(departmentId))
            throw new IllegalArgumentException("Wrong argument departmentId in static method Main.print()");
        System.out.println("Список всех сотрудников [" + depBook.findNameById(departmentId) + "]:");
        for(Employee e : employeeArray)
            if(e.getDepartment() == departmentId)
                System.out.println(e);
        System.out.println();
    }

    public static double calculateOverallSalary(Employee[] employeeArray){
        double sum = 0;
        for(Employee e : employeeArray)
            sum += e.getSalary();
        return sum;
    }

    public static double calculateOverallSalary(Employee[] employeeArray, int departmentId){
        if(!depBook.isIdExist(departmentId))
            throw new IllegalArgumentException("Wrong argument departmentId in static method Main.calculateOverallSalary()");
        double sum = 0;
        for(Employee e : employeeArray)
            if(e.getDepartment() == departmentId)
                sum += e.getSalary();
        return sum;
    }

    public static Employee findEmployeeWithMinimumSalary(Employee[] employeeArray){
        Employee eMin = employeeArray[0];
        for(int i = 1; i < employeeArray.length; i++){
            if(employeeArray[i].getSalary() < eMin.getSalary())
                eMin = employeeArray[i];
        }
        return eMin;
    }

    public static Employee findEmployeeWithMinimumSalary(Employee[] employeeArray, int departmentId){
        if(!depBook.isIdExist(departmentId))
            throw new IllegalArgumentException("Wrong argument departmentId in static method Main.findEmployeeWithMinimumSalary()");
        double minSalary = findEmployeeWithMaximumSalary(employeeArray).getSalary();
        int index = -1;
        for(int i = 0; i < employeeArray.length; i++){
            if(employeeArray[i].getSalary() < minSalary && employeeArray[i].getDepartment() == departmentId) {
                minSalary = employeeArray[i].getSalary();
                index = i;
            }
        }
        if( index >= 0 )
            return employeeArray[index];
        else
            throw new RuntimeException("Minimum salary in [" + depBook.findNameById(departmentId) + "] not found");
    }

    public static Employee findEmployeeWithMaximumSalary(Employee[] employeeArray){
        Employee eMax = employeeArray[0];
        for(int i = 1; i < employeeArray.length; i++){
            if(employeeArray[i].getSalary() > eMax.getSalary())
                eMax = employeeArray[i];
        }
        return eMax;
    }

    public static Employee findEmployeeWithMaximumSalary(Employee[] employeeArray, int departmentId){
        if(!depBook.isIdExist(departmentId))
            throw new IllegalArgumentException("Wrong argument departmentId in static method Main.findEmployeeWithMaximumSalary()");
        double maxSalary = findEmployeeWithMinimumSalary(employeeArray).getSalary();
        int index = -1;
        for(int i = 0; i < employeeArray.length; i++){
            if(employeeArray[i].getSalary() > maxSalary && employeeArray[i].getDepartment() == departmentId) {
                maxSalary = employeeArray[i].getSalary();
                index = i;
            }
        }
        if( index >= 0 )
            return employeeArray[index];
        else
            throw new RuntimeException("Maximum salary in [" + depBook.findNameById(departmentId) + "] not found");
    }

    public static void salaryIndexation(Employee[] employeeArray, int percent){ // perсent shoud be integer >= 0
        double ratio = 1 + (double) percent / 100;
        for(Employee e : employeeArray){
            e.setSalary(e.getSalary() * ratio);
        }
    }

    public static void salaryIndexation(Employee[] employeeArray, int percent, int departmentId){ // perсent shoud be integer >= 0
        if(!depBook.isIdExist(departmentId))
            throw new IllegalArgumentException("Wrong argument departmentId in static method Main.salaryIndexation()");
        double ratio = 1 + (double) percent / 100;
        for(Employee e : employeeArray){
            if(e.getDepartment() == departmentId)
                e.setSalary(e.getSalary() * ratio);
        }
    }

    public static int calculateNumberOfEmployees(Employee[] employeeArray, int departmentId) {
        if(!depBook.isIdExist(departmentId))
            throw new IllegalArgumentException("Wrong argument departmentId in static method Main.calculateNumberOfEmployees()");
        int sum = 0;
        for(Employee e : employeeArray)
            if(e.getDepartment() == departmentId)
                sum++;
        return sum;
    }

    public static Employee[] findAllEmployeesWithSalaryGreaterThan(Employee[] employeeArray, double minSalary){

        ArrayList<Employee> employeeList = new ArrayList<>();

        for(Employee e : employeeArray)
            if(e.getSalary() >= minSalary)
                employeeList.add(e);
        if(employeeList.size() > 0){
            Employee[] e = new Employee[employeeList.size()];
            employeeList.toArray(e);
            return e;
        } else {
            return null;
        }

    }

    public static Employee[] findAllEmployeesWithSalaryLessThan(Employee[] employeeArray, double maxSalary){

        ArrayList<Employee> employeeList = new ArrayList<>();

        for(Employee e : employeeArray)
            if(e.getSalary() <= maxSalary)
                employeeList.add(e);
        if(employeeList.size() > 0){
            Employee[] e = new Employee[employeeList.size()];
            employeeList.toArray(e);
            return e;
        } else {
            return null;
        }

    }

    public static Employee[] duplicateEmployeeArray(Employee[] employeeArray){
        Employee[] newEmployeeArray = new Employee[employeeArray.length];
        for(int i = 0; i < employeeArray.length; i++) {
            Employee e = employeeArray[i];
            Employee e1 = new Employee(e.getId(), e.getName(), e.getMiddleName(), e.getSecondName(), e.getDepartment(), e.getSalary());
            newEmployeeArray[i] = e1;
        }
        return newEmployeeArray;
    }

}