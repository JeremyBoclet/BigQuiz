package UI;

import Business.BusinessClass;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.charset.MalformedInputException;
import java.util.concurrent.TimeUnit;

public class MainScreen {
    private static JFrame RoundFrame;
    private static JFrame MainFrame;

    private static RoundScreen roundScreen;
    public static void main(String[] args) {
        MainFrame = BusinessClass.SetBackGroundPanel();
        SetButtons();
        MainFrame.setVisible(true);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RoundScreen.SetPlayers();
        RoundFrame = RoundScreen.SetRoundsThemes();
    }

    //Ajoute les boutons de l'écran d'accueil
    private static void SetButtons()
    {

        JButton btnQuit = BusinessClass.SetButtons(BusinessClass.AbsoluteFilePath.concat("/src/Assets/Quit.png"),10,
                MainFrame.getHeight() - 60,200,60);
        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.dispose();
            }
        });
        MainFrame.add(btnQuit);

        JButton btnFirstRound = BusinessClass.SetButtons(BusinessClass.AbsoluteFilePath.concat("/src/Assets/Round1.png"),
                MainFrame.getWidth() / 2 - 325,85,650,150);
        btnFirstRound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowRound(RoundFrame);
            }
        });

        MainFrame.add(btnFirstRound);
    }

    //Affichage de l'écran souhaité
    private static void ShowRound(JFrame pFrame)
    {
        pFrame.setVisible(true);
        pFrame.toFront();
    }
}



