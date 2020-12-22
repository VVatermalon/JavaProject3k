package by.osadchuk.students_base.controller;

import by.osadchuk.students_base.dto.StudentDto;
import by.osadchuk.students_base.entity.AcademicPerformance;
import by.osadchuk.students_base.entity.Role;
import by.osadchuk.students_base.entity.Subject;
import by.osadchuk.students_base.entity.User;
import by.osadchuk.students_base.repository.IPerformanceRepository;
import by.osadchuk.students_base.repository.ISubjectRepository;
import by.osadchuk.students_base.repository.IUserRepository;
import by.osadchuk.students_base.service.impl.PerformanceService;
import by.osadchuk.students_base.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/students/")
public class StudentRestController {
    private final UserService userService;
    private final PerformanceService perfomanceService;
    private final IPerformanceRepository iPerfomanceRepository;
    private final ISubjectRepository iSubjectRepository;
    private final IUserRepository iUserRepository;
    @Autowired
    public StudentRestController(UserService userService, PerformanceService perfomanceService, IPerformanceRepository iPerfomanceRepository, ISubjectRepository iSubjectRepository, IUserRepository iUserRepository) {
        this.userService = userService;
        this.perfomanceService = perfomanceService;
        this.iPerfomanceRepository = iPerfomanceRepository;
        this.iSubjectRepository = iSubjectRepository;
        this.iUserRepository = iUserRepository;
    }

    @GetMapping(value = "{username}")
    public ResponseEntity<StudentDto> getStudentByUsername(@PathVariable(name = "username") String username){
        User user = userService.findByUsername(username);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        StudentDto result = StudentDto.fromUser(user);
        log.info("Get request : /api/v1/auth/username");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "getUserSubjects")
    public ResponseEntity<List<String>> getStudentByUsername(@RequestParam Map<String, String> mapParam){
        var username = mapParam.get("username");
        log.info("Get request : /api/v1/auth/getUserSubjects");
        return new ResponseEntity<>(perfomanceService.findByUser(username), HttpStatus.OK);
    }

    @GetMapping(value = "getUserMarks")
    public Page<AcademicPerformance> getStudentMarks(@RequestParam Map<String, String> mapParam ){
        var username = mapParam.get("username");
        var subjectName = mapParam.get("subject");
        System.out.println(mapParam.get("page"));
        Optional<Integer> page = Optional.empty();
        if(mapParam.get("page") != null)
            page = Optional.of(Integer.parseInt(mapParam.get("page")));
        User user1 = iUserRepository.findByUsername(username);
        Subject subject = iSubjectRepository.findBySubject(subjectName).get(0);
        var q = iPerfomanceRepository.findByUserAndSubject(user1, subject,  PageRequest.of(page.orElse(0),2));
        for(var i:q){
            List<Role> roles = new ArrayList<>();
            roles.add(new Role());
            i.getUser().setRoles(roles);
        }
        log.info("Get request : /api/v1/auth/getUserMarks");
        return q;
    }
}
