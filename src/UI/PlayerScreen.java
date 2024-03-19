package UI;

import Business.BusinessClass;
import Models.Players;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerScreen {
    private static JFrame PlayerFrame;
    private static Players SelectedPlayer;

    private static List<Players> AllPlayers = new ArrayList<>();
    public static JFrame getPlayerFrame() {
        return PlayerFrame;
    }

    public static Players GetSelectedPlayer()
    {
        return SelectedPlayer;
    }

    public static List<Players> GetAllPlayers()
    {
        return AllPlayers;
    }

     //Ajout des joueurs à l'écran
    public static void AddPlayers()
    {
        PlayerFrame = BusinessClass.SetBackGroundPanel();
        AllPlayers = BusinessClass.GetPlayers();
        int x = PlayerFrame.getWidth() / 2 - 610, y= 100;

        for (Players player:AllPlayers){
            JButton button = BusinessClass.SetButtons(String.format("Player/%s.png",player.GetPlayerName()),
                    x,y,600,150);

            button.addActionListener(e -> {
                SelectedPlayer = player;
                RoundScreen.ChangePlayer();
                PlayerFrame.setVisible(false);
            });

            JLabel lbl = new JLabel("Point : ".concat(String.valueOf(player.GetPlayerPoints())));
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Verdana",Font.PLAIN,30));
            lbl.setBounds( x + (button.getWidth() /2) - 65,y+70,1000,100);
            y += 150;
            if (y >= 849)
            {
                x = PlayerFrame.getWidth() / 2 + 10;
                y = 100;
            }
            PlayerFrame.add(lbl);
            PlayerFrame.add(button);


        }
    }

    public static void ShowPoint(){
        int x = PlayerFrame.getWidth() / 2 - 200, y= 200;
        for(Players player:AllPlayers)
        {
            JLabel lblPts = new JLabel("Points : ".concat(String.valueOf(player.GetPlayerPoints())));
            lblPts.setBounds(x,y,1000,200);
            lblPts.setFont(new Font("Verdana",Font.PLAIN,40));
            lblPts.setForeground(Color.WHITE);

            y += 150;
            if (y >= 849)
            {
                x = PlayerFrame.getWidth() / 2 + 10;
                y = 100;
            }

            PlayerFrame.add(lblPts);
        }
    }

}
