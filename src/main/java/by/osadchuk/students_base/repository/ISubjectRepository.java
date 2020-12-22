package by.osadchuk.students_base.repository;

import by.osadchuk.students_base.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findBySubject(String subject);
    <S extends Subject> S save(S s);
}
