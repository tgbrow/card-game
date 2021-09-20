import java.util.Arrays;

import static java.util.stream.Collectors.toList;

public class Actions {
    private static final Action[] ACTIONS = {
        ViewDeckAction.SINGLETON,
        ViewHandAction.SINGLETON,
        DrawCardsAction.SINGLETON,
        DiscardCardsAction.SINGLETON,
        ExitGameAction.SINGLETON,
    };

    public static Action promptAction(GameIO io) {
        io.print(menu());
        Action action = null;
        while (true) {
            io.print("Please choose an action by entering its number: ");
            try {
                action = fromInt(io.getInt());
                break;
            } catch (IllegalArgumentException e) {
                io.println(e.getMessage());
            }
        }
        io.println("You chose action: " + action.getName());
        return action;
    }

    private static Action fromInt(int x) {
        if (0 <= x && x < ACTIONS.length) {
            return ACTIONS[x];
        }
        throw new IllegalArgumentException(
            String.format("%d does not correspond to an Action.", x));
    }

    public static String menu() {
        return TextUtil.menuFormat(
            "Action Menu",
            Arrays.stream(ACTIONS).map(x -> x.getName()).collect(toList()));
    }
}
