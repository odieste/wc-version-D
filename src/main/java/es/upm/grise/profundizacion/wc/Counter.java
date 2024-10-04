package es.upm.grise.profundizacion.wc;

import java.io.BufferedReader;
import java.io.IOException;

public class Counter {
	
	int numberCharacters = 0;
	int numberLines = 0;
	int numberWords = 0;
	
	public Counter(BufferedReader br) throws IOException {

	    int character = br.read();

	    while (character != -1) {
	    	// One more character
	    	numberCharacters++;
	    	
	    	// Check if a separator has been found, which means a word
	    	// has been detected
	    	if(character == ' ' || character == '\t' || character == '\n') {
	    		numberWords++;
	    	}
	    	
	    	// Check newline to count lines
	    	if(character == '\n') {
	    		numberLines++;
	    	}
	    	
	    	character = br.read();
	    }
		
	}
	
}
