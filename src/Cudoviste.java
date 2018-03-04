import java.util.Scanner;

public class Cudoviste {

    private static final char BUILDING_CHAR = '#';
    private static final char CAR_CHAR = 'X';
    private static final char FREE_CHAR = '.';

    private enum Space {
        BUILDING(BUILDING_CHAR),
        CAR(CAR_CHAR),
        FREE(FREE_CHAR);

        private String character;

        Space(char character) {
            this.character = "" + character;
        }

        @Override
        public String toString() {
            return character;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.nextLine();

        Space[][] arr = new Space[r][c];

        for (int i = 0; i < arr.length; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = parseChar(line.charAt(j));
            }
        }
        new Cudoviste().solve(r, c, arr);
    }

    private static Space parseChar(char c) {
        switch (c) {
            case BUILDING_CHAR:
                return Space.BUILDING;
            case CAR_CHAR:
                return Space.CAR;
            case FREE_CHAR:
                return Space.FREE;
            default:
                throw new RuntimeException("Could not parse char");
        }
    }

    private void solve(int r, int c, Space[][] arr) {
        int results[] = new int[5];

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr[i].length - 1; j++) {

                Tuple evaluation = evaluateSlot(arr[i][j], arr[i][j + 1], arr[i + 1][j], arr[i + 1][j + 1]);
                if (!evaluation.possibleSlot)
                    continue;
                results[evaluation.numberOfSquashedCars]++;
            }
        }
        for (int i : results){
            System.out.println(i);
        }
    }

    /**
     * Tuple is returned by evaluateSlot
     */
    private class Tuple{
        public final boolean possibleSlot;
        public final int numberOfSquashedCars;

        private Tuple(boolean possibleSlot, int numberOfSquashedCars) {
            this.possibleSlot = possibleSlot;
            this.numberOfSquashedCars = numberOfSquashedCars;
        }
    }

    /**
     * Evaluate a possible parking slot
     * Returns a tuple that represents if it is possible to park here
     * and the necessary numberOfSquashedCars of cars to squash
     */
    private Tuple evaluateSlot(Space... spaces) {
        int numberOfSquashedCars = 0;

        for (Space space : spaces){
            if (space == Space.BUILDING)
                return new Tuple(false, -1);
            if (space == Space.CAR)
                numberOfSquashedCars++;
        }

        return new Tuple(true, numberOfSquashedCars);
    }
}
