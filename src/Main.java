import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/marvel_konfrontationen.json";
        List<Character> events = readJSONFile(filePath);

        for (Character event : events) {
            System.out.println(event);
            System.out.println();
        }

    }
    public static List<Character> readJSONFile(String filename) {
        List<Character> events = new ArrayList<>();
        try {
            // Read JSON file as a string
            String jsonContent = new String(Files.readAllBytes(Paths.get(filename)));

            // Extract the array of JSON objects
            jsonContent = jsonContent.trim();
            if (!jsonContent.startsWith("[") || !jsonContent.endsWith("]")) {
                throw new RuntimeException("Invalid JSON format!");
            }

            // Remove brackets and split JSON objects
            String logsArray = jsonContent.substring(1, jsonContent.length() - 1).trim();
            String[] logEntries = logsArray.split("},\\s*\\{"); // Splitting individual objects

            for (String log : logEntries) {
                log = log.replace("{", "").replace("}", "").replace("\"", "").trim();
                String[] fields = log.split(",");

                Map<String, String> data = new HashMap<>();
                for (String field : fields) {
                    String[] keyValue = field.split(":");
                    if (keyValue.length == 2) {
                        data.put(keyValue[0].trim(), keyValue[1].trim());
                    }
                }

                // Create and add the Case object
                Character event = new Character(
                        Integer.parseInt(data.get("Id")),
                        data.get("Held"),
                        data.get("Antagonist"),
                        data.get("Konfrontationstyp"),
                        data.get("Ort"),
                        data.get("Datum"),
                        Double.parseDouble(data.get("GlobalerEinfluss"))
                );
                events.add(event);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return events;
    }
}