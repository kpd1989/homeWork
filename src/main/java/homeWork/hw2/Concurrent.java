package homeWork.hw2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Concurrent {
    private static double number = 1;
    private static List<String> sqrtList = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException, InterruptedException {
        long startTime = System.currentTimeMillis();
        BufferedWriter writerToFile = new BufferedWriter(new FileWriter("sqrtList.csv"));

        for (int threadNumber = 0; threadNumber < 10; ++threadNumber) {
            new Thread(() -> {

                for (int i = 0; i < 100_000; ++i) {
                    sqrtList.add(number + " - " + Math.sqrt(number) + "\n");
                    number++;
                }

            }).start();
            Thread.sleep(300);
        }

        writerToFile.write(String.valueOf(sqrtList));
        writerToFile.flush();

        long end = System.currentTimeMillis();
        System.out.println("Took " + (end - startTime));
    }
}
