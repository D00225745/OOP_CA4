package dkit.oop;


import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * CoursesManager
 * This software component Encapsulates the storage and management of
 * all courses available through the CAO system.
 * Only administrators would typically be allowed to update this data,
 * but other users can get a COPY of all the courses by calling getAllCourses().
 * The Web Client would need this data to display the course codes,
 * course titles and institutions to the students.
 */

public class CourseManager {

    HashMap<String, Course> courses = new HashMap<>();

    private static Scanner keyboard = new Scanner(System.in);
    // Store all the Course details.
    // Requires fast access given courseId.

    public CourseManager() {
        // Hardcode some values to get started
        // load from text file "courses.dat" and populate coursesMap
    }

    private void loadCoursesFromFile(HashMap<Integer, Course> courses)
    {
        try(Scanner courseFile = new Scanner(new BufferedReader(new FileReader("courses.txt"))))
        {
            String input;
            while(courseFile.hasNextLine())
            {
                input = courseFile.nextLine();
                String [] data = input.split(",");
                String courseId = data[0];
                String level = data[1];
                String title = data[2];
                String institution = data[3];


                Course readInCourses = new Course(courseId,level,title,institution);
                this.courses.put(courseId,readInCourses);

            }
        }

        catch (FileNotFoundException fne)
        {
            System.out.println("Could not load Courses...");
        }
    }

    public void saveCoursesToFile()
    {
        try(BufferedWriter coursesFile = new BufferedWriter(new FileWriter("courses.txt")))
        {
            for(Course course : courses)
            {
                coursesFile.write(course.getCourseId()+","+course.getLevel()+","+course.getTitle() + "," +course.getInstitution());
                coursesFile.write("\n");
            }
        }
        catch(IOException ioe)
        {
            System.out.println("Could not save the course...");
        }
    }


//
//
//    public  getAllCourses() {
//    }
//

      public static void addCourse()
      {
          String courseId = enterField("courseId");
          String level = enterField("level");
          String title = enterField("title");
          String institution = enterField("institution");
      }

      private static String enterField(String field)
      {
          String input;
          System.out.println("Enter your course: " + field + " .");
          input = keyboard.nextLine();
          return input;
      }
//
      public void removeCourse()
      {
          if(this.courses != null)
          {
              String courseToRemove = enterField("Courses to remove..");
              Course coursesToRemove = findCourse(courseToRemove);
              if(coursesToRemove != null)
              {
                  courses.remove(courseToRemove);
              }
              else
              {
                  System.out.println("Selected course does not exist..");
              }
          }
      }

    private Course findCourse(String courseToFind)
    {
        if(courses.containsKey(courseToFind))
        {
            courses.get(courseToFind);
        }
        return null;
    }

    public void printCourse()
    {
        String courseToDisplay = enterField("course to display");
        Course courseToPrint = findCourse(courseToDisplay);
        if(courseToPrint != null)
        {
            System.out.println(courseToPrint);
        }
        else
        {
            System.out.println("Seleced course does not exist...");
        }
    }



    // editCourse(courseId);       // not required for this iteration

}







