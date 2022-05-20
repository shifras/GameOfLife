public class Run extends Thread {
    public void run() {
        while (GOL.rSwitch) {
            if (GOL.on) {
                GOL.pullValues();// pull values from nextGenCellArr to currentGenArr
                GOL.calcNextGen();           // from currentGenArr to nextGenCellArr
                GOL.grid.repaint();
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
