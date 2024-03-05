package UI;

import Business.BusinessClass;
import Models.Players;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerScreen {
    private static JFrame PlayerFrame;
    public static List<Players> p;

    public static List<Players> getPlayers()
    {
        return p;
    }
    public static void main(String[] args) {
    }

    public static JFrame getPlayerFrame() {
        return PlayerFrame;
    }

    public static void AddPlayers()
    {
        PlayerFrame = BusinessClass.SetBackGroundPanel();


        List<Players> Players = BusinessClass.GetPlayers();
        p = Players;
        int x = PlayerFrame.getWidth() / 2 - 610,y=200;

        for (Players player:Players){
            JButton button = BusinessClass.SetButtons(String.format(String.format(BusinessClass.AbsoluteFilePath.concat("/src/Assets/Player/%s.png"),player.GetPlayerName())),x,y,600,150);


            y += 150;
            if (y >= 901)
            {
                x = PlayerFrame.getWidth() / 2 + 10;
                y = 200;
            }

            PlayerFrame.add(button);

        }
    }
}
