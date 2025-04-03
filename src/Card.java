
class Card {
    private final String suit;
    private final String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public boolean isFirstElementInteger() {
        return Character.isDigit(rank.charAt(0));
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    public int getNumericValue() {
        if (rank.equals("A")) return 11;
        if (rank.equals("J")) return 10;
        if (rank.equals("Q")) return 10;
        if (rank.equals("K")) return 10;
        return Integer.parseInt(rank);
    }

    public boolean isAce() {
        return this.getNumericValue() == 11;
    }

    public static int addCards(Card card1, Card card2) {
        int total = card1.getNumericValue() + card2.getNumericValue();
        if ((card1.isAce() || card2.isAce()) && total > 21) {
            total -= 10;
        }
        return total;
    }

    public static int alreadyAdded(int total, Card newCard) {
        total += newCard.getNumericValue();
        if (newCard.isAce() && total > 21) {
            total -= 10;
        }
        return total;
    }

}