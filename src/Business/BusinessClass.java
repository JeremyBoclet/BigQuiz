package Business;

import Models.Players;
import Data.DataClass;
import Models.Questions;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class BusinessClass {

    public static String AbsoluteFilePath = new File("").getAbsolutePath().concat("/src/Assets/");

    public static JButton SetButtons(String pImagePath, int pPosX, int pPosY, int pWidth, int pHeight) {
        try {
            JButton myButton = new JButton();
            myButton.setBounds(pPosX, pPosY, pWidth, pHeight);
            ImageIcon icon = new ImageIcon(AbsoluteFilePath.concat(pImagePath));
            Image image = icon.getImage().getScaledInstance(pWidth, pHeight, java.awt.Image.SCALE_SMOOTH);

            myButton.setOpaque(false);
            myButton.setContentAreaFilled(false);
            myButton.setBorderPainted(false);
            myButton.setFocusPainted(false);
            myButton.setIcon(new ImageIcon(image));

            return myButton;
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            throw ex;
        }
    }

    public static JFrame SetBackGroundPanel() {
        JFrame frame = new javax.swing.JFrame("Choose theme");

        ImageIcon icon = new ImageIcon(AbsoluteFilePath.concat("Background.jpg"));
        JLabel background = new JLabel();
        background.setIcon(icon);
        frame.setLayout(null);
        frame.setContentPane(background);

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] screens = ge.getScreenDevices();
            frame.setLocation(screens[1].getDefaultConfiguration().getBounds().getLocation());
        } catch (Exception ignored) {
        }


        // plein écran
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.pack();
        frame.setResizable(false);
        return frame;
    }

    public static List<Players> GetPlayers() {
        return DataClass.GetPlayers();
    }

    public static void SetPlayersPoints(List<Players> players) {
        DataClass.SetPlayersPoints(players);
    }

    public static List<Questions> GetQuestion(String pTheme) throws Exception {
        return DataClass.GetQuestion(pTheme);
    }

    public static List<String> GetThemes(String pRound) throws Exception {
        return DataClass.GetTheme(pRound);
    }

}

