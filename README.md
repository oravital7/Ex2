GPS coordinates
=========

This project convert csv files into one kml file, wich repreasent all points by time and location.


General info
--------------
The program analyzes data from a CSV file and converts them into Java objects
With the appropriate changes you can export the information to any 
file type you choose by going over the objects.

Currently, the system fully supports export to KML with the relevant tags
You can read about KML: https://en.wikipedia.org/wiki/Keyhole_Markup_Language


How to use
--------------
Just call the 'MultiCSV' class insert a path of any folder (including sub-folders), the algorithm will filter everything inside the discovered folders and retrieve only the relevant CSV files.
Now all the information is stored in Java objects, you can use the built-in KML export function, Just
use the function 'convert2kml' that exists in 'MultiCSV' to start the process.
Finally you get your KML file and feel free to use and change it.

How it works
--------------
All start in MultiCSV class has a recursive function which scans all folders in the given path,
then calls Csv2elem class for each CSV file that has been discovered, now Csv2elem translates the file 
into Java language so that uses CsvReader class which give us a array of all row in the CSV file.
now Csv2elem convert each row in the array string to elements that we can use after and export to 
any file type we want to.


Examples KML export
-------------------


Read more in Wiki:
- Geographic coordinate system https://en.wikipedia.org/wiki/Geographic_coordinate_system
- Polar coordinate system: https://en.wikipedia.org/wiki/Polar_coordinate_system

