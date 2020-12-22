package by.osadchuk.students_base.service;

import by.osadchuk.students_base.entity.Faculty;

import java.util.List;

public interface IFacultyService {
    List<Faculty> findAll();
    void addFaculty(String facultyName);
}
