public class DrawDiscardGameOptions {
    private static String HAND_SIZE_LIMIT_PROMPT = String.format(
        "Please enter a hand size limit between %d and %d: ",
        Hand.MIN_HAND_SIZE,
        Hand.MAX_HAND_SIZE);

    String playerName;
    int handSizeLimit;

    public static DrawDiscardGameOptions promptOptions(GameIO io) {
        DrawDiscardGameOptions options = new DrawDiscardGameOptions();

        io.print("Please enter your name: ");
        options.playerName = io.getString().trim();
        io.println(String.format("Thank you, %s! :D\n", options.playerName));

        io.print(HAND_SIZE_LIMIT_PROMPT);
        options.handSizeLimit = io.getInt(Hand.MIN_HAND_SIZE, Hand.MAX_HAND_SIZE);

        return options;
    }
}
