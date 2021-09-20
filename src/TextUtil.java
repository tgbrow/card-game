import java.util.List;

public class TextUtil {
    public static final String INDENT = "  ";

    private TextUtil() {}

    public static String menuFormat(String header, List<String> items) {
        StringBuilder sb = new StringBuilder();
        sb.append(header + "\n");
        for (int i = 0; i < items.size(); ++i) {
            sb.append(String.format("%s[%d] %s\n", INDENT, i, items.get(i)));
        }
        return sb.toString();
    }

    public static String pluralize(String s, int n) {
        return s + (n == 1 ? "" : "s");
    }
}
