mkdir -p build/classes

javac -sourcepath src -d build/classes src/*
java -cp build/classes HelloWorld
javac -cp lib/*:build/classes -d build/classes test/*
java -cp lib/*:build/classes org.junit.runner.JUnitCore HelloWorldTest
