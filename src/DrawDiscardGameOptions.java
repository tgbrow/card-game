public class DrawDiscardGameOptions {
    private static String HAND_SIZE_LIMIT_PROMPT = String.format(
        "Please choose the maximum number of cards to allow in a player's hand: ",
        Hand.MIN_HAND_SIZE,
        Hand.MAX_HAND_SIZE);

    String playerName;
    int handSizeLimit;
    boolean useSuitChars;

    public static DrawDiscardGameOptions promptOptions(GameIO io) {
        DrawDiscardGameOptions options = new DrawDiscardGameOptions();
        io.println("Before we begin, we need to configure a few things...");

        io.prompt("Please enter your name: ");
        options.playerName = io.getLine().trim();

        io.clearConsole();
        io.println(String.format("Thank you, %s!", options.playerName));

        io.prompt(HAND_SIZE_LIMIT_PROMPT);
        options.handSizeLimit = io.getInt(Hand.MIN_HAND_SIZE, Hand.MAX_HAND_SIZE);

        return options;
    }
}
