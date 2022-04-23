######### Instructions

#1 Cleaning
rm -rf target/ lib/

#2 Directories creation
mkdir -p target/ lib/

#3 External Libraries downloading
curl -o lib/jcommander-1.82.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar
curl -o lib/JCDP-4.0.2.jar https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar

#4 Unpacking libraries
jar xf lib/jcommander-1.82.jar
jar xf lib/JCDP-4.0.2.jar
mv com/ target/
rm -rf com/ META-INF

#5 Compiling sources with external libraries
javac -cp lib/JCDP-4.0.2.jar:lib/jcommander-1.82.jar src/java/edu/school21/printer/*/*.java -d target/
cp -R src/resources target/.

#6 Building jar archive
jar -cmf src/manifest.txt target/images-to-chars-printer.jar -C target .
chmod 777 target/images-to-chars-printer.jar

#7 Running
java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN

