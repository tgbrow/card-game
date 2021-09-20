import java.util.ArrayList;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;

public class DiscardCardsAction extends Action {
    public static final DiscardCardsAction SINGLETON = new DiscardCardsAction();

    private DiscardCardsAction() {
        name = "Discard Cards";
    }

    public Response execute(GameIO io, DrawDiscardGameState gs) {
        if (gs.playerHand.size() == 0) {
            io.print("Your hand is already empty!");
            return Response.END_GAME;
        }

        switch(DiscardStyle.getDiscardStyle(io)) {
            case Single:
                return executeSingle(io, gs);
            case Range:
                return executeRange(io, gs);
        }
        // Unreachable
        return Response.END_GAME;
    }

    public Response executeSingle(GameIO io, DrawDiscardGameState gs) {
        io.println(gs.playerHand.toStringWithIndex());

        io.print("Which card would you like to discard?: ");
        int index = io.getInt();

        Card c;
        try {
            c = gs.playerHand.discard(gs.discardPile, index);
        } catch (IllegalArgumentException e) {
            io.println(e.getMessage());
            return Response.CONTINUE_GAME;
        }

        io.println("You discarded: " + c);
        return Response.CONTINUE_GAME;
    }

    public Response executeRange(GameIO io, DrawDiscardGameState gs) {
        io.println(gs.playerHand.toStringWithIndex());

        io.print("Please select first card in range to discard: ");
        int first = io.getInt(0, gs.playerHand.size() - 1);

        io.print("Please select last card in range to discard: ");
        int last = io.getInt(first, gs.playerHand.size() - 1);

        ArrayList<Card> discarded = gs.playerHand.discardRange(gs.discardPile, first, last);
        io.println("Discarded: " + Card.listToString(discarded));
        return Response.CONTINUE_GAME;
    }

    private static enum DiscardStyle {
        Single, Range;

        static DiscardStyle getDiscardStyle(GameIO io) {
            io.print(prompt());
            int styleInt = io.getInt(0, values().length - 1);
            io.println();
            return values()[styleInt];
        }

        private static String prompt() {
            String s = TextUtil.menuFormat(
                "Which discard style would you like to use?",
                Arrays.stream(DiscardStyle.values()).map(x -> x.name()).collect(toList()));
            return s + "Style selection: ";
        }
    }
}
