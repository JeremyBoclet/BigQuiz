package UI;

import Business.BusinessClass;

import javax.swing.*;

public class MainScreen {
    private static JFrame RoundFrame;
    private static JFrame MainFrame;

    public static void main(String[] args) {
        MainFrame = BusinessClass.SetBackGroundPanel();
        RoundFrame = RoundScreen.SetRoundsThemes();

        SetButtons();
        MainFrame.setVisible(true);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RoundScreen.SetPlayers();
    }

    //Ajoute les boutons de l'écran d'accueil
    private static void SetButtons()
    {

        JButton btnQuit = BusinessClass.SetButtons(BusinessClass.AbsoluteFilePath.concat("/src/Assets/Quit.png"),10,
                MainFrame.getHeight() - 60,200,60);
        btnQuit.addActionListener(e -> MainFrame.dispose());
        MainFrame.add(btnQuit);

        JButton btnFirstRound = BusinessClass.SetButtons(BusinessClass.AbsoluteFilePath.concat("/src/Assets/Round1.png"),
                MainFrame.getWidth() / 2 - 325,85,650,150);
        btnFirstRound.addActionListener(e -> ShowRound(RoundFrame));

        MainFrame.add(btnFirstRound);
    }

    //Affichage de l'écran souhaité
    private static void ShowRound(JFrame pFrame)
    {
        pFrame.setVisible(true);
        pFrame.toFront();
    }
}



