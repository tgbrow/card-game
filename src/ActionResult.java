public class ActionResult {
    String result;
    Command command;

    private ActionResult(String result, Command command) {
        this.result = result;
        this.command = command;
    }

    public static ActionResult continueResult(String result) {
        return new ActionResult(result, Command.CONTINUE_GAME);
    }

    public static ActionResult endResult(String result) {
        return new ActionResult(result, Command.END_GAME);
    }
}
