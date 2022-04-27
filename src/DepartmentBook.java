import java.util.ArrayList;

public class DepartmentBook {
    private ArrayList<Department> depList;

    public DepartmentBook(String[] array){
        depList = new ArrayList<>();
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

    public int findIdByName(String name){
        for(Department dep : depList){
            if(dep.getName().equals(name)) {
                return dep.getId();
            }
        }
        return -1;
    }

    public void toArray(Department[] departmentArray){
        if(depList.size() > 0) {
            depList.toArray(departmentArray);
            return;
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
