package recorder;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlightDataRecordTest {

	@Test
	public void testRecordDataAtCapacity() {
		FlightDataRecorder fdr = new FlightDataRecorder();
		for (int i = 0; i < 11; i++) {
			fdr.record(0.11 + i);
		}
		
	    String expected = "[1.11, 2.11, 3.11, 4.11, 5.11, 6.11, 7.11, 8.11, 9.11, 10.11]";
        String found = fdr.getRecordedData().toString();
        String msg = "Unexpected (incorrect or incorrectly formatted) recorded data information or string output was returned.";

        assertFalse("record() didn't record the right data.", found.isEmpty());
        assertEquals(msg, expected, found);
	}
	
	@Test
	public void testGetRecordedData() {
		FlightDataRecorder fdr = new FlightDataRecorder();
		for (int i = 0; i < 17; i++) {
			fdr.record(0.11 + i);
		}
		
		String expected = "[7.11, 8.11, 9.11, 10.11, 11.11, 12.11, 13.11, 14.11, 15.11, 16.11]";
		String found = fdr.getRecordedData().toString();
		String msg = "Unexpected (incorrect or incorrectly formatted) recorded data information or string output was returned.";
		
        assertFalse("getRecordedData() didn't return the right data points.", found.isEmpty());
        assertEquals(msg, expected, found);
	}
	
	@Test
	public void testgetLastDataPoints() {
		FlightDataRecorder fdr = new FlightDataRecorder();
		for (int i = 0; i < 17; i++) {
			fdr.record(0.11 + i);
		}
		
		String expected = "[16.11]";
		String found = fdr.getLastDataPoints(17).toString();
		String msg = "Unexpected (incorrect or incorrectly formatted) recorded data information or string output was returned.";
		
        assertFalse("getLastDataPoints() didn't return the right last data points.", found.isEmpty());
        assertEquals(msg, expected, found);		
	}

}
