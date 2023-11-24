/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
* Redistribution of original or derived work requires permission of course staff.
*/      
package poet;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;

import java.io.File;
import java.io.IOException;

public class GraphPoetTest {
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
            assert false; // make sure assertions are enabled with VM argument: -ea
        }

    private GraphPoet poet;

    @Before
    public void setUp() throws IOException {
        // You should replace "path/to/corpus.txt" with the actual path to your corpus file.
        File corpusFile = new File("test/path/seven-words.txt");
        poet = new GraphPoet(corpusFile);
    }

    @Test(expected = IOException.class)
    public void testInvalidCorpusFile() throws IOException {
        File invalidFile = new File("invalid/file/path.txt");
        new GraphPoet(invalidFile);
    }

    @Test
    public void testPoemGenerationBasic() {
        String input = "This is a test.";
        String expected = "This is a test.";
        assertEquals(expected, poet.poem(input));
    }

    // @Test
    // public void testPoemGenerationWithCorpus() {
    //     String input = "Test the system.";
    //     String expected = "Test of the system.";
    //     assertEquals(expected, poet.poem(input));
    // }

    @Test
    public void testPoemGenerationWithCaseSensitiveInput() {
        String input = "The SYSTEM is a test.";
        String expected = "The SYSTEM is a test.";
        assertEquals(expected, poet.poem(input));
    }

    @Test
    public void testPoemGenerationWithPunctuationInput() {
        String input = "Test the system!";
        String expected = "Test of the system!";
        assertEquals(expected, poet.poem(input));
    }

    @Test
    public void testPoemGenerationWithUnknownWords() {
        String input = "Graphs are cool.";
        String expected = "Graphs are cool.";
        assertEquals(expected, poet.poem(input));
    }

    // Add more test cases based on the testing strategy...

}

