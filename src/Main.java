import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //a
        String path = System.getProperty("user.dir") + "\\src\\file.tsv";

        List<String[]> list = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] teile = line.split("&");
                list.add(teile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String[] eintrag : list) {
            System.out.println(Arrays.toString(eintrag));
        }

        //b
        Scanner scanner = new Scanner(System.in);
        System.out.print("Geben Sie einen Großbuchstaben ein: ");
        String letter = scanner.nextLine().toUpperCase();
        for (String[] eintrag :list) {
            String name = eintrag[1];
            if (name.toUpperCase().startsWith(letter)) {
                System.out.println(name);
            }
        }

    }
}