import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class BallTimerAdding {
    Toolkit toolkit;
    Timer timer;
    public BallTimerAdding(MainPanel panel) {
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new BallTimerAdding.RemindTask(panel),
                10,        //initial delay
                5 * 1000);  //subsequent rate
    }

    class RemindTask extends TimerTask {
        MainPanel GamePanel;
        int counter=1;

        public RemindTask(MainPanel panel){
            this.GamePanel = panel;
        }
        public void run() {
            if(MainPanel.IsPaused) {
                counter = 1;
                return;
            }
            Ball b1;
            int [] ballXY = make_random_number();
            if (counter%5 == 0){
                b1 = new Ball(ballXY[0], ballXY[1], 80,2,Random_easy_HP() ,program.mp);
            }
            else {
                if (counter%3 == 0) {
                    b1 = new Ball(ballXY[0], ballXY[1], 50, 1, Random_easy_HP() + 30, program.mp);
                }
                else {
                    b1 = new Ball(ballXY[0], ballXY[1], 50,1,Random_easy_HP() ,program.mp);
                }
            }
            GamePanel.ball_list.add(b1);
            b1.start();
            counter++;
        }

        public int Random_easy_HP()
        {
            Random random = new Random();
            int x = random.nextInt(21);
            return x + 10;
        }

        public int[] make_random_number()
        {
            int values[] = new int[2];
            int x;
            Random random = new Random();
            x = random.nextInt(2);
            if(program.f.getHeight() > 0)
            {
                if (x == 1) {
                    values[0] = 0;
                    x = random.nextInt(program.f.getHeight() / 4);
                    values[1] = x;
                } else {
                    x = random.nextInt(program.f.getHeight() / 4);
                    values[1] = x;
                    values[0] = program.f.getWidth();
                }
            }
            else
            {
                if (x == 1) {
                    values[0] = 0;
                    x = random.nextInt(500 / 4);
                    values[1] = x;
                } else {
                    x = random.nextInt(500 / 4);
                    values[1] = x;
                    values[0] = program.f.getWidth();
                }
            }
            return values;
        }
    }

}