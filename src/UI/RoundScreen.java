package UI;

import Business.BusinessClass;
import Models.Players;

import javax.swing.*;

public class RoundScreen {
    private static JButton btnPlayer;
    private static JFrame frame;
    public static void main(String[] args) {
    }

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

        btnPlayer = BusinessClass.SetButtons(String.format(BusinessClass.AbsoluteFilePath.concat("/src/Assets/Player/%s.png"),PlayerName),
                650,50,600,150);

        btnPlayer.addActionListener(e -> PlayerScreen.getPlayerFrame().setVisible(true));

        frame.add(btnPlayer);
    }

    public static JFrame SetRoundsThemes()
    {
        frame = BusinessClass.SetBackGroundPanel();

        JButton themeBen = BusinessClass.SetButtons(BusinessClass.AbsoluteFilePath.concat("/src/Assets/BEN.png"),
                10,10,600,200);

        frame.add(themeBen);

        JButton btnReturnToMain = BusinessClass.SetButtons(BusinessClass.AbsoluteFilePath.concat("/src/Assets/Round1.png"),
                1500,5,400,100);
        btnReturnToMain.addActionListener(e -> frame.setVisible(false));
        frame.add(btnReturnToMain);

        ChangePlayer();
        frame.setVisible(false);
        return frame;
    }
}
