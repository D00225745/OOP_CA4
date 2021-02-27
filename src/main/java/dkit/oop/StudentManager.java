package dkit.oop;
// StudentManager encapsulates the storage and ability
// to manipulate students objects

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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

    public void logIn()
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
                boolean loggedIn = false;

                Student student = students.get(caoNumber);
                    if(student.getDayOfBirth().equals(dateOfBirth) && student.getPassword().equals(password))
                    {
                        students.keySet().contains(caoNumber);
                    }
                }
            }
            

        private String enterinfo(String info)
        {
            String input;
            System.out.println("please enter your " + info + " .");
            input keyboard.nextLine();
            return input;
        }
        

//    public getStudent() {
//    }
//
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
