package com.officialsounding.statparse.utils;

import com.officialsounding.statparse.models.Skater;
import com.officialsounding.statparse.models.intermediate.IGRF;
import com.officialsounding.statparse.workers.IGRFParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Peter on 4/21/2017.
 */
public class IGRFToCRG {


    public static void main(String[] args) throws IOException, InvalidFormatException {

        IGRFParser parser = new IGRFParser();
        IGRFToCRG translator = new IGRFToCRG();
        CrgExportFormat export = new CrgExportFormat();

        List<File> input = new ArrayList<>();

        if(args.length == 0) {
            FileDialog openFileDialog = new FileDialog(new Frame(), "Open IGRF Excel Files for Import", FileDialog.LOAD);
            openFileDialog.setMultipleMode(true);
            openFileDialog.setFile("*.xlsx");
            openFileDialog.setVisible(true);
            for (File in : openFileDialog.getFiles()) {
                input.add(in);
            }
        } else {
            for (String filename : args) {
                if(filename.endsWith(".xlsx")) {
                    input.add(new File(filename));
                }
            }
        }


        if(input.size() > 0) {
            for (File file : input) {
                Workbook wb = WorkbookFactory.create(file);
                IGRF igrf = parser.parse(wb);
                export.getTeams().addAll(translator.generateFromIGRF(igrf));
            }

            FileDialog saveFileDialog = new FileDialog(new Frame(), "Save", FileDialog.SAVE);
            saveFileDialog.setFile("export.xml");
            saveFileDialog.setVisible(true);

            File output = new File(saveFileDialog.getDirectory(), saveFileDialog.getFile());

            if (!output.exists()) {
                output.createNewFile();
            }


            try (CRGXMLWriter writer = new CRGXMLWriter(new FileOutputStream(output))) {
                writer.writeOutput(export);
            }
        }

        System.exit(0);
    }

    public List<CrgExportTeam> generateFromIGRF(IGRF igrf) {

        List<CrgExportTeam> teams = new ArrayList<>();



        CrgExportTeam home = new CrgExportTeam();
        CrgExportTeam away = new CrgExportTeam();

        home.setName(igrf.getHome().getTeam());
        away.setName(igrf.getAway().getTeam());

        for(Skater sktr : igrf.getHome().getRoster()) {
            CrgExportSkater skater = new CrgExportSkater();
            skater.setName(sktr.getName());
            skater.setNumber(sktr.getNumber());

            home.getSkaters().add(skater);
        }

        for(Skater sktr : igrf.getAway().getRoster()) {
            CrgExportSkater skater = new CrgExportSkater();
            skater.setName(sktr.getName());
            skater.setNumber(sktr.getNumber());

            away.getSkaters().add(skater);
        }


        teams.add(home);
        teams.add(away);

        return teams;
    }
}
