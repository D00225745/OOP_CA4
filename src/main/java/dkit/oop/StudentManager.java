package dkit.oop;
// StudentManager encapsulates the storage and ability
// to manipulate students objects

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class StudentManager {

    // Store all students in data structure
    private static Scanner keyboard = new Scanner(System.in);

    HashMap<Integer, Student> students = new HashMap<Integer, Student>();


    public StudentManager() {
        // Hardcode some values to get started



        // later, load from text file "students.dat" and populate studentsMap
    }

    private void loadStudentsFromFile(HashMap<Integer, Student> students)
    {
        try(Scanner studentFile = new Scanner(new BufferedReader(new FileReader("students.txt"))))
        {
            String input;
            while(studentFile.hasNextLine())
            {
                input = studentFile.nextLine();
                String [] data = input.split(",");
                int caoNumber = Integer.parseInt(data[0]);
                String name = data[1];
                String dateOfBirth = data[2];
                String password = data[3];
                String email = data[4];

                Student readInStudent = new Student(caoNumber,name,dateOfBirth,password,email);
                this.students.put(caoNumber,readInStudent);

            }
        }

        catch (FileNotFoundException fne)
        {
            System.out.println(fne.getMessage());
        }
    }


        

//    public getStudent() {
//    }
public static void addStudent()
{
    int caoNumber = Integer.parseInt(enterInfo("caoNumber"));
    String name = enterInfo("name");
    String dateOfBirth = enterInfo("dateOfBirth");
    String password = enterInfo("password");
    String email = enterInfo("email");
}

    private static String enterInfo(String info)
    {
        String input;
        System.out.println("Enter your student: " + info + " .");
        input = keyboard.nextLine();
        return input;
    }
    //
    public void removeStudent()
    {
        if(this.students != null)
        {
            String studentToRemove = enterInfo("Student to remove");
            Student studentsToRemove = findStudent(studentToRemove);
            if(studentsToRemove != null)
            {
                students.remove(studentToRemove);
            }
            else
            {
                System.out.println("Selected course does not exist..");
            }
        }
    }

    private Student findStudent(String studentToFind)
    {
        if(students.containsKey(studentToFind))
        {
            students.get(studentToFind);
        }
        return null;
    }

    public void displayStudent()
    {
        String studentToDisplay = enterInfo("student to display");
        Student studentToPrint = findStudent(studentToDisplay);
        if(studentToPrint != null)
        {
            System.out.println(studentToPrint);
        }
        else
        {
            System.out.println("Seleced student does not exist...");
        }
    }
//    public addStudent() {
//    }
//
//    public removeStudent() {
//    }

//    isRegistered( caoNumber)
      public boolean isRegistered(int caoNumber)
      {
          return students.keySet().contains(caoNumber);
      }
//        students.isValid()
}
