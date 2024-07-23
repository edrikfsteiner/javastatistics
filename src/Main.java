import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VectStorage storage = new VectStorage();
        
        while (true) {
            Vect vec = storage.createVec();

            if (storage.vectors.size() == 1) {
                vec.useVec();

            } else if (storage.vectors.size() > 1) {
                System.out.printf("At the moment, you have %d vectors stored.", storage.vectors.size());
                System.out.printf("Your vectors: ", storage.vectors);
                System.out.println("Do you want to compare them or see one of them? [c/s]");
                String choice = scanner.nextLine();

                if (choice == "c") {
                    continue;
                } else if (choice == "s") {
                    while (true) {
                        System.out.println("Which one?");
                        String vecChoice = scanner.nextLine();
                        HashMap<String, Double> check = storage.getVec(vecChoice);

                        if (check != null) {
                            vec.useVec();
                            break;
                        } else {
                            System.out.println("Vector was not found.");
                            break;
                        }
                    }
                } else {
                    System.out.println("You didn't select any options given.");
                    continue;
                }
            }
        }
    }
}