mkdir -p build/classes

javac -sourcepath src -d build/classes src/*
java -cp build/classes Caculator
