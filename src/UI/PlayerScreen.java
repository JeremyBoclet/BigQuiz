package UI;

import Business.BusinessClass;
import Models.Players;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                PlayerFrame.dispose();
            });

            JLabel lbl = new JLabel("Point : ".concat(String.valueOf(player.GetPlayerPoints())));
            lbl.setName("lbl" + player.GetPlayerName());
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

    /// Mise à jour de l'affichage des points
    public static void UpdatePoints()
    {
        for (Players player:AllPlayers) {
            for(Component component: PlayerFrame.getContentPane().getComponents())
            {
                //Pour chaque joueur on met a jour son label point
                if(component instanceof JLabel)
                {
                    if (Objects.equals(component.getName(), "lbl" + player.GetPlayerName()))
                    {
                        ((JLabel) component).setText("Points : " + player.GetPlayerPoints());
                        break;
                    }
                }

            }
        }
    }

}
