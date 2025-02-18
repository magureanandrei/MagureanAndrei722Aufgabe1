import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/marvel_konfrontationen.json";
        List<Character> events = readJSONFile(filePath);

        for (Character event : events) {
            System.out.println(event);
            System.out.println();
        }
        getNames(events);
        filterthing(events);
        numberEvents(events);

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
    public static void numberEvents(List<Character> events) {
//        Map<String, Integer> numberEvents = new HashMap<>();
//        for (Event event : events) {
//            String house = event.getHouse();
//            numberEvents.put(house, numberEvents.getOrDefault(house, 0) + 1);
//        }

        Map<String,Integer> confruntations = new HashMap<>();
            for (Character event : events) {
                String confruntation = event.getWaytofight();
                confruntations.put(confruntation, confruntations.getOrDefault(confruntation, 0)+1);
            }


//        List<Map.Entry<String, Integer>> sortedNumberEvents = numberEvents.entrySet().stream().sorted((e1, e2) -> {
//            int compareNumber = Integer.compare(e2.getValue(), e1.getValue());
//            if(compareNumber == 0) {
//                return e1.getKey().compareTo(e2.getKey());
//            }
//            return compareNumber;
//        }).toList();



        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/bericht_konfrontationen.txt"))) {
            for (Map.Entry<String, Integer> entry : confruntations.entrySet()) {
                bw.write(entry.getKey() + "#" + entry.getValue());
                bw.newLine();
            }
            System.out.println("\nConfruntations saved to 'bericht_konfrontationen.txt'.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }
    public static void getNames(List<Character> events) {
        System.out.println("\nEnter a capacity: ");
        Scanner scanner = new Scanner(System.in);
        int capacityInput = scanner.nextInt();
        events.stream()
                .filter(x->x.getEinfluss()>=capacityInput).map(x-> x.getName()).distinct().forEach(System.out::println);
    }
    public static void filterthing(List<Character> events) {
        String place="Galaktisch";
        events.stream()
                .filter(game -> game.getWaytofight().equals(place))
                .sorted(Comparator.comparing(Character::getDate)).map(g -> g.getDate() + ": " + g.getName() + " vs " + g.getAntagonist()+" - "+g.getPlace()).forEach(System.out::println);
    }
}