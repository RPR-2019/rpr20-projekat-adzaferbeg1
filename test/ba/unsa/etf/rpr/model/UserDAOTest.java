package ba.unsa.etf.rpr.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDAOTest {

    final UserDAO model=UserDAO.getInstance();

    @Test
    void getAdmin() {

        Admin admin2 = model.getAdmin();
        assertEquals("admin",admin2.getUsername());
        assertEquals("admin", admin2.getPassword());
    }

    @Test
    void getStudent() {
        Student student = model.getStudent("ajla");
        assertEquals("18593",student.getIndex());
    }

    @Test
    void getStudentByName() {
        String name="Adna", lastname="Halilović";
        Student student=model.getStudentByName(name,lastname);
        assertEquals("17689",student.getIndex());
    }

    @Test
    void getProfessor() {
        String username="zjuric";
        Professor professor=model.getProfessor(username);
        assertEquals("Željko",professor.getName());
    }


    @Test
    void getDistinctSubjects() {
        String teacherName="Željko", teacherLastname="Jurić";
        ArrayList<String> subjects = new ArrayList<>(model.getDistinctSubjects(teacherName, teacherLastname));
        assertEquals("TP", subjects.get(0));
    }

    @Test
    void getProfessors() {
        ArrayList<String> staff = new ArrayList<>(model.getProfessors());
        assertEquals("Željko Jurić: zjuric@gmail.com",staff.get(0));
    }

    @Test
    void getAllStudents() {
        ArrayList<String> students = new ArrayList<>(model.getAllStudents());
        assertEquals("Ajla Džaferbegović",students.get(0));
    }

    @Test
    void getAllExams() {
        ArrayList<String> activities = new ArrayList<>(model.getAllExams());
        assertEquals("Ispit iz predmeta IM1 će biti održan dana 16-09-2020",activities.get(0));
    }

    @Test
    void getAllAnnouncements() {
        ArrayList<String> activities = new ArrayList<>(model.getAllAnnouncements());
        assertEquals("Obavijest: Predavanje iz predmeta IM1 se neće održati. / 05-08-2020",activities.get(0));
    }

    @Test
    void getAllHomework() {
        ArrayList<String> activities = new ArrayList<>(model.getAllHomework());
        assertEquals("Objavljena zadaća iz predmeta: IM1, krajnji rok: 18-08-2022",activities.get(0));
    }

    @Test
    void getSSGrade() {
        String ocjena=model.getSSGrade("Ajla", "Džaferbegović","DM");
        assertEquals("8",ocjena);
    }

    @Test
    void getStudentsForSubject(){
        ArrayList<StudentSubject> list = model.getStudentsForSubject("DM");
        StudentSubject subject = list.get(0);
        assertEquals("Ajla Džaferbegović", subject.getStudent());
    }
}