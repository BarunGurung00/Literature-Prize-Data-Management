package task1;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class LiteraturePrize {

    private ArrayList<ArrayList<String>> data;
    private ArrayList<Laureate> finalData;

    public String year;
    public String winners;

    public LiteraturePrize() {
        data = new ArrayList<>();
        finalData = load();
        showMenu();
    }

    public ArrayList<Laureate> load() {
        String filePath = System.getProperty("user.dir") + "/Task1" +  File.separator + "literature-prizes.txt";
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

        return sortData(data);
    }

    private static ArrayList<Laureate> sortData(ArrayList<ArrayList<String>> dt) {
        ArrayList<Laureate> arr = new ArrayList<>();

        for (int i = 0; i < dt.size(); i++) {

            if (dt.get(i).size() < 4) {
                //Condition if there is no winner
                Laureate lr = new Laureate(Integer.parseInt(dt.get(i).get(0)), "N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
                arr.add(lr); // Adding Laureate object to the ArrayList

            } else if (dt.get(i).size() == 4) {
                //Condition if there is only one winner in a specific year
                int year = Integer.parseInt(dt.get(i).get(0));
                String[] personalDetails = dt.get(i).get(1).split("\\|");

                String country = personalDetails[1];
                String language = personalDetails[2];

                String[] firstBit = personalDetails[0].split("\\(");

                String fullName = firstBit[0];
                String dob = "(" + firstBit[1];

                String citation = dt.get(i).get(2);
                String genre = dt.get(i).get(3);

                Laureate lr = new Laureate(year, fullName, dob, country, language, genre, citation);
                arr.add(lr); // Adding Laureate object to the ArrayList

            } else if (dt.get(i).size() > 4) {//This is for multiple winners in the same year

                int year = Integer.parseInt(dt.get(i).get(0));
                String[] personalDetails = dt.get(i).get(1).split("\\|");

                String country = personalDetails[1];
                String language = personalDetails[2];

                String[] firstBit = personalDetails[0].split("\\(");

                String fullName = firstBit[0];
                String dob = "(" + firstBit[1];

                String citation = dt.get(i).get(2);
                String genre = dt.get(i).get(3);

                Laureate lr1 = new Laureate(year, fullName, dob, country, language, genre, citation);
                arr.add(lr1); // Adding Laureate object to the ArrayList

                //second Laureate's details
                String[] personalDetails2 = dt.get(i).get(4).split("\\|");

                String country2 = personalDetails2[1];
                String language2 = personalDetails2[2];

                String[] firstBit2 = personalDetails2[0].split("\\(");

                String fullName2 = firstBit2[0];
                String dob2 = "(" + firstBit2[1];

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

            choice = scanner.next();

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
    private void listPrizes() {
        Scanner scanner = new Scanner(System.in);
        int startYear;
        int endYear;

        System.out.println();

        System.out.print("Enter start year > ");
        startYear = scanner.nextInt();
        System.out.print("Enter end year > ");
        endYear = scanner.nextInt();

        //Year, Name, [nation]
        ArrayList<Laureate> d = finalData;
        System.out.println("--------------------------------------------------------");
        String formatter = "| %-5s | %-45s |%n";
        System.out.format(formatter, "Year","Prize winners (and associated nations)");
        System.out.println("---------------------------------------------------------");





        for (Laureate l : d) {
            int yr = l.getYear();
            if (yr >= startYear && yr <= endYear) {
                if (l.getName().equals("N/A")) {
                    System.out.format(formatter,yr,"NOT AWARDED");
                } else {
                    String prizeWinner = l.getName() + " " + "[" + l.getNations() + "]";
                    System.out.format(formatter, yr, prizeWinner);
                }
            }
        }

        System.out.println("---------------------------------------------------------");
        System.out.println();
    }

    //Method to select prizes
    private void selectPrizes() {
        Scanner scanner = new Scanner(System.in);
        int year;

        System.out.println();
        System.out.print("Enter year of prize > ");
        year = scanner.nextInt();
        ArrayList<Laureate> laureates = finalData;

        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------");
        String formatter = "| %-25s | %-5s | %-5s | %-15s | %-30s | %n";
        System.out.format(formatter, "Winner(s)", "Born", "Died", "Language(s)", "Genre(s)");
        System.out.println("--------------------------------------------------------------------------------------------------");

        for (Laureate lr : laureates) {
            if (year == lr.year) {

                    String dob = lr.birth_death;
                    String[] db = dob.split("");
                    String born;
                    String died;
                    if (db.length == 9) {
                        born = db[4] + db[5] + db[6] + db[7];
                        died = "----";
                    } else {
                        born = db[1] + db[2] + db[3] + db[4];
                        died = db[6] + db[7] + db[8] + db[9];
                    }

                    System.out.format(formatter,lr.name, born, died, lr.languages, lr.genres);

                    System.out.println("------------------------------------------------------------------------------------------------");
                    String citation_formatter = "| %-30s | %n";
                    System.out.format(citation_formatter,"                        Citation:                                             ");
                    System.out.println("|                                                                                        |");
                    System.out.format(citation_formatter, lr.citation);
                    System.out.println("-------------------------------------------------------------------------------------------------");
            }
        }
        System.out.println();
    }

    //Search Laureates based on genre
    private void searchLaureate() {

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter search term for writing genre > ");
        String term = scanner.nextLine().toLowerCase();

        ArrayList<Laureate> dt = finalData;

        System.out.println("----------------------------------------------------------------------------------------------------");
        String formatter = "| %-20s | %-65s | %-5s | %n";
        System.out.format(formatter, "Name", "Genres", "Year");
        System.out.println("----------------------------------------------------------------------------------------------------");

        for(Laureate lr: dt){
            String[] lrGenres = lr.genres.split(",");
            for(String s: lrGenres){
               if(s.trim().contains(term)){

                   lr.setGenre(lr.genres.replace(term.trim(),term.trim().toUpperCase()));
                   System.out.format(formatter, lr.name, lr.genres, lr.year);
               }
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
    }


    @Override
    public String toString() {
        return "Year: " + year + " Winners: " + winners;
    }
}

