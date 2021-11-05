# Table of Contents
* [About the Project](#about-the-project)
    * [Built With](#built-with)
* [Getting Started](#getting-started)
    * [Prerequisites](#prerequisites)
    * [Installation](#installation)
* [Usage](#usage)
* [Roadmap](#roadmap)
* [Contributing](#contributing)
* [Contact](#contact)

# Madurobank Customer Statement Processor
Madurobank receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records of the 2 files need to be validated.

## Input
The format of the file is a simplified version of the MT940 format. The format is as follows:

## Table 1. Record description
| Field | Description |
| ---   | --- |
| Transaction reference | A numeric value |
| Account number  | An IBAN
|Start Balance | The starting balance in Euros |
| Mutation | Either an addition (+) or a deduction (-) |
| Description | Free text |
|End Balance | The end balance in Euros |

## Output
There are two validations:

* all transaction references should be unique
* the end balance needs to be validated

At the end of the processing, a report needs to be created which will display both the transaction reference 
and description of each of the failed records.

# Built With
* [Java 1.8](https://www.java.com)
* [Maven](https://maven.apache.org)

# Getting Started

# Installation
```bash
git clone https://github.com/jeortizquan/maduro-fileparser.git
```

# Usage
### To build
go to the cloned folder this will create jar file
```
mvn package
```

### To run
```
xml file
java -jar ./target/madurobank-0.0.1-SNAPSHOT.jar filename.xml

csv file
java -jar ./target/madurobank-0.0.1-SNAPSHOT.jar filename.csv

or multiple
java -jar ./target/madurobank-0.0.1-SNAPSHOT.jar filenameA.csv filenameB.xml
```

### Sample report
#### Success
```
18:35:44.056 [main] INFO io.axual.madurobank.MadurobankApplication - processing file:: records.csv
18:35:44.104 [main] INFO io.axual.madurobank.MadurobankApplication -
All records passed
```

#### Failure
```
18:36:35.455 [main] INFO io.axual.madurobank.MadurobankApplication - processing file:: records.xml
18:36:35.624 [main] INFO io.axual.madurobank.MadurobankApplication -
Failed records report [TransactionReference,Description]
149905::Tickets from Erik Theuß
166703::Flowers from Richard de Vries
```

# Roadmap
# Contributing
Any contributions you make are appreciated.

1. Fork the Project
2. Create your Feature Branch (git checkout -b feature/AmazingFeature)
3. Commit your Changes (git commit -m 'Add some AmazingFeature')
4. Push to the Branch (git push origin feature/AmazingFeature)
5. Open a Pull Request

# License
private

# Contact
jeortiz.quan@gmail.com