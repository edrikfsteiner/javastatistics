import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;
import java.util.Scanner;

public class Vect {
    Scanner scanner = new Scanner(System.in);
    private String name;
    private int capacity;
    private int count;
    private int lastPos;
    private double[] values;
    private VectStorage storage;

    public Vect(String name, int capacity, VectStorage storage) {
        this.name = name;
        this.capacity = capacity;
        this.count = 0;
        this.lastPos = -1;
        this.values = new double[capacity];
        this.storage = storage;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCount() {
        return count;
    }

    public int getLastPos() {
        return lastPos;
    }

    public double[] getValues() {
        return values;
    }

    public VectStorage getStorage() {
        return storage;
    }

    public boolean displayVector() {
        if (this.getLastPos() == -1) {
            return false;
        } else {
            for (int i = 0; i < this.getLastPos() + 1; i++) {
                System.out.println(i + " - " + this.getValues()[i]);
            }

            return true;
        }
    }

    public int binarySearch(double value) {
        double[] arr = this.mergeSort(this.getValues());
        int infLim = 0;
        int supLim = this.getLastPos();

        while (true) {
            int currentPos = (infLim + supLim) / 2;

            if (arr[currentPos] == value) {
                return currentPos;
            } else if (infLim > supLim) {
                return -1;
            } else {
                if (arr[currentPos] < value) {
                    infLim = currentPos + 1;
                } else {
                    supLim = currentPos - 1;
                }
            }
        }
    }

    public boolean insert(double value) {
        if (this.getLastPos() == this.getCapacity() - 1) {
            return false;
        } else {
            this.count++;
            this.lastPos++;
            this.getValues()[this.getLastPos()] = value;
            return true;
        }
    }

    public boolean delete(double value) {
        int position = this.binarySearch(value);
        
        if (position == -1) {
            return false;
        } else {
            for (int i = position; i < this.getLastPos(); i++) {
                this.getValues()[i] = this.getValues()[i + 1];
            }

            this.count--;
            this.lastPos--;
            return true;
        }
    }

    public double[] mergeSort(double[] arr) {
        if (arr.length > 1) {
            int half = arr.length / 2;
            double[] left = Arrays.copyOfRange(arr, 0, half);
            double[] right = Arrays.copyOfRange(arr, half, arr.length);

            left = mergeSort(left);
            right = mergeSort(right);

            int i, j, k;
            i = j = k = 0;

            while (i < left.length && j < right.length) {
                if (left[i] < right[j]) {
                    arr[k] = left[i];
                    i++;
                } else {
                    arr[k] = right[j];
                    j++;
                }
                
                k++;
            }

            while (i < left.length) {
                arr[k] = left[i];
                i++;
                k++;
            }

            while (j < right.length) {
                arr[k] = right[j];
                j++;
                k++;
            }
        }

        return arr;
    }

    public double aritMean() {
        double mean = Arrays.stream(this.getValues()).sum() / this.getCount();
        return mean;
    }

    public double findMode(double[] arr) {
        HashMap<Double, Integer> countMap = new HashMap<>();

        for (double num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        double mode = Double.NaN;
        int maxCount = 0;

        for (Map.Entry<Double, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mode = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mode;
    }

    public double variance() {
        double[] arr = new double[this.getCapacity()];
        double mean = this.aritMean();

        for (int i = 0; i <= this.getLastPos(); i++) {
            arr[i] = Math.pow((this.getValues()[i] - mean), 2);
        }

        double variance = Arrays.stream(arr).sum() / this.getCount();
        return variance;
    }

    public void useVec() {
        while (true) {
            System.out.println();
            System.out.println("What do you want to know about this vector? Choose the numbers below:");
            System.out.println("+-----------------------------------------+");
            System.out.println("1- Display vector");
            System.out.println("2- Search element");
            System.out.println("3- Insert element");
            System.out.println("4- Delete element");
            System.out.println("5- Sum of the elements");
            System.out.println("6- Quantity of elements");
            System.out.println("7- Sort vector");
            System.out.println("8- Arithmetic Mean");
            System.out.println("9- Median");
            System.out.println("10- Mode");
            System.out.println("11- Amplitude");
            System.out.println("12- Variance");
            System.out.println("13- Mean deviation");
            System.out.println("14- Standard deviation");
            System.out.println("15- Delete vector");
            System.out.println("16- Add another vector");
            System.out.println("+-----------------------------------------+");
            System.out.println();

            System.out.println("Which do you choose?");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Display vector
                    boolean displayVector = this.displayVector();

                    if (displayVector == false) {
                        System.out.println("Empty vector.");
                    } else {
                        System.out.println(displayVector);
                    }
                    break;
                
                case 2: // Search element
                    while (true) {
                        System.out.println("Which number to search?");
                        double num = scanner.nextDouble();
                        scanner.nextLine();
                        int position = this.binarySearch(num);

                        if (position == -1) {
                            System.out.println("Number was not found.");
                        } else {
                            System.out.printf("The number %.2f was found in position %d.%n", num, position);
                        }

                        System.out.println("Search another one? [y/n]");
                        String answer = scanner.nextLine();

                        if (answer.equals("n")) {
                            break;
                        }
                    }
                    break;

                case 3: // Insert element
                    while (true) {
                        System.out.println("Which number to insert?");
                        double num = scanner.nextDouble();
                        scanner.nextLine();
                        boolean insert = this.insert(num);
                        
                        if (insert == false) {
                            System.out.println("Full vector, can't insert number.");
                            break;
                        } else {
                            System.out.println("Number " + num + " was inserted.");
                        }

                        System.out.println("Insert another one? [y/n]");
                        String answer = scanner.nextLine();

                        if (answer.equals("n")) {
                            break;
                        }
                    }
                    break;

                case 4: // Delete element
                    while (true) {
                        System.out.println("Which number to delete?");
                        double num = scanner.nextDouble();
                        scanner.nextLine();
                        boolean delete = this.delete(num);

                        if (delete == false) {
                            System.out.println("Number was not found.");
                        } else {
                            System.out.println("Number " + num + " was deleted.");
                        }

                        System.out.println("Delete another one? [y/n]");
                        String answer = scanner.nextLine();

                        if (answer.equals("n")) {
                            break;
                        }
                    }
                    break;

                case 5: // Sum of the elements
                    System.out.println(Arrays.stream(this.getValues()).sum());
                    break;

                case 6: // Quantity of elements
                    System.out.println(this.getCount());
                    break;
                    
                case 7: // Sort vector
                    double[] sort_vec = this.mergeSort(this.getValues());
                    System.out.println(Arrays.toString(sort_vec));
                    break;

                case 8: // Arithmetic mean
                    System.out.println(this.aritMean());
                    break;

                case 9: // Median
                    this.mergeSort(this.getValues());
                    int median = this.getValues().length / 2;
                    System.out.println(this.getValues()[median]);
                    break;

                case 10: // Mode
                    System.out.println(this.findMode(this.getValues()));
                    break;

                case 11: // Amplitude
                    double max = Arrays.stream(this.getValues()).max().orElseThrow();
                    double min = Arrays.stream(this.getValues()).min().orElseThrow();
                    System.out.println(max - min);
                    break;

                case 12: // Variance
                    System.out.println(this.variance());
                    break;

                case 13: // Mean deviation
                    double mean_deviation = this.variance();

                    if (mean_deviation < 0) {
                        System.out.println(mean_deviation * -1);
                    } else {
                        System.out.println(mean_deviation);
                    }
                    break;

                case 14: // Standard deviation
                    System.out.println(Math.sqrt(this.variance()));
                    break;

                case 15: // Delete vector
                    this.getStorage().deleteVec(this.getName(), null);
                    return;

                case 16: // Add another vector
                    return;

                default:
                    System.out.println("You didn't select any of the menu numbers. Please repeat.");
                    break;
            }
        }
    }
}
