package task1;

import java.util.ArrayList;

import java.util.Scanner;

public class ApplicationRunner {


    public static void main(String[] args) {

        LiteraturePrize literaturePrize = new LiteraturePrize();
        ArrayList<Laureate> data = literaturePrize.load();

    }

}
