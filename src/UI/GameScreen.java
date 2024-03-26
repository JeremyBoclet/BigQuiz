package UI;

import Business.BusinessClass;
import Models.Questions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameScreen {
    private List<Questions> questions;
    private JFrame quizFrame = BusinessClass.SetBackGroundPanel();
    private List<Integer> questionIds;
    private JLabel questionLabel;
    private JLabel answerLabel;
    private JLabel lblPoint;
    private String currentTheme="";

    private JButton buttonPressed;

    private void SetQuestion(List<Questions> pValue){
        questions = pValue;
    }

    public static void main(String[] args) {
    }
     public void SetSelectedTheme(JButton pButtonPressed)
     {
         buttonPressed = pButtonPressed;
     }
    public void ShowScreen()
    {
        quizFrame = BusinessClass.SetBackGroundPanel();
        SetButtons();
        ChangeQuestion();
        quizFrame.setVisible(true);
    }

    public void GetQuestion(String pTheme) throws Exception {
        currentTheme = pTheme;

        questionIds = new ArrayList<>();

        SetQuestion(BusinessClass.GetQuestion(pTheme));

        for (int i=0; i<=questions.size()-1;i++)
            questionIds.add(i);

        //Permet un ordre aléatoire des questions
        Collections.shuffle(questionIds);

    }

    private void ChangeQuestion()
    {
        try
        {
            quizFrame.remove(questionLabel);
            quizFrame.remove(answerLabel);
        }
        catch(Exception ignored)  {}

        int nextQuestionID = GetNextQuestionId();
        //Question
        //Le html permet de passer sur plusieurs ligne si le texte est trop long + de centrer le texte
        questionLabel = new JLabel(("<html><body style='text-align: center'>".concat(questions.get(nextQuestionID).GetQuestion().concat("</html>")).replace("#","<br>")));
        questionLabel.setBounds(0,50,quizFrame.getWidth(),quizFrame.getHeight()/2);
        questionLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        questionLabel.setFont(new Font("Verdana",Font.PLAIN,60));
        questionLabel.setForeground(Color.WHITE);

        quizFrame.add(questionLabel);

        //Réponse
        answerLabel = new JLabel(questions.get(nextQuestionID).GetAnswer());
        answerLabel.setBounds(0,570,quizFrame.getWidth(),quizFrame.getHeight()/2);
        answerLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        answerLabel.setFont(new Font("Verdana",Font.PLAIN,70));
        answerLabel.setForeground(Color.YELLOW);

        quizFrame.add(answerLabel);

        quizFrame.revalidate();
        quizFrame.repaint();
    }

    private void SetButtons()
    {

        JButton imgTheme = BusinessClass.SetButtons(currentTheme.concat(".png"),quizFrame.getWidth()/2 - 300,10,600,150);
        quizFrame.add(imgTheme);

        JButton hideAnswer = BusinessClass.SetButtons("Hide_Answer.png",(quizFrame.getWidth() / 2 - 960),
                (int)(quizFrame.getHeight() / 1.45), 1910, 150);
        hideAnswer.addActionListener(e -> hideAnswer.setVisible(!hideAnswer.isVisible()));
        quizFrame.add(hideAnswer);

        JButton btnGoodAnswer = BusinessClass.SetButtons("Good_Answer.png",
                (int)((quizFrame.getWidth() - 400) / 1.3),
                quizFrame.getHeight() - 130,400,130);
        btnGoodAnswer.addActionListener(e ->
        {
            hideAnswer.setVisible(true);

            PlayerScreen.GetSelectedPlayer().AddPoint(1);
            lblPoint.setText("Point : ".concat(String.valueOf(PlayerScreen.GetSelectedPlayer().GetPlayerPoints())));

            if(questionIds.isEmpty())
            {
                buttonPressed.setVisible(false);
                quizFrame.dispose();
            }
            else
                ChangeQuestion();

        });
        quizFrame.add(btnGoodAnswer);

        JButton btnBadAnswer = BusinessClass.SetButtons("Bad_Answer.png",
                (quizFrame.getWidth() - 400) / 4,
                quizFrame.getHeight() - 130,400,130);
        btnBadAnswer.addActionListener(e ->
        {
            hideAnswer.setVisible(true);

            if(questionIds.isEmpty())
            {
                buttonPressed.setVisible(false);
                quizFrame.dispose();
            }
            else {
                ChangeQuestion();
            }
        });
        quizFrame.add(btnBadAnswer);

        JButton btnCancel = BusinessClass.SetButtons("Cancel.png",
                20,
                quizFrame.getHeight() - 65,200,65);
        btnCancel.addActionListener(e ->
                quizFrame.dispose());
        quizFrame.add(btnCancel);

        lblPoint = new JLabel("Point : ".concat(String.valueOf(PlayerScreen.GetSelectedPlayer().GetPlayerPoints())));

        lblPoint.setBounds(
                0,
                750,
                quizFrame.getWidth(),
                quizFrame.getHeight()/2

        );
        lblPoint.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        lblPoint.setFont(new Font("Verdana",Font.PLAIN,40));
        lblPoint.setForeground(Color.WHITE);
        quizFrame.add(lblPoint);


    }
    private int GetNextQuestionId()
    {
        //On randomise l'ordre des questions
        int randomIndex = (int) Math.floor(Math.random() * questionIds.size());
        int newQuestionId = (questionIds.get(randomIndex));
        questionIds.remove(randomIndex);
        return newQuestionId;
    }

}

