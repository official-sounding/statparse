package com.officialsounding.statparse.workers;

import com.officialsounding.statparse.models.intermediate.LineupEntry;
import com.officialsounding.statparse.models.intermediate.LineupResults;
import com.officialsounding.statparse.models.Skater;
import com.officialsounding.statparse.models.PlayerType;
import com.officialsounding.statparse.models.intermediate.Participant;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by Peter on 8/27/2016.
 */

public class LineupTests {

    private static ClassLoader loader;
    private Reader reader;

    @BeforeClass
    public static void setup() {
        loader = Thread.currentThread().getContextClassLoader();
    }

    @Before
    public void beforeEach() {
        reader = new Reader();
    }

    @Test
    public void basic_lineup_test() throws IOException{
        try(InputStream is = loader.getResourceAsStream("lineups/basic-test.xlsx")) {
            Workbook wb = new XSSFWorkbook(is);
            LineupResults results = reader.parseLineups(wb);

            List<LineupEntry> home = results.getHome();
            List<LineupEntry> away = results.getAway();

            assertNotNull(home);
            assertNotNull(away);

            assertEquals(7, home.size());
            assertEquals(7, away.size());

            LineupEntry homeJam1 = home.get(0);
            List<Participant> hp = homeJam1.getParticipants();

            assertEquals(5, hp.size());



            assertEquals("010", hp.get(0).getNumber());
            assertEquals(PlayerType.Jammer, hp.get(0).getType());
            assertEquals(0, hp.get(0).getTrips().size());
            assertEquals("37", hp.get(1).getNumber());
            assertEquals(PlayerType.Pivot, hp.get(1).getType());
            assertEquals("51", hp.get(2).getNumber());
            assertEquals(PlayerType.Blocker, hp.get(2).getType());
            assertEquals("25", hp.get(3).getNumber());
            assertEquals(PlayerType.Blocker, hp.get(3).getType());
            assertEquals("49", hp.get(4).getNumber());
            assertEquals(PlayerType.Blocker, hp.get(4).getType());


        }
    }

    @Test
    public void one_team_starpass_test(){

    }

    @Test
    public void both_team_starpass_test(){

    }

    @Test
    public void missing_players_test(){

    }

    @Test
    public void box_trips_test() {

    }


}
