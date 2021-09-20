import java.util.List;

import static java.util.stream.Collectors.toList;

public class ViewDeckAction extends Action {
    public static final ViewDeckAction SINGLETON = new ViewDeckAction();

    private ViewDeckAction() {
        name = "View Deck";
    }

    public Response execute(GameIO io, DrawDiscardGameState gs) {
        Deck d = getDeck(io, gs);
        io.println("\n" + d.toString());
        return Response.CONTINUE_GAME;
    }

    private Deck getDeck(GameIO io, DrawDiscardGameState gs) {
        List<Deck> decks = gs.getDecks();
        io.print(TextUtil.menuFormat(
            "Which deck would you like to view?",
            decks.stream().map(x -> x.getName()).collect(toList())));
        io.print("Deck selection: ");
        return decks.get(io.getInt(0, decks.size() - 1));
    }
}
