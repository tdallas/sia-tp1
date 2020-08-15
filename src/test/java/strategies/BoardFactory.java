package strategies;

import game.*;
import game.tiles.EmptyTile;
import game.tiles.FinishTile;
import game.tiles.RockTile;
import game.tiles.Tile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class BoardFactory {

    final static String hard = "    XXXXX\n" +
            "    X   X\n" +
            "    X*  X\n" +
            "  XXX  *XXX\n" +
            "  X  *  * X\n" +
            "XXX X XXX X     XXXXXX\n" +
            "X   X XXX XXXXXXX  ..X\n" +
            "X *  *             ..X\n" +
            "XXXXX XXXX X@XXXX  ..X\n" +
            "    X      XXX  XXXXXX\n" +
            "    XXXXXXXX";

    final static String easy = "XXX\n" +
            "X.X\n" +
            "X X\n" +
            "X*X\n" +
            "X@X\n" +
            "XXX";
    final static String medium = "XXXXXXX\n" +
            "X.  . X\n" +
            "X**   X\n" +
            "X     X\n" +
            "X@XX  X\n" +
            "XXXXXXX";

    public static enum Level {EASY, MEDIUM, HARD}

    @Getter
    @AllArgsConstructor
    private static class BoardGame {
        private final List<List<Tile>> matrix;
        private final Pusher pusher;
        private final List<Coordinate> finishPositions;
        private final List<Box> boxList;
    }

    public static Board createBoard(final Level level) {
        BoardGame boardGame = generateGame(level);

        return new Board(
                boardGame.getMatrix(),
                boardGame.getPusher(),
                boardGame.getFinishPositions(),
                boardGame.getBoxList()
        );
    }

    private static BoardGame generateGame(final Level level) {
        switch (level) {
            case EASY:
                return generateEasyLevel();
            case MEDIUM:
                return generateMediumLevel();
            case HARD:
                return generateHardLevel();
        }
        return null;
    }

    private static BoardGame generateHardLevel() {
        return generateLevel(hard);
    }

    private static BoardGame generateMediumLevel() {
        return generateLevel(medium);
    }

    private static BoardGame generateEasyLevel() {
        return generateLevel(easy);
    }

    private static BoardGame generateLevel(final String level) {
        List<List<Tile>> map = new ArrayList<>();
        List<Box> boxList = new ArrayList<>();
        List<Coordinate> finishPositions = new ArrayList<>();
        Pusher pusher = new Pusher();
        boolean newLine = true;
        List<Tile> row = new ArrayList<>();
        int colCount = 0;
        int rowCount = 0;
        int boxCount = 0;
        for (int i = 0; i < level.length(); i++) {
            if (newLine) {
                if (rowCount != 0) map.add(row);
                colCount = 0;
                row = new ArrayList<>();
                newLine = false;
            }
            char item = level.charAt(i);
            switch (item) {
                case 'X':
                    row.add(new RockTile(new Coordinate(rowCount, colCount++)));
                    break;
                case ' ':
                    row.add(new EmptyTile(new Coordinate(rowCount, colCount++)));
                    break;
                case '.':
                    row.add(new FinishTile(new Coordinate(rowCount, colCount)));
                    finishPositions.add(new Coordinate(rowCount, colCount++));
                    break;
                case '*':
                    row.add(new EmptyTile(new Coordinate(rowCount, colCount)));
                    boxList.add(new Box("Box" + boxCount++, new Coordinate(rowCount, colCount++)));
                    break;
                case '@':
                    row.add(new EmptyTile(new Coordinate(rowCount, colCount)));
                    pusher = new Pusher(new Path(), new Coordinate(rowCount, colCount++));
                    break;
                case '\n':
                    newLine = true;
                    rowCount++;
                    break;
                default:
                    break;
            }

        }
        return new BoardGame(map, pusher, finishPositions, boxList);
    }
}
