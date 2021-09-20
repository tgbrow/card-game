import java.util.ArrayList;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;

public class DiscardCardsAction extends Action {
    public static final DiscardCardsAction SINGLETON = new DiscardCardsAction();

    private DiscardCardsAction() {
        name = "Discard Cards";
    }

    public ActionResult execute(GameIO io, DrawDiscardGameState gs) {
        if (gs.playerHand.size() == 0) {
            return ActionResult.continueResult("Your hand is already empty!");
        }

        DiscardApproach da = DiscardApproach.getDiscardApproach(io);
        io.clearConsole();
        switch(da) {
            case Single:
                return executeSingle(io, gs);
            case Range:
                return executeRange(io, gs);
        }
        return ActionResult.endResult("Unreachable!");
    }

    public ActionResult executeSingle(GameIO io, DrawDiscardGameState gs) {
        io.println(gs.playerHand.toStringWithIndex());

        io.prompt("Which card would you like to discard?: ");
        int index = io.getInt();

        Card c;
        try {
            c = gs.playerHand.discard(gs.discardPile, index);
        } catch (IllegalArgumentException e) {
            return ActionResult.continueResult(e.getMessage());
        }

        return ActionResult.continueResult("You discarded: " + c);
    }

    public ActionResult executeRange(GameIO io, DrawDiscardGameState gs) {
        io.println(gs.playerHand.toStringWithIndex());

        io.prompt("Please select first card in range to discard: ");
        int first = io.getInt(0, gs.playerHand.size() - 1);

        io.prompt("Please select last card in range to discard: ");
        int last = io.getInt(first, gs.playerHand.size() - 1);

        ArrayList<Card> discarded = gs.playerHand.discardRange(gs.discardPile, first, last);
        return ActionResult.continueResult("You discarded: " + Card.listToString(discarded));
    }

    private static enum DiscardApproach {
        Single, Range;

        static DiscardApproach getDiscardApproach(GameIO io) {
            io.print(approachMenu());
            io.prompt("Select approach: ");
            int styleInt = io.getInt(0, values().length - 1);
            return values()[styleInt];
        }

        private static String approachMenu() {
            return TextUtil.menuFormat(
                "Which discard approach would you like to use?",
                Arrays.stream(DiscardApproach.values()).map(x -> x.name()).collect(toList()));
        }
    }
}
