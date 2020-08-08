package game;

import lombok.AllArgsConstructor;

import java.util.Queue;

@AllArgsConstructor
public class Pusher {
    // FIFO structure
    private Queue<Path> path;
}
