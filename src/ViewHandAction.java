public class ViewHandAction extends Action {
    public static final ViewHandAction SINGLETON = new ViewHandAction();

    private ViewHandAction() {
        name = "View Hand";
    }

    public Response execute(GameIO io, DrawDiscardGameState gs) {
        io.println(gs.playerHand.toString());
        return Response.CONTINUE_GAME;
    }
}
