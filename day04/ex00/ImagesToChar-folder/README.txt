mkdir target
javac -d target edu\school21\printer\app\Main.java edu\school21\printer\logic\Logic.java
java -cp target edu.school21.printer.app.Main [PATH TO IMAGE.BMP]
rm -rf target
