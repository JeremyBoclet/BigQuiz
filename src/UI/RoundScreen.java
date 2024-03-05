package UI;

import Business.BusinessClass;

import javax.swing.*;

public class RoundScreen {
    public static void main(String[] args) {
    }

    public static void SetPlayers()
    {
       PlayerScreen.AddPlayers();
    }

    /* public static void ChangePlayer()
    {

    }*/

    public static JFrame SetRoundsThemes()
    {
        JFrame frame = BusinessClass.SetBackGroundPanel();

        JButton themeBen = BusinessClass.SetButtons(BusinessClass.AbsoluteFilePath.concat("/src/Assets/BEN.png"),
                10,10,600,200);

        frame.add(themeBen);

        JButton btnReturnToMain = BusinessClass.SetButtons(BusinessClass.AbsoluteFilePath.concat("/src/Assets/Round1.png"),
                1500,5,400,100);
        btnReturnToMain.addActionListener(e -> frame.setVisible(false));
        frame.add(btnReturnToMain);

        JButton btnPlayer = BusinessClass.SetButtons(BusinessClass.AbsoluteFilePath.concat("/src/Assets/Player.png"),
               650,50,600,150);
        btnPlayer.addActionListener(e -> PlayerScreen.getPlayerFrame().setVisible(true));
        frame.add(btnPlayer);

        frame.setVisible(false);
        return frame;
    }


}
