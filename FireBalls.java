import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;

public class FireBalls extends Thread implements Serializable {
    MainPanel mainPanel;
    KeyEvent ev;
    public FireBalls(MainPanel p, KeyEvent key)
    {
        this.mainPanel = p;
        this.ev = key;
    }
    public void run() {
        while (!MainPanel.IsPaused)
        {
                Fire f = new Fire(MainPanel.r.x + MainPanel.r.width / 2, MainPanel.r.y, Color.black, program.mp);
                mainPanel.fire_list.add(f);
                f.start();
                try {
                    // re-create fire ball speed
                    Thread.sleep(75);
                } catch (InterruptedException t) {
                    t.printStackTrace();
                }
        }
    }
}
