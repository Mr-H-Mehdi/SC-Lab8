package poet;

import graph.Graph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class GraphPoet {

    private final Graph<String> graph = Graph.empty();

    public GraphPoet(File corpus) throws IOException {
        List<String> lines = Files.readAllLines(corpus.toPath());

        for (String line : lines) {
            String[] words = line.split("\\s+");

            for (int i = 0; i < words.length - 1; i++) {
                String currentWord = words[i].toLowerCase();
                String nextWord = words[i + 1].toLowerCase();

                graph.add(currentWord);
                graph.add(nextWord);

                int weight = graph.set(currentWord, nextWord, graph.targets(currentWord).getOrDefault(nextWord, 0) + 1);
                if (weight == 0) {
                    graph.set(currentWord, nextWord, 1);
                }
            }
        }
    }

    public String poem(String input) {
        String[] words = input.split("\\s+");
        StringBuilder poem = new StringBuilder();

        for (int i = 0; i < words.length - 1; i++) {
            String currentWord = words[i].toLowerCase();
            String nextWord = words[i + 1].toLowerCase();

            poem.append(words[i]).append(" ");

            // Check if there's a valid connection between the current and next words
            if (graph.sources(nextWord).containsKey(currentWord)) {
                Set<String> bridgeWords = graph.targets(currentWord).keySet();

                // Filter bridge words based on their connection with the next word
                List<String> validBridgeWords = bridgeWords.stream()
                        .filter(bridgeWord -> graph.sources(nextWord).containsKey(bridgeWord))
                        .collect(Collectors.toList());

                if (!validBridgeWords.isEmpty()) {
                    // Find the bridge word with the highest occurrence in the graph
                    String selectedBridge = Collections.max(validBridgeWords,
                            Comparator.comparingInt(word -> graph.targets(currentWord).getOrDefault(word, 0)));

                    poem.append(selectedBridge).append(" ");
                }
            }
        }

        poem.append(words[words.length - 1]);
        return poem.toString();
    }


    @Override
    public String toString() {
        return graph.toString();
    }
}
