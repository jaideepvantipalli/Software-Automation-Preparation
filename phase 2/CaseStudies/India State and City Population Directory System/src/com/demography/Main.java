package com.demography;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        PopulationDirectory directory = new PopulationDirectory();
        Scanner sc=new Scanner(System.in);
        directory.loadDummyData();
            while(true){
                System.out.println("\n========== INDIA POPULATION DIRECTORY ==========");
                System.out.println("1. Add State");
                System.out.println("2. Add City");
                System.out.println("3. Update City Population");
                System.out.println("4. Get City Population (State + City)");
                System.out.println("5. Display State Details");
                System.out.println("6. Global City Search");
                System.out.println("7. Find Cities Above Population Threshold");
                System.out.println("8. Display Complete Directory");
                System.out.println("9. Exit");

                System.out.print("\nEnter your choice: ");
                int choice =sc.nextInt();
                switch (choice){
                    case 1: directory.addStates();
                        break;
                    case 2: directory.addCities();
                        break;
                    case 3: directory.updatePopulation();
                        break;
                    case 4: directory.getCityPopulation();
                        break;
                    case 5: directory.getStatePopulation();
                        break;
                    case 6 : directory.searchCityGlobally();
                        break;
                    case 7 : directory.getCitiesAbovePopulation();
                        break;
                    case 8 : directory.getDirectoryDetails();
                        break;
                    case 9 : {
                        System.out.println("Exiting Application");
                        sc.close();
                        System.exit(0);
                    }
                    default: System.out.println("Invalid choice");
                        break;
                }
            }
    }

}
