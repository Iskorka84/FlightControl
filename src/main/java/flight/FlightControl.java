package flight;

import java.util.Scanner;

public class FlightControl {

    String passengerName;
    String planeModel;
    int seat;
    boolean freeSeat;

    public void inputData () {
        System.out.println("Enter passenger name: ");
        Scanner console = new Scanner(System.in);
        passengerName = console.nextLine();

        if ("exit".equals(passengerName)) {
            System.exit(0);
        }

        do {
            System.out.println("Enter plane model (airbus, boeing, private jet): ");
            console = new Scanner(System.in);
            planeModel = console.nextLine();
        }
        while (!("airbus".equals(planeModel) || "boeing".equals(planeModel) || "private jet".equals(planeModel)));

    }


    public void fillingThePlane(Plane[] planes) {
        for (int i = 0; i < planes.length; i++) {
            if (planes[i] != null && planes[i].getModelName().toLowerCase().equals(planeModel)) {
                do {
                    System.out.println("Enter seat number(from 1 to " + planes[i].getCapacity() + "): ");
                    Scanner console = new Scanner(System.in);
                    seat = console.nextInt();
                }
                while (seat < 1 || seat > planes[i].getCapacity());

                checkMySeat(planes[i], seat, passengerName);
                if (!freeSeat) {
                    searchFreeSeatInPlane(planes[i]);
                }
                if (!freeSeat) {
                    searchFreeSeatsInOtherPlanes(planes, planes[i]);
                }
            }
        }
    }

    public void checkMySeat(Plane myPlane, int seatNumber, String passenger){
        if (myPlane.getPassenger(seatNumber-1) != null){
            System.out.println(passenger + ", sorry, your seat is taken!");
            freeSeat = false;
        }
        else {
            myPlane.setPassenger(passenger, seatNumber);
            System.out.println();
            System.out.println("PLANE: " + myPlane.getModelName() + "; " + "SEAT: " + seatNumber);
            System.out.println(myPlane.getPassenger(seatNumber-1) + ", welcome! Your seat is free!");
            System.out.println();
            freeSeat = true;
        }
    }

    public void searchFreeSeatInPlane(Plane myPlane) {
        for (int i = 0; i < myPlane.getCapacity(); i++) {
            if (myPlane.getPassenger(i) == null) {
                myPlane.setPassenger(passengerName, i+1);
                System.out.println("Your new place is seat number " + (i+1) + " in " + myPlane.getModelName());
                System.out.println();
                freeSeat = true;
                return;
            }
        }
     }

    public void searchFreeSeatsInOtherPlanes(Plane[] planes, Plane myPlane){
        for (int i = 0; i < planes.length; i++) {
            if(!myPlane.getModelName().equals(planes[i].getModelName())) {
                searchFreeSeatInPlane(planes[i]);
            }
            if (freeSeat) {
                return;
            }
        }
        if (!freeSeat) {
            System.out.println("Sorry, there are no free seets!");
        }
    }



    public int searchDublicatesInOurPlane(Plane myPlane){
        int count = 0;
        for (int i = 0; i < myPlane.getCapacity(); i++){
            if (passengerName.equals(myPlane.getPassenger(i))) {
                count +=1;
            }
        }
        return count;
    }

    public int searchDuplicates(Plane[] planes) {
        int count = 0;
        for (int i = 0; i < planes.length; i++) {
            count += searchDublicatesInOurPlane(planes[i]);
            }
         return count;
    }

}
