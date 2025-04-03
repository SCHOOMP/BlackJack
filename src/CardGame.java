import java.util.*;

public class CardGame {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        bootUp();
        startGame();
    }

    public static void startGame() {
        Deck gameDeck = new Deck();
        gameDeck.shuffle();

        // Initial Cards
        Card firstCard = gameDeck.deal(1).getFirst();
        Card secondCard = gameDeck.deal(1).getFirst();
        Card dealerFirstCard = gameDeck.deal(1).getFirst();
        Card dealerSecondCard = gameDeck.deal(1).getFirst();

        System.out.println("Your first card: " + firstCard);
        System.out.println("Your second card: " + secondCard);
        System.out.println("Dealer's visible card: " + dealerFirstCard);

        int playerTotal = Card.addCards(firstCard, secondCard);
        int dealerTotal = Card.addCards(dealerFirstCard, dealerSecondCard);

        // Check for initial Blackjack
        if (totalChecker(playerTotal)) return;
        if (totalChecker(dealerTotal)) {
            System.out.println("Dealer reveals second card: " + dealerSecondCard);
            System.out.println(":( House wins with Blackjack!");
            askToPlayAgain();
            return;
        }

        // Player Turn
        String choice;
        do {
            System.out.println("You have: " + playerTotal);
            choice = askHitOrStand();
            if (choice.equalsIgnoreCase("hit")) {
                Card nextCard = gameDeck.deal(1).getFirst();
                System.out.println("The next card is: " + nextCard);
                playerTotal = Card.alreadyAdded(playerTotal, nextCard);

                // Check for Blackjack or Bust after a hit
                if (totalChecker(playerTotal)) return;
            }
        } while (choice.equalsIgnoreCase("hit"));

        // Dealer Turn
        System.out.println("Dealer reveals second card: " + dealerSecondCard);
        System.out.println("Dealer total: " + dealerTotal);

        while (dealerTotal < 17) {
            Card dealerNextCard = gameDeck.deal(1).getFirst();
            System.out.println("Dealer draws: " + dealerNextCard);
            dealerTotal = Card.alreadyAdded(dealerTotal, dealerNextCard);
        }

        System.out.println("Dealer final total: " + dealerTotal);

        // Determine Winner
        if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("You Win!");
        } else if (playerTotal < dealerTotal) {
            System.out.println("Dealer Wins!");
        } else {
            System.out.println("It's a Tie!");
        }

        askToPlayAgain();
    }

    private static String askHitOrStand() {
        System.out.print("Would you like to HIT or STAND? : ");
        return input.nextLine();
    }

    public static void askToPlayAgain() {
        System.out.print("Would you like to play again? (YES/NO): ");
        String response = input.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            startGame();
        } else {
            System.out.println("Thanks for playing! Goodbye.");
            input.close();
            System.exit(0);
        }
    }

    // Checks for Blackjack or Bust and acts accordingly
    public static boolean totalChecker(int total) {
        if (total == 21) {
            System.out.println("!!! BLACKJACK !!! You win!");
            askToPlayAgain();
            return true;
        } else if (total > 21) {
            System.out.println("Player ended at " + total);
            System.out.println("YOU BUSTED!");
            askToPlayAgain();
            return true;
        }
        return false;
    }

    public static void bootUp() throws InterruptedException {
        System.out.println("\n" +
                " $$$$$$\\   $$$$$$\\  $$\\   $$\\  $$$$$$\\   $$$$$$\\  $$\\      $$\\ $$$$$$$\\  $$\\  $$$$$$\\        $$$$$$$\\  $$\\                     $$\\          $$$$$\\                     $$\\       ");
        Thread.sleep(250);
        System.out.println("$$  __$$\\ $$  __$$\\ $$ |  $$ |$$  __$$\\ $$  __$$\\ $$$\\    $$$ |$$  __$$\\ $  |$$  __$$\\       $$  __$$\\ $$ |                    $$ |         \\__$$ |                    $$ |      ");
        Thread.sleep(250);
        System.out.println("$$ /  \\__|$$ /  \\__|$$ |  $$ |$$ /  $$ |$$ /  $$ |$$$$\\  $$$$ |$$ |  $$ |\\_/ $$ /  \\__|      $$ |  $$ |$$ | $$$$$$\\   $$$$$$$\\ $$ |  $$\\       $$ | $$$$$$\\   $$$$$$$\\ $$ |  $$\\ ");
        Thread.sleep(250);
        System.out.println("\\$$$$$$\\  $$ |      $$$$$$$$ |$$ |  $$ |$$ |  $$ |$$\\$$\\$$ $$ |$$$$$$$  |    \\$$$$$$\\        $$$$$$$\\ |$$ | \\____$$\\ $$  _____|$$ | $$  |      $$ | \\____$$\\ $$  _____|$$ | $$  |");
        Thread.sleep(250);
        System.out.println(" \\____$$\\ $$ |      $$  __$$ |$$ |  $$ |$$ |  $$ |$$ \\$$$  $$ |$$  ____/      \\____$$\\       $$  __$$\\ $$ | $$$$$$$ |$$ /      $$$$$$  / $$\\   $$ | $$$$$$$ |$$ /      $$$$$$  / ");
        Thread.sleep(250);
        System.out.println("$$\\   $$ |$$ |  $$\\ $$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |\\$  /$$ |$$ |          $$\\   $$ |      $$ |  $$ |$$ |$$  __$$ |$$ |      $$  _$$<  $$ |  $$ |$$  __$$ |$$ |      $$  _$$<  ");
        Thread.sleep(250);
        System.out.println("\\$$$$$$  |\\$$$$$$  |$$ |  $$ | $$$$$$  | $$$$$$  |$$ | \\_/ $$ |$$ |          \\$$$$$$  |      $$$$$$$  |$$ |\\$$$$$$$ |\\$$$$$$$\\ $$ | \\$$\\ \\$$$$$$  |\\$$$$$$$ |\\$$$$$$$\\ $$ | \\$$\\ ");
        Thread.sleep(250);
        System.out.println(" \\______/  \\______/ \\__|  \\__| \\______/  \\______/ \\__|     \\__|\\__|           \\______/       \\_______/ \\__| \\_______| \\_______|\\__|  \\__| \\______/  \\_______| \\_______|\\__|  \\__|\n");
        Thread.sleep(750);
    }
}
