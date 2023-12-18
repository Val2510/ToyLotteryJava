import java.io.BufferedWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyManager {
    private PriorityQueue<Toy> toyQueue;

    public ToyManager(String... toyStrings) {
        toyQueue = new PriorityQueue<>((t1, t2) -> Integer.compare(t2.getFrequency(), t1.getFrequency()));
        putToys(toyStrings);
        calculateTotalWeight();
    }

    private void calculateTotalWeight() {
        int totalFrequency = toyQueue.stream().mapToInt(Toy::getFrequency).sum();
        toyQueue.forEach(toy -> toy.setProbability((double) toy.getFrequency() / totalFrequency));
    }

    public void putToys(String... toyStrings) {
        for (String toyString : toyStrings) {
            String[] parts = toyString.split(" ");
            if (parts.length == 3) {
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int frequency = Integer.parseInt(parts[2]);
                toyQueue.add(new Toy(id, name, frequency));
            }
        }
    }

    public Toy getToyByWeightedValue(Random random) {
        double weightedValue = random.nextDouble();

        for (Toy toy : toyQueue) {
            if (weightedValue < toy.getProbability()) {
                return toy;
            }
            weightedValue -= toy.getProbability();
        }

        return toyQueue.peek();
    }

    public void drawToys(BufferedWriter writer, int draws) throws IOException {
        Random random = new Random();

        for (int i = 0; i < draws; i++) {
            Toy selectedToy = getToyByWeightedValue(random);
            if (selectedToy != null) {
                writer.write(selectedToy.toString());
                writer.newLine();
            } else {
                writer.write("The toy does not exist");
                writer.newLine();
            }
        }
    }
}
