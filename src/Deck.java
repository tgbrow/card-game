import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    public static final int FULL_DECK_SIZE =
        Suit.values().length * (FaceValue.FACE_VALUE_MAX - FaceValue.FACE_VALUE_MIN + 1);

    // Note: The first element is the bottom of the deck; the last element is the top of the deck.
    private ArrayList<Card> deck;
    private String name;

    private Deck(String name) {
        this.deck = new ArrayList<Card>();
        this.name = name;
    }

    public static Deck fullDeck() {
        return fullDeck(null);
    }

    public static Deck fullDeck(String name) {
        Deck d = new Deck(name);
        for (Suit suit : Suit.values()) {
            for (int i = FaceValue.FACE_VALUE_MIN; i <= FaceValue.FACE_VALUE_MAX; ++i) {
                d.deck.add(new Card(i, suit));
            }
        }
        return d;
    }

    public static Deck emptyDeck() {
        return emptyDeck(null);
    }

    public static Deck emptyDeck(String name) {
        return new Deck(name);
    }

    public String getName() {
        return name;
    }
    
    public Deck shuffle() {
        if (!deck.isEmpty()) {
            Collections.shuffle(deck);
        }
        return this;
    }

    public int size() {
        return deck.size();
    }

    // Removes/returns the top card of the deck.
    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new IllegalStateException();
        }
        return deck.remove(deck.size() - 1);
    }

    // Removes/returns the first n cards from the top of the deck.
    public ArrayList<Card> drawCards(int n) {
        if (deck.size() < n) {
            throw new IllegalStateException();
        }
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            cards.add(drawCard());
        }
        return cards;
    }

    // Adds a card to the top of the deck.
    public void addCard(Card c) {
        if (deckContains(c)) {
            throw new IllegalArgumentException();
        }
        deck.add(c);
    }

    // Adds a card to the top of the deck.
    public void addCards(List<Card> cards) {
        for (Card c : cards) {
            addCard(c);
        }
    }

    public boolean deckContains(Card c) {
        return deck.contains(c);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name != null) {
            sb.append(String.format("= Info for %s =\n", name));
        }
        sb.append(String.format("Card Count: %d", this.size()));
        if (this.size() > 0) {
            sb.append("\nCard Order (top to bottom):");
            ArrayList<Card> topToBot = new ArrayList<>(deck);
            Collections.reverse(topToBot);
            for (int i = 0; i < topToBot.size(); ++i) {
                if (i % 10 == 0) {
                    sb.append("\n" + TextUtil.INDENT);
                }
                sb.append(topToBot.get(i));
                if (i != topToBot.size() - 1) { // not last card
                    sb.append(", ");
                }
            }
        }
        return sb.toString();
    }
}
