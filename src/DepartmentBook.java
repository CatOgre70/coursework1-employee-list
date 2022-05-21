import java.util.ArrayList;

public class DepartmentBook {
    private final ArrayList<Department> depList;

    public DepartmentBook(String[] array){
        depList = new ArrayList<>();
        for(String s : array) {
            Department dep = new Department(s);
            depList.add(dep);
        }
    }

    public DepartmentBook(String[] array, int size) {
        depList = new ArrayList<>(size);
        for(String s : array) {
            Department dep = new Department(s);
            depList.add(dep);
        }
    }

    public int size(){
        return depList.size();
    }

    public boolean isIdExist(int id){
        for(Department dep : depList) {
            if (dep.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public String findNameById(int id){
        for(Department dep : depList){
            if(dep.getId() == id) {
                return dep.getName();
            }
        }
        return null;
    }

    public void toArray(Department[] departmentArray){
        if(depList.size() > 0) {
            depList.toArray(departmentArray);
        } else {
            throw new RuntimeException("Department list array is empty");
        }
    }

    public void print(){
        System.out.println("Список отделов: ");
        for(Department dep : depList){
            System.out.println(dep);
        }
    }

}
