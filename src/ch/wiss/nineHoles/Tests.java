package ch.wiss.nineHoles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Tests {




    @Test
    public void occupiedFieldNotEmpty() {
        Board tester = new Board();
        tester.setPiece(0, 0, 'O');

        assertEquals(false, tester.isFieldEmpty(0, 0), "Occupied field should not be empty.");


    }
    
    @Test
    public void notOccupiedFieldEmpty() {
        Board tester = new Board();
        tester.setPiece(0, 0, 'O');


        assertEquals(true, tester.isFieldEmpty(1, 1), "Not occupied field should be empty.");


    }
    
    @Test
    public void unavailableDirectionNotAvailable() {
        Board tester = new Board();
        tester.setPiece(0, 0, 'O');

        assertEquals(false, tester.isDirectionEmpty(0, 0, 'u'), "Unavailable direction should not be available.");


    }
    
    @Test
    public void availableDirectionAvailable() {
        Board tester = new Board();
        tester.setPiece(0, 0, 'O');


        assertEquals(true, tester.isDirectionEmpty(0, 0, 'd'), "Available direction should be available.");

    }
}
