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
    
    @Test
    public void isBoardaBoard() {
        Board tester = new Board();
    	assertEquals(Board.class, tester.getClass());
    }
    
    @Test
    public void isHumanaHuman() {
    	Board testBoard = new Board();
        Human tester = new Human(testBoard, 'O');
    	assertEquals(Human.class, tester.getClass());
    }
    
    @Test
    public void isAIaAI() {
    	Board testBoard = new Board();
        AI tester = new AI(testBoard, 'X');
    	assertEquals(AI.class, tester.getClass());
    }
    
    @Test
    public void isEmptyFieldEmpty() {
    	Board tester = new Board();
        tester.setPiece(2, 2, 'X');
    	assertEquals(true, tester.isFieldEmpty(1, 1));
    }
    
    @Test
    public void isOccupiedFieldNotEmpty() {
    	Board tester = new Board();
        tester.setPiece(2, 2, 'X');
    	assertEquals(false, tester.isFieldEmpty(2, 2));
    }
    
}
