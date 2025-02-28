# Arbitrary-Precision Calculator

A simple Prefix(Infix) Notation Calculator that supports arbitrary-precision calculations.

---

## How to Use

1. **Clone the repository**:
   First, clone the repository to your local machine using the following command:

   ```bash
   git clone <repository-url>

2. **Run the program**:
   Open the project in an IDE like IntelliJ IDEA or Eclipse.
   Alternatively, you can compile and run the program using Maven or directly from the main Java file.
3. **Starting the Calculater**: Once the program is running, you can enter commands directly into the terminal.

---

## Commands

1. **Define the Type**: Start by specifying the type of numbers you want to use by writing:

  ```bash
   Type: <type>
  ```
where `<type>` can be one of the following:
  * `int` (for `java.lang.int` calculations)
  * `integer` (for arbitrary-precision integer calculations)
  * `rational` (for arbitrary-precision rational number calculations)
  * `natural` (for arbitrary-precision natural number calculations)
2. **Enter PostFix Expression**: After setting the type, enter your expression in Infix notation, prefixed by `=`. For example:
   ```bash
   Enter expression (or 'exit' to quit): Type: integer
   Enter expression (or 'exit' to quit): = + 3 2
   Result: 5
   Enter expression (or 'exit' to quit): = - 3 2
   Result: 1
   Enter expression (or 'exit' to quit): = * 3 2
   Result: 6
   Enter expression (or 'exit' to quit): = \ 3 2
   Result: 1
   Enter expression (or 'exit' to quit): = ~ 1
   Result: -1
   ```
   **Note** when initializing numbers use numbers `<= java.lang.int.MAXVALUE`
3. **Process Files**: Additionally, using the terminal one can process files by writing:

   ```bash
   Process File: <absolute_path> <type>
   ```
  and the output will be generated with the name `<input_file_name>_output.txt`.
  
4. **Exiting**: Typing exit, will terminate the program.

---
**Note** make sure to write the command precisely as mentioned, the parser for the inputs is not intelligent.(not case insensitive)
---
# Future Extensions
1. Implementation of `Z_p` numbers, where `p` is a prime.
2. Implementation of matricies and their computations.
3. Implementation of a GUI.
