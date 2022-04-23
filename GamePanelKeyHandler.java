import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class KeyHandler extends KeyAdapter {
    MainPanel panel;
    public KeyHandler(MainPanel p)
    {
        this.panel = p;
    }
    public void keyPressed(KeyEvent ev) {
        if(panel.IsFirst)
        {
            FireBalls fireBall = new FireBalls(panel,ev);
            fireBall.start();
            panel.IsFirst = false;
        }
        if(ev.getKeyCode() == 80)
        {
            MainPanel.IsPaused = !MainPanel.IsPaused;
            if(!MainPanel.IsPaused)
            {
                FireBalls fireBall = new FireBalls(panel,ev);
                fireBall.start();
            }
        }
    }
}