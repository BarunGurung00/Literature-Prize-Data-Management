package task1;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LiteraturePrize {

    private ArrayList<ArrayList<String>> data;
    private ArrayList<Laureate> finalData;
    ;
    public String year;
    public String winners;

    public LiteraturePrize() {
        data = new ArrayList<>();
        showMenu();
    }

    public ArrayList<Laureate> load() {
        String filePath = System.getProperty("user.dir") + File.separator + "literature-prizes.txt";
        Scanner input = null;

        try {
            File readFile = new File(filePath);
            input = new Scanner(readFile);
            ArrayList<String> arr = new ArrayList<>(); // Change ArrayList to ArrayList<String>

            while (input.hasNext()) {

                String line = input.nextLine().trim();
                if (line.equals("-----")) { // Use equals() for string comparison
                    data.add(new ArrayList<>(arr));
                    arr.clear();
                } else arr.add(line);

            }


        } catch (FileNotFoundException fnc) {

            System.out.println("File not found!");
            System.exit(0);
        } finally {
            if (input != null) {
                input.close(); // closing scanner
            }
        }

        finalData = sortData(data);
        return finalData;
    }

    private static ArrayList<Laureate> sortData(ArrayList<ArrayList<String>> dt) {
        ArrayList<Laureate> arr = new ArrayList<>();

        for (int i = 0; i < dt.size(); i++) {

            if (dt.get(i).size() < 4) {
                //Condition if there is no winner
                Laureate lr = new Laureate(Integer.parseInt(dt.get(i).get(0)), "N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
                arr.add(lr); // Adding Laureate object to the ArrayList

            } else if(dt.get(i).size() == 4) {
                //Condition if there is one winner in a specific year
                int year = Integer.parseInt(dt.get(i).get(0));
                String[] personalDetails = dt.get(i).get(1).split(" ");
                String fullName = "";

                for (int j = 0; j < personalDetails.length - 1; j++) {
                    fullName += personalDetails[j] + " ";
                }

                String[] secondBit = personalDetails[personalDetails.length-1].split("\\|");

                String dob = "";
                String country = "";
                String language = "";
                if(secondBit.length<3){
                    dob = secondBit[0];
                    country = secondBit[1];
                    language = secondBit[1];
                } else {
                    dob = secondBit[0];
                    country = secondBit[1];
                    language = secondBit[2];
                }

                String citation = dt.get(i).get(2);
                String genre = dt.get(i).get(3);

                Laureate lr = new Laureate(year, fullName, dob, country, language, genre, citation);
                arr.add(lr); // Adding Laureate object to the ArrayList

            } else if(dt.get(i).size()>4){
                //This case will share more than one winner
                int year = Integer.parseInt(dt.get(i).get(0));
                String[] personalDetails = dt.get(i).get(1).split(" ");
                String fullName = "";

                for (int j = 0; j < personalDetails.length - 1; j++) {
                    fullName += personalDetails[j] + " ";
                }

                String[] secondBit = personalDetails[personalDetails.length-1].split("\\|");

                String dob = "";
                String country = "";
                String language = "";
                if(secondBit.length<3){
                    dob = secondBit[0];
                    country = secondBit[1];
                    language = secondBit[1];
                } else {
                    dob = secondBit[0];
                    country = secondBit[1];
                    language = secondBit[2];
                }
                String citation = dt.get(i).get(2);
                String genre = dt.get(i).get(3);

                Laureate lr1 = new Laureate(year, fullName, dob, country, language, genre, citation);
                arr.add(lr1); // Adding Laureate object to the ArrayList

                //second Laureate's details
                String[] personalDetails2 = dt.get(i).get(4).split(" ");
                String fullName2 = "";

                for (int j = 0; j < personalDetails2.length - 1; j++) {
                    fullName2 += personalDetails2[j] + " ";
                }

                String[] secondBit2 = personalDetails2[personalDetails2.length - 1].split("\\|");
                String dob2 = "";
                String country2 = "";
                String language2 = "";
                if(secondBit.length<3){
                    dob2 = secondBit[0];
                    country2 = secondBit[1];
                    language2 = secondBit[1];
                } else {
                    dob2 = secondBit[0];
                    country2 = secondBit[1];
                    language2 = secondBit[2];
                }
                String citation2 = dt.get(i).get(5);
                String genre2 = dt.get(i).get(6);

                Laureate lr2 = new Laureate(year, fullName2, dob2, country2, language2, genre2, citation2);
                arr.add(lr2); // Adding Laureate object to the ArrayList
            }
        }

        return arr;
    }

    private void showMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("----------------------");
            System.out.println("Literature prize menu");
            System.out.println("----------------------");
            System.out.println("List ................1");
            System.out.println("Select ..............2");
            System.out.println("Search ..............3");
            System.out.println("Exit.................0");
            System.out.println("----------------------");
            System.out.print("Enter choice > ");

            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listPrizes();
                    break;
                case "2":
                    selectPrizes();
                    break;
                case "3":
                    searchLaureate();
                    break;
                case "0":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("0"));

        scanner.close();
    }

    //Method to List winners
    private void listPrizes(){
        ArrayList<String> laureates = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int startYear;
        int endYear;

        System.out.println();

        System.out.print("Enter start year > ");
        startYear = scanner.nextInt();
        System.out.print("Enter end year > ");
        endYear = scanner.nextInt();

        //Year, Name, [nation]
        ArrayList<Laureate> d = load();
        System.out.println("------------------------------------------------------------");
        System.out.println("|  Year  |  Prize winners (and associated nations)         |");
        System.out.println("------------------------------------------------------------");

        for(Laureate l: d){
            int yr = l.getYear();
                 if(yr >= startYear && yr <= endYear) {
                     if (l.getName().equals("N/A")) {
                         System.out.println("|  " + yr + "  | NOT AWARDED                        ");
                     } else {
                         String prizeWinner = l.getName() + " " + "[" + l.getNations() + "]";
                         System.out.println("|  " + yr + "  | " + prizeWinner);
                     }
                 }
        }

        System.out.println("------------------------------------------------------------");

        System.out.println();
    }

    //Method to select prizes
    private static void selectPrizes(){
        System.out.println("All the selected winners");
    }

    //Search Laureates based on genre
    private static void searchLaureate(){
        System.out.println("Genres");
    }

    @Override
    public String toString() {
        return "Year: " + year + " Winners: " + winners;
    }
}

