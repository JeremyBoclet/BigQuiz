package UI;

import Business.BusinessClass;
import Models.Players;

import javax.swing.*;
import java.util.List;

public class PlayerScreen {
    private static JFrame PlayerFrame;
    private static Players SelectedPlayer;

    public static JFrame getPlayerFrame() {
        return PlayerFrame;
    }

    public static Players GetSelectedPlayer()
    {
        return SelectedPlayer;
    }

    //Ajout des joueurs à l'écran
    public static void AddPlayers()
    {
        PlayerFrame = BusinessClass.SetBackGroundPanel();
        List<Players> Players = BusinessClass.GetPlayers();
        int x = PlayerFrame.getWidth() / 2 - 610, y= 100;

        for (Players player:Players){
            JButton button = BusinessClass.SetButtons(String.format("Player/%s.png",player.GetPlayerName()),
                    x,y,600,150);

            button.addActionListener(e -> {
                SelectedPlayer = player;
                RoundScreen.ChangePlayer();
                PlayerFrame.setVisible(false);
            });

            y += 150;
            if (y >= 849)
            {
                x = PlayerFrame.getWidth() / 2 + 10;
                y = 100;
            }

            PlayerFrame.add(button);

        }
    }
}
