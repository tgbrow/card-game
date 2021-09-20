import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameIO {
    private static final String PROMPT_PREFIX = ">> ";

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

    public void prompt(String msg) {
        print("\n" + PROMPT_PREFIX + msg);
    }

    public String getLine() {
        return in.nextLine();
    }

    // Bounds min and max are both inclusive.
    public int getInt(int min, int max) {
        while (true) {
            int x = getInt();
            if (min <= x && x <= max) {
                return x;
            }
            out.print(String.format("Number must be in range [%d, %d]. Try again: ", min, max));
        }
    }

    public int getInt() {
        while (true) {
            try {
                return in.nextInt();
            } catch (InputMismatchException e) {
                // Just retry on exception.
            } finally {
                // Clear out remaining input.
                in.nextLine();
            }
            out.print("That is not a valid number. Try again: ");
        }
    }

    public void enterThenClear() {
        prompt("Press <Enter> to continue...");
        getLine();
        clearConsole();
    }

    public void clearConsole() {
        if (System.getProperty("os.name").contains("Windows")) {
            clearConsoleWin();
        } else {
            clearConsoleOther();
        }
        println();
    }

    private void clearConsoleWin() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            // swallow exception
        }
    }

    private void clearConsoleOther() {
        try {
            Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            // swallow exception
        }
    }
}
