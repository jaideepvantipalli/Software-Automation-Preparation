package com.demography;
import java.util.HashMap;
import java.util.Scanner;
public class PopulationDirectory {
    private final HashMap<String, HashMap<String,Long>> populationDirectory;
    public PopulationDirectory() {
        populationDirectory = new HashMap<>();
    }
    static Scanner sc=new Scanner(System.in);

    public void getCitiesAbovePopulation() {
        long population = readPopulation("Enter the threshold population to fetch the cities : ");
        if(populationDirectory.isEmpty()){
            System.out.println("Directories are not populated");
            return;
        }
        boolean present = false;
        for(String state : populationDirectory.keySet()) {
            System.out.println();
            for(String city : populationDirectory.get(state).keySet()) {
                long directoryPopulation = populationDirectory.get(state).get(city);

                if(directoryPopulation > population) {
                    present = true;
                    System.out.println("State : " + state);
                    System.out.println("City : " + city + "population : " + populationDirectory.get(state).get(city));
                }
            }
            System.out.println();
        }
        if(!present)
            System.out.println("There is no cities in the population with given threshold!.");

    }

    public void getDirectoryDetails() {

        if(populationDirectory.isEmpty()){
            System.out.println("Directories are not populated");
            return;
        }
        for(String state : populationDirectory.keySet()) {
            System.out.println("State : " + state);
            System.out.println();
            for(String city : populationDirectory.get(state).keySet()) {
                System.out.println("City : "+city + " population : " + populationDirectory.get(state).get(city));
            }
            System.out.println();
        }
    }

    public void searchCityGlobally() {
        String cityName=readName("Enter City Name :");
        boolean found=false;
        for(String state:populationDirectory.keySet()){
            HashMap<String,Long> citySearch = populationDirectory.get(state);
                if(citySearch.containsKey(cityName)) {
                    found = true;
                    System.out.println("City Found on Directory!");
                    System.out.println("------------------------------------");
                    System.out.println("State      : " + state);
                    System.out.println("City       : " + cityName);
                    System.out.println("Population : " + citySearch.get(cityName));
                    System.out.println("------------------------------------");
                }
        }
        if(!found){
            System.out.println("City Not Found on state!");
        }
    }

    public void getStatePopulation() {
        String stateName=readName("Enter State Name :");
        if(!populationDirectory.containsKey(stateName)){
            System.out.println("state not found");
            return;
        }
        System.out.println("State Name: "+stateName);
        long population = 0;
        for(String cityName:populationDirectory.get(stateName).keySet()){
            population+=populationDirectory.get(stateName).get(cityName);
            System.out.println("City Name :"+cityName+"Population: "+populationDirectory.get(stateName).get(cityName));
        }
        System.out.println("State Population: "+population);
    }

    public void getCityPopulation() {
        String stateName=readName("Enter State Name :");
        String cityName=readName("Enter City Name :");
        if(!populationDirectory.containsKey(stateName)){
            System.out.println("state not found");
            return;
        }
        if(!populationDirectory.get(stateName).containsKey(cityName)){
            System.out.println("city not found");
            return;
        }
        System.out.println("State : "+stateName+" City : "+cityName+" Population :"+populationDirectory.get(stateName).get(cityName));
    }

    public void updatePopulation() {
        String stateName=readName("Enter state Name :");
        String cityName=readName("Enter City Name :");
        long newPopulation=readPopulation("Enter population of the city :");
        if(newPopulation <= 0){
            System.out.print("Enter valid population! ");
            return;
        }
        if(!populationDirectory.containsKey(stateName)){
            System.out.println("State does not exist "+stateName);
            return;
        }
        if(!populationDirectory.get(stateName).containsKey(cityName)){
            System.out.println("city does not exist " + cityName);
            return;
        }
        long oldPopulation=populationDirectory.get(stateName).get(cityName);
        populationDirectory.get(stateName).put(cityName,newPopulation);
        System.out.println("population updated to the directory.");
        System.out.println("The population (old) of city "+cityName+" is "+oldPopulation);
        System.out.println("The population (new) of city "+cityName+" is "+newPopulation);
    }

    public void addCities() {
        String stateName=readName("Enter state Name to add city :");
        String cityName=readName("Enter City Name :");
        long population=readPopulation("Enter population of the city :");
        if(population <= 0){
            System.out.print("Enter valid population: ");
            return;
        }
        if(!populationDirectory.containsKey(stateName)){
            System.out.println("State does not exist"+stateName);
            return;
        }
        if(populationDirectory.get(stateName).containsKey(cityName)){
            System.out.println("city already exists in "+stateName);
            return;
        }
        populationDirectory.get(stateName).put(cityName,population);
        System.out.println("city has been added to the directory.");
    }

    public void addStates() {
        String stateName=readName("Enter State Name :");

        if(populationDirectory.containsKey(stateName)){
            System.out.println("State already exists in directory.");
            return;
        }
        populationDirectory.put(stateName, new HashMap<>());
        System.out.println("State has been added to the directory.");
    }

    public static String readName(String msg) {
        String name;
        while(true){
            System.out.println(msg);
            name = sc.nextLine().toLowerCase().trim();
            if(name.isEmpty())
                System.out.println("Please Enter valid State Name!");
            else break;
        }
        return name;
    }

    public static long readPopulation(String msg){
        System.out.print(msg);
        try{
            return Long.parseLong(sc.next().trim());
        }catch(NumberFormatException e){
            System.out.println("Invalid Population Value!");
            return Long.MIN_VALUE;
        }
    }

    public void loadDummyData() {

        // Adding states
        populationDirectory.put("andhra pradesh", new HashMap<>());
        populationDirectory.put("telangana", new HashMap<>());
        populationDirectory.put("karnataka", new HashMap<>());
        populationDirectory.put("tamil nadu", new HashMap<>());
        populationDirectory.put("kerala", new HashMap<>());

        // Andhra Pradesh
        populationDirectory.get("andhra pradesh").put("eluru", 220000L);
        populationDirectory.get("andhra pradesh").put("vijayawada", 1000000L);
        populationDirectory.get("andhra pradesh").put("guntur", 650000L);
        populationDirectory.get("andhra pradesh").put("visakhapatnam", 2000000L);

        // Telangana
        populationDirectory.get("telangana").put("hyderabad", 6800000L);
        populationDirectory.get("telangana").put("warangal", 800000L);
        populationDirectory.get("telangana").put("karimnagar", 300000L);

        // Karnataka
        populationDirectory.get("karnataka").put("bengaluru", 8400000L);
        populationDirectory.get("karnataka").put("mysuru", 900000L);
        populationDirectory.get("karnataka").put("hubli", 600000L);

        // Tamil Nadu
        populationDirectory.get("tamil nadu").put("chennai", 7100000L);
        populationDirectory.get("tamil nadu").put("coimbatore", 1600000L);
        populationDirectory.get("tamil nadu").put("madurai", 1500000L);

        // Kerala
        populationDirectory.get("kerala").put("kochi", 600000L);
        populationDirectory.get("kerala").put("thiruvananthapuram", 950000L);

        System.out.println("Dummy data loaded successfully!");
    }
}