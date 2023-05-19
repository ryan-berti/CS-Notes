// * ---REGULAR EXPRESSIONS (REs)---
// - A regular expression (regex) is a sequence of characters that forms a
// search pattern.

// ? Terminology:
// - Language: A set (possibly infinite) of strings
// - Pattern: A language specification (ie "A(B|C)*D")
//
// ie A pattern is a way to DESCRIBE what kind of strings are in the language.

// ? Operations:
//
// 1. Concatenation:
//
// - AB: The language {AB}, consisting only of the string formed by
// concatenating A and B.
//
// - ie Operation allows us to join strings.
//
// 2. Or:
// - A|E|I|O|U: The language {A,E,I,O,U}
// - ie Operation allows us to specify alternatives
//
// 3. Closure:
// - A(B)*: The language {A,AB,ABB,ABBB,ABBBB...}
// - ie Operation allows parts of the pattern to be repeated arbitrarily.

// ? Definition - An RE is either:
// - Empty
// - A single character
// - A regular expression enclosed in parentheses
// - Two or more concatenated regular expressions
// - Two or more regular expressions separated by the or operator (|)
// - A regular expression followed by the closure operator (*)

// ? Set-of-characters descriptor shortcuts:
//
// 1. Wildcard/Dot: "."
// - A dot serves as a wildcard that represents any character in the alphabet.
// - ie "A.B" represents the language {AAB,ABB,ACB,ADB...}
//
// 2. Specified set: enclosed in "[]"
// - Square brackets represent any ONE of the characters enclosed
// - ie "[ABCDE]" represents A|B|C|D|E -> language {A,B,C,D,E}
//
// - ie "[AB]*" represents the language containing all strings composed of A
// and/or B -> {A,B,AB,BA,ABA,AAA,BBB,ABABAB...}
//
// 3. Range: enclosed in "[]" & separated by "-"
// - A range represents any ONE character in the specified range.
// - ie [0-9] represents the language {0,1,2,3,4,5,6,7,8,9}
//
// 4. Complement: enclosed in "[]" preceded by "^"
// - A complement represents any ONE character that is NOT in the specified set
// - ie [^AB] represents the language containing all characters except A or B

// ? Closure shortcuts:
//
// 1. Plus sign: "+"
// - A plus sign specifies AT LEAST ONE copy of the pattern
// - ie A+ represents the language {A,AA,AAA,AAAA...}
//
// 2. Question mark: "?"
// - A question mark specifies zero or one copy of the pattern
// ie AB? represents the language {A,AB}
//
// 3. Braces: enclosed in "{}"
// - Specifies a given number or a range of copies
// - {n}: Exactly n repetitions
// - {n,}: At least n repetitions
// - {n,m}: Between n and m repetitions (inclusive)

// ? Escape sequences:
//
// - Used to:
// a. Specify special characters and whitespace
// b. Separate meta-characters from characters in the alphabet.
//
// 1. \\: Represents a backslash.
// 2. \t: Represents a tab character.
// 3. \n: Represents a newline.
// 4. \s: Represents any whitespace character.

// * ---NONDETERMINISTIC FINITE-STATE AUTOMATA---
//
// - DFA: For each state, there's exactly one transition for each possible
// input. It is "deterministic" because the next state is determined completely
// by the current state and the given input symbol.
//
// - NFA: On the other hand, an NFA can have 0,1,2... transition from a given
// state for a given possible input. It is "non-deterministic" because it's not
// always certain which state the automaton will move to for a given input.
//
// - ie An NFA can exist in multiple states at once.

// ? Characteristics of regex NFA:
//
// - The NFA corresponding to an RE of length M has exactly one state per
// pattern character, starts at state 0, and has a (virtual) accept state M.
//
// - States corresponding to a alphabet character have an outgoing (black) edge
// that goes to the state corresponding to the next character in the pattern.
//
//
// - States corresponding to the meta-characters have at least one outgoing
// (red) edge, which may go to any other state.
//
// - Some states have multiple outgoing edges, but no state has more than one
// outgoing black edge.

// ? Differences from DFA:
// - Characters (in the diagram) appear in the nodes, not the edges.
//
// - Our NFA recognizes a text string only after explicitly reading all its
// characters, whereas our DFA recognizes a pattern in a text without
// necessarily reading all the text characters.

// ? RE pattern matching algorithm overview:
// - Very similar to KMP search algorithm
//
// Steps:
// 1. Build the NFA corresponding to the given RE.
// 2. Simulate the operation of that NFA on the given text

// ? Changing states in a NFA:
//
// 1. Match transition:
// - If the current state matches the current text character, the automaton
// advances past the character and transitions (black edge) to the next state.
//
// 2. Epsilon (ε) transition:
// - The automaton can shift to another state (using any red edge) without
// consuming any text (ie empty string match)

// ? Non-deterministic:
// - The non-deterministic nature of NFAs allows them to have multiple potential
// transitions from each state, and they "recognize" an input string if there is
// at least one path of transitions leading to an accept state after consuming
// the ENTIRE input string.

// * ---NFA CORRESPONDING TO AN RE: public class NFA---
// - Translate a Regular Expression (RE) into an NFA using a slightly modified
// Dijkstra's two-stack algorithm
//
// - The NFA is created by iterating over each character the RE, during this
// process (red) edges are added based on current character of the RE

// ? Attributes:
// - private char[] re -> Match transitions (ie the regex)
// - private Digraph G -> Epsilon (red) transitions
// - private int M -> no. of states

// ? Constructor - public NFA(String regexp):
//
// 1. Initialization:
// - Stack<Integer> ops = new Stack<Integer>() -> indices of certain RE chars
// - re = regexp.toCharArray()
// - M = re.length
// - G = new Digraph(M+1)
//
// 2. Iterate over characters in re[]:
// - for (int i = 0; i < M; i++)
//
// 2a. If character is "(" OR "|":
// - Push current index (i) position.
// - ie Store the position where a new group "(" or alternative path "|" starts.
//
// 2b. If character is ")":
// - ")" indicates the end of a group or alternative path.
// - Thus, pop the stack to get the position of the matching "(" or "|".
//
// 2bi. Set int or = popped index.
//
// 2bii. If POPPED position corresponds to "|":
//
// - Pop the stack again to get index of the corresponding "(", because at this
// point we only have the indexes of "|" and ")".
//
// - Add ε-transition from the position of "(" to the position right after "|"
// - Add ε-transition from "|" to current position (i)
//
// 2biii. If POPPED index corresponds to "(":
// - Set int lp = or (ie set left parenthesis index)
//
// 2c. If NEXT character is "*" and i < M-1:
// - Add ε-transition from the position of "(" to the position of "*"
// - Add ε-transition from the position of "*" to the position of "("
//
// 2d. If character is "(" OR "*" OR ")":
// - Add ε-transition from CURRENT position (i) to NEXT position (i+1)

// ! finish (pg 799)
// ? recognizes(String txt):
//
