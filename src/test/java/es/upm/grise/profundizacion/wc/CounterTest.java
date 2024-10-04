package es.upm.grise.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CounterTest {
	
	@TempDir 
	File TEMP_FOLDER; 

	String CORRECT_FILENAME = "TextFile.txt";
	// 3 lines, 6 words, 21 chars
	List<String> LINES = Arrays.asList(
        "Line 1", 
        "Line 2", 
        "Line 3"
    );
	int NUMBER_LINES = 3;
	int NUMBER_WORDS = 6;
	int NUMBER_CHARS = 21;
	
	@Test
	public void checkCounts() throws IOException {
   	    File textFile = new File(TEMP_FOLDER, CORRECT_FILENAME);
   	    Files.write(textFile.toPath(), LINES);
   	    // Not needed to pass the absolute file path
   	    BufferedReader br = new BufferedReader(new FileReader(textFile));
		Counter counter = new Counter(br);
		
		assertEquals(NUMBER_LINES, counter.getNumberLines());
		assertEquals(NUMBER_WORDS, counter.getNumberWords());
		assertEquals(NUMBER_CHARS, counter.getNumberCharacters());
	}

}
