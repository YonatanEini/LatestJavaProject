import com.google.gson.Gson;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

 class SendingDataToSever{
    public SendingDataToSever() {}
    ActionListener changeScore = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            Gson gson = new Gson();
            List<Point> fireDto = CreteFireSendList(program.mp.fire_list);
            DataDto data = new DataDto(MainPanel.IsPaused, new Point(MainPanel.r.x, MainPanel.r.y), fireDto, MainPanel.IsGameOver, program.mp.score, MainPanel.IsNewGameRequest);
            String jsonStr = gson.toJson(data);
            try {
                ObjectOutputStream objectTransfer = new ObjectOutputStream(program.outputStream);
                objectTransfer.writeObject(jsonStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // converting Fire List to Dto (XY Points)
        public List<Point> CreteFireSendList(List<Fire> fires)
        {
            List<Point> fireList = new ArrayList<>();
            for (int i=0; i<fires.size();i++)
            {
                Point currentFirePosition = new Point(fires.get(i).x, fires.get(i).y);
                fireList.add(currentFirePosition);
            }
            return fireList;
        }
    };
}