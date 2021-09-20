import java.util.List;

public class Card {
    private int faceValue;
    private Suit suit;

    public Card(int faceValue, Suit suit) {
        if (!FaceValue.isValid(faceValue)) {
            throw new IllegalArgumentException();
        }
        this.faceValue = faceValue;
        this.suit = suit;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public Suit getSuit() {
        return suit;
    }

    public String toString() {
        return FaceValue.toString(faceValue) + suit;
    }

    public static String listToString(List<Card> cards) {
        return listToString(cards, false);
    }

    public static String listToStringWithIndex(List<Card> cards) {
        return listToString(cards, true);
    }

    private static String listToString(List<Card> cards, boolean includeIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.size(); ++i) {
            if (includeIndex) {
                sb.append("[" + i + "] ");
            }
            sb.append(cards.get(i));
            if (i != cards.size() - 1) {
                sb.append(", ");
            }
        }
        if (cards.size() == 0) {
            sb.append("EMPTY");
        }
        return sb.toString();
    }
}
