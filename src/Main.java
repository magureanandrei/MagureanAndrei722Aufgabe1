import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //a
        String path = System.getProperty("user.dir") + "\\src\\file.tsv";

        List<String[]> list = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] teile = line.split("   ");
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
        for (String[] l :list) {
            System.out.println(Arrays.toString(l));
            String name = l[1];
            if (name.toUpperCase().startsWith(letter)) {
                System.out.println(name);
            }
        }

        //c
        List<String> Sorted= new ArrayList<>();
        for(String[] eintrag : list) {
            if(eintrag[2].equals("Fieber")) {
                Sorted.add(eintrag[1]);
            }
        }
        Collections.sort(Sorted);
        System.out.println(Sorted);

    }
}