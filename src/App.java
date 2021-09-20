public class App {
    public static void main(String[] args) throws Exception {
        DrawDiscardGame game = DrawDiscardGame.initNewGame(System.in, System.out);
        game.run();
    }
}
