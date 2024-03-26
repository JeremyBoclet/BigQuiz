package UI;

import Business.BusinessClass;
import Models.Players;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class RoundScreen {
    private final int THEME_WIDTH = 500;
    private final int THEME_HEIGHT= 110;
    private final int MAX_THEME_2_COLUMNS = 14;
    private final int PLAYER_THEME_WIDTH = 600;
    private final int PLAYER_THEME_HEIGHT= 150;
    private final int INITIAL_POS_Y = 200;
    private JButton btnPlayer;
    private JLabel lblPlayerPts;
    private JFrame frame;

    public static void main(String[] args) {
        }

    public JFrame SetRoundButtons()
    {
        PlayerScreen playerScreen = new PlayerScreen();
        playerScreen.AddPlayers();

        frame = BusinessClass.SetBackGroundPanel();
        frame.addWindowFocusListener(new WindowFocusListener() {
                                             @Override
                                             public void windowGainedFocus(WindowEvent e) {
                                                 if (playerScreen.GetSelectedPlayer() != null)
                                                 {
                                                     for(Players player:playerScreen.GetAllPlayers())
                                                     {
                                                         if(Objects.equals(player.GetPlayerName(), playerScreen.GetSelectedPlayer().GetPlayerName()))
                                                         {
                                                             player.SetPoint(playerScreen.GetSelectedPlayer().GetPlayerPoints());
                                                             break;
                                                         }
                                                     }
                                                 }
                                                 //playerScreen.AddPlayers();

                                                 SetPlayer(frame,playerScreen);
                                                 frame.revalidate();
                                                 frame.repaint();
                                             }

                                             @Override
                                             public void windowLostFocus(WindowEvent e) {
                                             }});

        int posx = 350;
        int posy = INITIAL_POS_Y;
        int count = 0;

        for(Players player:playerScreen.GetAllPlayers())
        {
            count+=1;

            JButton theme = BusinessClass.SetButtons(player.GetPlayerName().toUpperCase().concat(".png"), posx, posy,
                    PLAYER_THEME_WIDTH,PLAYER_THEME_HEIGHT);
            theme.addActionListener(e -> {
                try {
                    if(player.GetPlayerName() == null) return;
                    GameScreen quizScreen = new GameScreen();
                    quizScreen.SetSelectedTheme(theme);
                    quizScreen.GetQuestion(player.GetPlayerName().toUpperCase());
                    quizScreen.setCurrentPlayer(playerScreen.GetSelectedPlayer());
                    quizScreen.ShowScreen();
                } catch (Exception ignored) {
                }
            });
            frame.add(theme);

            posy += 150;
            if (count == 5)
            {
                posx = 1000;
                posy = INITIAL_POS_Y;
            }
        }

        JButton btnReturnToMain = BusinessClass.SetButtons("Round1.png",
                1500,5,400,100);
        btnReturnToMain.addActionListener(e -> {
            BusinessClass.SetPlayersPoints(playerScreen.GetAllPlayers());
            frame.setVisible(false);
        });
        frame.add(btnReturnToMain);

        SetPlayer(frame,playerScreen);

        frame.setVisible(false);
        return frame;
    }

      public JFrame SetSecondRoundButtons() throws Exception {
        PlayerScreen playerScreen = new PlayerScreen();
        playerScreen.AddPlayers();

        frame = BusinessClass.SetBackGroundPanel();
        frame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                if (playerScreen.GetSelectedPlayer() != null)
                {
                    for(Players player:playerScreen.GetAllPlayers())
                    {
                        if(Objects.equals(player.GetPlayerName(), playerScreen.GetSelectedPlayer().GetPlayerName()))
                        {
                            player.SetPoint(playerScreen.GetSelectedPlayer().GetPlayerPoints());
                            break;
                        }
                    }
                }
                //playerScreen.AddPlayers();
                SetPlayer(frame,playerScreen);
                frame.revalidate();
                frame.repaint();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }});


        int posx;
        int posy = INITIAL_POS_Y;
        int count = 0;

        List<String> themes = BusinessClass.GetThemes("Round1");
        int listSize = themes.size();

        int nextStep;
        //S'il y a plus de 12 thèmes alors on passe sur 3 colonnes
        if(listSize > MAX_THEME_2_COLUMNS)
        {
            posx = 100;
            nextStep =(int)Math.ceil((double) listSize / 3);
        }
        else
        {
            posx = 350;
            nextStep =(int)Math.ceil((double) listSize / 2);
        }

        for(String theme:themes)
        {
            count++;

            JButton buttonTheme = BusinessClass.SetButtons(theme.concat(".png"), posx, posy,
                    THEME_WIDTH,THEME_HEIGHT);
            buttonTheme.addActionListener(e -> {
                try {
                    GameScreen quizScreen = new GameScreen();
                    quizScreen.SetSelectedTheme(buttonTheme);
                    quizScreen.GetQuestion(theme);
                    quizScreen.setCurrentPlayer(playerScreen.GetSelectedPlayer());
                    quizScreen.ShowScreen();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });

            frame.add(buttonTheme);

            posy += 110;

            if (listSize > MAX_THEME_2_COLUMNS && (count == nextStep))
            {
                posy = INITIAL_POS_Y;
                posx += 600;
                nextStep += (int)Math.ceil((double) listSize / 3);
            } else if (listSize <= MAX_THEME_2_COLUMNS && (count == nextStep)) {
                posy = INITIAL_POS_Y;
                posx += 600;
                nextStep += (int)Math.ceil((double) listSize / 2);
            }
        }


        JButton btnReturnToMain2 = BusinessClass.SetButtons("Round2.png",
                1500,5,400,100);
        btnReturnToMain2.addActionListener(e -> {
            BusinessClass.SetPlayersPoints(playerScreen.GetAllPlayers());
            frame.setVisible(false);
        });
        frame.add(btnReturnToMain2);
        SetPlayer(frame,playerScreen);

        frame.setVisible(false);
        return frame;
    }

    private void SetPlayer(JFrame pFrame, PlayerScreen pPlayerScreen)
    {
        try
        {
            //On supprime l'ancien joueur de l'écran pour ajouter celui sélectionné
            pFrame.remove(btnPlayer);
            pFrame.remove(lblPlayerPts);
            pFrame.revalidate();
            pFrame.repaint();
        }
        catch(Exception ex)
        {
            //Le bouton n'existe pas (possible lors de la 1ere itération)
        }

        btnPlayer = new JButton();
        lblPlayerPts = new JLabel();

        Players player = pPlayerScreen.GetSelectedPlayer();
        String PlayerName = "Player";
        int PlayerPoint = 0;

        if(player != null) {
            PlayerName = player.GetPlayerName();
            PlayerPoint = player.GetPlayerPoints();
        }

        lblPlayerPts.setText("Points : "+ PlayerPoint);
        lblPlayerPts.setForeground(Color.WHITE);
        lblPlayerPts.setFont(new Font("Verdana",Font.PLAIN,40));
        lblPlayerPts.setBounds(840, 0,800,60);

        pFrame.add(lblPlayerPts);

        btnPlayer = BusinessClass.SetButtons(String.format("Player/%s.png",PlayerName),
                650,50,600,150);

        btnPlayer.addActionListener(e -> {
            pPlayerScreen.UpdatePoints();
            pPlayerScreen.getPlayerFrame().setVisible(true);
        });

        pFrame.add(btnPlayer);
    }

}
