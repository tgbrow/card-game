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
        io.clearConsole();
        io.println("Welcome to Draw & Discard!");

        DrawDiscardGameOptions options = DrawDiscardGameOptions.promptOptions(io);
        DrawDiscardGame game = new DrawDiscardGame(io, options);

        io.clearConsole();
        io.println("Great, the game has been initialized! Time to play!");
        io.enterThenClear();
        return game;
    }

    public void run() {
        ActionResult result;
        while (true) {
            Action action = Actions.promptAction(io);
            io.clearConsole();
            result = action.execute(io, gs);
            io.clearConsole();
            io.println(result.result);
            if (result.command == Command.END_GAME) {
                break;
            }
            io.enterThenClear();
            io.println(result.result + "\n");
        }
    }
}
