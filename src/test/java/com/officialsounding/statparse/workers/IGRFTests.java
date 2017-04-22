package com.officialsounding.statparse.workers;

import com.officialsounding.statparse.models.Team;
import com.officialsounding.statparse.models.intermediate.IGRF;
import com.officialsounding.statparse.models.intermediate.LineupResults;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Peter on 4/21/2017.
 */
public class IGRFTests {

    private static ClassLoader loader;
    private IGRFParser reader;

    @BeforeClass
    public static void setup() {
        loader = Thread.currentThread().getContextClassLoader();
    }

    @Before
    public void beforeEach() {
        reader = new IGRFParser();
    }

    @Test
    public void basic_rosters_test() throws IOException {
        try (InputStream is = loader.getResourceAsStream("lineups/basic-test.xlsx")) {
            Workbook wb = new XSSFWorkbook(is);
            IGRF results = reader.parse(wb);

            Team home = results.getHome();
            Team away = results.getAway();

            assertEquals("League One", home.getLeague());
            assertEquals("", away.getLeague());

            assertEquals("Team A", home.getTeam());
            assertEquals("Team B", away.getTeam());

            assertEquals("Chartreuse", home.getColor());
            assertEquals("Burgundy", away.getColor());


            assertEquals(7, home.getRoster().size());
            assertEquals(8, away.getRoster().size());

            assertArrayEquals(new String[]{"010","13","25","37","49","51","73"}, home.getRoster().stream().map(s -> s.getNumber()).toArray());
            assertArrayEquals(new String[]{"A","B","C","D","E","F","G"}, home.getRoster().stream().map(s -> s.getName()).toArray());

            assertArrayEquals(new String[]{"003","24","36","48","50","62","74","86"}, away.getRoster().stream().map(s -> s.getNumber()).toArray());
            assertArrayEquals(new String[]{"AA","BB","CC","DD","EE","FF","GG","HH"}, away.getRoster().stream().map(s -> s.getName()).toArray());
        }
    }
}
