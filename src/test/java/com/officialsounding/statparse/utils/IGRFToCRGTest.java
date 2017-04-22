package com.officialsounding.statparse.utils;

import com.officialsounding.statparse.models.intermediate.IGRF;
import com.officialsounding.statparse.workers.IGRFParser;
import com.officialsounding.statparse.workers.Reader;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Peter on 4/21/2017.
 */
public class IGRFToCRGTest {

    private static ClassLoader loader;

    @BeforeClass
    public static void setup() {
        loader = Thread.currentThread().getContextClassLoader();
    }


    //@Test
    public void test_output() throws IOException {
        try(InputStream is = loader.getResourceAsStream("lineups/basic-test.xlsx")) {
            Workbook wb = new XSSFWorkbook(is);


            IGRFParser parser = new IGRFParser();
            IGRFToCRG exporter = new IGRFToCRG();

            File output = new File("C:/temp/test.xml");

            if(!output.exists()) {
                output.createNewFile();
            }

            IGRF igrf = parser.parse(wb);


            CrgExportFormat export = exporter.generateFromIGRF(igrf);
            exporter.writeOutput(export, output);
        }

    }
}
