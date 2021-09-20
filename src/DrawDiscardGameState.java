import java.util.List;

public class DrawDiscardGameState {
    String playerName;
    Hand playerHand;
    Deck drawDeck;
    Deck discardPile;
    
    public DrawDiscardGameState(String name, Hand hand, Deck draw, Deck discard) {
        playerName = name;
        playerHand = hand;
        drawDeck = draw;
        discardPile = discard;
    }

    public List<Deck> getDecks() {
        return List.of(drawDeck, discardPile);
    }
}
