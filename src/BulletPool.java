import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class BulletPool{
    protected static  BulletPool instance = null;
    public List<Bullet> bullets = new ArrayList<Bullet>();
    public static BulletPool getInstance(){

        if(instance == null){
            instance = new BulletPool();
        }
        return instance;
    }
    public BulletPool(){
        int pool_size = 400;
        for (int i = 0; i < pool_size; i++) {
            bullets.add(new Bullet(-50,-50,0,0));
        }
    }

    public Bullet acquireReusable(){
        Bullet bullet = bullets.get(0);
        Collections.rotate(bullets, -1);
        return bullet;
    }
    public void releaseReusable(Bullet bullet) {
            bullet.set(-50,-50,0,0);
    }

    }



