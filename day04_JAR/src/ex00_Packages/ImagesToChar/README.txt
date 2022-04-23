######### Instructions

WHITE_CHAR=.
BLACK_CHAR=0
IMAGE=it.bmp

#1 Cleaning
rm -rf target/

#2 Compiling
mkdir -p target/
javac src/java/edu/school21/printer/*/*.java -d target/
chmod 777 target/images-to-chars-printer.jar

#3 Running
java -cp target edu.school21.printer.app.Program ${WHITE_CHAR} ${BLACK_CHAR} ${IMAGE}

