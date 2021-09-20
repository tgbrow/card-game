import java.util.List;

public class TextUtil {
    private TextUtil() {}

    public static String menuFormat(String header, List<String> items) {
        StringBuilder sb = new StringBuilder();
        sb.append(header + "\n");
        for (int i = 0; i < items.size(); ++i) {
            sb.append(String.format("  [%d] %s\n", i, items.get(i)));
        }
        return sb.toString();
    }
}
