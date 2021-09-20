public class ViewHandAction extends Action {
    public static final ViewHandAction SINGLETON = new ViewHandAction();

    private ViewHandAction() {
        name = "View Hand";
    }

    public ActionResult execute(GameIO io, DrawDiscardGameState gs) {
        return ActionResult.continueResult(gs.playerHand.toString());
    }
}
