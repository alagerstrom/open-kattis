import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameRank {

    private List<Result> results = new ArrayList<>();

    public static void main(String[] args) {
        readFromSystemIn();
    }

    private static void readFromSystemIn() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        GameRank gameRank = new GameRank(line.trim());
        System.out.println(gameRank.solve());
    }

    public GameRank(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'W')
                results.add(Result.WIN);
            else if (input.charAt(i) == 'L')
                results.add(Result.LOOSE);
            else throw new RuntimeException("Bad input");
        }
    }

    enum Result {
        WIN,
        LOOSE
    }

    public String solve() {

        int currentRank = 25;
        int numberOfStars = 0;
        int winningStreak = 0;

        for (int i = 0; i < results.size(); i++) {
            Result result = results.get(i);
            if (result == Result.WIN) {
                winningStreak++;

                if (winningStreak >= 3 && currentRank >= 6) {
                    numberOfStars += 2;
                } else {
                    numberOfStars++;
                }
                if (numberOfStars > starsForRank(currentRank)) {
                    numberOfStars -= starsForRank(currentRank);
                    currentRank--;
                }
            } else {
                winningStreak = 0;
                if (currentRank <= 20) {
                    if (numberOfStars > 0) {
                        numberOfStars--;
                    } else {
                        if (currentRank < 20) {
                            currentRank++;
                            numberOfStars = starsForRank(currentRank) - 1;
                        }
                    }
                }
            }

            if (currentRank == 0) {
                return "Legend";
            }
        }
        return "" + currentRank;
    }

    public int starsForRank(int rank) {
        if (rank >= 21)
            return 2;
        if (rank >= 16)
            return 3;
        if (rank >= 11)
            return 4;
        return 5;
    }
}
