public abstract class Action {
    protected String name;
    protected Action singleton;

    public String getName() {
        return name;
    }

    protected abstract Response execute(GameIO io, DrawDiscardGameState gs);
}
