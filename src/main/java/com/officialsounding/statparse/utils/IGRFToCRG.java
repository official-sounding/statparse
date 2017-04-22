package com.officialsounding.statparse.utils;

import com.officialsounding.statparse.models.Skater;
import com.officialsounding.statparse.models.intermediate.IGRF;
import com.officialsounding.statparse.workers.IGRFParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Peter on 4/21/2017.
 */
public class IGRFToCRG {


    public static void main(String[] args) throws IOException, InvalidFormatException {
        String inputFile = args[0];
        String outputFile = args[1];

        Workbook wb = WorkbookFactory.create(new File(inputFile));
        IGRFParser parser = new IGRFParser();
        IGRFToCRG exporter = new IGRFToCRG();

        File output = new File(outputFile);

        if(!output.exists()) {
            output.createNewFile();
        }

        IGRF igrf = parser.parse(wb);


        CrgExportFormat export = exporter.generateFromIGRF(igrf);
        exporter.writeOutput(export, output);
    }

    public CrgExportFormat generateFromIGRF(IGRF igrf) {

        CrgExportFormat format = new CrgExportFormat();



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


        format.getTeams().add(home);
        format.getTeams().add(away);

        return format;
    }

    public void writeOutput(CrgExportFormat export, File file) {
        try(OutputStream os = new FileOutputStream(file)) {
            JAXBContext context = JAXBContext.newInstance(CrgExportFormat.class);
            Marshaller marshaller = context.createMarshaller();

           // marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


            XMLSerializer serializer = getXMLSerializer(os);


            marshaller.marshal(export, serializer.asContentHandler());
        }catch(JAXBException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private XMLSerializer getXMLSerializer(OutputStream out) throws FileNotFoundException {
        // configure an OutputFormat to handle CDATA
        OutputFormat of = new OutputFormat();

        // specify which of your elements you want to be handled as CDATA.
        // The use of the '^' between the namespaceURI and the localname
        // seems to be an implementation detail of the xerces code.
        // When processing xml that doesn't use namespaces, simply omit the
        // namespace prefix as shown in the third CDataElement below.
        of.setCDataElements(
                new String[] {
                        "^Number",   //
                        "^Name" });   //

        // set any other options you'd like
        //of.setPreserveSpace(true);
        //of.setIndenting(true);

        // create the serializer
        XMLSerializer serializer = new XMLSerializer(of);
        serializer.setOutputByteStream(out);

        return serializer;
    }

}
