import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;

 class ReceiveDataToSever{
    public ReceiveDataToSever() {}
    ActionListener secondClientData = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            if (!MainPanel.IsGameOver) {
                try {
                    ObjectInputStream objectTransfer = new ObjectInputStream(program.inputStream);
                    Type receiveDataDtoType = new TypeToken<RecceiveDataDto>() {
                    }.getType();
                    RecceiveDataDto outputContent = new Gson().fromJson((String) objectTransfer.readObject(), receiveDataDtoType);

                    // New Game Request
                    if (MainPanel.IsNewGameRequest){
                        if (outputContent.NewGameRequest){
                            MainPanel.IsNewGameRequest = false;
                            MainPanel.IsPaused = false;
                            System.out.println(outputContent.IsPaused + " " + outputContent.current_ballList + " " + outputContent.cannonPos);
                            program.mp.otherClientData = outputContent;
                            program.mp.repaint();
                        }
                    }

                    // Handle Regular Data
                    else {
                        if (outputContent.IsPaused) {
                            MainPanel.IsPaused = true;
                        }
                        if (outputContent.IsGameOver && !MainPanel.IsGameOver) {
                            MainPanel.IsGameOver = true;
                            EndGameMenu endGame = new EndGameMenu(program.mp.score, program.mp);
                        }
                        program.mp.otherClientData = outputContent;
                        program.mp.repaint();
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    };
}