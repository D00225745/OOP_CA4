package dkit.oop;

public class Student {

    private int caoNumber;  // In the CAO system, cao number is unique identifier for students
    private String name;  // Name of student
    private String dateOfBirth; // yyyy-mm-dd
    private String password;    // min 8 characters
    private String email;

    // Copy Constructor
    // Copies the contents of a Student object argument into
    // a new Student object, and returns that new object (a clone)
    // (add here)
    public Student()
    {
        this(123,"Cain","2001-02-19","ShadowAssassin","KaynMaynmail");
    }

    public Student(Student studentCopy1)
    {
        this.caoNumber = studentCopy1.caoNumber;
        this.name = studentCopy1.name;
        this.dateOfBirth = studentCopy1.dateOfBirth;
        this.password = studentCopy1.password;
        this.email = studentCopy1.email;
    }

    // Constructor
    public Student(int caoNumber, String name, String dateOfBirth, String password, String email) {
        this.caoNumber = caoNumber;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.email = email;
    }

    //public boolean verifyLoginCredentials( yyy-mm-dd, password);

    public int getCaoNumber() {
        return caoNumber;
    }

    public void setCaoNumber(int caoNumber) {
        this.caoNumber = caoNumber;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDayOfBirth() {
        return dateOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dateOfBirth = dayOfBirth;
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

    @Override
    public String toString() {
        return "Student{" +
                "caoNumber=" + caoNumber +
                ", name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
