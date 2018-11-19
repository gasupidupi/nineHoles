package ch.wiss.nineHoles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Tests {

	@Test
	void test() {
		fail("Not yet implemented");
	}


    @Test
    public void checkPiecesAmount() {
        Board tester = new Board();
        tester.setPiece(0, 0, 'O');

        assertEquals(false, tester.isFieldEmpty(0, 0), "Occupied field should not be empty.");
        assertEquals(true, tester.isFieldEmpty(1, 1), "Not occupied field should be empty.");
        assertEquals(true, tester.isDirectionEmpty(0, 0, 'u'), "Unavailable direction should not be available.");
        assertEquals(true, tester.isDirectionEmpty(0, 0, 'd'), "Available direction should be available.");

    }
}
