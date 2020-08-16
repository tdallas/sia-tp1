package game;

import game.tiles.Tile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import strategies.utils.Step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static game.Direction.*;

@AllArgsConstructor
@Getter
public class Board {
    private List<List<Tile>> matrix;
    private List<Coordinate> finishPositions;
    private State initialState;
    //private State state;

    private static final Map<Direction, Coordinate> coordinateVectorMap = new HashMap<>() {{
        put(UP, new Coordinate(-1, 0));
        put(DOWN, new Coordinate(1, 0));
        put(RIGHT, new Coordinate(0, 1));
        put(LEFT, new Coordinate(0, -1));
    }};

    /**public void setState(final State state){
        this.state = state;
    }**/

    protected Tile getTileIfExists(final Coordinate coordinate) {
        if (coordinate.getX() >= matrix.size()) {
            return null;
        }
        final List<Tile> row = matrix.get(coordinate.getX());
        if (row != null && row.size() > 0) {
            if (coordinate.getY() >= row.size()) {
                return null;
            }
            return row.get(coordinate.getY());
        }
        return null;
    }

    /**
     * Returns coordinate to move if it exists and if walkable, otherwise null
     * @param fromCoordinate
     * @param direction
     * @return
     */
    protected Coordinate getCoordinateToMoveTo(final Coordinate fromCoordinate, final Direction direction, boolean isBoxMovement, final State state) {
        final Coordinate coordinateToMove = Coordinate.add(fromCoordinate, coordinateVectorMap.get(direction));
        final Tile tileToMove = getTileIfExists(coordinateToMove);
        if (tileToMove != null && tileToMove.isWalkable()) {
            if (getBoxInCoordinate(coordinateToMove, state) != null) {
                if(isBoxMovement) {
                    return null;
                }
                else {
                    final Coordinate nextCoordinate = Coordinate.add(coordinateToMove, coordinateVectorMap.get(direction));
                    final Tile nextTile = getTileIfExists(nextCoordinate);
                    if(nextTile != null && nextTile.isWalkable() && getBoxInCoordinate(nextCoordinate, state) == null){
                        return coordinateToMove;
                    }
                    else{
                        return null;
                    }
                }
            }
            return coordinateToMove;
        }
        return null;
    }

    public List<Direction> getPusherPossibleDirectionsToMove(final State state) {
        return coordinateVectorMap
                .keySet()
                .parallelStream()
                .filter(direction -> {
                    Coordinate newCoordinate = getCoordinateToMoveTo(state.getPusher().getCoordinate(), direction, false, state);
                    if (newCoordinate == null || isFinalTileAndContainsBox(newCoordinate,state)) return false;
                    Box boxInCoordinate = getBoxInCoordinate(newCoordinate, state);
                    if (boxInCoordinate == null) return true;
                    Coordinate coordinateToMoveBox = getCoordinateToMoveTo(newCoordinate, direction, true,  state);
                    return coordinateToMoveBox != null;
                })
                .collect(Collectors.toList());
    }

    public boolean coordinateContainsBox(final Coordinate coordinateToMove, final State state) {
        return state.getBoxes()
                .parallelStream()
                .map(Box::getCoordinate)
                .anyMatch(coordinate -> coordinate.equals(coordinateToMove));
    }

    /**
     * This function is always called after calling coordinateContainsBox iff that function returns true
     *
     * @param coordinate
     * @return
     */
    private Box getBoxInCoordinate(final Coordinate coordinate, final State state) {
        List<Box> boxToReturn = state.getBoxes()
                .parallelStream()
                .filter(box -> box.getCoordinate().equals(coordinate))
                .collect(Collectors.toList());
        if (boxToReturn.size() == 0) return null;
        return boxToReturn.get(0);
    }

    public State moveBoxIfPossible(final Coordinate coordinate, final Direction direction, final State state) {
        if (coordinateContainsBox(coordinate, state)) {
            final Coordinate coordinateToMoveBox = getCoordinateToMoveTo(coordinate, direction, true, state);
            if (coordinateToMoveBox != null) {
                Box box = getBoxInCoordinate(coordinate, state);
                if(box != null) {
                    State newState = new State(state);
                    newState.getBoxes().remove(box);
                    newState.getBoxes().add(new Box(box.getLabel(), coordinateToMoveBox));
                    return newState;
                }
            }
        }
        return new State(state);
    }

    /**
     * This function moves pusher to desired direction.
     * If that direction also push a box, that move is also made
     *
     * @param direction
     * @throws Exception
     */
    public State moveTo(final Direction direction, final State state) {
        final Coordinate coordinateToMove = getCoordinateToMoveTo(state.getPusher().getCoordinate(), direction, false, state);
        if (coordinateToMove != null) {
            if (!isFinalTileAndContainsBox(coordinateToMove, state)) {
                State newState = moveBoxIfPossible(coordinateToMove, direction, state);
                newState.getPusher().setCoordinate(coordinateToMove);
                return newState;
            } else {
                System.out.println("ROMPEMOS");
                // FIXME THROW AN ERROR or something like that
            }
        }
        return null;
    }

    protected boolean isFinalTileAndContainsBox(Coordinate coordinateToMove, final State state) {
        Tile tile = getTileIfExists(coordinateToMove);
        return tile != null && tile.isFinalTile() && getBoxInCoordinate(coordinateToMove, state) != null;
    }

    /**
     * returns true if a given boxesList are in final spots, false otherwise
     *
     * @return
     */
    public boolean gameHasEnded(final State state) {
        return state.getBoxes()
                .parallelStream()
                .map(box -> {
                    final Tile boxTile = getTileIfExists(box.getCoordinate());
                    return boxTile != null && boxTile.isFinalTile();
                })
                .reduce(true, (acum, current) -> acum && current);
    }

    // TODO
    /**
     * returns true if pusher is in deadlock, false otherwise
     *
     * @return
     */
    public boolean isDeadlock() {
        return false;
    }

}
