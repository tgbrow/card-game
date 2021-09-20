import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Hand {
    public static final int MIN_HAND_SIZE = 1;
    public static final int MAX_HAND_SIZE = Deck.FULL_DECK_SIZE;

    private ArrayList<Card> hand;
    private int sizeLimit;
    private String owner;

    public Hand(int sizeLimit, String owner) {
        if (owner == null) {
            throw new IllegalArgumentException();
        }
        this.hand = new ArrayList<>();
        this.sizeLimit = sizeLimit;
        this.owner = owner;
    }

    public Card draw(Deck d) {
        if (hand.size() == sizeLimit) {
            throw new IllegalStateException(
                String.format("Drawing a card would exceed the %d-card hand size limit.", sizeLimit));
        }
        Card c = d.drawCard();
        this.add(c);
        return c;
    }

    public ArrayList<Card> draw(Deck d, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Cannot draw a negative number of cards.");
        }
        if (hand.size() + n > sizeLimit) {
            throw new IllegalStateException(
                String.format("Drawing %d card(s) would exceed the %d-card hand size limit.", n, sizeLimit));
        }
        if (n > d.size()) {
            throw new IllegalStateException(
                String.format(
                    "Cannot draw %d cards from %s. There are only %d cards left in the deck.",
                     n, d.getName(), d.size()));
        }
        ArrayList<Card> cards = d.drawCards(n);
        this.add(cards);
        return cards;
    }

    public Card discard(Deck d, int cardIndex) {
        validateIndex(cardIndex);
        Card c = hand.remove(cardIndex);
        d.addCard(c);
        return c;
    }

    public ArrayList<Card> discardRange(Deck d, int first, int last) {
        validateIndex(first);
        validateIndex(last);
        if (first > last) {
            throw new IllegalArgumentException("First index must not exceed last index.");
        }
        List<Card> toDiscard = hand.subList(first, last + 1);
        ArrayList<Card> discardCopy = new ArrayList<>(toDiscard);
        toDiscard.clear();
        d.addCards(discardCopy);
        return discardCopy;
    }

    private void validateIndex(int idx) {
        if (idx < 0 || idx > hand.size() + 1) {
            throw new IllegalArgumentException(
                String.format("No card exists in this hand with index %d.", idx));
        }
    }

    public int size() {
        return hand.size();
    }

    public String toString() {
        return toString(false);
    }

    public String toStringWithIndex() {
        return toString(true);
    }

    private String toString(boolean includeIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s's Hand: ", owner));
        sb.append(Card.listToStringWithIndex(hand));
        return sb.toString();
    }

    private void add(Card c) {
        hand.add(c);
    }

    private void add(Collection<Card> cards) {
        for (Card c : cards) {
            this.add(c);
        }
    }
}
