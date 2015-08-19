mkdir -p build/classes

javac -cp .:lib/* -sourcepath src -d build/classes src/caculator/* test/*
java -cp lib/*:build/classes org.junit.runner.JUnitCore CaculatorTest
