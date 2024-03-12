package Models;

import java.lang.reflect.Type;

public class Questions {
    private String Question;
    private String Answer;
    private String ExternalName;
    private String TypeQuestion;

    public static void main(String[] args) {

    }

    public String GetQuestion()
    {
        return Question;
    }

    public String GetAnswer(){return Answer;}

    public void SetQuestion(String pValue)
    {
        Question=pValue;
    }
    public void SetAnswer(String pValue)
    {
        Answer=pValue;
    }
    public void SetExternalName(String pValue)
    {
        ExternalName=pValue;

    }
    public void SetTypeQuestion(String pValue)
    {
        TypeQuestion=pValue;
    }
}
