import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String SPACE_REGEX = "\\s+";

    public String getResult(String input){


        if (input.split(SPACE_REGEX).length==1) { // TODO: constant regex
            return input + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] wordList = input.split(SPACE_REGEX); // TODO: split new function

                List<WordCount> wordCountList = new ArrayList<>();
                for (String word : wordList) {
                    WordCount wordCount = new WordCount(word, 1); // TODO: inline for loop
                    wordCountList.add(wordCount);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordCount>> wordToWordCountMap =getListMap(wordCountList);

                List<WordCount> list = new ArrayList<>();
                for (Map.Entry<String, List<WordCount>> entry : wordToWordCountMap.entrySet()){
                    WordCount wordCount = new WordCount(entry.getKey(), entry.getValue().size());
                    list.add(wordCount);
                }
                wordCountList = list;

                wordCountList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount()); // TODO: split new function

                StringJoiner output = new StringJoiner("\n"); // TODO: Don't use string joiner
                for (WordCount wordCount : wordCountList) {
                    String outputLine = wordCount.getValue() + " " +wordCount.getWordCount(); // TODO: split new function
                    output.add(outputLine);
                }
                return output.toString();
            } catch (Exception e) {  // TODO: Why calculate error?


                return "Calculate Error";
            }
        }
    }


    private Map<String,List<WordCount>> getListMap(List<WordCount> wordCountList) {
        Map<String, List<WordCount>> wordToWordCountMap = new HashMap<>();
        for (WordCount wordCount : wordCountList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordToWordCountMap.containsKey(wordCount.getValue())){
                ArrayList wordCounts = new ArrayList<>();
                wordCounts.add(wordCount);
                wordToWordCountMap.put(wordCount.getValue(), wordCounts);
            }

            else {
                wordToWordCountMap.get(wordCount.getValue()).add(wordCount);
            }
        }


        return wordToWordCountMap;
    }


}
