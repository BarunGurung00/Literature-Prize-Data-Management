package task2;

import java.util.Comparator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class ApplicationCompute {

    private SwimSchool swimschool;

    public ApplicationCompute() {
        this.swimschool = new SwimSchool();
        addDummyData(swimschool);
        ShowMenu();
    }

    public static void main(String[] args) {
        ApplicationCompute instance1 = new ApplicationCompute();
        instance1.ShowMenu();
    }

    // Before the management console runs, we add some dummy data
    public void addDummyData(SwimSchool swimSchool) {

        // Two instructors for our swim school
        Instructor ins1 = new Instructor("Ins Randy");
        swimSchool.addInstructor(ins1);// Added our first instructor
        Instructor ins2 = new Instructor("Ins Bruno");
        swimSchool.addInstructor(ins2);// Added our second instructor

        // Three students for our swim school
        SwimStudent ram = new SwimStudent("Ram", SwimLevel.NOVICE);
        swimSchool.addStudent(ram);// Added our first student
        SwimStudent sita = new SwimStudent("Sita", SwimLevel.IMPROVER);
        swimSchool.addStudent(sita);// Added our second student
        SwimStudent hari = new SwimStudent("Hari", SwimLevel.ADVANCED);
        swimSchool.addStudent(hari);// Added our third student

        // Three classes for our swim school
        SwimClass novice = new SwimClass(SwimDay.FRIDAY, "19:00", SwimLevel.NOVICE, ins1);
        swimSchool.addClass(novice);// Added our first class and assigned instructor1
        novice.addStudent(ram);
        SwimClass improver = new SwimClass(SwimDay.MONDAY, "18:30", SwimLevel.IMPROVER, ins2);
        swimSchool.addClass(improver);// Added our second class and assigned instructor2
        improver.addStudent(sita);
        SwimClass advance = new SwimClass(SwimDay.WEDNESDAY, "18:00", SwimLevel.ADVANCED, ins1);
        swimSchool.addClass(advance);// Added our third class and assigned instructor1
        advance.addStudent(hari);

        // Two qualifications for our swim school
        Qualification qualification1 = new Qualification("20m", ram, ins1);
        swimSchool.addQualification(qualification1);// Added our first qualification
        Qualification qualification2 = new Qualification("400m", sita, ins2);
        swimSchool.addQualification(qualification2);// Added our second qualification

        // One student on waiting list and since we are required have a novice level in
        // waiting list, we add a novice student
        SwimStudent anna = new SwimStudent("Anaa", SwimLevel.NOVICE);
        swimSchool.addToWaitingList(anna);// Adding anna to waiting list as a novice student
    }

    public void ShowMenu() {
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
            System.out.println("Exit the Management Console ..............0");
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
            System.out.println();
        } while (!choice.equals("0"));

        scanner.close();
    }

    public void viewSwimStudentInfo() {
        System.out.println("-----------------------------------------");
        System.out.println("|    View Swim Student Information      |");
        System.out.println("-----------------------------------------");

        // Create a sorted list of swim students
        List<SwimStudent> sortedStudents = new ArrayList<>(SwimSchool.getStudents());
        sortedStudents.sort(Comparator.comparing(SwimStudent::getName));

        // Display list of swim students with their names and levels
        System.out.println("List of Swim Students:");
        for (int i = 0; i < sortedStudents.size(); i++) {
            SwimStudent student = sortedStudents.get(i);
            String output = String.format("%-3d. %-20s (Level: %s)", (i + 1), student.getName(), student.getLevel());
            System.out.println(output);
        }

        // Prompt user to select a swim student
        int studentIndex = getUserInput("Enter corresponding number of the swim student to view details > ", 1,
                sortedStudents.size()) - 1;

        // Get details of the selected swim student
        SwimStudent selectedStudent = sortedStudents.get(studentIndex);

        // Display details of the selected swim student
        displayStudentDetails(selectedStudent);
    }

    /**
     * Displays detailed information about a specific swim student including their
     * class, instructor, and qualifications.
     * 
     * @param student The swim student for which details are to be displayed.
     */
   
     private void displayStudentDetails(SwimStudent student) {
        // Display student's name and level
        System.out.println("\n===== Student Details =====");
        System.out.printf("Student Name: %-20s\n", student.getName());
        System.out.printf("Student Level: %-20s\n", student.getLevel());
    
        // Check if the student is on the waiting list
        if (student.isOnWaitingList()) {
            System.out.println("Student is on the waiting list.");
        } else {
            // Find the swim class for the student
            SwimClass studentClass = SwimSchool.findClassForStudent(student);
            if (studentClass != null) {
                System.out.printf("Day and Time of Swim Class: %-10s at %-10s\n", studentClass.getDay(), studentClass.getStart_time());
                System.out.printf("Instructor: %-20s\n", studentClass.getInstructor().getName());
            }
        }
    
        // Display swim qualifications for the selected student
        System.out.println("Swim Qualifications:");
        List<Qualification> studentQualifications = SwimSchool.getQualificationsForStudent(student);
        for (Qualification qualification : studentQualifications) {
            System.out.printf("- %-30s (Awarded by: %s)\n", qualification.getName(), qualification.getInstructor().getName());
        }
    }
    

    /**
     * Prompts the user to enter a number within a specified range and ensures the
     * input is valid.
     * 
     * @param prompt The prompt message asking for user input.
     * @param min    The minimum valid input value.
     * @param max    The maximum valid input value.
     * @return The user-entered valid input number.
     */
    private int getUserInput(String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print(prompt);
                scanner.next(); // Consume invalid input
            }
            userInput = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (userInput < min || userInput > max) {
                System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
            }
        } while (userInput < min || userInput > max);
        return userInput;
    }

    public void viewSwimLessonDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------");
        System.out.println("|        View Swim Lesson Details       |");
        System.out.println("-----------------------------------------");

        // Prompt user to choose a day for the swim class
        SwimDay selectedDay = promptDaySelection(scanner);

        // Prompt user to choose a swim level
        SwimLevel selectedLevel = promptLevelSelection(scanner);

        // Final helper method to find and display swim lesson details
        displayLessonDetails(selectedDay, selectedLevel);
    }

    private SwimDay promptDaySelection(Scanner scanner) {
        System.out.println("Enter the corresponding number to choose a day:");
        for (SwimDay day : SwimDay.values()) {
            System.out.println(day + " ............" + (day.ordinal() + 1));
        }
        System.out.println("-----------------------------------------");
        System.out.print("Enter number > ");
        int dayChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return SwimDay.values()[dayChoice - 1];
    }

    private SwimLevel promptLevelSelection(Scanner scanner) {
        System.out.println("Select a swim level:");
        for (SwimLevel level : SwimLevel.values()) {
            System.out.println((level.ordinal() + 1) + ". " + level);
        }
        System.out.print("Enter your choice > ");
        int levelChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return SwimLevel.values()[levelChoice - 1];
    }

    private void displayLessonDetails(SwimDay selectedDay, SwimLevel selectedLevel) {
        SwimClass selectedClass = null;
        for (SwimClass swimClass : SwimSchool.getClasses()) {
            if (swimClass.getDay() == selectedDay && swimClass.getLevel() == selectedLevel) {
                selectedClass = swimClass;
                break;
            }
        }

        System.out.println();
        if (selectedClass != null) {
            System.out.println("-----------------------------------------");
            System.out.println("|           Swim Lesson Details         |");
            System.out.println("-----------------------------------------");

            List<SwimStudent> students = selectedClass.getStudents();
            System.out.println("Students enrolled in the selected class:");
            for (SwimStudent student : students) {
                System.out.println("- " + student.getName());
            }

            System.out.println("Day: " + selectedClass.getDay());
            System.out.println("Start Time: " + selectedClass.getStart_time());
            System.out.println("Swim Level: " + selectedClass.getLevel());
            System.out.println("Instructor: " + selectedClass.getInstructor().getName());

            int remainingCapacity = 4 - students.size();
            if (remainingCapacity > 0) {
                System.out.println("\nSeats available: " + remainingCapacity + " out of 4");
            } else {
                System.out.println("\nThe class is currently full.");
            }
        } else {
            System.out.println("No class found for the selected day and level.");
        }
    }

    public void viewInstructorSchedule() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------");
        System.out.println("|       View Instructor's Schedule       |");
        System.out.println("-----------------------------------------");

        System.out.println("Select an instructor:");
        List<Instructor> instructors = SwimSchool.getInstructors();
        for (int i = 0; i < instructors.size(); i++) {// Instuctor list, in out case 2 instructors
            System.out.println((i + 1) + ". " + instructors.get(i).getName());
        }
        System.out.print("Choose an Instructor: ");
        int instructorChoice = scanner.nextInt();
        scanner.nextLine();

        // Retrieve selected instructor
        Instructor selectedInstructor = instructors.get(instructorChoice - 1);

        // Display schedule for selected instructor
        displayInstructorSchedule(selectedInstructor);
    }

    private void displayInstructorSchedule(Instructor selectedInstructor) {
        System.out.println("-----------------------------------------");
        System.out.println("|   Schedule for " + selectedInstructor.getName() + "  |");
        System.out.println("-----------------------------------------");
        boolean foundClasses = false;
        for (SwimClass swimClass : SwimSchool.getClasses()) {
            if (swimClass.getInstructor().equals(selectedInstructor)) {
                System.out.println("Day: " + swimClass.getDay());
                System.out.println("Start Time: " + swimClass.getStart_time());
                System.out.println("Swim Level: " + swimClass.getLevel());

                List<SwimStudent> students = swimClass.getStudents();
                System.out.println("Students:");
                for (SwimStudent student : students) {
                    System.out.println("- " + student.getName());
                }

                System.out.println();
                foundClasses = true;
            }
        }

        if (!foundClasses) {
            System.out.println("No classes found for " + selectedInstructor.getName());
        }
    }

    public void awardSwimQualification() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------");
        System.out.println("|       Award Swim Qualification        |");
        System.out.println("-----------------------------------------");

        System.out.println("Select an instructor:");
        List<Instructor> instructors = SwimSchool.getInstructors();
        for (int i = 0; i < instructors.size(); i++) {// Instructor list gets displayed in our case two
            System.out.println((i + 1) + ". " + instructors.get(i).getName());
        }
        System.out.print("Enter your choice: ");
        int instructorChoice = scanner.nextInt();
        scanner.nextLine();

        // Retrieve selected instructor
        Instructor selectedInstructor = instructors.get(instructorChoice - 1);

        // Display list of available students
        System.out.println("\nSelect a student:");
        List<SwimStudent> students = SwimSchool.getStudents();
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getName());
        }
        System.out.print("Enter your choice: ");
        int studentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Retrieve selected student
        SwimStudent selectedStudent = students.get(studentChoice - 1);

        // Check if the selected student is advanced level
        if (selectedStudent.getLevel() == SwimLevel.ADVANCED) {
            System.out.println("\nSelect a qualification:");
            System.out.println("1. Distance Swim Qualification");
            System.out.println("2. Personal Survival Qualification");
            System.out.print("Enter your choice: ");
            int qualificationChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (qualificationChoice == 1) {
                awardDistanceQualification(selectedInstructor, selectedStudent);
            } else if (qualificationChoice == 2) {
                awardSurvivalQualification(selectedInstructor, selectedStudent);
            } else {
                System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("The selected student is not at the advanced level. Awarding distance qualification.");
            awardDistanceQualification(selectedInstructor, selectedStudent);
        }
    }

    public void addNewSwimStudent() {
        System.out.println("-----------------------------------------");
        System.out.println("|-------- Add New Swim Student ----------|");
        System.out.println("-----------------------------------------");

        Scanner scanner = new Scanner(System.in);
        String newName = promptForStudentName(scanner);

        if (displayWeeklyScheduleForNoviceLevel()) {
            int dayChoice = promptForClassSelection(scanner);
            SwimDay selectedDay = SwimDay.values()[dayChoice - 1];
            SwimClass selectedClass = findSelectedClass(selectedDay, SwimLevel.NOVICE);

            if (selectedClass != null) {
                addStudentToClass(selectedClass, newName);
            } else {
                addStudentToWaitingList(newName);
            }
        } else {
            addStudentToWaitingList(newName);
        }
    }

    private String promptForStudentName(Scanner scanner) {
        System.out.print("Please enter the name of the new student: ");
        return scanner.nextLine();
    }

    // When student is added it is to as a novice student as a criteria
    private boolean displayWeeklyScheduleForNoviceLevel() {
        System.out.println("-----------------------------------------");
        System.out.println("|----Weekly Schedule for Novice Level---|");
        System.out.println("-----------------------------------------");
        boolean availableClasses = false;
        for (SwimClass swimClass : swimschool.getClasses()) {
            if (swimClass.getLevel() == SwimLevel.NOVICE) {
                List<SwimStudent> students = swimClass.getStudents();
                int remainingCapacity = 4 - students.size();
                if (remainingCapacity > 0) {
                    System.out.println("Day: " + swimClass.getDay());
                    System.out.println("Start Time: " + swimClass.getStart_time());
                    System.out.println("Remaining Capacity: " + remainingCapacity + " out of 4\n");
                    availableClasses = true;
                }
            }
        }
        return availableClasses;
    }

    private int promptForClassSelection(Scanner scanner) {
        System.out.print("Enter the day (1-3) to select a class: ");
        return scanner.nextInt();
    }

    private SwimClass findSelectedClass(SwimDay selectedDay, SwimLevel level) {
        for (SwimClass swimClass : swimschool.getClasses()) {
            if (swimClass.getDay() == selectedDay && swimClass.getLevel() == level) {
                return swimClass;
            }
        }
        return null;
    }

    private void addStudentToClass(SwimClass selectedClass, String newName) {
        SwimStudent newStudent = new SwimStudent(newName, SwimLevel.NOVICE);
        selectedClass.addStudent(newStudent);
        swimschool.addStudent(newStudent);
        System.out.println("New student '" + newName + "' added to class on " + selectedClass.getDay() + " at "
                + selectedClass.getStart_time());
    }

    private void addStudentToWaitingList(String newName) {
        System.out.println(
                "No available classes for novice level. The new student will be added to the waiting list.");
        SwimStudent newStudent = new SwimStudent(newName, SwimLevel.NOVICE);
        swimschool.addToWaitingList(newStudent);
        System.out.println("New student '" + newName + "' added to the waiting list.");
    }

    private void awardDistanceQualification(Instructor instructor, SwimStudent student) {
        System.out.println("\nSelect a distance qualification:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. 800m");
        System.out.println("2. 1500m");
        System.out.println("3. 3000m");
        System.out.print("Enter your choice: ");
        int distanceChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (distanceChoice) {
            case 1:
                awardQualification("800m", instructor, student);
                break;
            case 2:
                awardQualification("1500m", instructor, student);
                break;
            case 3:
                awardQualification("3000m", instructor, student);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void awardSurvivalQualification(Instructor instructor, SwimStudent student) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSelect a survival qualification:");
        System.out.println("1. Bronze");
        System.out.println("2. Silver");
        System.out.println("3. Gold");
        System.out.print("Enter your choice: ");
        int survivalChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (survivalChoice) {
            case 1:
                awardQualification("Bronze", instructor, student);
                break;
            case 2:
                awardQualification("Silver", instructor, student);
                break;
            case 3:
                awardQualification("Gold", instructor, student);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void awardQualification(String qualificationName, Instructor instructor, SwimStudent student) {
        Qualification qualification = new Qualification(qualificationName, student, instructor);
        SwimSchool.addQualification(qualification);
        System.out.println("Qualification awarded: " + qualificationName + " for student: " + student.getName());
        // Check if student needs to be moved to waiting list
        if (student.getLevel() == SwimLevel.NOVICE && qualificationName.equals("20m")
                || student.getLevel() == SwimLevel.IMPROVER && qualificationName.equals("400m")) {
            SwimSchool.addToWaitingList(student);
            System.out.println("Student moved to waiting list for level transition.");
        }
    }

    public void moveSwimStudentFromWaitingList() {
        System.out.println("\n===== Move Student from Waiting List =====");
        Scanner scanner = new Scanner(System.in);

        List<SwimStudent> waitingList = displayWaitingList();
        if (waitingList.isEmpty()) {
            System.out.println("No students are currently on the waiting list.");
            return;
        }

        SwimStudent selectedStudent = selectStudentFromWaitingList(waitingList, scanner);
        SwimLevel targetLevel = determineTargetLevel(selectedStudent);
        displayAvailableClassesForTargetLevel(targetLevel);

        int dayChoice = promptForClassSelection(scanner);
        SwimDay selectedDay = SwimDay.values()[dayChoice - 1];
        SwimClass selectedClass = findSelectedClassForTargetLevel(selectedDay, targetLevel);

        if (selectedClass != null) {
            moveStudentToSelectedClass(selectedStudent, selectedDay, selectedClass);
        } else {
            System.out.println("Invalid day selection. Cannot move the student.");
        }
    }

    private List<SwimStudent> displayWaitingList() {
        List<SwimStudent> waitingList = SwimSchool.getWaitingList();
        System.out.println("Select a student to move from the waiting list:");
        for (int i = 0; i < waitingList.size(); i++) {
            System.out.println((i + 1) + ". " + waitingList.get(i).getName());
        }
        return waitingList;
    }

    private SwimStudent selectStudentFromWaitingList(List<SwimStudent> waitingList, Scanner scanner) {
        System.out.print("Enter your choice: ");
        int studentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return waitingList.get(studentChoice - 1);
    }

    private SwimLevel determineTargetLevel(SwimStudent selectedStudent) {
        return getNextLevel(selectedStudent.getLevel());
    }

    private void displayAvailableClassesForTargetLevel(SwimLevel targetLevel) {
        System.out.println("\nAvailable classes for " + targetLevel + " level:");
        boolean foundAvailableClass = false;
        for (SwimClass swimClass : SwimSchool.getClasses()) {
            if (swimClass.getLevel() == targetLevel) {
                List<SwimStudent> students = swimClass.getStudents();
                int remainingCapacity = 4 - students.size();
                if (remainingCapacity > 0) {
                    System.out.println("Day: " + swimClass.getDay());
                    System.out.println("Start Time: " + swimClass.getStart_time());
                    System.out.println("Remaining Capacity: " + remainingCapacity + " out of 4\n");
                    foundAvailableClass = true;
                }
            }
        }
        if (!foundAvailableClass) {
            System.out.println("No available classes for " + targetLevel + " level. Cannot move the student.");
        }
    }

    private SwimClass findSelectedClassForTargetLevel(SwimDay selectedDay, SwimLevel targetLevel) {
        for (SwimClass swimClass : SwimSchool.getClasses()) {
            if (swimClass.getDay() == selectedDay && swimClass.getLevel() == targetLevel) {
                return swimClass;
            }
        }
        return null;
    }

    private void moveStudentToSelectedClass(SwimStudent selectedStudent, SwimDay selectedDay, SwimClass selectedClass) {
        SwimSchool.removeFromWaitingList(selectedStudent);
        SwimLevel targetLevel = determineTargetLevel(selectedStudent);
        selectedStudent.setLevel(targetLevel);
        selectedClass.addStudent(selectedStudent);
        SwimSchool.addStudent(selectedStudent);
        System.out.println("Student '" + selectedStudent.getName() + "' moved to class on " + selectedDay + " at "
                + selectedClass.getStart_time());
    }

    
    /**
     * The SwimLevel enum represents the different levels of swimming proficiency.
     * Used in DetermineTargetLevel
     */
    private SwimLevel getNextLevel(SwimLevel currentLevel) {
        switch (currentLevel) {
            case NOVICE:
                return SwimLevel.IMPROVER;
            case IMPROVER:
                return SwimLevel.ADVANCED;
            default:
                return null;
        }
    }

}
