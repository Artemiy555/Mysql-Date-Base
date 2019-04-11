package testsql.insert;

public class Person {

    private Integer id;
    private String name;
    private String surename;
    private Integer number;

    public Person() {
    }

    public Person(String name, String surename) {
        this.name = name;
        this.surename = surename;
    }


    public Person(Integer id, String name, String surename, Integer number) {
        this.id = id;
        this.name = name;
        this.surename = surename;
        this.number = number;
    }

   @Override
       public String toString() {
           StringBuilder sb = new StringBuilder("Person{");
           sb.append("id='").append(id).append('\'');
           sb.append(", name='").append(name).append('\'');
           sb.append(", surname='").append(surename).append('\'');
           sb.append(", age='").append(number).append('\'');
           sb.append("}");
           return sb.toString();
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer are) {
        this.number = are;
    }
}
