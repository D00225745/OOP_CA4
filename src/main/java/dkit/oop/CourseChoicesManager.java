package dkit.oop;

// Stores all students CAO choices.
// Allows students to make course choices, save them and update them later.
//
// emphasis on speed of access when multiple users are accessing this at same time
//
// This component would interact with a Network component that would, in turn, send
// data over the internet to a web client.
//
// Clone all received and returned objects - encapsulation

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseChoicesManager {

    // reference to constructor injected studentManager
    private StudentManager studentManager;

    // reference to constructor injected courseManager
    private CourseManager courseManager;

    // Store all the Course details -  fast access
    private ArrayList<Course> courseList = new ArrayList<>();

    // caoNumber, course selection list - for fast access
    private Map<Student, List<Course>> studentChoices = new HashMap<>();

    // CourseChoicesManager DEPENDS on both the StudentManager and CourseManager to access
    // students details and course details.  So, we receive a reference to each via
    // the constructor.
    // This is called "Dependency Injection", meaning that we
    // inject (or pass in) objects that this class requires to do its job.
    //
    CourseChoicesManager(StudentManager studentManager, CourseManager courseManager) {
        this.studentManager = studentManager;
        this.courseManager = courseManager;

    }

//    public Student getStudentDetails() {
//    }
//
//    public getCourseDetails() {
//    }
//
//    public  getStudentChoices() {
//    }
//
//    void updateChoices() {
//    }
//
//    public  getAllCourses() {
//    }
//
//    boolean login() {
//    }


}
