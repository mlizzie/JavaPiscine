package classes;

import javax.naming.Name;
import java.util.StringJoiner;

public class Car {

    private String name;
    private String model;
    private  Integer weight;

    public Car(){
     this.name = "lada";
     this.model = "default";
     this.weight = 0;
    }

    public Car(String name, String model, Integer weight){
        this.name = name;
        this.model = model;
        this.weight = weight;
    }
    public int grow(int value) {
        this . weight += value;
        return weight;
    }
    @Override
    public String toString() {
        return new StringJoiner(", ", User.class . getSimpleName() + "[", "]")
                . add("Name='" + name + "'")
                . add("Model='" + model + "'")
                . add("weight=" + weight)
                . toString();
    }
}
