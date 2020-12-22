package by.osadchuk.students_base.repository;

import by.osadchuk.students_base.entity.TeacherToGroup;
import by.osadchuk.students_base.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITeacherToGroupRepository extends JpaRepository<TeacherToGroup, Long> {
    <S extends TeacherToGroup> S save(S s);
    List<TeacherToGroup> findByUser(User user);
}