package ba.unsa.etf.rpr.model;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDAO {
    private Connection conn;
    private static UserDAO instance;
    private PreparedStatement queryAdmin, queryAddStudent, queryFindStudentId, queryFindProfessorId, queryAddProfessor,
            queryFindEmployeeId, queryAddEmployee, queryFindStudent, queryGetSubjects, queryGetProfessors,
            queryAddSubject, queryFindSubjectId, queryFindProfessor, queryGetDistinctSubjects, queryGetAllStudents,
    queryAddActivity, queryFindActivityId, queryGetHomeworks, queryGetExams, queryGetAnnouncements, queryEnrollStudent,
    queryFindSubjectStudentId, queryFindStudentByName, queryFindSSByTitle, queryUpdateStudentSubject,queryGetSubjects2,
    queryGetSSExam, queryGetSSHomework, queryGetSSOther, queryGetSSGrade, queryDeleteStudent, queryDeleteSubject, queryDeleteProfessor,
    queryGetSubjectByTitle, queryFindEnrolled;

    private static void initialize(){
        instance = new UserDAO();
    }

    public static UserDAO getInstance(){
        if(instance==null){
            instance=new UserDAO();
        }
        return instance;
    }

    public UserDAO(){
        try{
            conn= DriverManager.getConnection("jdbc:sqlite:baza.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            queryAdmin =conn.prepareStatement("Select username,password FROM admin WHERE id='1' ");
        } catch (SQLException e) {
            refreshDB();
            try {
                queryAdmin =conn.prepareStatement("Select username,password FROM admin WHERE id='1' ");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        try{
            queryAdmin =conn.prepareStatement("select username,password from admin where id=?");
            queryAddStudent =conn.prepareStatement("insert into student values (?,?,?,?,?,?,?,?,?,?,?)");
            queryFindStudentId =conn.prepareStatement("select Max(id)+1 from student");
            queryFindProfessorId =conn.prepareStatement("select Max(id)+1 from profesor");
            queryAddProfessor =conn.prepareStatement("insert into profesor values (?,?,?,?,?,?,?,?,?)");
            queryFindEmployeeId =conn.prepareStatement("select Max(id)+1 from uposlenikss");
            queryAddEmployee =conn.prepareStatement("insert into uposlenikss values (?,?,?,?,?,?,?,?)");
            queryFindStudent =conn.prepareStatement("select * from student where username=?");
            queryGetSubjects =conn.prepareStatement("select * from predmet where odsjek=?");
            queryGetSubjects2 =conn.prepareStatement("select * from predmet where odsjek=? and studijska_godina<=?");
            queryGetProfessors =conn.prepareStatement("select * from profesor");
            queryFindProfessor=conn.prepareStatement("select * from profesor where username=? ");
            queryAddSubject = conn.prepareStatement("insert into predmet values (?,?,?,?,?,?,?)");
            queryFindSubjectId =conn.prepareStatement("select Max(id)+1 from predmet");
            queryGetDistinctSubjects=conn.prepareStatement("select * from predmet where predavac=?");
            queryGetAllStudents=conn.prepareStatement("select * from student");
            queryAddActivity=conn.prepareStatement("insert into aktivnost values (?,?,?,?)");
            queryFindActivityId=conn.prepareStatement("select Max(id)+1 from aktivnost");
            queryGetHomeworks=conn.prepareStatement("select tekst,datum from aktivnost where tip=? ");
            queryGetAnnouncements=conn.prepareStatement("select tekst,datum from aktivnost where tip=? ");
            queryGetExams=conn.prepareStatement("select tekst,datum from aktivnost where tip=?");
            queryEnrollStudent=conn.prepareStatement("insert into predmet_student values (?,?,?,?,?,?,?,?)");
            queryFindSubjectStudentId=conn.prepareStatement("select Max(id)+1 from predmet_student");
            queryFindStudentByName=conn.prepareStatement("select * from student where ime=? and prezime=?");
            queryFindSSByTitle=conn.prepareStatement("select student,predmet,bodovi,ocjena from predmet_student where predmet=?");
            queryUpdateStudentSubject=conn.prepareStatement("update predmet_student set bodovi=?,ocjena=?,ispit=?,zadaca=?,ostalo=? where student=? and predmet=?");
            queryGetSSExam=conn.prepareStatement("select ispit from predmet_student where student=? and predmet=?");
            queryGetSSHomework=conn.prepareStatement("select zadaca from predmet_student where student=? and predmet=?");
            queryGetSSOther=conn.prepareStatement("select ostalo from predmet_student where student=? and predmet=?");
            queryGetSSGrade=conn.prepareStatement("select ocjena from predmet_student where student=? and predmet=?");
            queryDeleteStudent=conn.prepareStatement("delete from student where ime=?");
            queryDeleteSubject=conn.prepareStatement("delete from predmet where naziv=?");
            queryDeleteProfessor=conn.prepareStatement("delete from profesor where ime=?");
            queryGetSubjectByTitle=conn.prepareStatement("select studijska_godina from predmet where naziv=?");
            queryFindEnrolled=conn.prepareStatement("select * from predmet_student where student=? and predmet=?");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void removeInstance() {
        if (instance == null) return;
        instance.close();
        instance=null;
    }

    public void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshDB() {
        Scanner ulaz;
        try {
            ulaz = new Scanner(new FileInputStream("baza.db.sql"));
            StringBuilder sqlUpit= new StringBuilder();
            while(ulaz.hasNext()){
                sqlUpit.append(ulaz.nextLine());
                if( sqlUpit.charAt(sqlUpit.length()-1)==';'){
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit.toString());
                        sqlUpit = new StringBuilder();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public Admin resultSetAdmin(ResultSet rs) throws SQLException {
        return new Admin(rs.getString(1), rs.getString(2));
    }
    public Student resultSetStudent(ResultSet rs) throws SQLException{
        return new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),
                rs.getString(10),rs.getString(11));
    }

    public Subject resultSetSubject(ResultSet rs) throws SQLException{
        return new Subject(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),
                rs.getString(5),rs.getString(6),rs.getInt(7));
    }

    public Professor resultSetProfessor(ResultSet rs)throws SQLException{
        return new Professor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                rs.getString(9));
    }
    public Announcement resultSetAnnouncement(ResultSet rs) throws SQLException {
        return new Announcement(rs.getString(1),rs.getString(2));
    }
    public Homework resultSetHomework(ResultSet rs) throws SQLException {
        return new Homework(rs.getString(1),rs.getString(2));
    }
    public Exam resultSetExam(ResultSet rs) throws SQLException {
        return new Exam(rs.getString(1),rs.getString(2));
    }
    public StudentSubject resultSetStudentSubject(ResultSet rs) throws SQLException {
        return new StudentSubject(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4));
    }
    public int resultSetPoints(ResultSet rs)throws SQLException{return rs.getInt(1);}
    public String resultSetGrade(ResultSet rs)throws SQLException{return rs.getString(1);}

    private int resultSetYearOfStudy(ResultSet rs) throws SQLException { return rs.getInt(1);
    }

    public Admin getAdmin(){
        try {
            queryAdmin.setInt(1,1);
            ResultSet rs= queryAdmin.executeQuery();
            if(!rs.next()){
                return null;
            } return resultSetAdmin(rs);
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }

    public Student getStudent(String username){
        try {
            queryFindStudent.setString(1,username);
            //queryFindStudent.setString(2,password);
            ResultSet rs = queryFindStudent.executeQuery();
            if(!rs.next()){
                return null;
            } return resultSetStudent(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }
    public Student getStudentByName(String name, String lastname){
        try {
            queryFindStudentByName.setString(1,name);
            queryFindStudentByName.setString(2,lastname);
            //queryFindStudent.setString(2,password);
            ResultSet rs = queryFindStudentByName.executeQuery();
            if(!rs.next()){
                return null;
            } return resultSetStudent(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } return null;
    }

    public Professor getProfessor(String username){
        try {
            queryFindProfessor.setString(1,username);
            //queryFindProfessor.setString(2,password);
            ResultSet rs = queryFindProfessor.executeQuery();
            if(!rs.next()){
                return null;
            } return resultSetProfessor(rs);
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }


    public ArrayList<StudentSubject> getStudentsForSubject(String subject){
        ArrayList<StudentSubject> list = new ArrayList<>();
        try {
            queryFindSSByTitle.setString(1,subject);
            ResultSet rs = queryFindSSByTitle.executeQuery();
            while(rs.next()){
                StudentSubject studentSubject = resultSetStudentSubject(rs);
                list.add(studentSubject);
            } return list;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }

    public ArrayList<String> getSubjects(String branch){
        ArrayList<String> strings = new ArrayList<>();
        try {
            queryGetSubjects.setString(1,branch);
            ResultSet rs = queryGetSubjects.executeQuery();
            while (rs.next()){
                Subject subject = resultSetSubject(rs);
                strings.add(subject.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return strings;
    }
    public ArrayList<Subject> getSubjects2(String branch, int year){
        ArrayList<Subject> subjects = new ArrayList<>();
        try {
            queryGetSubjects2.setString(1,branch);
            queryGetSubjects2.setInt(2,year);
            ResultSet rs = queryGetSubjects2.executeQuery();
            while (rs.next()){
                Subject subject = resultSetSubject(rs);
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return subjects;
    }

    public ArrayList<String> getDistinctSubjects(String name, String lastname){
        ArrayList<String> strings = new ArrayList<>();
        try {
            queryGetDistinctSubjects.setString(1,name+" "+lastname);
            ResultSet rs = queryGetDistinctSubjects.executeQuery();
            while (rs.next()){
                Subject subject = resultSetSubject(rs);
                strings.add(subject.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return strings;
    }

    public ArrayList<String> getProfessors(){
        ArrayList<String> profesori=new ArrayList<>();
        try {
            ResultSet rs = queryGetProfessors.executeQuery();
            while(rs.next()){
                Professor professor= resultSetProfessor(rs);
                profesori.add(professor.getName()+" "+professor.getSurname()+": "+professor.getEmail());
            } return profesori;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }
    public ArrayList<String> getProfessors2(){
        ArrayList<String> profesori=new ArrayList<>();
        try {
            ResultSet rs = queryGetProfessors.executeQuery();
            while(rs.next()){
                Professor professor= resultSetProfessor(rs);
                profesori.add(professor.getName()+" "+professor.getSurname());
            } return profesori;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }

    public ArrayList<String> getAllStudents(){
        ArrayList<String> students=new ArrayList<>();
        try {
            ResultSet rs = queryGetAllStudents.executeQuery();
            while(rs.next()){
                Student student= resultSetStudent(rs);
                students.add(student.getName()+" "+student.getSurname());
            } return students;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }
    }
    public ArrayList<String> getAllExams() {
        ArrayList<String> activities = new ArrayList<>();
        try {
            queryGetExams.setString(1,"Ispit");
            ResultSet rs = queryGetExams.executeQuery();
            while (rs.next()) {
                Exam exam = resultSetExam(rs);
                activities.add(exam.toString());
            } return activities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }
    public ArrayList<String> getAllAnnouncements() {
        ArrayList<String> activities = new ArrayList<>();
        try {
            queryGetAnnouncements.setString(1,"Obavijest");
            ResultSet rs = queryGetAnnouncements.executeQuery();
            while (rs.next()) {
                Announcement ann = resultSetAnnouncement(rs);
                activities.add(ann.toString());
            } return activities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }
    public ArrayList<String> getAllHomework() {
        ArrayList<String> activities = new ArrayList<>();
        try {
            queryGetHomeworks.setString(1,"Zadaća");
            ResultSet rs = queryGetHomeworks.executeQuery();
            while (rs.next()) {
                Homework homework = resultSetHomework(rs);
                activities.add(homework.toString());
            } return activities;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }
    public String getSSGrade(String name, String lastname, String subject){
        try {
            queryGetSSGrade.setString(1,name+" "+lastname);
            queryGetSSGrade.setString(2,subject);
            ResultSet rs = queryGetSSGrade.executeQuery();
            return resultSetGrade(rs);

        } catch (SQLException e) {
            return "Nije upisan";
        }
    }

    public int getSSExam(String name, String lastname, String subject){
        try {
            queryGetSSExam.setString(1,name+" "+lastname);
            queryGetSSExam.setString(2,subject);
            ResultSet rs = queryGetSSExam.executeQuery();
            return resultSetPoints(rs);

        } catch (SQLException e) {
            return 0;
        }
    }
    public int getSSHomework(String name, String lastname, String subject){
        try {
            queryGetSSHomework.setString(1,name+" "+lastname);
            queryGetSSHomework.setString(2,subject);
            ResultSet rs = queryGetSSHomework.executeQuery();
            return resultSetPoints(rs);

        } catch (SQLException e) {
            return 0;
        }
    }
    public int getSSOther(String name, String lastname, String subject){
        try {
            queryGetSSOther.setString(1,name+" "+lastname);
            queryGetSSOther.setString(2,subject);
            ResultSet rs = queryGetSSOther.executeQuery();
            return resultSetPoints(rs);

        } catch (SQLException e) {
            return 0;
        }
    }
    public int getSubjectYear(String title){
        try {
            queryGetSubjectByTitle.setString(1,title);
            ResultSet rs = queryGetSubjectByTitle.executeQuery();
            return resultSetYearOfStudy(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } return 0;
    }


    public void addStudent(Student student){
        try {
            ResultSet rs = queryFindStudentId.executeQuery();
            int id=1;
            if(rs.next()){
                id=rs.getInt(1);
            }
            queryAddStudent.setInt(1,id);
            queryAddStudent.setString(2,student.getName());
            queryAddStudent.setString(3,student.getSurname());
            queryAddStudent.setString(4,student.getDate());
            queryAddStudent.setString(5,student.getSex());
            queryAddStudent.setString(6,student.getIndex());
            queryAddStudent.setString(7,student.getBranch());
            queryAddStudent.setInt(8,student.getYearOfStudy());
            queryAddStudent.setString(9,student.getUsername());
            queryAddStudent.setString(10,student.getPassword());
            queryAddStudent.setString(11,student.getEmail());
            queryAddStudent.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addProfessor(Professor professor){
        try {
            ResultSet rs = queryFindProfessorId.executeQuery();
            int id=1;
            if(rs.next()){
                id=rs.getInt(1);
            }
            queryAddProfessor.setInt(1,id);
            queryAddProfessor.setString(2, professor.getName());
            queryAddProfessor.setString(3,professor.getSurname());
            queryAddProfessor.setString(4,professor.getDate());
            queryAddProfessor.setString(5,professor.getSex());
            queryAddProfessor.setString(6,professor.getTitle());
            queryAddProfessor.setString(7,professor.getUsername());
            queryAddProfessor.setString(8,professor.getPassword());
            queryAddProfessor.setString(9,professor.getEmail());
            queryAddProfessor.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(Employee employee){
        try {
            ResultSet rs = queryFindEmployeeId.executeQuery();
            int id=1;
            if(rs.next()){
                id=rs.getInt(1);
            }
            queryAddEmployee.setInt(1,id);
            queryAddEmployee.setString(2,employee.getName());
            queryAddEmployee.setString(3,employee.getSurname());
            queryAddEmployee.setString(4,employee.getTitle());
            queryAddEmployee.setString(5,employee.getUsername());
            queryAddEmployee.setString(6,employee.getPassword());
            queryAddEmployee.setString(7,employee.getEmail());
            queryAddEmployee.setString(8,employee.getDateEpl());
            queryAddEmployee.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSubject(Subject subject){
        try {
            ResultSet rs= queryFindSubjectId.executeQuery();
            int id=1;
            if(rs.next()){
                id=rs.getInt(1);
            }
            queryAddSubject.setInt(1,id);
            queryAddSubject.setString(2, subject.getTitle());
            queryAddSubject.setString(3, subject.getEcts());
            queryAddSubject.setInt(4, subject.getClassYear());
            queryAddSubject.setString(5, subject.getSubjectTeacher());
            queryAddSubject.setString(6, subject.getBranch());
            queryAddSubject.setInt(7, subject.getSemester());
            queryAddSubject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addHomework(Homework homework, String activityType){
        try {
            ResultSet rs= queryFindActivityId.executeQuery();
            int id=1;
            if(rs.next()){
                id=rs.getInt(1);
            }
            queryAddActivity.setInt(1,id);
            queryAddActivity.setString(2,homework.getSubName());
            queryAddActivity.setString(3,homework.getDate());
            queryAddActivity.setString(4,activityType);
            queryAddActivity.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addExam(Exam exam, String activityType){
        try {
            ResultSet rs= queryFindActivityId.executeQuery();
            int id=1;
            if(rs.next()){
                id=rs.getInt(1);
            }
            queryAddActivity.setInt(1,id);
            queryAddActivity.setString(2,exam.getExamSubject());
            queryAddActivity.setString(3,exam.getDate());
            queryAddActivity.setString(4,activityType);
            queryAddActivity.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void enrollStudent(Student student, String subject, int points, String grade){
        try {
            ResultSet rs = queryFindSubjectStudentId.executeQuery();
            int id=1;
            if(rs.next()){
                id=rs.getInt(1);
            }
            queryEnrollStudent.setInt(1,id);
            queryEnrollStudent.setString(2,student.getName()+" "+student.getSurname());
            queryEnrollStudent.setString(3,subject);
            queryEnrollStudent.setInt(4,points);
            queryEnrollStudent.setString(5,grade);
            queryEnrollStudent.setInt(6,0);
            queryEnrollStudent.setInt(7,0);
            queryEnrollStudent.setInt(8,0);
            queryEnrollStudent.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addAnnouncement(Announcement announcement, String activityType){
        try {
            ResultSet rs= queryFindActivityId.executeQuery();
            int id=1;
            if(rs.next()){
                id=rs.getInt(1);
            }
            queryAddActivity.setInt(1,id);
            queryAddActivity.setString(2,announcement.getText());
            queryAddActivity.setString(3,announcement.getDate());
            queryAddActivity.setString(4,activityType);
            queryAddActivity.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateStudentSubject(Integer points, String grade,String student, String subject,int exam,int homew, int other){
        try {
            queryUpdateStudentSubject.setInt(1, points);
            queryUpdateStudentSubject.setString(2,grade);
            queryUpdateStudentSubject.setInt(3,exam);
            queryUpdateStudentSubject.setInt(4,homew);
            queryUpdateStudentSubject.setInt(5,other);
            queryUpdateStudentSubject.setString(6,student);
            queryUpdateStudentSubject.setString(7,subject);

            queryUpdateStudentSubject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void deleteStudent(String name){
        try {
            queryDeleteStudent.setString(1,name);
            queryDeleteStudent.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteSubject(String title){
        try {
            queryDeleteSubject.setString(1,title);
            queryDeleteSubject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteProfessor(String name){
        try {
            queryDeleteProfessor.setString(1,name);
            queryDeleteProfessor.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConn() { return conn;
    }

    public StudentSubject findEnrolledStudent(String name, String subject) {
        try {
            queryFindEnrolled.setString(1,name);
            queryFindEnrolled.setString(2,subject);
            ResultSet rs = queryFindEnrolled.executeQuery();
            if(!rs.next()){
                return null;
            } return resultSetStudentSubject(rs);
        } catch (SQLException e) {
            return null;
        }
    }

    public void writeFile(File file, Student student) throws IOException {
        try {
            StringBuilder grades = new StringBuilder();
            ArrayList<Subject> list1 = getSubjects2("Svi", student.getYearOfStudy());
            list1.addAll(getSubjects2(student.getBranch(), student.getYearOfStudy()));

            grades.append("Naziv predmeta::Ocjena \r\n");
            for (Subject s : list1) {
                String title = s.getTitle();
                grades.append(title).append("::").append(getSSGrade(student.getName(), student.getSurname(), title)).append("\r\n");

            }
            FileWriter fw = new FileWriter(file);
            fw.write(grades.toString());
            fw.close();
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
    }
}
