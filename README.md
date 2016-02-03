# JOpenCC

### Introdction

JOpenCC is a Java based tool to convert Traditional Chinese to Simplified Chinese and vice versa.

This project is a java port of [OpenCC](https://github.com/BYVoid/OpenCC) which developed by BYVoid <byvoid.kcp@gmail.com>.

Since the foundation differences between Java and C, only the dictionary is the same as OpenCC. The mapping methodology is completely different from the native C Opencc.

The GUI part is based on the Standard Widget ToolkitStand (SWT). SWT is a graphical widget toolkit for use with the Java platform and it is written in Java. To display GUI elements, the SWT implementation accesses the native GUI libraries of the operating system using JNI (Java Native Interface) in a manner that is similar to those programs written using operating system-specific APIs. Programs that call SWT are portable, but the implementation of the toolkit, despite the fact that part of it is written in Java, is unique for each platform.

SWT is chosen instead of Swing because the interface looks more native and more responsive in SWT. However, it should be easy for a Swing port.

### Interface

English, Traditional Chinese and Simplified Chinese interfaces are provided.

### Build 

#### Prerequisites

* Java 1.5 or above

* [SWT](http://www.eclipse.org/swt/) (tested 3.4, 3.7 and 4.5)

  - By default the SWT JAR will be placed in ..\org.eclipse.swt\swt.jar

* [Apache ANT](https://ant.apache.org) is needed to build the project

#### Compile

```bash
 $ ant build
```

### Run
It's suggested that to enlarge the memory for the JVM for processing long text

```bash
java -Xms32M -Xmx512m -Xmn16M -XX:PermSize=32M -XX:MaxPermSize=256m -jar jopencc.jar
```

or

```bash
 $ ant run
```

### TODO

1. adding different encoding support for input and output, currently only supports UTF-8
2. use background thread for conversion
3. Able to select convert mode using menu bar
4. Able to select interface language
5. Replace [Apache ANT](https://ant.apache.org) with [Apache Maven](https://maven.apache.org)