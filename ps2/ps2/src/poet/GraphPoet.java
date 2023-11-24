package poet;

import graph.Graph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Comparator;

/**
 * A graph-based poetry generator.
 */
public class GraphPoet {

    private final Graph<String> graph = Graph.empty();

    /**
     * Create a new poet with the graph from corpus (as described above).
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
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
                    // Edge didn't exist before, set weight to 1
                    graph.set(currentWord, nextWord, 1);
                }
            }
        }
    }

    /**
     * Generate a poem.
     *
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        String[] words = input.split("\\s+");
        StringBuilder poem = new StringBuilder();

        for (int i = 0; i < words.length - 1; i++) {
            String currentWord = words[i].toLowerCase();
            String nextWord = words[i + 1].toLowerCase();

            poem.append(words[i]).append(" ");

            if (graph.sources(nextWord).containsKey(currentWord)) {
                // There is a path from currentWord to nextWord
                Set<String> bridgeWords = graph.targets(currentWord).keySet();
//                Optional<String> bridge = bridgeWords.stream()
//                        .max(Comparator.comparingInt(bridgeWords::get));

                bridge.ifPresent(b -> poem.append(b).append(" "));
            }
        }

        // Append the last word of the input
        poem.append(words[words.length - 1]);

        return poem.toString();
    }

    /**
     * Checks the representation invariant of the graph.
     */
    private void checkRep() {
        // TODO: Add representation invariant checks
    }

    /**
     * Returns a string representation of the GraphPoet.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        // TODO: Implement toString() method
        return super.toString();
    }
}
