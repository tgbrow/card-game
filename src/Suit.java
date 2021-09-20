public enum Suit {
    CLUB("♣"),
    DIAMOND("♦"),
    HEART("♥"),
    SPADE("♠");

    private String s;

    private Suit(String s) {
        this.s = s;
    }

    public String toString() {
        return s;
    }
}
