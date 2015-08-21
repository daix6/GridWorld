mkdir -p build/classes

cp -r assets/* build/classes

javac -cp .:lib/* -sourcepath src -d build/classes src/*
java -cp .:lib/*:build/classes AllCrittersRunner