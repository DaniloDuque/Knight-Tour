# Knight's Tour Problem

This repository provides a solution in various languages for the Knight's Tour problem. The problem involves finding a sequence of moves for a knight on a chessboard of dimensions $\(f \times c\)$ such that the knight visits each square exactly once.

## Problem Description

The Knight's Tour problem is a classic chess problem that entails moving a chess knight across a board without revisiting any square. The board has dimensions $\(f \times c\)$, and the goal is to find a sequence of knight moves that covers every square on the board.
The Knight's Tour problem is known to be an NP-complete problem. The term "NP-complete" stands for nondeterministic polynomial time complete, and it is a class of problems in computational complexity theory.

A problem is NP-complete if it belongs to the class NP (nondeterministic polynomial time) and has the property that every problem in NP can be reduced to it by a polynomial-time algorithm. In simpler terms, if there exists a polynomial-time algorithm for solving an NP-complete problem, then there is a polynomial-time algorithm for solving all problems in NP, making the NP-complete problem particularly challenging.

In the context of the Knight's Tour problem, this means that finding a solution, i.e., a sequence of knight moves that covers every square on the chessboard, is a computationally challenging task. The backtracking algorithm used in the provided solution explores a potentially large search space of possible move sequences, and the time complexity is exponential in the worst case. While heuristics can help improve practical performance, solving the Knight's Tour problem for large chessboards remains a complex computational task.

## Backtracking

The solution employs the backtracking technique, a depth-first search strategy that starts from an initial point and explores all possible solutions, backtracking when it reaches an unsolvable state. In this case, the algorithm begins from an initial position $\((i, j)\)$ and advances by choosing knight moves such that each square is visited only once.

## Source Code

The source code is located in the `KnightTour.py` file and utilizes various functions to check the validity of positions, calculate valid moves, and perform the knight's tour. It also implements a heuristic that sorts routes based on the accessibility of moves, favoring less accessible moves.

## Usage

To run the program, provide the dimensions of the board $\(f\)$ and $\(c\)$, as well as the initial coordinates of the knight $\((i, j)\)$. Here is an example of input:

```bash
8 8 0 0
```

## Output Explanation

After running the program, the output will consist of either the sequence of knight moves or the message "Impossible Knight Tour." 

### Successful Knight's Tour

If a knight's tour is feasible, the program will display a sequential series of numbers, each representing the knight's position after a move. The numbers are presented in a linear index format. For instance, if the knight is initially at position $(0, 0)$ on the chessboard, the program will output $0001$ to indicate the first position in the sequence.

Here's an example output:

```plaintext
0001 0011 0005 0015 0032 0047 0064 0054 
0048 0063 0053 0059 0049 0034 0017 0002 
0012 0006 0016 0031 0021 0004 0010 0025 
0019 0009 0003 0018 0033 0027 0042 0057 
0051 0041 0058 0043 0026 0036 0030 0020 
0037 0022 0007 0024 0014 0008 0023 0013 
0028 0038 0055 0040 0046 0061 0044 0029 
0039 0056 0062 0052 0035 0045 0060 0050  
```

In this representation, each number denotes a step in the knight's tour.

### Impossible Knight's Tour

If it is impossible to perform a knight's tour on the given board, the program will output the message "Impossible Knight Tour." This occurs when the conditions for a knight's tour are not met, such as when the board size is too small or when the parity conditions are not satisfied.

Example:

```plaintext
Impossible Knight Tour
```

In this case, the program informs the user that a knight's tour cannot be achieved under the given conditions.

Understanding the output matrix requires interpreting the sequential order of numbers, where each number represents the step of the knight's tour on the chessboard.

## Time Complexity Analysis

The time complexity of the provided algorithm is influenced by the backtracking approach used to explore possible solutions. Let $\(N\)$ be the total number of squares on the chessboard.

The worst-case time complexity is exponential, specifically $\(O(N^k)\)$, where $\(k\)$ is the number of moves required to visit all squares. In the worst case, the algorithm explores all possible combinations of knight moves, making it impractical for large chessboards.

However, the provided heuristic that sorts routes based on the accessibility of moves can significantly improve the algorithm's performance in practice. The heuristic helps prioritize less accessible moves, potentially reducing the search space.

## Contributions

Contributions are welcome. If you find improvements or want to add new features, feel free to open an issue or submit a pull request. Thank you for your interest in the Knight's Tour problem! 🚀



