package Models;

import javax.swing.*;

public class Players {
    private String Name;
    private int Points;
    private String Category;

    public Players(String pName, int pPoint)
    {
            Name = pName;
            Points = pPoint;
    }

    public Players()
    {
        Name = "";
        Points = 0;
        Category = "";
    }

    public void SetPoint(int pPoint)
    {
        Points = pPoint;
    }
    public void SetName(String pName)
    {
        Name = pName;
    }
    public void SetCategory(String pCategory)
    {
        Category = pCategory;
    }
    public String GetPlayerName()
    {
        return Name;
    }


}
