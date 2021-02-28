package dkit.oop;

import com.sun.tools.javac.Main;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
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

    private static Scanner keyboard = new Scanner(System.in);



    private void start() {

        System.out.println("The Student Course Listing Menu starts here...?..");
        // load students
        StudentManager studentManager = new StudentManager();
        // load courses
        CourseManager courseManager= new CourseManager();

        doMainMenuLoop(studentManager,courseManager);


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

    private static void makeDeepCopy(Student studentCopy1)
    {
        Student studentCopy2 = new Student(studentCopy1);
        studentCopy2.setCaoNumber(557);
        studentCopy2.setName("Raum");
        studentCopy2.setDayOfBirth("2003-04-18");
        studentCopy2.setPassword("BigMac");
        studentCopy2.setEmail("Zbabweilolz");
        System.out.println(studentCopy1);
        System.out.println(studentCopy2);
    }

    private void doMainMenuLoop(StudentManager studentManager, CourseManager courseManager)
    {
        boolean loop = true;
        MainMenu menuOption;
        int option = -1;
        while(loop)
        {
            printMainMenu();
            try
            {
                String input = keyboard.nextInt();
                if(option < 0 || option >= MainMenu.values().length)
                {
                    throw new IllegalArgumentException();
                }
                keyboard.nextLine();
                menuOption = MainMenu.values()[option];
                switch (menuOption)
                {
                    case QUIT_APPLICATION:
                        loop = false;
                        break;
                        case LOG_IN;
                        if(studentManager.logIn())
                        {
                            loggedIn=true;
                        }
                }

                if(option < 0 || option >= MainMenu.values().length)
                {
                    throw new IllegalArgumentException();
                }
                //keyboard.nextLine();
                menuOption = MainMenu.values()[option];
                switch(menuOption)
                {
                    case QUIT_APPLICATION:
                        loop = false;
                        break;
                    case DISPLAY_PLAYER_MENU:
                        doPlayerMainMenuLoop(studentManager, courseManager);
                        break;
                }
            }
            catch(InputMismatchException ime)
            {
                System.out.println("Please enter a valid option");
                keyboard.nextLine();
            }
            catch(IllegalArgumentException iae)
            {
                System.out.println("Please enter a valid option");
            }
        }
        System.out.println("Thanks for using the app");
    }

    private void doPlayerMainMenuLoop(StudentManager studentManager)
    {
        boolean loop = true;
        StudentMenu menuOption;
        int option;
        while (loop)
        {
            printStudentMenu();
            try
            {
                option = keyboard.nextInt();
                keyboard.nextLine();
                menuOption = StudentMenu.values()[option];
                switch (menuOption)
                {
                    case QUIT_PLAYER_MENU:
                        loop = false;
                        break;
                    case ADD_PLAYER:
                        studentManager.addStudent();
                        break;
                    case DELETE_PLAYER:
                        studentManager.deleteStudent();
                        break;
                    case PRINT_PLAYER:
                        studentManager.printStudent();
                        break;
                }
            } catch (InputMismatchException ime)
            {
                System.out.println("Please enter a valid option");
            }
        }
    }

    private void printStudentMenu()
    {
        System.out.println("\n Options to select:");
        for(int i=0; i < StudentMenu.values().length;i++)
        {
            System.out.println("\t" +  i + ". " + StudentMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }

    private void printMainMenu()
    {
        System.out.println("\n Options to select:");
        for(int i=0; i < MainMenu.values().length;i++)
        {
            System.out.println("\t" +  i + ". " + MainMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }



}
