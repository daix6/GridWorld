mkdir -p build/classes

javac -cp .:lib/* -sourcepath src -d build/classes src/*
java -cp .:lib/*:build/classes org.junit.runner.JUnitCore JumperRunner