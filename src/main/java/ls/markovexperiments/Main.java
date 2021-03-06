package ls.markovexperiments;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args){

    final TransitionStates states = new TransitionStates(readFile("src/main/resources/brides.txt"));
    System.out.println(states.generateLine());
  }

  private static List<String> readFile(String filename) {
    List<String> records = new ArrayList<>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      String line;
      while ((line = reader.readLine()) != null) {
        records.add(line);
      }
      reader.close();
      return records;
    }
    catch (Exception e) {
      System.err.format("Exception occurred trying to read '%s'.", filename);
      e.printStackTrace();
      return null;
    }
  }
}
