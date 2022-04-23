import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class Mp3Player {
    private final String filename;
    public Mp3Player(String filename) {
        this.filename = filename;
    }

    public void play() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
                        Player player = new Player(buffer);
                        player.play();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        });
        t1.start();
    }
}