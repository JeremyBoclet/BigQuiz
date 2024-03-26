package UI;

import Business.BusinessClass;

import javax.swing.*;

public class MainScreen {
    private static JFrame MainFrame;

    public static void main(String[] args) throws Exception {
        MainFrame = BusinessClass.SetBackGroundPanel();

        SetButtons();
        MainFrame.setVisible(true);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Ajoute les boutons de l'écran d'accueil
    private static void SetButtons() throws Exception {
        RoundScreen roundOneScreen = new RoundScreen();
        JFrame RoundOneFrame = roundOneScreen.SetRoundButtons();

        RoundScreen roundScreen = new RoundScreen();
        JFrame RoundTwoFrame = roundScreen.SetSecondRoundButtons();

        JButton btnQuit = BusinessClass.SetButtons("Quit.png",10,
                MainFrame.getHeight() - 60,200,60);
        btnQuit.addActionListener(e -> System.exit(0));
        MainFrame.add(btnQuit);

        JButton btnFirstRound = BusinessClass.SetButtons("Round1.png",
                MainFrame.getWidth() / 2 - 325,85,650,150);
        btnFirstRound.addActionListener(e -> ShowRound(RoundOneFrame));
        MainFrame.add(btnFirstRound);

        JButton btnSecondRound = BusinessClass.SetButtons("Round2.png",
                MainFrame.getWidth() / 2 - 325,300,650,150);
        btnSecondRound.addActionListener(e -> ShowRound(RoundTwoFrame));
        MainFrame.add(btnSecondRound);


    }

    //Affichage de l'écran souhaité
    private static void ShowRound(JFrame pFrame)
    {
        pFrame.setVisible(true);
        pFrame.toFront();
    }
}



