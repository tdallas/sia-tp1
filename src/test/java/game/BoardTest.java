package game;

import org.junit.Before;
import org.junit.Test;
import strategies.BoardFactory;

import java.util.List;

import static org.junit.Assert.*;
import static game.Direction.*;

public class BoardTest {

    private Board currentBoard;

    @Before
    public void setup() {
        currentBoard = BoardFactory.createBoard(BoardFactory.Level.MEDIUM);
    }

    @Test
    public void getExistentTileTest() {
        assertNotNull(currentBoard.getTileIfExists(new Coordinate(3, 3)));
    }

    @Test
    public void getNonExistentTileTest() {
        assertNull(currentBoard.getTileIfExists(new Coordinate(10, 10)));
    }

    @Test
    public void getNonExistentTileOverXTest() {
        assertNull(currentBoard.getTileIfExists(new Coordinate(10, 3)));
    }

    @Test
    public void getNonExistentTileOverYTest() {
        assertNull(currentBoard.getTileIfExists(new Coordinate(3, 10)));
    }

    @Test
    public void getValidCoordinateToMoveIfTileExistsAndIsWalkableTest() {
        State state = new State(currentBoard.getInitialState());
        state.setPusher(new Coordinate(4,1));
        Coordinate to = currentBoard.getPusherCoordinateToMoveTo(UP, state);

        assertNotNull(to);
        assertEquals(new Coordinate(3,1), to);
    }

    @Test
    public void getNullIfTileDoesNotExistTest() {
        State state = new State(currentBoard.getInitialState());
        state.setPusher(new Coordinate(10,10));

        assertNull(currentBoard.getPusherCoordinateToMoveTo(DOWN, state));
    }

    @Test
    public void getNullIfTileExistsButItIsNotWalkableTest() {
        State state = new State(currentBoard.getInitialState());
        state.setPusher(new Coordinate(4,1));

        assertNull(currentBoard.getPusherCoordinateToMoveTo(DOWN, state));
    }

    @Test
    public void getPusherPossibleDirectionsToMoveIfCanMoveOnlyInOneDirectionTest() {
        State state = new State(currentBoard.getInitialState());
        state.setPusher(new Coordinate(4,1));
        List<Direction> directionsToMove = currentBoard.getPusherPossibleDirectionsToMove(state);

        assertEquals(1, directionsToMove.size());
        assertEquals(UP, directionsToMove.get(0));
    }

    @Test
    public void getPusherPossibleDirectionsToMoveIfCanMakeMultipleMovesTest() {
        State state = new State(currentBoard.getInitialState());
        state.setPusher(new Coordinate(3,3));
        List<Direction> directionsToMove = currentBoard.getPusherPossibleDirectionsToMove(state);

        assertEquals(3, directionsToMove.size());
        assertTrue(directionsToMove.contains(UP));
        assertTrue(directionsToMove.contains(LEFT));
        assertTrue(directionsToMove.contains(RIGHT));
    }

    @Test
    public void shouldReturnTrueIfCoordinateContainsBoxTest() {
        assertTrue(currentBoard.stateContainsBoxAt(new Coordinate(2,1), currentBoard.getInitialState()));
    }

    @Test
    public void shouldReturnFalseIfCoordinateDoesNotContainsBoxTest() {
        assertFalse(currentBoard.stateContainsBoxAt(new Coordinate(2,4), currentBoard.getInitialState()));
    }

    @Test
    public void gameHasNotBeenEndedIfThereIsAtLeastOneFinalTileWithoutBox() {
        assertFalse(currentBoard.gameHasEnded(currentBoard.getInitialState()));
    }

    @Test
    public void shouldNotBeAValidMoveIfPusherIsTryingToMoveTwoConsecutiveBoxes() {
        State state = new State(currentBoard.getInitialState());
        state.setPusher(new Coordinate(2,3));
        List<Direction> directionsToMove = currentBoard.getPusherPossibleDirectionsToMove(state);

        assertFalse(directionsToMove.contains(LEFT));
        assertTrue(directionsToMove.contains(RIGHT));
        assertTrue(directionsToMove.contains(UP));
        assertTrue(directionsToMove.contains(DOWN));

    }

    @Test
    public void stateShouldBeEqual() {
        Board board1 = BoardFactory.createBoard(BoardFactory.Level.EASY);
        Board board2 = BoardFactory.createBoard(BoardFactory.Level.EASY);

        assertEquals(board1.getInitialState(), board2.getInitialState());
    }

}
