package com.officialsounding.statparse.utils;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

/**
 * Created by Peter on 3/13/2018.
 */
public class CRGXMLWriter implements Closeable {

    private OutputStream os;

    public CRGXMLWriter(OutputStream os) {
        this.os = os;
    }

    public void writeOutput(CrgExportFormat export) {
        try {
            JAXBContext context = JAXBContext.newInstance(CrgExportFormat.class);
            Marshaller marshaller = context.createMarshaller();
            XMLSerializer serializer = getXMLSerializer(os);
            marshaller.marshal(export, serializer.asContentHandler());
        }catch(JAXBException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        os.close();
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
