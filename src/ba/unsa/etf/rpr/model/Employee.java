package ba.unsa.etf.rpr.model;

public class Employee extends User{
    private int id;
    private String name,surname,title,username,password,email,dateEpl;

    public Employee() {
    }

    public Employee(int id, String name, String surname, String title, String username, String password, String email, String dateEpl) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateEpl = dateEpl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateEpl() {
        return dateEpl;
    }

    public void setDateEpl(String dateEpl) {
        this.dateEpl = dateEpl;
    }
}
