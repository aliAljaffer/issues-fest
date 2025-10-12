#!/bin/bash
# Build script for the projekt

echo "Starting build proces..."

# Compile Java files
echo "Compiling Java sorce files..."
javac src/java/*.java

# Run tests
echo "Runing tests..."
cd tests
javac CalculatorTest.java
java CalculatorTest

echo "Build complte!"
