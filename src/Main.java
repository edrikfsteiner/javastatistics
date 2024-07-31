import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VectStorage storage = new VectStorage();
        
        while (true) {
            Vect vec = storage.createVec();
            int vectorSize = storage.getVectors().size();

            if (vectorSize == 1) {
                vec.useVec();

            } else if (vectorSize > 1) {
                System.out.printf("At the moment, you have %d vectors stored.", vectorSize);
                System.out.println("Your vectors: " + storage.getVectors());
                System.out.println("Do you want to compare them or see one of them? [c/s]");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "c":
                        // CONTINUE CODING HERE
                        break;

                    case "s":
                        while (true) {
                            System.out.println("Which one?");
                            String vecChoice = scanner.nextLine();
                            HashMap<String, Vect> check = storage.getVec(vecChoice);

                            if (check != null) {
                                check.get("Vector").useVec();
                                break;
                            } else {
                                System.out.println("Vector was not found.");
                                break;
                            }
                        }
                        break;
                    
                    default:
                        System.out.println("You didn't select any options given.");
                        break;
                }
            }
        }
    }
}