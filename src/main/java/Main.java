import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String args[]) throws Exception {
        System.out.println(getMostVisited(5, Arrays.asList(2, 1, 5)));
    }

    public static int getMostVisited(int n, List<Integer> sprints) {
        int[] numVisited = new int[n + 1];
        int min = 0, max = 0;

        for (int i = 1; i < sprints.size() - 1; i++) {
            if (sprints.get(i) > sprints.get(i + 1)) {
                min = sprints.get(i + 1);
                max = sprints.get(i);
            } else if (sprints.get(i) < sprints.get(i + 1)) {
                max = sprints.get(i + 1);
                min = sprints.get(i);
            } else continue;

            for (int j = min; j <= max; j++) {
                numVisited[j]++;
            }
        }

        max = 0;
        for (int i = 0; i < numVisited.length; i++) {
            if (numVisited[i] > max)
                max = numVisited[i];
        }

        for (int i = 0; i < numVisited.length; i++) {
            if (numVisited[i] == max)
                return i;
        }

        return 0;
    }

    public void ex2() throws Exception {
        String filename = "./src/main/resources/file.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        String request = line.substring(line.indexOf("GET"));
        List<String> gifs = new ArrayList<>();

        while (line != null) {
            String[] result = request.split("\"");
            if (!result[1].contains("200") || !(result[0].contains(".gif") || result[0].contains(
                    ".GIF"))) {
                line = reader.readLine();
                if (line != null)
                    request = line.substring(line.indexOf("GET"));
                continue;
            }

            String path = request.substring(4, request.lastIndexOf("HTTP") - 1);
            String gifFile = path.substring(path.lastIndexOf("/") + 1);

            if (!gifs.contains(gifFile))
                gifs.add(gifFile);

            line = reader.readLine();
            if (line != null)
                request = line.substring(line.indexOf("GET"));
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("gifs_" + filename)));
        gifs.forEach(gif -> {
            try {
                writer.write(gif);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.write("Hello world!");
        reader.close();
    }
}
