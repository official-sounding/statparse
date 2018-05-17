package com.officialsounding.statparse.utils;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.*;
import java.io.*;

/**
 * Created by Peter on 3/13/2018.
 */
public class MothershipToCRG {
    public static void main(String[] args) throws IOException {

        CrgExportFormat export = new CrgExportFormat();
        MothershipToCRG translator = new MothershipToCRG();

        List<File> input = new ArrayList<>();

        if(args.length == 0) {
            FileDialog openFileDialog = new FileDialog(new Frame(), "Open Mothership CSV Files", FileDialog.LOAD);
            openFileDialog.setMultipleMode(true);
            openFileDialog.setFile("*.csv");
            openFileDialog.setVisible(true);
            for (File in : openFileDialog.getFiles()) {
                input.add(in);
            }
        } else {
            for (String filename : args) {
                input.add(new File(filename));
            }
        }

        if(input.size() > 0) {
            for (File csv : input) {
                CrgExportTeam team = translator.teamFromCSV(csv);
                export.getTeams().add(team);
            }


            FileDialog saveFileDialog = new FileDialog(new Frame(), "Save CRG Export", FileDialog.SAVE);
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

    public CrgExportTeam teamFromCSV(File csvFile) {
        CrgExportTeam team = new CrgExportTeam();

        try(Reader in = new FileReader(csvFile)) {
            List<CSVRecord> records = IteratorUtils.toList(CSVFormat.RFC4180.parse(in).iterator());

            // format
            // row 0-9: key-value team-level information
            // row 10: blank
            // row 12: skater info header
            // row 13-[n-1]: skater info data
            // row n: blank

            team.setName(records.get(0).get(1));


            for(int i = 12; i < records.size(); i++) {
                CrgExportSkater skater = new CrgExportSkater();
                skater.setName(records.get(i).get(1));
                skater.setNumber(records.get(i).get(0));
                team.getSkaters().add(skater);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return team;
    }
}
