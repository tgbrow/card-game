import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameIO {
    private Scanner in;
    private PrintStream out;

    public GameIO(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void print(String msg) {
        out.print(msg);
    }

    public void println() {
        out.println();
    }

    public void println(String msg) {
        out.println(msg);
    }

    public String getString() {
        return in.next();
    }

    // Bounds min and max are both inclusive.
    public int getInt(int min, int max) {
        while (true) {
            int x = getInt();
            if (min <= x && x <= max) {
                return x;
            }
            out.print(String.format("Number must be in range [%d, %d]. Try again:", min, max));
        }
    }

    public int getInt() {
        while (true) {
            try {
                return in.nextInt();
            } catch (InputMismatchException e) {
                // Clear out remaining input.
                in.nextLine();
            }
            out.print("That is not a valid number. Try again: ");
        }
    }
}
