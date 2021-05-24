package controler;

import java.net.URL;
import java.util.ResourceBundle;

import view.MazeView;
import view.PathView;
import model.Maze;
import model.Solver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;

public class Controler implements Initializable {
    @FXML TextField size;
    @FXML TextField cost;
    @FXML TextField score;
    @FXML Canvas drawingArea;
    
    @FunctionalInterface
    interface Solve
    {
       void solve();
    }
    
    final Solver solver = new Solver();
    Solve solve = new Solve() { public void solve() { solver.dfs(); } }; 
    //Solve solve = () -> { solver.dfs(); };
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newMaze();
        draw();
    }
    
    @FXML
    void onReset(ActionEvent evt) {
        solver.reset();
        draw();
    }
    
    @FXML
    void onSize(ActionEvent evt) {
        try {
            int n = Integer.valueOf(size.getText());
            if (n < 1) {
                size.setText("1");
            } else if (n > 1000) {
                size.setText("1000");
            }
        } catch (NumberFormatException e) {
            size.setText("20");
        }
    }
    
    @FXML
    void onGenerate(ActionEvent evt) {
        onSize(new ActionEvent());
        newMaze();
        draw();
    }
        
    @FXML
    void onSolve(ActionEvent evt) {
        solve.solve();
        score.setText(solver.getCost()+"");
        draw();
    }
    
    @FXML
    void onBfs(ActionEvent evt) {
        solve = new Solve() { public void solve() { solver.bfs(); } };
        //solve = () -> solver.bfs();
    }
    
    @FXML
    void onDfs(ActionEvent evt) {
        solve = new Solve() { public void solve() { solver.dfs(); } };
        //solve = () -> solver.dfs();
    }
    
    @FXML
    void onDijkstra(ActionEvent evt) {
        solve = new Solve() { 
            public void solve() { 
                onCost(new ActionEvent()); solver.dijkstra(Integer.valueOf(cost.getText())); 
            } };
        //solve = () -> { onCost(new ActionEvent()); solver.dijkstra(Integer.valueOf(cost.getText())); };
    }
    
    @FXML
    void onCost(ActionEvent evt) {
        try {
            int n = Integer.valueOf(cost.getText());
            if (n < 0) {
                cost.setText("0");
            }
        } catch (NumberFormatException e) {
            cost.setText("50");
        }
    }
    
    private void newMaze() {
       onSize(new ActionEvent());
       solver.setMaze(new Maze(Integer.valueOf(size.getText())));
    }
    
    private void draw() {
        int cost = solver.getCost();
        score.setText(cost == -1 ? "" : String.valueOf(cost));
        MazeView.draw(solver.getMaze(), drawingArea.getGraphicsContext2D(), drawingArea.getWidth(), drawingArea.getHeight());
        PathView.draw(solver, drawingArea.getGraphicsContext2D(), drawingArea.getWidth(), drawingArea.getHeight());
    }
}
