public class ExitGameAction extends Action {
    public static final ExitGameAction SINGLETON = new ExitGameAction();

    private ExitGameAction() {
        name = "Exit Game";
    }

    public ActionResult execute(GameIO io, DrawDiscardGameState gs) {
        return ActionResult.endResult(String.format("Thanks for playing, %s!\n", gs.playerName));
    }
}

