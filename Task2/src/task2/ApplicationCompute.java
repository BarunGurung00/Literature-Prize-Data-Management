package task2;

import java.util.Scanner;

public class ApplicationCompute {

    ApplicationCompute() {
        ShowMenu();
    }

    public static void ShowMenu(){

            Scanner scanner = new Scanner(System.in);
            String choice;

            do {
                System.out.println();
                System.out.println("-------------------------------------------");
                System.out.println("     Swimming school management system    ");
                System.out.println("-------------------------------------------");
                System.out.println("View swim student information ............1");
                System.out.println("View swim lesson details .................2");
                System.out.println("View instructor schedule .................3");
                System.out.println("Add new swim student  ....................4");
                System.out.println("Award swim qualification  ................5");
                System.out.println("Move swim student from waiting list ......6");
                System.out.println("-------------------------------------------");
                System.out.print("Enter choice > ");

                choice = scanner.next();

                System.out.println();

                switch (choice) {
                    case "1":
                       viewSwimStudentInfo();
                        break;
                    case "2":
                        viewSwimLessonDetails();
                        break;
                    case "3":
                        viewInstructorSchedule();
                        break;
                    case "4":
                        addNewSwimStudent();
                        break;
                    case "5":
                        awardSwimQualification();
                        break;
                    case "6":
                        moveSwimStudentFromWaitingList();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (!choice.equals("0"));

            scanner.close();
    }

    public static void viewSwimStudentInfo(){
        //step 1. Ask the user to select student from the Student list displayed in alphabetical order

        //After choosing the student from the list;

        //step 2. Display the Day and time_start of the swim class along with the instructor
                //if(student level == novice) and in the waiting list indicate this fact

        //There are 2 type of qualification provided a-DistanceSwim b-PersonalSurvival
        //step 3. Display a list of swim qualification awarded ot the student including the name of the swim instructor who awarded
                 //if(studentLevel == advance)  display personal survival qualification by the student with the name of the awarding instructor

    }

    public static void viewSwimLessonDetails(){

    }
    public static void viewInstructorSchedule(){

    }
    public static void  addNewSwimStudent(){

    }
    public static void  awardSwimQualification(){

    }
    public static void  moveSwimStudentFromWaitingList(){

    }
}
