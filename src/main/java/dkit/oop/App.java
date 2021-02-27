package dkit.oop;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * Notes:
 *  Synchronisation of multiple reads and writes to file is not considered here.
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "CAO Online - CA4" );
        new App().start();

    Map<Integer, Student> studentMap = new HashMap<>();
    loadStudentsFromFile(studentMap, "students.txt");
    printStudentMap(studentMap);
    saveStudentsToFile(studentMap);


    }

    //with the student file that I made, after a successful run, the students other than the first one dissappear
    //Update: names no longer disappear, but 2nd and 3rd students switched places after initial test, but now they seem in place

    private static void saveStudentsToFile(Map<Integer, Student> studentMap)
    {
        try(BufferedWriter studentsFile = new BufferedWriter(new FileWriter("students.txt")))
        {
            for(Map.Entry<Integer, Student> entry : studentMap.entrySet())
            {
                studentsFile.write(entry.getValue().getCaoNumber() + "," + entry.getValue().getName()+ "," + entry.getValue().getDayOfBirth() + "," + entry.getValue().getPassword() + "," + entry.getValue().getEmail() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printStudentMap(Map<Integer, Student> studentMap)
    {
        for(Integer key : studentMap.keySet())
        {
            System.out.println(studentMap.get(key));
        }
    }

    private static void loadStudentsFromFile(Map<Integer, Student> studentMap, String fileToLoad)
    {
        try(Scanner studentFile = new Scanner(new BufferedReader(new FileReader("students.txt"))))
        {
            String input;
            while(studentFile.hasNextLine())
            {
                input = studentFile.nextLine();
                String[] data = input.split(",");
                int caoNumber = Integer.parseInt(data[0]);
                String name = data[1];
                String dateOfBirth = data[2];
                String password = data[3];
                String email = data[4];

                Student readInStudent = new Student(caoNumber, name, dateOfBirth, password, email);
                studentMap.put(caoNumber, readInStudent);
            }
        }

        catch (FileNotFoundException fne)
        {
            System.out.println(fne.getMessage());
        }


    }

    private void start() {

        // load students
        StudentManager studentManager = new StudentManager();

        // load courses
        CourseManager courseManager= new CourseManager();

        // load manager to provide functionality to allow a students
        // to login and add/update their course selections
        // This CourseChoicesManager component depends on the
        // StudentManager and the CourseManager,
        // so we 'inject' or pass-in these objects.
        //
        CourseChoicesManager mgr = new CourseChoicesManager(studentManager, courseManager);

        // display a menu to do things
        // manual testing of mgr public interface

//        if ( mgr.login(22224444, "xxxx","bbbb") )
//        {
//            Student students = mgr.getStudentDetails(22224444);
//
//            System.out.println("Student: " + students);
//        }
//        else
//            System.out.println("Not logged in - try again");


        //mgr.saveToFile();

    }
}
