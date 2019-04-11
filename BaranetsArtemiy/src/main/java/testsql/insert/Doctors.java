package testsql.insert;

public class Doctors {

    private Integer id;
    private String name;
    private String surename;
    private String position;
    private String separation;
    private Integer departament;

    public Doctors(Integer id, String name, String surename, String position, String separation, Integer departament) {
        this.id = id;
        this.name = name;
        this.surename = surename;
        this.position = position;
        this.separation = separation;
        this.departament = departament;
    }

    @Override
    public String toString() {
        return "Doctors{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surename='" + surename + '\'' +
                ", position='" + position + '\'' +
                ", separation='" + separation + '\'' +
                ", departament=" + departament +
                '}';
    }

    public Doctors() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSeparation() {
        return separation;
    }

    public void setSeparation(String separation) {
        this.separation = separation;
    }

    public Integer getDepartament() {
        return departament;
    }

    public void setDepartament(Integer departament) {
        this.departament = departament;
    }
}
