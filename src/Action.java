public abstract class Action {
    protected String name;
    protected Action singleton;

    public String getName() {
        return name;
    }

    protected abstract ActionResult execute(GameIO io, DrawDiscardGameState gs);
}
