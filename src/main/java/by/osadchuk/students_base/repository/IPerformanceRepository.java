package by.osadchuk.students_base.repository;

import by.osadchuk.students_base.entity.AcademicPerformance;
import by.osadchuk.students_base.entity.Subject;
import by.osadchuk.students_base.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPerformanceRepository  extends JpaRepository<AcademicPerformance, Integer> {
    <S extends AcademicPerformance> S save(S s);
    List<AcademicPerformance> findByUser(User user);
    Page<AcademicPerformance> findByUserAndSubject(User user, Subject subject, Pageable pageable);
    Page<AcademicPerformance> findByUser(User user, Pageable pageable);
}
