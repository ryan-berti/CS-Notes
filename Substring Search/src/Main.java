// * ---INTRODUCTION---
// - Substring search: given a TEXT string of length N and a PATTERN string of
// length M, find an occurrence of the PATTERN within the text (and return its
// index)

// * ---BRUTE FORCE SUBSTRING SEARCH---

// ? Algorithm:
//
// 1. Use a for-loop to iterate from i = 0 to i = N-M:
//
// 1a. Use a nested loop to iterate from j = 0 to j = M-1:
//
// 1ai. Check if jth char of PATTERN is equal to i+jth char of TEXT, if not:
// break nested loop.
//
// 1b. If j == M return i (Search hit)
// 2. If for(i) loop does not return, return N (search miss)

// ? Performance:
// - Worst case: brute-force substring search requires ~NM character compares.
//
// - eg. pat = "AAAB" and txt = "AAAAAAAB"
// (Brute-force is extremely INEFFICIENT in this case)

// * ---KMP SEARCH DATA STRUCTURE---
//
// - Whenever we detect a mismatch, we already know some of the characters in
// the text. We can take advantage of this information to avoid backing up the
// text pointer over all those known characters.
//
// - ie Instead of always incrementing the text pointer (i) by 1, we can
// sometimes increment by 2,3,4...
//
// - Other than the mismatch "trick", KMP is relatively similar to brute force.
//
// - General process:
// 1. Construct a DFA using the given pattern String
// 2. Simulate the DFA using the given text String
// 2a. If the DFA is in the accept state, return the start index of the pattern.
// 3. If the DFA is NOT in the accept state after simulation, return N

// ? Attributes:
// - private String pat
// - private int[][] dfa (Deterministic Finite Automaton)

// ? Deterministic Finite Automaton (DFA):
// - The DFA is constructed using only the PATTERN string (ie String pat).
//
// Dimensions:
// - R rows ; where R = alphabet size
// - M columns; where M = pattern string length
//
// dfa[c][j] = x means that: while in state j, the c-th alphabet character will
// transition the DFA to state x.

// ? DFA constructor - public KMP(String pat):
//
// 1. Initialize pattern string and DFA
// - this.pat = pat
// - dfa = new int[R][M]
//
// 2. Set initial state:
// - dfa[pat.charAt(0)][0] = 1
// - Note that pat.charAt(0) is implicitly cast to an integer
//
// 3. Construct Remaining States:
// - For each state j = 0 to j = M-1, do the following:
//
// 3a. Copy Mismatch Cases:
// - for (int c = 0; c < R; c++) dfa[c][j] = dfa[c][X]
//
// - The "restart" state X represents the length of the longest prefix of the
// pattern that is also a suffix of the pattern up to (not including) index j.
//
// 3b. Set Match Case:
// - dfa[pat.charAt(j)][j] = j+1
//
// - ie If we see this character in the text when we're at state j, we move to
// state j+1
//
// 3c. Update Restart State:
// - X = dfa[pat.charAt(j)][X]
// - This prepares X for the next state (j+1)
//
// 4. DFA is fully constructed!

// ? KMP search():
//
// 1. Initialize variables:
// - int N = txt.length();
// - int M = pat.length();
// - int i, j;
//
// 2. Iterate over text and pattern:
// - for (i = 0, j = 0; i < N && j < M; i++)
// - Change DFA states using: j = dfa[txt.charAt(i)][j]
//
// 2a. If j = M:
// - The for-loop will break (because j must be less than M)
// - Return i-M (the start index of the pattern)
//
// 2b. If for loop does not break:
// - All the text has been "scanned"
// - Return N (search miss!)

// ? Performance:
// - Worst case: accesses no more than M + N characters to search for a pattern
// of length M in a text of length N.

// * ---BOYER MOORE SEARCH DATA STRUCTURE---
// - When a mismatch is detected, the Boyer-Moore algorithm uses the Mismatched
// character heuristic to enhance efficiency.
//
// - Similar to KMP in the sense that it is optimized to not "re-scan" text

// ? Mismatched character heuristic:
//
// 1. If the mismatched character is not present in the pattern, the pattern is
// moved past the mismatched character in the text.
//
// 2. If the mismatched character is present, the pattern is shifted so that the
// mismatched character in the text aligns with the last (rightmost)
// occurrence of the same character in the PATTERN.

// ? Attributes:
// 1. private String pat
//
// 2. private int[] right:
// - right[i] = rightmost occurrence of i-th alphabet character in the pattern.

// ? Constructor - public BoyerMoore(String pat):
//
// 1. Set all values of right[] to -1:
// - for (int c = 0; c < R; c++) right[c] = -1
//
// 2. Set the rightmost index of all chars in the pattern:
// - for (int j = 0; j < M; j++) right[pat.charAt(j)] = j

// ? Boyer-Moore search():
//
// 1. Initialize variables:
// - int N = txt.length();
// - int M = pat.length();
// - int skip;
//
// 2. Iterate over text:
// - for (int i = 0; i <= N-M; i += skip)
//
// 2a. Set skip = 0
//
// 2b. Iterate through pattern (RIGHT TO LEFT):
// - for (int j = M-1; j >= 0; j--)
//
// 2bi. Mismatch Handling:
// - If a mismatch is found, skip = j - right[txt.charAt(i+j)],
// - If skip < 1, set skip = 1.
//
// 2c. If skip = 0, return i (search hit!)
//
// 3. If for(i) loop does not return, return N (search miss)

// ? Performance:
// - Avg case: search requires ~N/M character compares to search for a pattern
// of length M in a text of length N.

// * ---ROBIN KARP FINGERPRINT SEARCH DATA STRUCTURE---
// Basic process:
//
// 1. Compute a hash function for the pattern and look for a match by using the
// same hash function for each possible M-character substring of the text.
//
// 2. If the hash values are equal, we can check for a match.

// ? Attributes:
// - String pat
// - long patHash
// - int M
// - long Q: A large prime
// - int R = 256
// - long RM (where RM = R^(M-1) % Q)

// ? Robin Karp search():
//
// 1. Create variables:
// - int N = txt.length()
// - long txtHash = hash(txt, M)
//
// 2. Check for immediate match:
// - if (patHash == txtHash) return 0
// - ie pat = "abc" & txt = "abcde"
//
// 3. Iterate over text:
// - for (int i = M; i < N; i++)
// - Starts at M since there was no immediate match.
//
// 3a. Remove leading digit & add trailing digit:
// - txtHash = (txtHash + Q - RM*txt.charAt(i-M) % Q) % Q
// - txtHash = (txtHash*R + txt.charAt(i)) % Q
//
// 3b. Check for match (2 conditions):
// i. patHash == txtHash
// ii. check(i - M + 1)
// - if (i && ii) return i - M + 1 (search hit!)
//
// 4. If for(i) loop does not return, return N (search miss)

// ? Monte Carlo algorithms:
// 1. Guaranteed running time (known)
// 2. May occasionally produce incorrect results
// 3. Error probability can be reduced by running algorithm multiple times
// 4. They are typically used when speed is a priority

// ? Las Vegas algorithms:
// 1. Variable running time
// 2. Always produce correct results (zero error probability)
// 3. Expected running time is usually bounded
// 4. They are typically used when correctness is a priority
