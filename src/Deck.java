import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Deck {
    private final List<Card> cards;
    private final Random random = new Random();

    public Deck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        cards = new ArrayList<>();

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards, random);
    }

    public List<Card> deal(int numCards) {
        if (numCards > cards.size()) {
            throw new IllegalArgumentException("Not enough cards left in the deck");
        }
        List<Card> hand = new ArrayList<>(cards.subList(0, numCards));
        cards.subList(0, numCards).clear();
        return hand;
    }
}