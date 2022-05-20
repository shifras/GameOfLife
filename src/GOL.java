import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GOL {
    static int rows = 50;
    static int cols = 50;
    static int height=20;
    static int width=20;
    
    static Cell c;

    static JPanel grid=new JPanel(new GridLayout(rows, cols));
    static boolean[][] currentGenArr = new boolean[rows][cols];
    static Cell[][] nextGenCellArr= initializeNextGenCellArr();

    static final JFrame GOL=new JFrame("GameOfLife");

    public volatile static boolean on=false; //volatile priesingybe final yra
    static volatile boolean rSwitch=true;
    static Run r;

    public static void main(String[] args) throws InterruptedException {
        GOL.setSize(cols*10,rows*10);
        GOL.setLocationRelativeTo(null);
        GOL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        grid.setSize(rows, cols);
        initializeGrid();
        JPanel buttons=new JPanel(new GridLayout(1, 4));

        JButton ClcNxt=new JButton("Next");
        JButton startStop=new JButton("Start/Stop");
        JButton reset=new JButton("Reset");
        JButton gosper= new JButton("Create Gosper");

        gosper.addActionListener(e -> {
            gosperGlider();
        });

        startStop.addActionListener(e -> {
            if (!rSwitch) rSwitch = true;
            if( r==null) {
                r=new Run();
                r.start();
            }
            on = !on;
        });

        ClcNxt.addActionListener(e -> {
            pullValues();
            calcNextGen();
        });

        reset.addActionListener(e -> {
            rSwitch = false;
            r = null;
            GOL.remove(grid);
            grid = null;
            grid= new JPanel(new GridLayout(rows, cols));
            nextGenCellArr = null;
            nextGenCellArr = initializeNextGenCellArr();
            for (Cell[] row : nextGenCellArr) {
                for (Cell c : row) {
                    grid.add(c);
                }
            }
            GOL.add(grid, BorderLayout.CENTER);
            grid.repaint();
            GOL.setVisible(true);
        });

        buttons.add(ClcNxt);
        buttons.add(startStop);
        buttons.add(reset);
        buttons.add(gosper);

        gosperGlider();

        GOL.add(grid,BorderLayout.CENTER);
        GOL.add(buttons, BorderLayout.PAGE_END);
        GOL.setVisible(true);


    }

    public static void pullValues() {
        for (int row=0; row< rows; row++) {
            for (int cell = 0; cell < cols; cell++) {
                currentGenArr[row][cell] = nextGenCellArr[row][cell].isAlive();
            }
        }
    }
    
    static Cell[][] initializeNextGenCellArr(){
        Cell[][] tmp=new Cell[rows][cols];
        int x=0;
        int y=0;
        for (int row = 0; row < rows; row++) {
            for (int cell = 0; cell < cols; cell++) {
                tmp[row][cell] = new Cell(x,y,width,height, false);
                x+= width;
            }
            x=0;
            y+= height;
        }
        return tmp;
    }

    public static void initializeGrid(){
        for (Cell[] row : nextGenCellArr) {
            for (Cell c : row) {
                grid.add(c);
            }
        }
    }

    static void calcNextGen(){
        for (int row = 0; row < rows; row++) {
            for (int cell = 0; cell < cols; cell++) {
                nextGenCellArr[row][cell].setIsAlive(calcCell(currentGenArr[row][cell], row, cell));
            }
        }
    }

    static boolean calcCell(boolean self, int row, int cell) {
        ArrayList<Boolean> neighbours=new ArrayList<>(0);

        for (int  rw = row - 1; rw < row + 2; rw++) {
            for (int cll = cell - 1; cll < cell + 2; cll++) {
                if ( ((rw==row) && (cll==cell)) || rw<0 || cll<0 || rw==rows || cll==cols ) {
                    continue;
                }
                neighbours.add(currentGenArr[rw][cll]);
            }
        }

        int count = count(neighbours);
        return deadBornOrAlive(count, self);
    }
    
    static int count(ArrayList<Boolean> neighbours){
        int count = 0;
        for (Boolean b : neighbours) {
            if (b) count++;
        }
        return count;
    }

    static boolean deadBornOrAlive(int count, boolean self){
        if ((count < 2 || 3 < count) && self) return false;
        if ((count==2 || count == 3) && self) return true;
        return !self && count==3;
    }
    
    private static void gosperGlider() {
//        rSwitch = false;
//        r = null;

        nextGenCellArr[0][24-1].setIsAlive(true);
        nextGenCellArr[0][26-1].setIsAlive(true);
        nextGenCellArr[2-1][24-1].setIsAlive(true);
        nextGenCellArr[2-1][24-1].setIsAlive(true);
        nextGenCellArr[2-1][27-1].setIsAlive(true);
        nextGenCellArr[3-1][9-1].setIsAlive(true);
        nextGenCellArr[3-1][16-1].setIsAlive(true);
        nextGenCellArr[3-1][27-1].setIsAlive(true);
        nextGenCellArr[3-1][28-1].setIsAlive(true);
        nextGenCellArr[3-1][35-1].setIsAlive(true);
        nextGenCellArr[3-1][36-1].setIsAlive(true);
        nextGenCellArr[4-1][8-1].setIsAlive(true);
        nextGenCellArr[4-1][10-1].setIsAlive(true);
        nextGenCellArr[4-1][16-1].setIsAlive(true);
        nextGenCellArr[4-1][25-1].setIsAlive(true);
        nextGenCellArr[4-1][29-1].setIsAlive(true);
        nextGenCellArr[4-1][30-1].setIsAlive(true);
        nextGenCellArr[4-1][35-1].setIsAlive(true);
        nextGenCellArr[4-1][36-1].setIsAlive(true);
        nextGenCellArr[5-1][0].setIsAlive(true);
        nextGenCellArr[5-1][2-1].setIsAlive(true);
        nextGenCellArr[5-1][7-1].setIsAlive(true);
        nextGenCellArr[5-1][9-1].setIsAlive(true);
        nextGenCellArr[5-1][10-1].setIsAlive(true);
        nextGenCellArr[5-1][17-1].setIsAlive(true);
        nextGenCellArr[5-1][27-1].setIsAlive(true);
        nextGenCellArr[5-1][28-1].setIsAlive(true);
        nextGenCellArr[6-1][0].setIsAlive(true);
        nextGenCellArr[6-1][2-1].setIsAlive(true);
        nextGenCellArr[6-1][6-1].setIsAlive(true);
        nextGenCellArr[6-1][7-1].setIsAlive(true);
        nextGenCellArr[6-1][9-1].setIsAlive(true);
        nextGenCellArr[6-1][10-1].setIsAlive(true);
        nextGenCellArr[6-1][20-1].setIsAlive(true);
        nextGenCellArr[6-1][21-1].setIsAlive(true);
        nextGenCellArr[6-1][24-1].setIsAlive(true);
        nextGenCellArr[6-1][27-1].setIsAlive(true);
        nextGenCellArr[7-1][7-1].setIsAlive(true);
        nextGenCellArr[7-1][9-1].setIsAlive(true);
        nextGenCellArr[7-1][10-1].setIsAlive(true);
        nextGenCellArr[7-1][16-1].setIsAlive(true);
        nextGenCellArr[7-1][17-1].setIsAlive(true);
        nextGenCellArr[7-1][18-1].setIsAlive(true);
        nextGenCellArr[7-1][21-1].setIsAlive(true);
        nextGenCellArr[7-1][24-1].setIsAlive(true);
        nextGenCellArr[7-1][26-1].setIsAlive(true);
        nextGenCellArr[8-1][8-1].setIsAlive(true);
        nextGenCellArr[8-1][10-1].setIsAlive(true);
        nextGenCellArr[8-1][18-1].setIsAlive(true);
        nextGenCellArr[8-1][19-1].setIsAlive(true);
        nextGenCellArr[8-1][20-1].setIsAlive(true);
        nextGenCellArr[8-1][21-1].setIsAlive(true);
        nextGenCellArr[9-1][9-1].setIsAlive(true);
        nextGenCellArr[9-1][19-1].setIsAlive(true);
        nextGenCellArr[9-1][20-1].setIsAlive(true);

        nextGenCellArr[10+5+4+16][10+5+18+16].setIsAlive(true);
        nextGenCellArr[10+5+4+16][9+5+18+16].setIsAlive(true);
        nextGenCellArr[9+5+4+16][9+5+18+16].setIsAlive(true);
        nextGenCellArr[8+5+4+16][9+5+18+16].setIsAlive(true);
        nextGenCellArr[8+5+4+16][7+5+18+16].setIsAlive(true);
        nextGenCellArr[7+5+4+16][7+5+18+16].setIsAlive(true);
        nextGenCellArr[7+5+4+16][8+5+18+16].setIsAlive(true);

    }



}
/*
currentGen[10+5+4][10+5+18] = true;
        currentGen[10+5+4][9+5+18] = true;
        currentGen[9+5+4][9+5+18] = true;
        currentGen[8+5+4][9+5+18] = true;
        currentGen[8+5+4][7+5+18] = true;
        currentGen[7+5+4][7+5+18] = true;
        currentGen[7+5+4][8+5+18] = true;

        //gosper glider gun
        currentGen[1-1][24-1] = true;
        currentGen[1-1][26-1] = true;
        currentGen[2-1][24-1] = true;
        currentGen[2-1][24-1] = true;
        currentGen[2-1][27-1] = true;
        currentGen[3-1][9-1] = true;
        currentGen[3-1][16-1] = true;
        currentGen[3-1][27-1] = true;
        currentGen[3-1][28-1] = true;
        currentGen[3-1][35-1] = true;
        currentGen[3-1][36-1] = true;
        currentGen[4-1][8-1] = true;
        currentGen[4-1][10-1] = true;
        currentGen[4-1][16-1] = true;
        currentGen[4-1][25-1] = true;
        currentGen[4-1][29-1] = true;
        currentGen[4-1][30-1] = true;
        currentGen[4-1][35-1] = true;
        currentGen[4-1][36-1] = true;
        currentGen[5-1][1-1] = true;
        currentGen[5-1][2-1] = true;
        currentGen[5-1][7-1] = true;
        currentGen[5-1][9-1] = true;
        currentGen[5-1][10-1] = true;
        currentGen[5-1][17-1] = true;
        currentGen[5-1][27-1] = true;
        currentGen[5-1][28-1] = true;
        currentGen[6-1][1-1] = true;
        currentGen[6-1][2-1] = true;
        currentGen[6-1][6-1] = true;
        currentGen[6-1][7-1] = true;
        currentGen[6-1][9-1] = true;
        currentGen[6-1][10-1] = true;
        currentGen[6-1][20-1] = true;
        currentGen[6-1][21-1] = true;
        currentGen[6-1][24-1] = true;
        currentGen[6-1][27-1] = true;
        currentGen[7-1][7-1] = true;
        currentGen[7-1][9-1] = true;
        currentGen[7-1][10-1] = true;
        currentGen[7-1][16-1] = true;
        currentGen[7-1][17-1] = true;
        currentGen[7-1][18-1] = true;
        currentGen[7-1][21-1] = true;
        currentGen[7-1][24-1] = true;
        currentGen[7-1][26-1] = true;
        currentGen[8-1][8-1] = true;
        currentGen[8-1][10-1] = true;
        currentGen[8-1][18-1] = true;
        currentGen[8-1][19-1] = true;
        currentGen[8-1][20-1] = true;
        currentGen[8-1][21-1] = true;
        currentGen[9-1][9-1] = true;
        currentGen[9-1][19-1] = true;
        currentGen[9-1][20-1] = true;
 */

