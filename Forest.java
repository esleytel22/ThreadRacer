import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Forest {
    private static final String FOREST_FILE_TXT = "forestFile.txt";
    private static final int WORD_LENGTH = 4;
    private Random rnd = new Random();

    public Forest() {
        generateForest();
    }

    private void generateForest() {
        int numWords = 300 + rnd.nextInt(200 + 1);
        try (PrintWriter pw = new PrintWriter(FOREST_FILE_TXT)) {
            for (int i = 0; i < numWords; i++) {
                String word = generateWord();
                pw.println(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String generateWord() {
        char[] chars = new char[WORD_LENGTH];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ('a' + rnd.nextInt(WORD_LENGTH + 1));
        }
        return new String(chars);
    }

    public boolean contains(String magicWord) {
        try (Scanner sc = new Scanner(new File(FOREST_FILE_TXT))) {
            while (sc.hasNextLine()) {
                String word = sc.nextLine();
                if (word.equals(magicWord)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}