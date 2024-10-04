package es.upm.grise.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;
import uk.org.webcompere.systemstubs.stream.SystemOut;

@ExtendWith(SystemStubsExtension.class)
public class AppTest {
	
	@TempDir 
	File TEMP_FOLDER; 
	
	@SystemStub
	SystemOut OUT;
	
	String FILENAME = "TextFile.txt";
	String FILENAME_WITH_PATH = "";
	
	
	// 3 lines, 6 words, 21 chars
	List<String> LINES = Arrays.asList(
        "Line 1", 
        "Line 2", 
        "Line 3"
    );
	int NUMBER_LINES = 3;
	int NUMBER_WORDS = 6;
	int NUMBER_CHARS = 21;
	
	String NON_EXISTING_FILE = "OtherFile.txt";
	
	@BeforeEach
	public void setUp() throws IOException {
		
		File txtFile = new File(TEMP_FOLDER, FILENAME);
   	    Files.write(txtFile.toPath(), LINES);
   	    FILENAME_WITH_PATH = txtFile.getAbsolutePath();
	}
	
    @Test
    public void generatesUsageWithoutParameters() throws IOException {

    	String [] PARAMETERS = {};
        	
    	App.main(PARAMETERS);
    	String output = OUT.getLines().toArray()[0].toString();
    	assertEquals("Usage: wc [-clw file]", output);
   	 
    }
    
    @Test
    public void givesAnErrorWithOneParameter() throws IOException {

    	String [] PARAMETERS = {"anything"};
        	
    	App.main(PARAMETERS);
    	String output = OUT.getLines().toArray()[0].toString();
    	assertEquals("Wrong arguments!", output);
   	 
    }
    
    @Test
    public void givesAnErrorWithOneParameterIncludingDash() throws IOException {

    	String [] PARAMETERS = {"-"};
        	
    	App.main(PARAMETERS);
    	String output = OUT.getLines().toArray()[0].toString();
    	assertEquals("Wrong arguments!", output);
   	 
    }
    
    @Test
    public void givesAnErrorWithMoreThanTwoParameters() throws IOException {

    	String [] PARAMETERS = {"anything", "somethingElse", "oneMore"};
        	
    	App.main(PARAMETERS);
    	String output = OUT.getLines().toArray()[0].toString();
    	assertEquals("Wrong arguments!", output);
   	 
    }
    
    @Test
    public void warnsThatTheCommandsDoNotStartWithaDash() throws IOException {

    	String COMMAND_NOT_STARTING_WITH_DASH = "c";
    	String [] PARAMETERS = {COMMAND_NOT_STARTING_WITH_DASH, FILENAME_WITH_PATH};
        	
    	App.main(PARAMETERS);
    	String output = OUT.getLines().toArray()[0].toString();
    	assertEquals("The commands do not start with -", output);
   	 
    }
    
    @Test
    public void warnsThatTheFileDoesNotExist() throws IOException {

    	String CORRECT_COMMAND = "-c";
    	String [] PARAMETERS = {CORRECT_COMMAND, NON_EXISTING_FILE};
        	
    	App.main(PARAMETERS);
    	String output = OUT.getLines().toArray()[0].toString();
    	assertEquals("Cannot find file: " + NON_EXISTING_FILE, output);
   	 
    }
    
    @Test
    public void wharsThatAWrongCommandHasBeenFound() throws IOException {

    	String WRONG_COMMAND = "-t";
    	String [] PARAMETERS = {WRONG_COMMAND, FILENAME_WITH_PATH};
        	
    	App.main(PARAMETERS);
    	String output = OUT.getLines().toArray()[0].toString();
    	assertEquals("Unrecognized command: " + "t", output);
   	 
    }
    
    @Test
    public void wharsThatAWrongCommandHasBeenFoundButTryaPositionDifferentThanTHeFirstInTheCommandString() throws IOException {

    	String WRONG_COMMAND = "-ct";
    	String [] PARAMETERS = {WRONG_COMMAND, FILENAME_WITH_PATH};
        	
    	App.main(PARAMETERS);
    	String output = OUT.getLines().toArray()[0].toString();
    	assertEquals("Unrecognized command: " + "t", output);
   	 
    }
    
    @Test
    public void readTheNumberofLinesCorrectly() throws IOException {

    	String CORRECT_COMMAND = "-l";
    	String [] PARAMETERS = {CORRECT_COMMAND, FILENAME_WITH_PATH};
        	
    	App.main(PARAMETERS);
    	String output = OUT.getLines().toArray()[0].toString();
    	String expected = "\t" + NUMBER_LINES + "\t" + FILENAME_WITH_PATH;
    	assertEquals(expected, output);
   	 
    }
    
    @Test
    public void readTheNumberofWordssCorrectly() throws IOException {

    	String CORRECT_COMMAND = "-w";
    	String [] PARAMETERS = {CORRECT_COMMAND, FILENAME_WITH_PATH};
        	
    	App.main(PARAMETERS);
    	String output = OUT.getLines().toArray()[0].toString();
    	String expected = "\t" + NUMBER_WORDS + "\t" + FILENAME_WITH_PATH;
    	assertEquals(expected, output);
   	 
    }
    
    @Test
    public void readTheNumberofCharsCorrectly() throws IOException {

    	String CORRECT_COMMAND = "-c";
    	String [] PARAMETERS = {CORRECT_COMMAND, FILENAME_WITH_PATH};
        	
    	App.main(PARAMETERS);
    	String output = OUT.getLines().toArray()[0].toString();
    	String expected = "\t" + NUMBER_CHARS + "\t" + FILENAME_WITH_PATH;
    	assertEquals(expected, output);
   	 
    }
    
    @Test
    public void ProvideSeveralCountsCorrecly() throws IOException {

    	String CORRECT_COMMAND = "-clw";
    	String [] PARAMETERS = {CORRECT_COMMAND, FILENAME_WITH_PATH};
        	
    	App.main(PARAMETERS);
    	String output = OUT.getLines().toArray()[0].toString();
    	String expected = "\t" + NUMBER_CHARS + "\t" + NUMBER_LINES + "\t" + NUMBER_WORDS + "\t" + FILENAME_WITH_PATH;
    	assertEquals(expected, output);
   	 
    }
    
    
}
