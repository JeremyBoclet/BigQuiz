package UI;

import Business.BusinessClass;
import Data.DataClass;
import Models.Players;
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
    private String currentTheme="";

    private void SetQuestion(List<Questions> pValue){
        questions = pValue;
    }

    public static void main(String[] args) {
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

        SetQuestion(DataClass.GetQuestion(pTheme));

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
        questionLabel.setFont(new Font("Verdana",Font.PLAIN,70));
        questionLabel.setForeground(Color.WHITE);

        quizFrame.add(questionLabel);

        //Réponse
        answerLabel = new JLabel(questions.get(nextQuestionID).GetAnswer());
        answerLabel.setBounds(0,570,quizFrame.getWidth(),quizFrame.getHeight()/2);
        answerLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        answerLabel.setFont(new Font("Verdana",Font.PLAIN,70));
        answerLabel.setForeground(Color.WHITE);

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

            if(questionIds.isEmpty())
                quizFrame.dispose();
            else {
                PlayerScreen.GetSelectedPlayer().AddPoint(1);
                System.out.println(Integer.toString(PlayerScreen.GetSelectedPlayer().GetPlayerPoints()));
                ChangeQuestion();
            }
        });
        quizFrame.add(btnGoodAnswer);

        JButton btnBadAnswer = BusinessClass.SetButtons("Bad_Answer.png",
                (quizFrame.getWidth() - 400) / 4,
                quizFrame.getHeight() - 130,400,130);
        btnBadAnswer.addActionListener(e ->
        {
            hideAnswer.setVisible(true);

            if(questionIds.isEmpty())
                quizFrame.dispose();
            else {
                ChangeQuestion();
            }
        });
        quizFrame.add(btnBadAnswer);


    }
    private int GetNextQuestionId()
    {
        int randomIndex = (int) Math.floor(Math.random() * questionIds.size());
        int newQuestionId = (questionIds.get(randomIndex));
        questionIds.remove(randomIndex);
        return newQuestionId;
    }

}

