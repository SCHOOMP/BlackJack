import java.util.ArrayList;
import java.util.List;

class Player {
    int chips;
    List<Card> cards;
    int handsPlayed;
    double rewardMultiplier;

    // Constructor to initialize chips
    public Player() {
        this.chips = 100;
        this.cards = new ArrayList<>();
        this.handsPlayed = 0;
        this.rewardMultiplier = 1.0;
    }


    public boolean placeBet(int amount) {
        if (amount > 0 && amount <= chips) {
            chips -= amount;
            return true;
        } else {
            System.out.println("Invalid bet. Not enough chips!");
            return false;
        }
    }

    public void addWinnings(int amount) {
        chips += (int) (amount * rewardMultiplier);
    }

    public void looseWinning(int amount) {
        chips -= amount;
    }

    public int getChips() {
        return chips;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void resetPlayerHand() {
        cards.clear();
    }

    public void incrementHands() {
        handsPlayed++;
    }

    public int getHandsPlayed() {
        return handsPlayed;
    }

    public void spendChips(int amount) {
        if (amount > 0 && amount <= chips) {
            chips -= amount;
        } else {
            System.out.println("Not enough chips to spend!");
        }
    }

    public void setRewardMultiplier(double multiplier) {
        this.rewardMultiplier = multiplier;
    }

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }
}
