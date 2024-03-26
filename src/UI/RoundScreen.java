package UI;

import Business.BusinessClass;
import Models.Players;

import javax.swing.*;

public class RoundScreen {
    private static JButton btnPlayer;
    private static JFrame frame;

    public static void SetPlayers()
    {
       PlayerScreen.AddPlayers();
    }

     public static void ChangePlayer()
    {
        try
        {
            //On supprime l'ancien joueur de l'écran pour ajouter celui sélectionné
            frame.remove(btnPlayer);
            frame.revalidate();
            frame.repaint();
        }
        catch(Exception ex)
        {
            //Le bouton n'existe pas (possible lors de la 1ere itération)
        }

        btnPlayer = new JButton();
        Players player = PlayerScreen.GetSelectedPlayer();
        String PlayerName = "Player";

        if(player != null)
            PlayerName = player.GetPlayerName();

        btnPlayer = BusinessClass.SetButtons(String.format("Player/%s.png",PlayerName),
                650,50,600,150);

        btnPlayer.addActionListener(e -> {
            PlayerScreen.UpdatePoints();
            PlayerScreen.getPlayerFrame().setVisible(true);
            //PlayerScreen.ShowPoint();

        });

        frame.add(btnPlayer);
    }

    public static JFrame SetFirstRoundButtons()
    {
        frame = BusinessClass.SetBackGroundPanel();

        int posx = 350;
        int posy = 200;
        int count = 0;

        for(Players player:PlayerScreen.GetAllPlayers())
        {
            count+=1;

            JButton theme = BusinessClass.SetButtons(player.GetPlayerName().toUpperCase().concat(".png"), posx, posy,
                    600,150);
            theme.addActionListener(e -> {
                try {
                    GameScreen quizScreen = new GameScreen();
                    quizScreen.SetSelectedTheme(theme);
                    quizScreen.GetQuestion(player.GetPlayerName().toUpperCase());
                    quizScreen.ShowScreen();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            frame.add(theme);

            posy += 150;
            if (count == 5)
            {
                posx = 1000;
                posy = 200;
            }
        }

        JButton btnReturnToMain = BusinessClass.SetButtons("Round1.png",
                1500,5,400,100);
        btnReturnToMain.addActionListener(e -> {
            BusinessClass.SetPlayersPoints(PlayerScreen.GetAllPlayers());
            frame.setVisible(false);
        });
        frame.add(btnReturnToMain);

        ChangePlayer();
        frame.setVisible(false);
        return frame;
    }
}
