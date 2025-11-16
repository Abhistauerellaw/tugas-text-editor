import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static StringBuilder text = new StringBuilder();
    private static Stack<String> undoStack = new Stack<>();
    private static Stack<String> redoStack = new Stack<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("Masukkan perintah (show, write, undo, redo, exit): ");
            command = sc.nextLine().trim().toLowerCase();

            switch (command) {
                case "show":
                    show();
                    break;

                case "write":
                    System.out.print("Tulis teks: ");
                    String newText = sc.nextLine();
                    write(newText);
                    break;

                case "undo":
                    undo();
                    break;

                case "redo":
                    redo();
                    break;

                case "exit":
                    System.out.println("Keluar dari program.");
                    return;

                default:
                    System.out.println("Perintah tidak dikenal!");
            }
        }
    }

    private static void show() {
        System.out.println("Isi teks saat ini:");
        System.out.println(text.toString());
    }

    private static void write(String newText) {
        undoStack.push(text.toString());
        text.append(newText);
        redoStack.clear();
        System.out.println("Teks berhasil ditambahkan.");
    }

    private static void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(text.toString()); 
            text = new StringBuilder(undoStack.pop());
            System.out.println("Undo berhasil.");
        } else {
            System.out.println("Tidak ada yang bisa di-undo.");
        }
    }

    private static void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(text.toString());
            text = new StringBuilder(redoStack.pop());
            System.out.println("Redo berhasil.");
        } else {
            System.out.println("Tidak ada yang bisa di-redo.");
        }
    }
}
