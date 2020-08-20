# sia-tp1

## Install dependencies
`mvn clean install`

or to avoid running full test suite

`mvn clean install -DskipTests`

## Compile
`mvn clean compile` 

## Generate .jar
`mvn clean package` 

or to avoid running full test suite

`mvn clean package -DskipTests`

## Execute command
Modify `config.txt` file to run the desire environment and execute .jar:

`java -jar target/sia-tp1-1.0.0.jar`

## Example
In config.txt:

`
-s bfs
-i maps/m4.txt
`

Or in config.txt:

`
-s greedy
-h h1
-i maps/m4.txt`