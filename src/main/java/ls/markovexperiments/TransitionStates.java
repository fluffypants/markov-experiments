package ls.markovexperiments;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class TransitionStates {

  final String START_WORD_1 = "BEGIN";
  final String START_WORD_2 = "HERE";
  final String STOP_WORD = "END";
  Random random = new Random();
  HashMap<Pair<String, String>, List<String>> states = new HashMap<>();

  public TransitionStates(List<String> lines) {
    this.buildStatesFromLines(lines);
  }

  public String generateLine() {
    return generateLine(Pair.of(START_WORD_1, START_WORD_2), "");
  }

  private String generateLine(final Pair<String, String> pair, String line) {
    final List<String> words = states.get(pair);
    if(words == null) {
      return line;
    }
    final String nextWord = words.get(random.nextInt(words.size()));
    if(nextWord.equals(STOP_WORD)) {
      return line;
    }
    return generateLine(Pair.of(pair.getRight(), nextWord), line + nextWord + " ");
  }

  private void buildStatesFromLines(List<String> lines) {
    lines.forEach(this::addStatesFromLine);
  }

  private void addStatesFromLine(final String line) {
    final List<String> splitWords = Arrays.asList(line.split(" "));
    for(int i = 0; i < splitWords.size() - 3; i++) {
      final Pair<String, String> wordCombo = Pair.of(splitWords.get(i), splitWords.get(i + 1));
      final String nextWord = splitWords.get(i + 2);
      states.computeIfAbsent(wordCombo, w -> new ArrayList<>()).add(nextWord);
    }
  }
}
