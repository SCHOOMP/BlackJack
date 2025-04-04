import java.util.*;

public class CardGame {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        bootUp();
        Player player = new Player();
        startGame(player);
    }

    // ==============================
    // GAME START & INITIALIZATION
    // ==============================

    public static void startGame(Player player) {

        if (player.getHandsPlayed() == 2) {
            Shop shop = new Shop(player);
            shop.displayShop();

            System.out.print("Enter choice (yes/no): ");
            String choice = input.nextLine();
            shop.attemptUpgrade(choice);

            player.setRewardMultiplier(shop.getRewardMultiplier());
        }

        Deck gameDeck = new Deck();
        gameDeck.shuffle();

        System.out.print("Enter a bet: ");
        int bet = Integer.parseInt(input.nextLine());

        // Initial cards
        Card firstCard = gameDeck.deal(1).getFirst();
        Card secondCard = gameDeck.deal(1).getFirst();
        Card dealerFirstCard = gameDeck.deal(1).getFirst();
        Card dealerSecondCard = gameDeck.deal(1).getFirst();

        player.addCard(firstCard);
        player.addCard(secondCard);

        System.out.println("You have " + player.getChips() + " chips");
        System.out.println("Your first card: " + firstCard);
        System.out.println("Your second card: " + secondCard);
        System.out.println("Dealer's visible card: " + dealerFirstCard);

        int playerTotal = Card.addCards(firstCard, secondCard);
        int dealerTotal = Card.addCards(dealerFirstCard, dealerSecondCard);

        // Check for Blackjack or Bust
        if (GameUtils.totalChecker(playerTotal, player, bet)) return;
        if (GameUtils.totalChecker(dealerTotal, player, bet)) {
            System.out.println("Dealer reveals second card: " + dealerSecondCard);
            System.out.println(":( House wins with Blackjack!");
            GameUtils.askToPlayAgain(player);
            return;
        }

        // Player Turn
        String choice;
        do {
            System.out.println("You have: " + playerTotal);
            choice = GameUtils.askHitOrStand();
            if (choice.equalsIgnoreCase("hit")) {
                Card nextCard = gameDeck.deal(1).getFirst();
                System.out.println("The next card is: " + nextCard);
                playerTotal = Card.alreadyAdded(playerTotal, nextCard);

                if (GameUtils.totalChecker(playerTotal, player, bet)) return;
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
            player.addWinnings(bet);
        } else if (playerTotal < dealerTotal) {
            System.out.println("Dealer Wins!");
            player.looseWinning(bet);
        } else {
            System.out.println("It's a Tie!");
        }

        System.out.println("You have " + player.getChips() + " chips");
        GameUtils.askToPlayAgain(player);
    }

    // ==============================
    // GAME BOOTUP
    // ==============================

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
