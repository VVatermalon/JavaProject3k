package by.osadchuk.students_base.service;

import by.osadchuk.students_base.entity.Subject;

import java.util.List;

public interface ISubjectService {
    List<Subject> findAll();
    void deleteSubjectByName(String subject);
    void addSubject(String subject);
}