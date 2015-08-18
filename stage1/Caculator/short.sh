mkdir -p build/classes

javac -sourcepath src/**/*.java -d build/classes src/*
java -cp build/classes caculator.Caculator
