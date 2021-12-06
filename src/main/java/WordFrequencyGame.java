import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class WordFrequencyGame {

    private static final String SPACE_REGEX = "\\s+";

    public String getResult(String input){


        if (input.split(SPACE_REGEX).length==1) { // TODO: constant regex
            return input + " 1";
        } else {

            try {

                List<WordCount> wordCountList = calculateWordFrequency(input);

                wordCountList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount()); // TODO: split new function

                StringJoiner output = new StringJoiner("\n"); // TODO: Don't use string joiner
                for (WordCount wordCount : wordCountList) {
                    String outputLine = wordCount.getValue() + " " +wordCount.getWordCount(); // TODO: split new function
                    output.add(outputLine);
                }
                return output.toString();
            } catch (Exception e) {  // TODO: Why calculate error? -> x delete it, may have other reasons to exist


                return "Calculate Error";
            }
        }
    }

    private List<WordCount> calculateWordFrequency(String input) {
        //split the input string with 1 to n pieces of spaces
        List<String> wordList = splitString(input);

         Map<String, Integer> wordFrequencyMap = wordList
                .stream()
                .collect(groupingBy(Function.identity(), summingInt(e -> 1)));

        return wordFrequencyMap.entrySet()
                .stream()
                .map(wordFrequency -> new WordCount(wordFrequency.getKey(), wordFrequency.getValue()))
                .collect(Collectors.toList());
    }

    private List<String> splitString(String input) {
        return Arrays.asList(input.split(SPACE_REGEX));
    }

}
