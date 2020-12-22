package by.osadchuk.students_base.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subjects")
@Data
public class Subject extends BaseEntity{
    public Subject() {
    }

    public Subject(String subject) {
        this.subject = subject;
    }

    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
