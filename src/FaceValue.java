public class FaceValue {
    private static final String ACE_STR   = "A";
    private static final String TEN_STR   = "T";
    private static final String JACK_STR  = "J";
    private static final String QUEEN_STR = "Q";
    private static final String KING_STR  = "K";

    private static final int FACE_VALUE_ACE   =  1;
    private static final int FACE_VALUE_TEN   = 10;
    private static final int FACE_VALUE_JACK  = 11;
    private static final int FACE_VALUE_QUEEN = 12;
    private static final int FACE_VALUE_KING  = 13;

    public static final int FACE_VALUE_MIN = FACE_VALUE_ACE;
    public static final int FACE_VALUE_MAX = FACE_VALUE_KING;

    public static boolean isValid(int value) {
        return FACE_VALUE_MIN <= value && value <= FACE_VALUE_MAX;
    }

    public static String toString(int value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException();
        }
        switch (value) {
            case FACE_VALUE_KING:
                return KING_STR;
            case FACE_VALUE_QUEEN:
                return QUEEN_STR;
            case FACE_VALUE_JACK:
                return JACK_STR;
            case FACE_VALUE_TEN:
                return TEN_STR;
            case FACE_VALUE_ACE:
                return ACE_STR;
            default:
                return String.valueOf(value);
        }
    }
}
