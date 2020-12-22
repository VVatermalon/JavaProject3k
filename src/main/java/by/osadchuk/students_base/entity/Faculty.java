package by.osadchuk.students_base.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Faculty {
    public Faculty(){
    }

    public Faculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Id
    private String faculty;

}
