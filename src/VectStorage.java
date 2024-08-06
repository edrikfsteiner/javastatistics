import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VectStorage {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<HashMap<String, Vect>> vectors;

    public VectStorage() {
        this.vectors = new ArrayList<>();
    }

    public ArrayList<HashMap<String, Vect>> getVectors() {
        return this.vectors;
    }

    public Vect createVec() {
        System.out.println();
        if (this.vectors.size() == 0) {
            System.out.println("Hello there!");
            System.out.println("This program gives to you the ability to insert numbers that will be stored in a vector.");
            System.out.println("You can do numerous things with it later, but first;");
        }

        System.out.println("What will be its name?");
        String name = scanner.nextLine();
        System.out.println("How many numbers your vector will have?");
        int cap = scanner.nextInt();

        if (cap > 1) {
            Vect vec = new Vect(name, cap, this);
            HashMap<String, Vect> vecEntry = new HashMap<>();

            while (vec.getLastPos() != vec.getCapacity() - 1) {
                System.out.println("Insert number:");
                double num = scanner.nextDouble();
                vec.insert(num);
            }

            vecEntry.put(vec.getName(), vec);
            this.vectors.add(vecEntry);

            return vec;
        } else {
            System.out.println("Your vector must have at least 2 values.");
            return null;
        }
    }

    public HashMap<String, Vect> getVec(String name) {
        for (HashMap<String, Vect> vecEntry : this.vectors) {
            if (vecEntry.containsKey(name)) {
                return vecEntry;
            }
        }
        return null;
    }    

    public boolean deleteVec(String name, HashMap<String, Vect> newVec) {
        HashMap<String, Vect> vecEntry = this.getVec(name);

        if (vecEntry != null) {
            this.vectors.remove(vecEntry);
            return true;
        }

        return false;
    }
}