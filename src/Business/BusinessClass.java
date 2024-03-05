package Business;

import Models.Players;
import Data.DataClass;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class BusinessClass {

    public static String AbsoluteFilePath = new File("").getAbsolutePath();

    public static JButton SetButtons(String pImagePath, int pPosX, int pPosY,int pWidth, int pHeight)
    {
        try
        {
            JButton myButton = new JButton();
            myButton.setBounds(pPosX,pPosY,pWidth,pHeight);
            ImageIcon icon = new ImageIcon(pImagePath);
            Image image = icon.getImage().getScaledInstance(pWidth, pHeight,  java.awt.Image.SCALE_SMOOTH);

            myButton.setOpaque(false);
            myButton.setContentAreaFilled(false);
            myButton.setBorderPainted(false);
            myButton.setFocusPainted(false);
            myButton.setIcon(new ImageIcon(image));

            return  myButton;
        }
        catch (Exception ex)
        {
            System.out.printf(ex.getMessage());
            throw ex;
        }
    }

    public static JFrame SetBackGroundPanel()
    {
        JFrame frame = new javax.swing.JFrame("Choose theme");

        ImageIcon icon = new ImageIcon(AbsoluteFilePath.concat("/src/Assets/Background.jpg"));
        JLabel background = new JLabel();
        background.setIcon(icon);

        frame.setContentPane(background);

        // plein écran
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.pack();
        frame.setResizable(false);
        return frame;
    }

    public static List<Players> GetPlayers()
    {
        return DataClass.GetPlayers();
    }

}

