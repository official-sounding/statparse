package com.officialsounding.statparse.workers;

import com.officialsounding.statparse.models.Game;
import com.officialsounding.statparse.models.Skater;
import com.officialsounding.statparse.models.Team;
import com.officialsounding.statparse.models.intermediate.IGRF;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter on 4/21/2017.
 */
public class IGRFParser {

    public IGRF parse(Workbook wb) {
        Sheet igrf = wb.getSheet("IGRF");

        List<Skater> homeRoster = new ArrayList<>();
        List<Skater> awayRoster = new ArrayList<>();

        for(int rowIdx = 13; rowIdx < 34; rowIdx++) {
            Row row = igrf.getRow(rowIdx);

            if(!row.getCell(1).getStringCellValue().isEmpty()) {
                Skater home = new Skater(row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue());
                homeRoster.add(home);
            }

            if(!row.getCell(8).getStringCellValue().isEmpty()) {
                Skater away = new Skater(row.getCell(8).getStringCellValue(), row.getCell(9).getStringCellValue());
                awayRoster.add(away);
            }
        }

        Row league = igrf.getRow(9);
        Row teamNames = igrf.getRow(10);
        Row color = igrf.getRow(11);

        Team home = new Team(league.getCell(1).getStringCellValue(), teamNames.getCell(1).getStringCellValue(), color.getCell(1).getStringCellValue(), homeRoster);
        Team away = new Team(league.getCell(7).getStringCellValue(), teamNames.getCell(8).getStringCellValue(), color.getCell(8).getStringCellValue(), awayRoster);

        //TODO Read Game Info


        //TODO Read Final Scores (Since OS Offset could occur)

        //TODO Read Officials

        return new IGRF(home, away);
    }

}
