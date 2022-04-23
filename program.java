import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class program {
    public static JFrame f = new JFrame("Ball Blast");
    public static MainPanel mp;
    public static Socket connectionToServer;
    public static OutputStream outputStream;
    public static InputStream inputStream;

    public static void main(String[] args) throws IOException {
        StartMenu menu = new StartMenu();
    }
}