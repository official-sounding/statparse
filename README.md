# statparse

A library/application for parsing WFTDA statsbooks. 

## Current Support

  * IGRF Parsing
  * Lineup Sheet Parsing
  * Utility to convert IGRF rosters to CRG XML
 
## How-to build and use the IGRF to CRG Tool

use the gradle build to build the jar + dependencies  

`gradle shadowJar`
 
then run the java command below from the directory you checked the code out to

`java -cp build\libs\StatParse-1.0-SNAPSHOT-all.jar com.officialsounding.statparse.utils.IGRFToCRG inputXLSXFilePath> <outputXMLFilePath>`

## License
Apache 2.0
