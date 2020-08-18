package game;

import game.tiles.EmptyTile;
import game.tiles.FinishTile;
import game.tiles.RockTile;
import game.tiles.Tile;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
     * Returns the Tile on the given coordinate or a RockTile with the given coordinate
     *
     * @param coordinate
     * @return
     */
    protected Tile getTileIfExists(final Coordinate coordinate) {
        if(emptyCoordinates.contains(coordinate)){
            return new EmptyTile(coordinate);
        }
        else if(finishCoordinates.contains(coordinate)){
            return new FinishTile(coordinate);
        }
        else {
            return new RockTile(coordinate);
        }
    }

    /**
     * Returns coordinate to move if it exists and if walkable, otherwise null
     *
     * @param direction
     * @return
     */
    protected Coordinate getPusherCoordinateToMoveTo(final Direction direction, final State state) {
        final Coordinate firstCoordinateNext = Coordinate.add(state.getPusher(), coordinateVectorMap.get(direction));
        final Tile firstTileNext = getTileIfExists(firstCoordinateNext);
        if (firstTileNext != null && firstTileNext.isWalkable()) {
            final Coordinate secondCoordinateNext = Coordinate.add(firstCoordinateNext, coordinateVectorMap.get(direction));
            final Tile secondTileNext = getTileIfExists(secondCoordinateNext);
            if (!stateContainsBoxAt(firstCoordinateNext, state)) {
                return firstCoordinateNext;
            } else if (secondTileNext != null && secondTileNext.isWalkable() && !stateContainsBoxAt(secondCoordinateNext, state)) {
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
                .filter(direction -> getPusherCoordinateToMoveTo(direction, state) != null)
                .collect(Collectors.toList());

    }

    /**
     * Returns true if there is a box on that coordinate, false otherwise
     *
     * @param coordinate
     * @param state
     * @return
     */
    public boolean stateContainsBoxAt(final Coordinate coordinate, final State state) {
        return state.getBoxes().contains(coordinate);
    }

    /**
     * returns true if the boxes in state are in final spots, false otherwise
     *
     * @param state
     * @return
     */
    public boolean gameHasEnded(final State state) {
        for(Coordinate coordinate : finishCoordinates){
            if(!state.getBoxes().contains(coordinate)){
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
        Tile center, up, down, left, right, upLeft, upRight, downLeft, downRight, downDown, upUp, rightRight, leftLeft;
        for(Coordinate box : state.getBoxes()){
            center = getTileIfExists(box);
            int xCoordinate = box.getX();
            int yCoordinate = box.getY();
            if(!center.isFinalTile()){
                up = getTileIfExists(new Coordinate(xCoordinate - 1, yCoordinate));
                down = getTileIfExists(new Coordinate(xCoordinate + 1, yCoordinate));
                left = getTileIfExists(new Coordinate(xCoordinate, yCoordinate - 1));
                right = getTileIfExists(new Coordinate(xCoordinate, yCoordinate + 1));

                if(!up.isWalkable() && !left.isWalkable()){
                    return true;
                }
                if(!up.isWalkable() && !right.isWalkable()){
                    return true;
                }
                if(!down.isWalkable() && !right.isWalkable()){
                    return true;
                }
                if(!down.isWalkable() && !left.isWalkable()){
                    return true;
                }

                upLeft = getTileIfExists(new Coordinate(xCoordinate - 1, yCoordinate - 1));
                upRight = getTileIfExists(new Coordinate(xCoordinate - 1, yCoordinate + 1));
                downLeft = getTileIfExists(new Coordinate(xCoordinate + 1, yCoordinate - 1));
                downRight = getTileIfExists(new Coordinate(xCoordinate + 1, yCoordinate + 1));
                upUp = getTileIfExists(new Coordinate(xCoordinate - 2, yCoordinate));
                downDown = getTileIfExists(new Coordinate(xCoordinate + 2, yCoordinate));
                leftLeft = getTileIfExists(new Coordinate(xCoordinate, yCoordinate - 2));
                rightRight = getTileIfExists(new Coordinate(xCoordinate, yCoordinate + 2));

                if (!upLeft.isWalkable() &&
                        !up.isWalkable() &&
                        !upRight.isWalkable() &&
                        !leftLeft.isWalkable() &&
                        !rightRight.isWalkable() &&
                        !left.isFinalTile() &&
                        !right.isFinalTile()) {
                    return true;  //top & sides
                }
                if (!downLeft.isWalkable() &&
                        !down.isWalkable() &&
                        !downRight.isWalkable() &&
                        !leftLeft.isWalkable() &&
                        !rightRight.isWalkable() &&
                        !left.isFinalTile() &&
                        !right.isFinalTile()) {
                    return true; //bottom & sides
                }
                if (!upLeft.isWalkable() &&
                        !left.isWalkable() &&
                        !downLeft.isWalkable() &&
                        !upUp.isWalkable() &&
                        !downDown.isWalkable() &&
                        !up.isFinalTile() &&
                        !down.isFinalTile()) {
                    return true; //left & vertical
                }
                if (!upRight.isWalkable() &&
                        !right.isWalkable() &&
                        !downRight.isWalkable() &&
                        !upUp.isWalkable() &&
                        !downDown.isWalkable() &&
                        !up.isFinalTile() &&
                        !down.isFinalTile()) {
                    return true; //right & top/bottom
                }
            }
        }
        return false;
    }
}
