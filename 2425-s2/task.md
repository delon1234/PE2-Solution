# CS2030S AY24/25 Sem 2
## Practical Assessment 2 (Session I @ 4 PM)

## INSTRUCTIONS TO CANDIDATES

1. The total mark is 40.  We may deduct up to 4 marks for violating of
   style.

2. This is a CLOSED-BOOK assessment. You are only allowed to refer to one
   double-sided A4-size paper.

3. You should see the following files/directories in your home directory.

   * The subdirectories Q1a, Q1b, Q1c, and Q2.

   * ListAPI.md, StreamAPI.md and ComparatorAPI.md contain the API for 
     Java's List, Stream, and Comparator, respectively.

   * For Question 1, 

       * test.jar contains the automated tests Test1, Test2, Test3, and
         CS2030STest for testing.

       * Q1a/Program.java is a skeleton code for you to complete. 

       * Q1b/Transformer.java and Q1c/Transformer.java are provided to you
         to solve Question 1.

   * For Question 2,

       * Q2/Demo.java to demonstrate the use of the methods you will implement.

       * Q2/Station.java is provided to you to solve Question 2.  You should 
         not change this class.

       * Q2/Q2a.java to Q2/Q2d.java contain working code that you need to change 
         to solve the question.

4. Write your student number on top of EVERY FILE you created or edited as
   part of the @author tag. Do not write your name.

5. To compile your code, run 

   javac -Xlint:unchecked -Xlint:rawtypes *.java.

   You can also compile the files individually if you wish.

## Question 1 (24 marks): Generics

In this question, we develop an abstraction for a program that
consists of a lazily evaluated sequence of computations that
evaluates to a value.  A program can be constructed as a sequence of
lambda expressions, chained together with the `flatMap` operator.
The lambda expressions are only evaluated when the `get` method is
called.

Use of generic classes and methods are required for this question.
The type arguments and the return type of methods are not always
specified in the question.  If so, you should determine the correct
type arguments to use in declaring your classes and methods and the
correct return type to use for the methods.  Use wildcards
appropriately to make your classes and methods as general as possible.

Build up your solution incrementally in different parts of this
question, by solving them under different subdirectories `Q1a`,
`Q1b`, and `Q1c`.  The files under these subdirectories will be
graded independently.

### Part (a) (8 marks)

**In the `Q1a` subdirectory**, create an abstract generic class
`Program` with the following specifications:

- `Program` contains an abstract `get()` method that evaluates the
  lambda expression and returns the result;

- `Program` contains an abstract `isResult()` method that determines
  whether the object is a result or a chain of computations;

- `Program` contains a private static nested generic class `Result`.
  The class `Result` must:

  - inherit from `Program`

  - contain a private field `value` of a generic type

  - have a constructor that initializes the `value`

  - provide the implementation for `get()` that returns the `value`

  - provide the implementation for `isResult()` that returns `true`

- `Program` contains a static factory method `result()` that creates
  and returns a `Result` instance.

The implementation should support generic type arguments and use
wildcards to maximize flexibility.

Write your class so that it behaves as follows:

```
jshell> new Program<>("hello")
|  Error:
|  Program is abstract; cannot be instantiated
|  new Program<>("hello")
|  ^--------------------^

jshell> new Program.Result<>("hello")
|  Error:
|  Program.Result has private access in Program
|  new Program.Result<>("hello")
|      ^------------^

jshell> Program.<String>result("hello")
$2 ==> Program$Result@2b98378d

jshell> Program.<Integer>result("hello")
|  Error:
|  incompatible types: java.lang.String cannot be converted to java.lang.Integer
|  Program.<Integer>result("hello")
|                          ^-----^

jshell> Program.result(0).isResult()
$3 ==> true

jshell> String s = Program.result("hello").get()
s ==> "hello"

jshell> int i = Program.result(10).get()
i ==> 10
```

You are encouraged to test your code in jshell first, following the commands and 
interactions above. When you are ready, run the automated test provided in Test1:

```
$ java -cp ../test.jar:. Test1
```

### Part (b) (8 marks)

Now that we have a way to encapsulate a value in a program, we want
to chain one or more computations sequentially.

Copy the file `Program.java` from subdirectory `Q1a` to subdirectory
`Q1b`.  **In the subdirectory `Q1b`**, update `Program.java` with
the following additions:

- Create a private static nested generic class `Chain` that:

  - inherits from `Program`

  - contains private fields for a `Program` and a `Transformer`

  - has a constructor that initializes the `program` and
    `transformer`

  - implements `get()` to transform the result of the `program`
    using the transformer

  - implements `isResult()` so that it returns `false`

- Implement a static factory method `chain()` in the `Program` class
  that takes a `Program` and a `Transformer` as arguments and
  returns a new `Chain` instance.

Write your class so that it behaves as follows:

```
jshell> Transformer<String, Program<Integer>> t1 = s -> Program.result(s.length())
t1 ==> $Lambda$17/0x00000008000c1040@4c70fda8

jshell> Program<String> p = Program.result("hello")
p ==> Program$Result@14acaea5

jshell> Program.<String, Integer>chain(p, t1)
|  Error:
|  incompatible types: Program<java.lang.String> cannot be converted to 
|  Program<? extends java.lang.Integer>
|  Program.<String, Integer>chain(p, t1)
|                                 ^

jshell> Program.<String, Integer>chain(p, t1).get()
|  Error:
|  incompatible types: Program<java.lang.String> cannot be converted to 
|  Program<? extends java.lang.Integer>
|  Program.<String, Integer>chain(p, t1).get()

jshell> Transformer<Object, Program<Integer>> t2 = obj -> Program.result(obj.hashCode())
t2 ==> $Lambda$18/0x00000008000c1440@59fa1d9b

jshell> Program.chain(p, t2).isResult()
$6 ==> false

jshell> Program.chain(p, t2).get()
$7 ==> 99162322

jshell> Program<Integer> p = Program.result(0)
p ==> Program$Result@4501b7af

jshell> Program.<Integer, Integer>chain(p, t1)
|  Error:
|  incompatible types: Transformer<java.lang.String,Program<java.lang.Integer>> cannot 
|  be converted to Transformer<? super java.lang.Integer,? extends Program<? extends 
|  java.lang.Integer>>
|  Program.<Integer, Integer>chain(p, t1)
|                                     ^^
```

You are encouraged to test your code in jshell first, following the commands and 
interactions above. When you are ready, run the automated test provided in Test2:

```
$ java -cp ../test.jar:. Test2
```


### Part (c) (8 marks)

Copy the file `Program.java` from subdirectory `Q1b` to subdirectory
`Q1c` and continue solving this part of the question **under the
subdirectory `Q1c`**.

Now that we have the encapsulations of `Result` and `Chain`, we can
chain one or more computations together with the `flatMap` method.

Extend the `Program` class with the following additions:

- Add an abstract method `flatMap` to the `Program` class that takes
  in a lambda expression of the form `x -> y` where `y` is a
  `Program`.

- Implement the `flatMap` method in the `Result` class such that it
  lazily applies the transformer to the stored value.

- Implement the `flatMap` method in the `Chain` class such that it
  lazily applies the transformer to the result of the chained
  program.

Write your class so that it behaves as follows:

```
jshell> Transformer<String, Program<Integer>> t1 = str -> Program.result(str.length())
t1 ==> $Lambda$17/0x00000008000c1040@4c70fda8

jshell> Transformer<Object, Program<Integer>> t2 = obj -> Program.result(obj.hashCode())
t2 ==> $Lambda$18/0x00000008000c1440@46d56d67

jshell> Program.result("hello").flatMap(t1).flatMap(t2).isResult()
$5 ==> false

jshell> Program.result("hello").flatMap(t1).flatMap(t2).get()
$6 ==> 5

jshell> Transformer<String, Program<Integer>> t3 = str -> {
   ...>   System.out.println("t3: " + str);
   ...>   return Program.result(str.length());
   ...> }
t3 ==> $Lambda$20/0x00000008000c1c40@523884b2

jshell> Program.result("hello").flatMap(t3)
$8 ==> Program$Chain@61832929

jshell> Program.result("hello").flatMap(t3).get()
t3: hello
$9 ==> 5
```

You are encouraged to test your code in jshell first, following the
commands and interactions above. When you are ready, run the automated test
provided in Test3:

```
$ java -cp ../test.jar:. Test3
```

## Question 2 (16 marks): Streams

Under the subdirectory `Q2`, you are provided a class `Station`
which represents an MRT station. Each station has a name, opening
year, and a list of bus connections represented as strings (e.g.,
bus numbers).

You are also provided four classes `Q2a`, `Q2b`, `Q2c` and `Q2d`
each with a method that uses traditional loops to process data from
Singapore's MRT network. Each method operates on a list of `Station`
objects.

Your task is to refactor each method into a single Java Stream
pipeline by editing these classes.  You can either comment out the
given code or delete the given code.

**IMPORTANT:** For the rest of this paper, all methods written
should follow the following constraints:

- The method body must be exactly one statement, specifically a
  return statement.

- The method body must not contain any loop or conditional statement.

- The method body must not use any lambda expression with blocks
  (e.g., `() -> { .. }`.)

Useful APIs are provided in the files `ListAPI.md` and
`StreamAPI.md`.

You can test your implementation by running the following commands.
```
 javac Demo.java
 java Demo
```

### Part (a) (3 marks): getAllBusConnections

This method takes in a list of stations `L` and returns a list of
all distinct buses (sorted in alphabetical order) found in the lists
of connecting buses, across all stations in `L`.

Write your class so that it behaves as follows:

```
jshell> /open Station.java
jshell> /open Q2a.java

jshell> List<Station> sampleStations = List.of(
   ...>   new Station("Bugis", 1985, List.of("Bus 7", "Bus 97", "Bus 133")),
   ...>   new Station("Bayfront", 2012, List.of("Bus 97", "Bus 106")),
   ...>   new Station("Bedok", 1989, List.of("Bus 2", "Bus 30", "Bus 168")),
   ...>   new Station("Boon Lay", 1990, List.of("Bus 30", "Bus 172", "Bus 180")),
   ...>   new Station("Dhoby Ghaut", 1987, List.of("Bus 14", "Bus 16", "Bus 190", "Bus 200"))
   ...> );
sampleStations ==> [Bugis (1985), Bayfront (2012), Bedok (1989), Boon Lay (1990), 
                    Dhoby Ghaut (1987)]

jshell> Q2a.getAllBusConnections(sampleStations);
$.. ==> [Bus 106, Bus 133, Bus 14, Bus 16, Bus 168, Bus 172, Bus 180, 
         Bus 190, Bus 2, Bus 200, Bus 30, Bus 7, Bus 97]
```

### Part (b) (3 marks): getOldStations

This method takes in a list of stations and a year `y`, and returns
a list of the names of all stations opened strictly before `y`.

Write your class so that it behaves as follows (continuing from the
previous jshell input):

```
jshell> /open Q2b.java

jshell> Q2b.getOldStations(sampleStations, 1987);
$.. ==> [Bugis]

jshell> Q2b.getOldStations(sampleStations, 1990);
$.. ==> [Bugis, Bedok, Dhoby Ghaut]
```

### Part (c) (5 marks): getTopConnectedStations

This method takes in a list of stations `L` and returns a list
containing the names of the top three stations with the most connections,
breaking ties by the station name in alphabetical order.

A Comparator is provided for you in the `Q2c` class. You can use it
to sort the stations as required above.  You can use it as is
without any changes.

Write your class so that it behaves as follows (continuing from the
previous jshell input):

```
jshell> /open Q2c.java

jshell> Q2c.getTopConnectedStations(sampleStations);
$.. ==> [Dhoby Ghaut, Bedok, Boon Lay]
```

### Part (d) (5 marks): getConnectedStationPairs

This method takes in a list of stations and a list of bus names, and
returns all distinct pairs of stations (as a
`List<List<Station>>`) that are connected by at least one of the
given buses.  Do not create a custom class to represent a pair.  The
order of stations in a pair should be exactly the same as what would
be produced by the given skeleton.

Write your class so that it behaves as follows (continuing from the
previous jshell input):

```
jshell> /open Q2d.java

jshell> List<String> targetBuses1 = List.of("Bus 30", "Bus 97");
targetBuses1 ==> [Bus 30, Bus 97]

jshell> Q2d.getConnectedStationPairs(sampleStations, targetBuses1);
$.. ==> [[Bugis (1985), Bayfront (2012)], [Bedok (1989), Boon Lay (1990)]]

jshell> List<String> targetBuses2 = List.of("Bus 190");
targetBuses2 ==> [Bus 190]

jshell> Q2d.getConnectedStationPairs(sampleStations, targetBuses2);
$.. ==> []
```

## END OF PAPER
