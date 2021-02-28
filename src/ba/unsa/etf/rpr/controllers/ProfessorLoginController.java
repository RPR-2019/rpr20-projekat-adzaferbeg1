package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.Locale;

public class ProfessorLoginController implements ILogout, IWindow, IAlert{
    private final Professor professor;
    final Locale locale = Locale.getDefault();
    private final UserDAO model = UserDAO.getInstance();
    @FXML
    private Text txtWelcome, txtCount;
    @FXML
    private ChoiceBox<String> cbSubjectGrade;
    @FXML
    private ChoiceBox<String> cbSubjectEnroll;
    @FXML
    private ChoiceBox<String> cbStudent;
    @FXML
    private ChoiceBox<String> cbActivity;
    @FXML
    public TableColumn<StudentSubject,String> colStudent,colGrade;
    @FXML
    public TableColumn<StudentSubject,Integer> colPoints;
    @FXML
    private TableView<StudentSubject> tbStudents;


    public ProfessorLoginController(String username) {
        professor=model.getProfessor(username);
    }

    @FXML
    public void initialize(){
        txtWelcome.setText(txtWelcome.getText()+" "+professor.getName());
        txtCount.setText("Broj upisanih studenata: *nije odabran predmet");
        cbSubjectEnroll.setItems(createSubjectList());
        cbSubjectGrade.setItems(createSubjectList());
        cbStudent.setItems(FXCollections.observableArrayList(model.getAllStudents()));
        cbActivity.setItems(FXCollections.observableArrayList("Ispit", "Zadaća", "Obavijest"));
    }

    public ObservableList<String> createSubjectList(){
        ObservableList<String> lista = FXCollections.observableArrayList();
        lista.addAll(model.getDistinctSubjects(professor.getName(),professor.getSurname()));
        return lista;
    }
    @Override
    public void logout(ActionEvent actionEvent){
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        openWindow("login", locale);
    }
    public void addActivity(ActionEvent actionEvent){
        if(cbActivity.getValue()!=null) {
            ActivityFormController ctrl = new ActivityFormController(cbActivity.getValue());
            openWindowController(ctrl, "activityForm", locale);
        }else{
            showAlertWarning("Nije odabrana aktivnost", "Pokušajte ponovo");
        }

    }


    public void enroll(ActionEvent actionEvent){
        try {
            String name = cbStudent.getValue();
            String[] arrayName = name.split(" ", 2);
            String sName = arrayName[0];
            String sLastname = arrayName[1];
            Student student = model.getStudentByName(sName, sLastname);
            int year = model.getSubjectYear(cbSubjectEnroll.getValue());

        try {
            if(year == student.getYearOfStudy() || student.getYearOfStudy() == year + 1){
                try{
                    StudentSubject isStudentEnrolled = model.findEnrolledStudent(name, cbSubjectEnroll.getValue());
                    showAlertWarning("Student je već upisan na kurs", "Odaberite drugog studenta");
                }catch (Exception e){

                model.enrollStudent(student, cbSubjectEnroll.getValue(), 0, "Nije ocijenjeno");
                showAlertInformation("Student je upisan", "Zatvorite prozor");
            }}
            else{
            showAlertWarning("Student ne može biti upisan jer ne pripada "+year+". godini studija ili nije ponovac",
                        "Odaberite drugog studenta");

            }
        }catch (Exception e){
            showAlertWarning("Student nije upisan", "Pokušajte opet" );

        }
        }catch (Exception e){
            showAlertWarning("Niste odabrali studenta ili predmet iz padajuće liste","Pokušajte opet");
        }
    }

    public void showStudents(ActionEvent actionEvent){

            try {
                ObservableList<StudentSubject> list = FXCollections.observableArrayList();
                list.addAll(model.getStudentsForSubject(cbSubjectGrade.getValue()));
                int enrolledStudentsNo = (int) list.stream().distinct().count();
                txtCount.setText("Broj upisanih studenata: " + enrolledStudentsNo);
                tbStudents.setItems(list);
                colStudent.setCellValueFactory(new PropertyValueFactory<>("student"));
                colPoints.setCellValueFactory(new PropertyValueFactory<>("points"));
                colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
            } catch (Exception ignored) {

            }
        }


    public void updateGrade(ActionEvent actionEvent){
        if(tbStudents.getSelectionModel().getSelectedItem() == null){
            showAlertWarning("Niste odabrali nijednog studenta", "Odaberite studenta");
        }else {
            StudentSubject studentSubject = tbStudents.getSelectionModel().getSelectedItem();
            GradeSubjectController ctrl = new GradeSubjectController(studentSubject, cbSubjectGrade.getValue());
            openWindowController(ctrl, "gradeSubject", locale);
        }

    }

}
