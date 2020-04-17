public class Bullet {

    private int x;
    private int y;

    private int dx;
    private int dy;

    public Bullet(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void move() {
        int speed = 10;
        x += dx * speed;
        y += dy * speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void set(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }
}
