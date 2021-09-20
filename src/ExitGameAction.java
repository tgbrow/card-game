public class ExitGameAction extends Action {
    public static final ExitGameAction SINGLETON = new ExitGameAction();

    private ExitGameAction() {
        name = "Exit Game";
    }

    public Response execute(GameIO io, DrawDiscardGameState gs) {
        io.println(String.format("Thanks for playing, %s!", gs.playerName));
        return Response.END_GAME;
    }
}

