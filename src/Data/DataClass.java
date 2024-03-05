package Data;

import Models.Players;
import Models.Questions;
import com.aspose.cells.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataClass {
    public static void main(String[] args) {
        GetPlayers();
    }

    //Récupère la liste des joueurs
    public static List<Players> GetPlayers()
    {
        try {
            List<Players> Players = new ArrayList<>();
            Workbook book = new Workbook(new File("").getAbsolutePath().concat("/src/Players.ods"));
            Worksheet sheet = book.getWorksheets().get("Players");
            Cells cells = sheet.getCells();

            for (int rowIndex = 1; rowIndex <=  cells.getMaxDataRow(); rowIndex++) {
                Players player = new Players();
                player.SetName(cells.get(rowIndex, 0).getStringValue());
                player.SetCategory(cells.get(rowIndex, 1).getStringValue());
                player.SetPoint(Integer.parseInt(cells.get(rowIndex, 2).getStringValue()));

                Players.add(player);
            }

            return Players;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Récupère la feuille excel contenant les questions du thème passé en paramètre
    public static List<Questions> GetQuestion(String pTheme) throws Exception {
        List<Questions> Questions = new ArrayList<>();

        Workbook book = new Workbook(new File("").getAbsolutePath().concat("/src/Questions.ods"));
        Worksheet sheet = book.getWorksheets().get(pTheme);
        Cells cells = sheet.getCells();

        for (int rowIndex = 1; rowIndex <=  cells.getMaxDataRow(); rowIndex++) {
            Questions question = new Questions();
            question.SetQuestion(cells.get(rowIndex, 0).getStringValue());
            question.SetAnswer(cells.get(rowIndex, 1).getStringValue());
            question.SetExternalName(cells.get(rowIndex, 2).getStringValue());
            question.SetTypeQuestion(cells.get(rowIndex, 3).getStringValue());

            Questions.add(question);
        }
        return Questions;
    }

}

