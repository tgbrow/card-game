import java.io.InputStream;
import java.io.PrintStream;

public class DrawDiscardGame {
    private GameIO io;
    private DrawDiscardGameState gs;

    private DrawDiscardGame(GameIO io, DrawDiscardGameOptions options) {
        this.io = io;
        this.gs = new DrawDiscardGameState(
            options.playerName,
            new Hand(options.handSizeLimit, options.playerName),
            Deck.fullDeck("Draw Deck").shuffle(),
            Deck.emptyDeck("Discard Pile"));
    }

    public static DrawDiscardGame initNewGame(InputStream in, PrintStream out) {
        GameIO io = new GameIO(in, out);
        io.println("\n~~ Welcome to the Draw/Discard Game! ~~\n");

        DrawDiscardGameOptions options = DrawDiscardGameOptions.promptOptions(io);
        DrawDiscardGame game = new DrawDiscardGame(io, options);

        io.println("Great, the game has been initialized! Time to play...");
        return game;
    }

    public void run() {
        Action action;
        do {
            io.println();
            action = Actions.promptAction(io);
            io.println();
        } while (action.execute(io, gs) == Response.CONTINUE_GAME);
    }
}
