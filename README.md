# statparse

A library/application for parsing WFTDA statsbooks. 

## Current Support

  * IGRF Parsing
  * Lineup Sheet Parsing
  * Utility to convert IGRF rosters to CRG XML
  * Utility to convert Mothership CSV output to CRG XML

## Running the "to CRG" Utilities

the IGRF Utility is the default.  You can run it by double clicking on the jar file in the release, or (on Windows) by using the supplied batch file.  The batch file will also allow you to drag files onto the program to create the crg xml.

For the mothership utility, you will need to use the supplied batch file (again, on windows), or run the command below:

`javaw -cp %~dp0statparse.jar com.officialsounding.statparse.utils.MothershipToCRG`

## How-to build and use the IGRF to CRG Tool

use the following gradle command to build the jar + dependencies

`gradle shadowJar`


## License
Apache 2.0
