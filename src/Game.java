import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Game extends Observable {

    private int width = 600;
    private int height = 600;

    private BulletPool bulletsPool = BulletPool.getInstance();
    private List<Bullet> use_bullets = new ArrayList<Bullet>();
    private Thread mainLoop;
    private boolean alive;

    public Game() {
        alive = true;
        mainLoop = new Thread() {
            @Override
            public void run() {
                while(alive) {
                    tick();
                    setChanged();
                    notifyObservers();
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mainLoop.start();
    }

    public void tick() {
        moveBullets();
        checkBullet();
        cleanupBullets();
    }

    private void moveBullets() {
        for(Bullet bullet : use_bullets) {
            bullet.move();
        }
    }

    private void cleanupBullets() {
        use_bullets.removeIf(b -> b.getX() <= 0 ||
                b.getX() >= width ||
                b.getY() <= 0 ||
                b.getY() >= height);
            }

    private void checkBullet(){
        for (Bullet bullet : use_bullets){
            if (bullet.getX() <= 0 ||
                    bullet.getX() >= width ||
                    bullet.getY() <= 0 ||
                    bullet.getY() >= height){
                bulletsPool.releaseReusable(bullet);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Bullet> getBullets() {
        return use_bullets;
    }

    public List<Bullet> getBulletPool() {
        return bulletsPool.bullets;
    }

    public void burstBullets(int x, int y) {
        Bullet bullet = bulletsPool.acquireReusable();
        bullet.set(x, y, 1, 0);
        Bullet bullet2 = bulletsPool.acquireReusable();
        bullet2.set(x, y, 0, 1);
        Bullet bullet3 = bulletsPool.acquireReusable();
        bullet3.set(x, y, -1, 0);
        Bullet bullet4 = bulletsPool.acquireReusable();
        bullet4.set(x, y, 0, -1);
        Bullet bullet5 = bulletsPool.acquireReusable();
        bullet5.set(x, y, 1, 1);
        Bullet bullet6 = bulletsPool.acquireReusable();
        bullet6.set(x, y, 1, -1);
        Bullet bullet7 = bulletsPool.acquireReusable();
        bullet7.set(x, y, -1, 1);
        Bullet bullet8 = bulletsPool.acquireReusable();
        bullet8.set(x, y, -1, -1);
        use_bullets.add(bullet);
        use_bullets.add(bullet2);
        use_bullets.add(bullet3);
        use_bullets.add(bullet4);
        use_bullets.add(bullet5);
        use_bullets.add(bullet6);
        use_bullets.add(bullet7);
        use_bullets.add(bullet8);
    }
}
