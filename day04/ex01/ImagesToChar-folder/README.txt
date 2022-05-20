mkdir target
mkdir target\resources
javac -d target edu\school21\printer\app\Main.java edu\school21\printer\logic\Logic.java
java  cvfm target\images-to-chars-printer.jar .\manifest.txt -C target/ .
java -jar .\target\images-to-chars-printer.jar
