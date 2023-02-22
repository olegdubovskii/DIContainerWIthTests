package Test;

public class Test implements ITest {

    public int x;
    public int y;
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void out() {
        x = 10;
        y = 20;
        System.out.println(x);
        System.out.println(y);
    }
}
