package org.zerock.myapp;

public class TTT {

	public static void main(String[] args) {
		int number = 1;
		
		switch(number) {}	// OK
		
//		switch(number);		// XX
		
		switch(number) {	// OK
			default:
		}
	}

}
