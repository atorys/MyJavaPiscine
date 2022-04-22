######### Instructions

WHITE_CHAR=.
BLACK_CHAR=0

#1 Cleaning
rm -rf target/

#2 Compiling
mkdir -p target/
javac src/java/edu/school21/printer/*/*.java -d target/
cp -R src/resources target/.
jar -cmf src/manifest.txt target/images-to-chars-printer.jar -C target .

#3 Running
java -jar target/images-to-chars-printer.jar ${WHITE_CHAR} ${BLACK_CHAR}

