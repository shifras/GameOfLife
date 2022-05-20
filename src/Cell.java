import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JButton implements ActionListener {
    private Color clr;
    private boolean isAlive;

    public Cell(int x, int y, int width, int height, boolean isAlive){
        this.setBounds(x, y, width, height);
        this.addActionListener(this);
        setIsAlive(isAlive);
        this.setBackground(clr);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isAlive) {
            clr = Color.white;
            isAlive = false;
            this.setBackground(Color.white);
        } else {
            clr = Color.black;
            isAlive=true;
            this.setBackground(Color.black);
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean alive) {
        if (alive) {
            this.isAlive = true;
            this.clr=Color.black;
            this.setBackground(Color.black);
        } else {
            this.isAlive= false;
            this.clr = Color.white;
            this.setBackground(Color.white);
        }
    }

    public String toString(){
        return isAlive ? "0" : ".";
    }
}
