package Spring.part2.entity;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Company {

private String title;

@Autowired
private List<Emploee> emploees;

    public Company(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<Emploee> getEmployee() {
        return emploees;
    }
    public void setEmployee(List<Emploee> employee) {
        this.emploees = employee;
    }

    @Override
    public String toString() {
        return "Company{" +
                "title='" + title + '\'' +
                ", employee=" + emploees +
                '}';
    }
}
