package view;

import util.Coordinate;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Maze;
import model.Solver;

public class PathView {

    public static void draw(Solver solver, GraphicsContext gc, double width, double height) {
        if (solver.getPath() == null) return;
        Maze maze = solver.getMaze();
        gc.save();
        gc.scale(width / (maze.size() + 2), height / (maze.size() + 2));
        gc.setFill(Color.RED);
        gc.setLineWidth(0.1);        
        for (Coordinate c : solver.getPath()) {
            int x = c.getX();
            int y = c.getY();
            gc.fillRect(x + 0.25, y + 0.25, 0.5, 0.5);
        }        
        gc.restore();
    }

}
