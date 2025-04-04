import java.util.Scanner;

public class GameUtils {
    private static final Scanner input = new Scanner(System.in);

    public static String askHitOrStand() {
        System.out.print("Would you like to HIT or STAND? : ");
        return input.nextLine();
    }

    public static void askToPlayAgain(Player player) {
        System.out.print("Would you like to play again? (YES/NO): ");
        player.incrementHands();
        String response = input.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            player.resetPlayerHand();
            CardGame.startGame(player); // Call startGame from CardGame
        } else {
            System.out.println("Thanks for playing! Goodbye.");
            input.close();
            System.exit(0);
        }
    }

    public static boolean totalChecker(int total, Player player, int bet) {
        if (total == 21 && player.cards.size() == 2) {
            System.out.println("!!! BLACKJACK !!! You win!");
            player.addWinnings((int) (bet * 1.5));
        } else if (total == 21) {
            System.out.println("Hit 21!!!");
            player.addWinnings(bet);
        } else if (total > 21) {
            System.out.println("Player ended at " + total);
            System.out.println("YOU BUSTED!");
            player.looseWinning(bet);
        } else {
            return false;
        }

        System.out.println("Chips at: " + player.getChips());
        askToPlayAgain(player);
        return true;
    }
}
