package by.osadchuk.students_base.repository;

import by.osadchuk.students_base.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacultyRepository  extends JpaRepository<Faculty, Integer> {
    Faculty findByFaculty(String faculty);
}
