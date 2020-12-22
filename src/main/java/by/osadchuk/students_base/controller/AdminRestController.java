package by.osadchuk.students_base.controller;

import by.osadchuk.students_base.dto.AdminUserDto;
import by.osadchuk.students_base.dto.TeacherDto;
import by.osadchuk.students_base.dto.TeacherToGroupDto;
import by.osadchuk.students_base.entity.Faculty;
import by.osadchuk.students_base.entity.Subject;
import by.osadchuk.students_base.entity.User;
import by.osadchuk.students_base.service.impl.FacultyService;
import by.osadchuk.students_base.service.impl.SubjectService;
import by.osadchuk.students_base.service.impl.TeacherToGroupService;
import by.osadchuk.students_base.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestController {

    private final UserService userService;
    private final SubjectService subjectService;
    private final FacultyService facultyService;
    private final TeacherToGroupService teacherToGroupService;

    @Autowired
    public AdminRestController(UserService userService, SubjectService subjectService, FacultyService facultyService, TeacherToGroupService teacherToGroupService) {
        this.userService = userService;
        this.subjectService = subjectService;
        this.facultyService = facultyService;
        this.teacherToGroupService = teacherToGroupService;
    }

    @GetMapping(value = "users/{username}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "username") String username) {
        User user = userService.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);
        log.info("Get request : /api/v1/admin/users");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = {"teachers"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TeacherDto>> facultyList() {
        List<TeacherDto> teacherDto = new ArrayList<>();
        for (var i:userService.getTeachers()){
            teacherDto.add(TeacherDto.fromUser(i));
        }
        log.info("Get request : /api/v1/admin/teachers");
        return new ResponseEntity<>(teacherDto, HttpStatus.OK);
    }

    @PostMapping("addSubject")
    public ResponseEntity addSubject(RequestEntity<Subject> subject) {
        subjectService.addSubject(Objects.requireNonNull(subject.getBody()).getSubject());
        log.info("Get request : /api/v1/admin/addSubject");
        return new ResponseEntity<>(Objects.requireNonNull(subject.getBody()).getSubject(), HttpStatus.CREATED);
    }

    @PostMapping("addFaculty")
    public ResponseEntity addFaculty(RequestEntity<Faculty> faculty) {
        facultyService.addFaculty(Objects.requireNonNull(faculty.getBody()).getFaculty());
        log.info("Get request : /api/v1/admin/addFaculty");
        return new ResponseEntity<>(Objects.requireNonNull(faculty.getBody()).getFaculty(), HttpStatus.CREATED);
    }

    @PutMapping("deActivateSubject")
    public ResponseEntity deActivateSubject(RequestEntity<Subject> subject) {
        subjectService.deleteSubjectByName(Objects.requireNonNull(subject.getBody()).getSubject());
        log.info("Get request : /api/v1/admin/deActivateSubject");
        return new ResponseEntity<>(Objects.requireNonNull(subject.getBody()).getSubject(), HttpStatus.CREATED);
    }

    @PostMapping("addTeacherToGroupRecord")
    public ResponseEntity addTeacherToGroupRecord(RequestEntity<TeacherToGroupDto> teacherDtoRequestEntity) {
        teacherToGroupService.addRecord(Objects.requireNonNull(teacherDtoRequestEntity.getBody()));
        log.info("Get request : /api/v1/admin/addTeacherToGroupRecord");
        return new ResponseEntity<>(Objects.requireNonNull(teacherDtoRequestEntity.getBody()).getSubject(), HttpStatus.CREATED);
    }
}