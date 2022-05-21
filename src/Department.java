import java.util.Formatter;

public class Department {

    private static int idIndex = 0;
    private final int id;
    private final String name;

    public Department(String name){
        this.name = name;
        this.id = idIndex++;
    }

    public int getId() { return id; }

    public String getName(){ return name; }

    public String toString(){
        Formatter f = new Formatter();
        f.format("%3d: %-30s", this.id, this.name);
        return f.toString();
    }
}
