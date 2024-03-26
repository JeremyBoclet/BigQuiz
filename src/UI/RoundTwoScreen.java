package UI;

import Business.BusinessClass;
import Models.Players;

import javax.swing.*;
import java.util.List;

public class RoundTwoScreen {
    private final int THEME_WIDTH = 500;
    private final int THEME_HEIGHT= 110;
    private final int MAX_THEME_2_COLUMNS = 14;
    private final int PLAYER_THEME_WIDTH = 600;
    private final int PLAYER_THEME_HEIGHT= 150;
    private final int INITIAL_POS_Y = 200;

    private JButton btnPlayer;
    private JFrame frame;


    public void SetPlayers(PlayerScreen playerScreen)
    {
        playerScreen.AddPlayers();
    }



    public void ChangePlayer()
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
        });

        frame.add(btnPlayer);
    }

    public JFrame SetRoundButtons()
    {
        frame = BusinessClass.SetBackGroundPanel();

        int posx = 350;
        int posy = INITIAL_POS_Y;
        int count = 0;

        for(Players player:PlayerScreen.GetAllPlayers())
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
                posy = INITIAL_POS_Y;
            }
        }

        JButton btnReturnToMain = BusinessClass.SetButtons("Round1.png",
                1500,5,400,100);
        btnReturnToMain.addActionListener(e -> {
            BusinessClass.SetPlayersPoints(PlayerScreen.GetAllPlayers());
            frame.dispose();
        });
        frame.add(btnReturnToMain);

        ChangePlayer();
        frame.setVisible(false);
        return frame;
    }

    public JFrame SetSecondRoundButtons() throws Exception {
        frame = BusinessClass.SetBackGroundPanel();
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
                nextStep += (int)Math.ceil((double) listSize / 3);;
            } else if (listSize <= MAX_THEME_2_COLUMNS && (count == nextStep)) {
                posy = INITIAL_POS_Y;
                posx += 600;
                nextStep += (int)Math.ceil((double) listSize / 2);;
            }
        }


        JButton btnReturnToMain2 = BusinessClass.SetButtons("Round2.png",
                1500,5,400,100);
        btnReturnToMain2.addActionListener(e -> {
            BusinessClass.SetPlayersPoints(PlayerScreen.GetAllPlayers());
            frame.setVisible(false);
        });
        frame.add(btnReturnToMain2);

        ChangePlayer();
        frame.setVisible(false);
        return frame;
    }
}
