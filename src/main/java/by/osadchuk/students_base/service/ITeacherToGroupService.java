package by.osadchuk.students_base.service;

import by.osadchuk.students_base.dto.TeacherToGroupDto;
import by.osadchuk.students_base.entity.User;

import java.util.List;

public interface ITeacherToGroupService {
    void addRecord(TeacherToGroupDto teacherToGroupDto);
    List<User> getStudents(String facultyName, String subjectName, Integer groupValue, Integer courseValue, String username);
}
