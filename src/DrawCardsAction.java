import java.util.List;

public class DrawCardsAction extends Action {
    public static final DrawCardsAction SINGLETON = new DrawCardsAction();

    private DrawCardsAction() {
        name = "Draw Cards";
    }

    public ActionResult execute(GameIO io, DrawDiscardGameState gs) {
        io.print("How many cards would you like to draw?: ");

        int numCards = io.getInt();
        List<Card> cards;
        try {
            cards = gs.playerHand.draw(gs.drawDeck, numCards);
        } catch (IllegalArgumentException|IllegalStateException e) {
            return ActionResult.continueResult(e.getMessage());
        }

        return ActionResult.continueResult("You drew: " + Card.listToString(cards));
    }
}
