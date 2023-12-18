import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class LotteryOfToy {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("lottery.txt"))) {
            ToyManager toyManager = new ToyManager("1 ball 2", "2 lego 2", "3 doll 6");
            Random random = new Random();

            for (int i = 0; i < 10; i++) {
                Toy selectedToy = toyManager.getToyByWeightedValue(random);
                if (selectedToy != null) {
                    writer.write(selectedToy.toString());
                    writer.newLine();
                } else {
                    writer.write("The toy does not exist");
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}