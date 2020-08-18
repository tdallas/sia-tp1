package game;

import game.tiles.EmptyTile;
import game.tiles.FinishTile;
import game.tiles.RockTile;
import game.tiles.Tile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import strategies.utils.Step;

import java.util.*;
import java.util.stream.Collectors;

import static game.Direction.*;

@AllArgsConstructor
@Getter
public class Board {
    private final Set<Coordinate> finishCoordinates;
    private final Set<Coordinate> rockCoordinates;
    private final Set<Coordinate> emptyCoordinates;
    private final State initialState;

    public static final Map<Direction, Coordinate> coordinateVectorMap = new HashMap<>() {{
        put(UP, new Coordinate(-1, 0));
        put(DOWN, new Coordinate(1, 0));
        put(RIGHT, new Coordinate(0, 1));
        put(LEFT, new Coordinate(0, -1));
    }};

    /**
     * Returns the Tile on the given coordinate, or null if the coordinate is invalid
     *
     * @param coordinate
     * @return
     */
    protected Tile getTileIfExists(final Coordinate coordinate) {
        if(emptyCoordinates.contains(coordinate)){
            return new EmptyTile(coordinate);
        }
        else if(rockCoordinates.contains(coordinate)){
            return new RockTile(coordinate);
        }
        else if(finishCoordinates.contains(coordinate)){
            return new FinishTile(coordinate);
        }
        return null;
    }

    /**
     * Returns coordinate to move if it exists and if walkable, otherwise null
     *
     * @param fromCoordinate
     * @param direction
     * @return
     */
    protected Coordinate getPusherCoordinateToMoveTo(final Coordinate fromCoordinate, final Direction direction, final State state) {
        final Coordinate firstCoordinateNext = Coordinate.add(fromCoordinate, coordinateVectorMap.get(direction));
        final Tile firstTileNext = getTileIfExists(firstCoordinateNext);
        if (firstTileNext != null && firstTileNext.isWalkable()) {
            final Coordinate secondCoordinateNext = Coordinate.add(firstCoordinateNext, coordinateVectorMap.get(direction));
            final Tile secondTileNext = getTileIfExists(secondCoordinateNext);
            if (!coordinateContainsBox(firstCoordinateNext, state)) {
                return firstCoordinateNext;
            } else if (secondTileNext != null && secondTileNext.isWalkable() && !coordinateContainsBox(secondCoordinateNext, state)) {
                return firstCoordinateNext;
            }
        }
        return null;
    }

    /**
     * Returns List of possible directions for the pusher to move
     *
     * @param state
     * @return
     */
    public List<Direction> getPusherPossibleDirectionsToMove(final State state) {
        return coordinateVectorMap
                .keySet()
                .parallelStream()
                .filter(direction -> getPusherCoordinateToMoveTo(state.getPusher().getCoordinate(), direction, state) != null)
                .collect(Collectors.toList());
    }

    /**
     * Returns true if there is a box on that coordinate, false otherwise
     *
     * @param coordinate
     * @param state
     * @return
     */
    public boolean coordinateContainsBox(final Coordinate coordinate, final State state) {
        return state.getBoxes()
                .parallelStream()
                .map(Box::getCoordinate)
                .anyMatch(c -> c.equals(coordinate));
    }

    /**
     * Returns the box in the coordinate or null if there is no box on that coordinate
     *
     * @param coordinate
     * @param state
     * @return
     */
    public Box getBoxInCoordinate(final Coordinate coordinate, final State state) {
        List<Box> boxToReturn = state.getBoxes()
                .parallelStream()
                .filter(box -> box.getCoordinate().equals(coordinate))
                .collect(Collectors.toList());
        if (boxToReturn.size() == 0) return null;
        return boxToReturn.get(0);
    }

    /**
     * returns true if the boxes in state are in final spots, false otherwise
     *
     * @param state
     * @return
     */
    public boolean gameHasEnded(final State state) {
        Box box = new Box("", new Coordinate(0, 0));
        for(Coordinate coordinate : finishCoordinates){
            box.setCoordinate(coordinate);
            if(!state.getBoxes().contains(box)){
                return false;
            }
        }
        return true;
    }

    /**
     * returns true if state is in deadlock, false otherwise
     *
     * @param state
     * @return
     */
    public boolean isDeadlock(final State state) {
//        for (Box box : state.getBoxes()) {
//            int row = box.row;
//            int col = box.col;
//            if (!setContains(goals, row, col)) {
//                if (setContains(walls, row-1, col)&&setContains(walls, row, col-1))
//                    return true; //top & left
//                if (setContains(walls, row-1, col)&&setContains(walls, row, col+1))
//                    return true; //top & right
//                if (setContains(walls, row+1, col)&&setContains(walls, row, col-1))
//                    return true; //bottom & left
//                if (setContains(walls, row+1, col)&&setContains(walls, row, col+1))
//                    return true; //bottom & right
//
//                if (setContains(walls, row-1, col-1)&&setContains(walls, row-1, col)&&
//                        setContains(walls, row-1, col+1)&&setContains(walls, row, col-2)&&
//                        setContains(walls, row, col+2)&&(!setContains(goals, row, col-1))&&
//                        !setContains(goals, row, col+1))
//                    return true; //top & sides
//                if (setContains(walls, row+1, col-1)&&setContains(walls, row+1, col)&&
//                        setContains(walls, row+1, col+1)&&setContains(walls, row, col-2)&&
//                        setContains(walls, row, col+2)&&(!setContains(goals, row, col-1))&&
//                        (!setContains(goals, row, col+1)))
//                    return true; //bottom & sides
//                if (setContains(walls, row-1, col-1)&&setContains(walls, row, col-1)&&
//                        setContains(walls, row+1, col-1)&&setContains(walls, row-2, col)&&
//                        setContains(walls, row+2, col)&&(!setContains(goals, row-1, col))&&
//                        (!setContains(goals, row+1, col)))
//                    return true; //left & vertical
//                if (setContains(walls, row-1, col+1)&&setContains(walls, row, col+1)&&
//                        setContains(walls, row+1, col+1)&&setContains(walls, row-2, col)&&
//                        setContains(walls, row+2, col)&&(!setContains(goals, row-1, col))&&
//                        (!setContains(goals, row+1, col)))
//                    return true; //right & top/bottom
//            }
//        }
        return false;
    }
}
