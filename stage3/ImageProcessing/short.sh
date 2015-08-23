mkdir -p build/classes

cp -r bmptest/ build/classes

javac -cp .:lib/* -sourcepath src/ -d build/classes src/*
java -cp .:lib/*:build/classes MyIImageIO