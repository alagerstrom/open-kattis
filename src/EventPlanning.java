import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventPlanning {

    private static class Hotel {
        public int price;
        public int availableBeds[];

        public Hotel(int price, int[] availableBeds) {
            this.price = price;
            this.availableBeds = availableBeds;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfParticipants = scanner.nextInt();
        int budget = scanner.nextInt();
        int numberOfHotels = scanner.nextInt();
        int numberOfWeekends = scanner.nextInt();

        List<Hotel> hotels = new ArrayList<>();

        for (int i = 0; i < numberOfHotels; i++) {
            int price = scanner.nextInt();
            int availableBeds[] = new int[numberOfWeekends];
            for (int j = 0; j < numberOfWeekends; j++) {
                availableBeds[j] = scanner.nextInt();
            }
            hotels.add(new Hotel(price, availableBeds));
        }

        solve(numberOfParticipants, budget, numberOfHotels, numberOfWeekends, hotels);
    }

    private static void solve(int numberOfParticipants,
                              int budget,
                              int numberOfHotels,
                              int numberOfWeekends,
                              List<Hotel> hotels) {
        int lowestPrice = -1;
        for (Hotel hotel : hotels){
            for (int available : hotel.availableBeds){
                if (available >= numberOfParticipants){
                    int totalCost = numberOfParticipants * hotel.price;
                    if (lowestPrice < 0)
                        lowestPrice = totalCost;
                    else lowestPrice = Math.min(lowestPrice, totalCost);
                }
            }
        }
        if (lowestPrice < 0 || lowestPrice > budget)
            System.out.println("stay home");
        else System.out.println(lowestPrice);
    }
}
