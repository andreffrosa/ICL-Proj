# Programming Language

This project is a new programming language developed in the context of the ICL course.

## Features

 - Types: Integers, Booleans, Floats, Doubles
 - Recursive Functions
 - Nested Functions
 - Registers
 - Strings
 - String concatenation (+) with Integers, Doubles, and Booleans
 - '#' operator returns the String's length
 - println 
 - toString converts any IValue into a String
 - for and while loops
 - modulus operator '%'
 - Boolean conditions' short circut

## Development

### Install Dependencies

This project depends on Java SDK 1.8 and [JavaCC](https://javacc.github.io/javacc/).

```
sudo apt update
sudo apt install openjdk-8-jdk


curl -O https://github.com/javacc/javacc/archive/javacc-7.0.12.tar.gz
tar xvf javacc-7.0.12.tar.gz
chmod +x scripts/javacc
```

Add `scripts/javacc` to PATH environment variable.

### Compile

To compile the project, run:
```
cd src
javacc Parser.jj
javac *.java
```

### Run

```
java Parser <file>
```
If no file is provided, it enters interactive mode.

Examples of input files: [/tests](/tests)
