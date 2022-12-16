package flight;

public class Plane {

    private String modelName;
    private int capacity;
    private String[] passengers;

    public Plane(String model, int maxNumberOfPassengers){
        modelName = model;
        capacity = maxNumberOfPassengers;
        passengers = new String[capacity];
    }
    public int getCapacity() {
        return capacity;
    }

    public void setPassenger(String name, int index){
        passengers[index-1] = name;
    }

    public String getPassenger(int index){
        return passengers[index];
    }

    public String getModelName() {
        return modelName;
    }


   }
