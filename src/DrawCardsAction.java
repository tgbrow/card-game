import java.util.List;

public class DrawCardsAction extends Action {
    public static final DrawCardsAction SINGLETON = new DrawCardsAction();

    private DrawCardsAction() {
        name = "Draw Cards";
    }

    public Response execute(GameIO io, DrawDiscardGameState gs) {
        io.print("How many cards would you like to draw?: ");

        int numCards = io.getInt();
        List<Card> cards;
        try {
            cards = gs.playerHand.draw(gs.drawDeck, numCards);
        } catch (IllegalArgumentException|IllegalStateException e) {
            io.println(e.getMessage());
            return Response.CONTINUE_GAME;
        }

        io.println("You drew: " + Card.listToString(cards));
        return Response.CONTINUE_GAME;
    }
}
