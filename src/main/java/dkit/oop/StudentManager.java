package dkit.oop;
// StudentManager encapsulates the storage and ability
// to manipulate students objects

import java.io.*;
import java.util.HashMap;
import java.util.Map;
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


    public int logIn()
    {
        boolean loop = true;
        int caoNumber = 0;

        while(loop)
        {
            try {
                caoNumber = Integer.parseInt(enterinfo("caoNumber"));
                loop = false;
                isRegistered(caoNumber);
            }
            catch(NumberFormatException nfe)
            {
                System.out.println("enter a valid CAO number");
            }

            String password = enterinfo("password");
            String dateOfBirth = enterinfo("dateOfBirth");
            Student student = students.get(caoNumber);

            if(student.getDayOfBirth().equals(dateOfBirth) && student.getPassword().equals(password))
            {
                return caoNumber;
            }
            else
            {
                System.out.println("Incorrect information. User not registered.. Try again...");
            }
        }
        return caoNumber;
    }


    private String enterinfo(String info)
    {
        String input;
        System.out.println("please enter your " + info + " .");
        input keyboard.nextLine();
        return input;
    }

        




    private static String enterInfo(String info)
    {
        String input;
        System.out.println("Enter your student: " + info + " .");
        input = keyboard.nextLine();
        return input;
    }
    //



    private Student findStudent(String studentToFind)
    {
        if(students.containsKey(studentToFind))
        {
            students.get(studentToFind);
        }
        return null;
    }



    public void saveStudentsToFile()
    {
        try(BufferedWriter studentsFile = new BufferedWriter(new FileWriter("students.txt")))
        {
            for(Student student : students)
            {
                studentsFile.write(student.getName()+","+student.getCaoNumber()+","+student.getName()+","+ student.getDayOfBirth()+","+student.getPassword()+","+student.getEmail());
                studentsFile.write("\n");
            }
        }
        catch(IOException ioe)
        {
            System.out.println("Could not save the students");
        }
    }

    public void printStudent()
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

    public static void addStudent()
    {
        int caoNumber = Integer.parseInt(enterInfo("caoNumber"));
        String name = enterInfo("name");
        String dateOfBirth = enterInfo("dateOfBirth");
        String password = enterInfo("password");
        String email = enterInfo("email");
    }

    public void deleteStudent()
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

//    isRegistered( caoNumber)
      public boolean isRegistered(int caoNumber)
      {
          return students.keySet().contains(caoNumber);
      }


//        students.isValid()
}
