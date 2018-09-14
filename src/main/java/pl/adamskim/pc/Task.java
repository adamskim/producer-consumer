package pl.adamskim.pc;

import java.io.Serializable;

public class Task implements Serializable {
    
    private final int x;
    private final int y;

    public Task(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void execute() {
        System.out.println(String.format("%s x %s = %s", x, y, x * y)); 
    }

    @Override
    public String toString() {
        return String.format("%s x %s", x, y);
    }
}
