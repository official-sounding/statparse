package com.officialsounding.statparse.workers;

import com.officialsounding.statparse.models.*;
import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Peter on 8/23/2016.
 */
public class Reader {

    private final int HOME_TEAM = 1;
    private final int AWAY_TEAM = 26;

    private DataFormatter formatter = new DataFormatter(Locale.US);

    public Game readWorkbook(Workbook bk) {

        //parse lineup sheet


        //parse score sheet

        //parse penalty sheet

        //emit game

        return new Game(null, null);
    }

    public LineupResults parseLineups(Workbook bk) {
        FormulaEvaluator evaluator = bk.getCreationHelper().createFormulaEvaluator();
        Sheet lineups = bk.getSheet("Lineups");

        List<LineupEntry> homeJams = new ArrayList<>();
        List<LineupEntry> awayJams = new ArrayList<>();

        //first half is (zero-indexed) rows 3-40
        //second half is (zero-indexed) rows 45-83

        for(int rowIdx = 3; rowIdx < 83; rowIdx++) {

            if(rowIdx > 40 && rowIdx < 45) {
                continue;
            }

            int period = rowIdx < 40 ? 1 : 2;
            Row row = lineups.getRow(rowIdx);
            String jamNumber = formatter.formatCellValue(row.getCell(0), evaluator);


            //skip blank or starpass rows
            if(    jamNumber == ""
                || jamNumber == "SP"
                || jamNumber == "SP*") {
                continue;
            }

            List<Participant> homeParticipants = IntStream
                                .range(0,5)
                                .mapToObj(p -> buildParticipant(row, evaluator, HOME_TEAM, p))
                                .collect(Collectors.toList());

            List<Participant> awayParticipants = IntStream
                                .range(0,5)
                                .mapToObj(p -> buildParticipant(row, evaluator, AWAY_TEAM, p))
                                .collect(Collectors.toList());

            //check the following row for starpasses
            Cell homeSPCell = lineups.getRow(rowIdx+1).getCell(HOME_TEAM - 1);
            Cell awaySPCell = lineups.getRow(rowIdx+1).getCell(AWAY_TEAM - 1);
            boolean homeSP = false;
            boolean awaySP = false;

            if(homeSPCell != null && awaySPCell != null) {
                homeSP = formatter.formatCellValue(homeSPCell, evaluator) == "SP";
                awaySP = formatter.formatCellValue(awaySPCell, evaluator) == "SP";
            }

            homeJams.add(new LineupEntry(period, Integer.valueOf(jamNumber), homeParticipants, homeSP));
            awayJams.add(new LineupEntry(period, Integer.valueOf(jamNumber), awayParticipants, awaySP));
        }


        return new LineupResults(homeJams, awayJams);
    }

    private Participant buildParticipant(Row row, FormulaEvaluator eval, int team, int position) {
        int offset = team + (4 * position) + 1;

        //don't read roster numbers as a number, that way lies madness!
        String number = formatter.formatCellValue(row.getCell(offset), eval);
        PlayerType type;

        if(position == 0) {
            type = PlayerType.Jammer;
        }else if(position == 1 && formatter.formatCellValue(row.getCell(team)).toUpperCase() != "X") {
            type = PlayerType.Pivot;
        } else {
            type = PlayerType.Blocker;
        }


        List<BoxTrip> trips = parseBoxTrips(row, offset);

        return new Participant(number, type, trips);

    }

    private List<BoxTrip> parseBoxTrips(Row row, int offset) {
        List<BoxTrip> result = new ArrayList<>();

        for(int i = 1; i < 4; i++) {
            Cell trip = row.getCell(offset + i);

            if(trip.getCellType() == Cell.CELL_TYPE_BLANK) {
                continue;
            }

            result.add(BoxTrip.fromLineup(trip.getStringCellValue()));
        }

        return result;
    }
}
