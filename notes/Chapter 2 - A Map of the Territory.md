# 2.1 - The Parts of a Language
## 2.1.4 - Intermediate Representations
* **Control flow graph**: A Control Flow Graph (CFG) is the graphical representation of control flow or [computation during the execution of programs](https://www.geeksforgeeks.org/cyclomatic-complexity/) or applications. Control flow graphs are mostly used in static analysis as well as compiler applications, as they can accurately represent the flow inside of a program unit.

* **Static single-assignment**: In [compiler](https://en.wikipedia.org/wiki/Compiler "Compiler") design, static single assignment form (often abbreviated as SSA form or simply SSA) is a property of an [intermediate representation](https://en.wikipedia.org/wiki/Intermediate_representation "Intermediate representation") (IR), which requires that each variable be [assigned](https://en.wikipedia.org/wiki/Assignment_(computer_science) "Assignment (computer science)") exactly once, and every variable be defined before it is used. Existing variables in the original IR are split into _versions_, new variables typically indicated by the original name with a subscript in textbooks, so that every definition gets its own version.

* **Continuation-passing style**: In [functional programming](https://en.wikipedia.org/wiki/Functional_programming "Functional programming"), continuation-passing style (CPS) is a style of programming in which [control](https://en.wikipedia.org/wiki/Control_flow "Control flow") is passed explicitly in the form of a [continuation](https://en.wikipedia.org/wiki/Continuation "Continuation"). This is contrasted with [direct style](https://en.wikipedia.org/wiki/Direct_style "Direct style"), which is the usual style of programming.

* **Three-address code**: In [computer science](https://en.wikipedia.org/wiki/Computer_science "Computer science"), three-address code (often abbreviated to TAC or 3AC) is an [intermediate code](https://en.wikipedia.org/wiki/Intermediate_language "Intermediate language") used by [optimizing compilers](https://en.wikipedia.org/wiki/Optimizing_compiler "Optimizing compiler") to aid in the implementation of [code-improving transformations](https://en.wikipedia.org/wiki/Code-improving_transformation "Code-improving transformation"). Each TAC instruction has at most three operands and is typically a combination of assignment and a binary operator.

## 2.1.5 - Optimization
* **Constant propagation**: Constant propagation is the process of substituting the values of known constants in expressions. Constant propagation eliminates cases in which values are copied from one location or variable to another, in order to simply assign their value to another variable.

* **Common subexpression elimination**: In [compiler theory](https://en.wikipedia.org/wiki/Compiler_theory "Compiler theory"), common subexpression elimination (CSE) is a [compiler optimization](https://en.wikipedia.org/wiki/Compiler_optimization "Compiler optimization") that searches for instances of identical [expressions](https://en.wikipedia.org/wiki/Expression_(computer_science) "Expression (computer science)") (i.e., they all evaluate to the same value), and analyzes whether it is worthwhile replacing them with a single variable holding the computed value.

* **Loop-invariant code motion**: In [computer programming](https://en.wikipedia.org/wiki/Computer_programming "Computer programming"), [loop-invariant code](https://en.wikipedia.org/wiki/Loop-invariant_code "Loop-invariant code") consists of statements or expressions (in an [imperative](https://en.wikipedia.org/wiki/Imperative_programming "Imperative programming") [programming language](https://en.wikipedia.org/wiki/Programming_language "Programming language")) which can be moved outside the body of a loop without affecting the semantics of the program.

* **Global value numbering**: Value numbering is a technique of determining when two [computations](https://en.wikipedia.org/wiki/Computations "Computations") in a program are equivalent and eliminating one of them with a semantics preserving optimization. 

* **Strength reduction:** In [compiler construction](https://en.wikipedia.org/wiki/Compiler_construction "Compiler construction"), strength reduction is a [compiler optimization](https://en.wikipedia.org/wiki/Compiler_optimization "Compiler optimization") where expensive operations are replaced with equivalent but less expensive operations.

* **Scalar replacement of aggregates:** Scalar Replacement of Aggregates (SRA) creates new scalar variables for parts of aggregates which cannot be aliased. The chief motivation is to make optimizations which are applicable only to scalar variables also work on values stored in such aggregates and perhaps to eliminate some loads from memory.

* **Dead code elimination:** In [compiler theory](https://en.wikipedia.org/wiki/Compiler_theory "Compiler theory"), dead code elimination (also known as DCE, dead code removal, dead code stripping, or dead code strip) is a [compiler optimization](https://en.wikipedia.org/wiki/Compiler_optimization "Compiler optimization") to remove code which does not affect the program results.

* **Loop unrolling:** Loop unrolling, also known as loop unwinding, is a [loop transformation](https://en.wikipedia.org/wiki/Loop_transformation "Loop transformation") technique that attempts to optimize a program's execution speed at the expense of its [binary](https://en.wikipedia.org/wiki/Binary_file "Binary file") size, which is an approach known as [space–time tradeoff](https://en.wikipedia.org/wiki/Space%E2%80%93time_tradeoff "Space–time tradeoff"). The transformation can be undertaken manually by the programmer or by an [optimizing compiler](https://en.wikipedia.org/wiki/Optimizing_compiler "Optimizing compiler"). On modern processors, loop unrolling is often counterproductive, as the increased code size can cause more cache misses; _cf._ [Duff's device](https://en.wikipedia.org/wiki/Duff%27s_device#Performance "Duff's device").

# 2.2 - Shortcuts and Alternate Routes
## 2.2.1 - Single-pass compilers
* A single-pass compiler interlaves parsing, analysis, and codegen so that output code is produced directly by the parser, without ever allocating a syntax tree or IR. This restricts the design of a language - this is why forward declarations are required in C.

## 2.2.2 - Tree-walk interpreters
* This implementation will begin execiting code right after parsing to an AST. 

### There's also Transpiliers and JIT but I know what those are lol

# 2.3 - Compilers and Interpreters
* **Compiling** translates source to some other - usually lower level - form. It is then up to the user to run it.
* An **Interpreter** runs code immediately.

# 2.4 - Our Journey
## Challenges
Q: What reasons are there not to JIT?
A: Faster run time with AOT?