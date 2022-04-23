import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ScoreTimer{
    MainPanel mainPanel;
    public ScoreTimer(MainPanel panel) {
        this.mainPanel = panel;
    }
    ActionListener changeScore = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            ChangeGameScore changeScore = new ChangeGameScore(1, mainPanel);
            changeScore.start();
        }
    };
}
