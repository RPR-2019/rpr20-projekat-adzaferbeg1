package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Locale;

public class StudentLoginController implements ILogout, IWindow{
    final UserDAO model = UserDAO.getInstance();
    final Locale locale = Locale.getDefault();
    private final Student student;
    @FXML
    public ChoiceBox<String> cbEmail;
    @FXML
    public Text txtWelcome, txtInfo, txtGPA;
    @FXML
    public TableColumn<Subject, String> colTitle,  colGrade;
    @FXML
    public TableColumn<Subject,Integer> colExam, colHomework, colPoints, colOther;
    @FXML
    public TableView<Subject> tableViewSubject;
    @FXML
    private CheckBox checkbAnn, checkbEx,checkbH;
    @FXML
    private ListView<String> lvActivity;

    public StudentLoginController(String username) {
        this.student =  model.getStudent(username);
    }
    @FXML
    public void initialize(){

        String info = "Broj indeksa: "+student.getIndex()+"\n"+"Odsjek: "+student.getBranch()+"\n"+"Godina studija: "+student.getYearOfStudy()+"\n"+
                "Username: "+student.getUsername()+"\n"+"Password: "+student.getPassword()+"\n"+"E-mail: "+student.getEmail();
        txtInfo.setText(info);
        setWelcomeText();
        ArrayList<String> subjects = model.getSubjects("Svi"); subjects.addAll(model.getSubjects(student.getBranch()));
        cbEmail.setItems(FXCollections.observableArrayList(model.getProfessors()));
        if(student.getYearOfStudy()<=3){ txtGPA.setText(txtGPA.getText()+" Niste završili I ciklus");}


    }
    @Override
    public void logout(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        openWindow("login", locale);
    }

  public void sendEmail(ActionEvent actionEvent) {

            try {
                Desktop desktop = Desktop.getDesktop();
                URI oURL = new URI("http://www.gmail.com");
                desktop.browse(oURL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


  public void subjectListAll(ActionEvent actionEvent){
      tableViewSubject.setItems(null);
      ObservableList<Subject> lista = createSubjectList();
      setTable(lista);
  }
  public void subjectListCurrent(ActionEvent actionEvent){
      tableViewSubject.setItems(null);
      ObservableList<Subject> lista = createSubjectList();
      lista.removeIf(e -> e.getClassYear()!=student.getYearOfStudy());
      setTable(lista);

  }
    public void subjectListPast(ActionEvent actionEvent){
        tableViewSubject.setItems(null);
        ObservableList<Subject> list = createSubjectList();
        list.removeIf(e -> e.getClassYear()>=student.getYearOfStudy());
        setTable(list);
    }

    public void setTable(ObservableList<Subject> list){

            tableViewSubject.setItems(list);
            colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            colHomework.setCellValueFactory(new PropertyValueFactory<>("studentHomework"));
            colExam.setCellValueFactory(new PropertyValueFactory<>("studentExam"));
            colOther.setCellValueFactory(new PropertyValueFactory<>("studentOther"));
            colPoints.setCellValueFactory(new PropertyValueFactory<>("points"));
            colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

    }

    public ObservableList<Subject> createSubjectList() {

            ArrayList<Subject> list1 = model.getSubjects2("Svi", student.getYearOfStudy());
            list1.addAll(model.getSubjects2(student.getBranch(), student.getYearOfStudy()));
            for (Subject s : list1) {

                    String title = s.getTitle();
                    s.setStudentExam(model.getSSExam(student.getName(), student.getSurname(), title));
                    s.setStudentHomework(model.getSSHomework(student.getName(), student.getSurname(), title));
                    s.setStudentOther(model.getSSOther(student.getName(), student.getSurname(), title));
                    s.setGrade(model.getSSGrade(student.getName(), student.getSurname(), title));
                    s.setPoints(s.getStudentExam() + s.getStudentHomework() + s.getStudentOther());

            }
            ObservableList<Subject> list = FXCollections.observableArrayList();
            list.addAll(list1);
            return list;

    }


    public void exams(ActionEvent actionEvent){
        if(checkbEx.isSelected()){
            checkbAnn.setSelected(false);
            checkbH.setSelected(false);
            ObservableList<String> exams = FXCollections.observableArrayList();
            exams.addAll(model.getAllExams());
            lvActivity.setItems(exams);
        }
    }
    public void homeworks(ActionEvent actionEvent){
        if(checkbH.isSelected()){
            checkbEx.setSelected(false);
            checkbAnn.setSelected(false);
            ObservableList<String> home = FXCollections.observableArrayList();
            home.addAll(model.getAllHomework());
            lvActivity.setItems(home);

        }

    }
    public void announcements(ActionEvent actionEvent){
        if(checkbAnn.isSelected()){
            checkbEx.setSelected(false);
            checkbH.setSelected(false);
            ObservableList<String> ann = FXCollections.observableArrayList();
            ann.addAll(model.getAllAnnouncements());
            lvActivity.setItems(ann);
        }

    }
    public void setWelcomeText(){
        if (student.getYearOfStudy()>3) txtWelcome.setText(txtWelcome.getText()+" "+student.getName()+" (MSc)");
        if (student.getYearOfStudy()>5) txtWelcome.setText(txtWelcome.getText()+" "+student.getName()+" (PhD)");
        txtWelcome.setText(txtWelcome.getText()+" "+student.getName()+" (BSc)");
    }

    public void print(ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Desktop"));
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("prepis_ocjena");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT File", "*.txt"));
        try{
            File file = fileChooser.showSaveDialog(cbEmail.getScene().getWindow());
            if(file!=null){
                model.writeFile(file, this.student);
            }
        }catch(Exception e){
            System.out.println("Error saving file");
        }

    }
}
