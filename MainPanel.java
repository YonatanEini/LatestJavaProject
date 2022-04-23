import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements Serializable {
    public static boolean IsPaused = false;
    public static boolean IsGameOver = false;
    public static boolean IsSinglePlayer = true;
    public static boolean IsNewGameRequest = false;
    public Image bg1;
    public static Cannon r;
    public List<Ball> ball_list = new ArrayList<Ball>();
    public List<Fire> fire_list = new ArrayList<Fire>();
    public RecceiveDataDto otherClientData = new RecceiveDataDto();
    public int score;
    public boolean IsFirst;

    public MainPanel()  {
        ImageIcon ii = new ImageIcon("background (1).jpg");
        bg1 = ii.getImage();
        addMouseMotionListener(new MML());
        addKeyListener(new KeyHandler(this));
        this.score = 0;
        setFocusable(true);
        ScoreTimer scoreTimer = new ScoreTimer(this);
        javax.swing.Timer t = new javax.swing.Timer(1000, scoreTimer.changeScore);
        t.start();
        this.IsFirst = true;
        if (IsSinglePlayer) {
            new BallTimerAdding(this);
            Mp3Player mp3 = new Mp3Player("game-music.mp3");
            mp3.play();
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg1, 0, 0, getWidth(), getHeight(), null);
        Font f = new Font(Font.DIALOG, Font.BOLD, 50);
        g.setFont(f);
        g.setColor(Color.WHITE);
        String score = "score: "+this.score;
        g.drawString(score, 1000, 50);
        r.draw(g);
        if (otherClientData.cannonPos.y != 0) {
            Cannon otherClientCannon = new Cannon(otherClientData.cannonPos, this);
            otherClientCannon.draw(g);
        }
        for (int i = 0; i < fire_list.size(); i++) {
            if (fire_list.get(i).isAlive())
                fire_list.get(i).draw(g);
            else
                fire_list.remove(fire_list.get(i));
        }
        for (int i = 0; i < ball_list.size(); i++) {
            if (ball_list.get(i).isAlive()){
                ball_list.get(i).draw(g);
        }
            else
                ball_list.remove(ball_list.get(i));

        }
        for (int i=0; i<otherClientData.current_ballList.size();i++){
            otherClientData.current_ballList.get(i).draw(g);
        }

        for (int i=0; i<otherClientData.fire_listXY.size(); i++){
            DrawFireBall(g, otherClientData.fire_listXY.get(i));
        }

    }

    class MML extends MouseMotionAdapter {
        public void mouseMoved(MouseEvent e) {
            if (MainPanel.IsSinglePlayer){
                if (e.getX() < getWidth() - r.width  && !MainPanel.IsPaused){
                    r.x = e.getX();
                }
            }
            else{
                if (e.getX() + r.width <= 635   && e.getX() < getWidth() - r.width  && !MainPanel.IsPaused)
                    r.x = e.getX();
            }
        }
    }

    // starting (x,y) to the balls
    public void DrawFireBall(Graphics g, Point fireXY){
        ImageIcon ii=new ImageIcon("bullets.gif");
        Image i = ii.getImage();
        g.drawImage(i, fireXY.x-15, fireXY.y + 10, 25, 25, null);
    }

    public void hideMouseCursor()
    {
        BufferedImage cursorImg= new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor =  Toolkit.getDefaultToolkit().createCustomCursor(cursorImg,new Point(0,0),"blank cursor");
        setCursor(blankCursor);
    }

}