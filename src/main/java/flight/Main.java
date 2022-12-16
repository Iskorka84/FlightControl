package flight;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        FlightControl boarding = new FlightControl();

        Plane airbus = new Plane("Airbus", 6);
        Plane boeing = new Plane("Boeing", 10);
        Plane privateJet = new Plane ("Private Jet", 5);

        Plane[] planes = {airbus, boeing, privateJet};

        printCurrentPassengersPlacement(planes);

        String nextStep;
        do {
            do {
                boarding.inputData();
                if (boarding.searchDuplicates(planes) > 0) {
                    System.out.println("A passenger with the same name is already registered!");
                    System.out.println();
                }
            }
            while (boarding.searchDuplicates(planes) > 0);

            boarding.fillingThePlane(planes);

            do {
                System.out.println("Do you want to continue? (y/n)");
                Scanner console = new Scanner(System.in);
                nextStep = console.nextLine();
            }
            while (!("y".equals(nextStep) || "n".equals(nextStep)));
        }
        while ("y".equals(nextStep));

        printCurrentPassengersPlacement(planes);

    }

    public static void printCurrentPassengersPlacement(Plane[] planes) {
        for (int i = 0; i < planes.length; i++) {
            System.out.println(planes[i].getModelName() + " :");
            for (int j = 0; j < planes[i].getCapacity(); j++) {
                System.out.println((j+1) + " " + planes[i].getPassenger(j));
            }
        }
    }

}

