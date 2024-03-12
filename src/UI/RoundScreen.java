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

        btnPlayer.addActionListener(e -> PlayerScreen.getPlayerFrame().setVisible(true));

        frame.add(btnPlayer);
    }

    public static JFrame SetFirstRoundButtons()
    {
        frame = BusinessClass.SetBackGroundPanel();

        JButton themeBen = BusinessClass.SetButtons("BEN.png",350,200,600,150);
        themeBen.addActionListener(e -> {
            try {
                GameScreen quizScreen = new GameScreen();
                quizScreen.GetQuestion("BEN");
                quizScreen.ShowScreen();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        frame.add(themeBen);
        JButton themeMartin = BusinessClass.SetButtons("MARTIN.png",350,350,600,150);
        frame.add(themeMartin);
        JButton themeMathis = BusinessClass.SetButtons("MATHIS.png",350,500,600,150);
        frame.add(themeMathis);
        JButton themeRobin = BusinessClass.SetButtons("ROBIN.png",350,650,600,150);
        frame.add(themeRobin);
        JButton themePTF = BusinessClass.SetButtons("PTF.png",350,800,600,150);
        frame.add(themePTF);

        JButton btnReturnToMain = BusinessClass.SetButtons("Round1.png",
                1500,5,400,100);
        btnReturnToMain.addActionListener(e -> frame.setVisible(false));
        frame.add(btnReturnToMain);

        ChangePlayer();
        frame.setVisible(false);
        return frame;
    }
}
