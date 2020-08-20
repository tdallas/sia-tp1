import game.Board;
import game.BoardFactory;
import strategies.SearchStrategy;
import strategies.heuristics.Heuristic;
import strategies.heuristics.almostNonTrivial.Euclidean;
import strategies.heuristics.almostNonTrivial.Manhattan;
import strategies.heuristics.nonTrivials.GlobalMinEuclidean;
import strategies.heuristics.nonTrivials.GlobalMinManhattan;
import strategies.heuristics.nonTrivials.ManhattanCheckingHalf;
import strategies.informed.AStar;
import strategies.informed.Greedy;
import strategies.informed.IDAStar;
import strategies.nonInformed.BFS;
import strategies.nonInformed.DFS;
import strategies.nonInformed.IDDFS;
import strategies.utils.Path;

import java.io.*;

public class Main {

    public static void main(String[] args){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("config.txt"));
            String line;
            String searchStrategy = null;
            String heuristic = null;
            String depth = null;
            String inputFile = null;
            Board board = null;

            while((line = reader.readLine()) != null) {
                if(line.contains("-s")){
                    searchStrategy = line.substring(3);;
                }
                else if(line.contains("-i")){
                    inputFile = line.substring(3);;
                }
                else if(line.contains("-d")){
                    depth = line.substring(3);;
                }
                else if(line.contains("-h")){
                    heuristic = line.substring(3);;
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
            if(inputFile == null){
                System.out.println("Error, not given input map file (-i inputfile)");
            }
            else{
                board = BoardFactory.createBoardFromInputFile(inputFile);
            }
            if(board != null) {
                if(searchStrategy != null) {
                    if (searchStrategy.equalsIgnoreCase ("bfs")) {
                        callStrategy(new BFS(board), searchStrategy, null, -1);
                    } else if (searchStrategy.equalsIgnoreCase ("dfs")) {
                        callStrategy(new DFS(board), searchStrategy, null, -1);
                    } else if (searchStrategy.equalsIgnoreCase ("iddfs")) {
                        int d;
                        if (depth == null) {
                            System.out.println("No given max depth, using default 1000");
                            d = 1000;
                        } else {
                            d = Integer.parseInt(depth);
                        }
                        if (d < 0) {
                            System.out.println("Invalid depth, must be over 0");
                        } else {
                            callStrategy(new IDDFS(board, d), searchStrategy, null, d);
                        }
                    } else {
                        Heuristic h = null;
                        if (heuristic == null) {
                            System.out.println("No given heuristic, using default GlobalMinManhattan");
                            heuristic = "GlobalMinManhattan";
                            h = new GlobalMinManhattan(board.getFinishCoordinates());
                        }
                        else{
                            if(heuristic.equalsIgnoreCase ("h1")){
                                heuristic = "GlobalMinManhattan";
                                h = new GlobalMinEuclidean(board.getFinishCoordinates());
                            } else if(heuristic.equalsIgnoreCase ("h2")){
                                heuristic = "GlobalMinEuclidean";
                                h = new GlobalMinEuclidean(board.getFinishCoordinates());
                            } else if(heuristic.equalsIgnoreCase ("h3")){
                                heuristic = "ManhattanCheckingHalf";
                                h = new ManhattanCheckingHalf(board.getFinishCoordinates());
                            } else if(heuristic.equalsIgnoreCase ("h4")){
                                heuristic = "Manhattan";
                                h = new Manhattan(board.getFinishCoordinates());
                            } else if(heuristic.equalsIgnoreCase ("h5")){
                                heuristic = "Euclidean";
                                h = new Euclidean(board.getFinishCoordinates());
                            }
                            else{
                                System.out.println("Invalid given heuristic on config.txt");
                            }
                        }
                        if(h != null) {
                            if (searchStrategy.equalsIgnoreCase ("greedy")) {
                                callStrategy(new Greedy(board, h), searchStrategy, heuristic, -1);
                            } else if (searchStrategy.equalsIgnoreCase ("astar")) {
                                callStrategy(new AStar(board, h), searchStrategy, heuristic, -1);
                            } else if (searchStrategy.equalsIgnoreCase ("idastar")) {
                                //callStrategy(new IDAStar(board, h), searchStrategy, heuristic, -1);
                                System.out.println("Implementar IDAStar");
                            } else {
                                System.out.println("Invalid given search strategy on config.txt");
                            }
                        }
                    }
                }
                else {
                    System.out.println("Invalid given search strategy on config.txt");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error while reading config file.");
        } catch (IOException e) {
            System.out.println("Error while reading config file.");
        }
    }

    private static void callStrategy(SearchStrategy searchStrategy, String strategy, String heuristic, int depth){
        System.out.println("Search Parameters: ");
        System.out.println("Strategy: " + strategy.toUpperCase());
        if(depth != -1){
            System.out.println("Max depth: " + depth);
        }
        if(heuristic != null){
            System.out.println("Heuristic: " + heuristic);
        }
        System.out.print("\nSolving status: ");
        Path path = searchStrategy.findSolution();
        if(path != null){
            System.out.println("SUCCESS");
            System.out.println("Time to solve: " + searchStrategy.getSolveTime() + "ms");
            System.out.println("Depth: " + path.getLength());
            System.out.println("Cost: " + path.getNode().getCost());
            System.out.println("Expanded nodes: " + searchStrategy.getExpandedNodes());
            System.out.println("Border nodes: " + searchStrategy.getBorderNodes());
            System.out.println("Solution: ");
            System.out.println(path);
        }
        else{
            System.out.println("FAILURE");
        }

    }
}
