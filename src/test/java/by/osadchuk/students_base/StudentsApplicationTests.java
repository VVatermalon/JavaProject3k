package by.osadchuk.students_base;

import by.osadchuk.students_base.entity.Faculty;
import by.osadchuk.students_base.entity.Subject;
import by.osadchuk.students_base.repository.IFacultyRepository;
import by.osadchuk.students_base.repository.ISubjectRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class StudentsApplicationTests {

    @MockBean
    private IFacultyRepository facultyRepository;

    @MockBean
    private ISubjectRepository subjectRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAddSubject() {
        List<Subject> subjects = Arrays.asList(
                new Subject("subject 1"),
                new Subject("subject 2")
        );
        when(subjectRepository.findAll()).thenReturn(subjects);

        Assert.assertEquals(subjectRepository.findAll(), subjects);
    }

    @Test
    public void testGetSubjects() throws Exception {
        setUp();
        List<Subject> subjects = Arrays.asList(
                new Subject("subject 1"),
                new Subject("subject 2")
        );

        when(subjectRepository.findAll()).thenReturn(subjects);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/subjects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[*].subject", Matchers.containsInAnyOrder("subject 1", "subject 2")));
    }

    @Test
    public void testAddFaculty() {
        List<Faculty> faculties = Arrays.asList(
                new Faculty("faculty 1"),
                new Faculty("faculty 2")
        );
        when(facultyRepository.findAll()).thenReturn(faculties);

        Assert.assertEquals(facultyRepository.findAll(), faculties);
    }

    @Test
    public void testGetFaculties() throws Exception {
        setUp();
        List<Faculty> faculties = Arrays.asList(
                new Faculty("faculty 1"),
                new Faculty("faculty 2")
        );

        when(facultyRepository.findAll()).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/faculties"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[*].faculty", Matchers.containsInAnyOrder("faculty 1", "faculty 2")));
    }
}