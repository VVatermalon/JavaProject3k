package by.osadchuk.students_base.service;

import by.osadchuk.students_base.entity.User;

import java.util.List;

public interface IUserService {
    User register(User user);
    List<User> getAll();
    User findByUsername(String userName);
    User findByEmail(String userName);
    User findById(Long id);
    void delete(Long id);
    List<User> getTeachers();
}
