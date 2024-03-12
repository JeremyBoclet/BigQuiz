package UI;

import Business.BusinessClass;
import Data.DataClass;
import Models.Questions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GameScreen {
    private List<Questions> questions;
    private JFrame quizFrame = BusinessClass.SetBackGroundPanel();
    private List<Integer> questionIds;

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
        questionIds = new ArrayList<>();
        int i=0;

        SetQuestion(DataClass.GetQuestion(pTheme));

        for (Questions question:questions)
        {
            questionIds.add(i);
            i+=1;
        }
        //Permet un ordre aléatoire des questions
        Collections.shuffle(questionIds);

    }

    private void ChangeQuestion()
    {
        JLabel questionLabel = new JLabel(questions.get(GetNextQuestionId()).GetQuestion(),SwingConstants.CENTER);
        questionLabel.setFont(new Font("Verdana",Font.PLAIN,24));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setBounds(0,0,2000,quizFrame.getHeight()/2);
        quizFrame.add(questionLabel);
        quizFrame.revalidate();
    }

    private void SetButtons()
    {

        JButton btnGoodAnswer = BusinessClass.SetButtons("Good_Answer.png",
                (int)((quizFrame.getWidth() - 400) / 1.3),
                quizFrame.getHeight() - 130,400,130);
        btnGoodAnswer.addActionListener(e ->
        {
            if(questionIds.isEmpty())
                quizFrame.dispose();
            else {
                //PlayerScreen.GetSelectedPlayer().SetPoint(1);
                ChangeQuestion();
            }
        });
        quizFrame.add(btnGoodAnswer);

    }
    private int GetNextQuestionId()
    {
        int randomIndex = (int) Math.floor(Math.random() * questionIds.size());
        int newQuestionId = (questionIds.get(randomIndex));
        questionIds.remove(randomIndex);
        return newQuestionId;
    }

}

