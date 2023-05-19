// * ---DATA COMPRESSION---

// ? Lossy vs lossless compression:
// - In LOSSLESS compression: no information is lost. The result of compressing
// and expanding a bit-stream must match the original, bit for bit.
//
// - In LOSSY compression: some information is lost. The result of compressing
// and expanding a bit-stream does not exactly match the original. The quality
// of the reconstructed data may be lower than the original, but this method
// typically achieves greater compression ratios.

// ? Compression ratio:
//
// - Suppose COMPRESSION transforms a bit-stream (ie 001010011) B into a
// compressed version C(B); and that DECOMPRESSION transforms C(B) back to C (ie
// lossless compression)
//
// - Compression ratio = |C(B)| / |B|; where |*| is no. of bits in bit-stream.

// ? public class BinaryStdIn:
// - boolean readBoolean(): Reads 1 bit of data and returns it as a boolean
// value.
// - char readChar(): Reads 8 bits of data and returns it as a char value.
// - char readChar(int r): Reads r (between 1 and 16) bits of data and returns
// it as a char value.
// - boolean isEmpty(): Checks if the bit-stream is empty.
// - void close(): Closes the bit-stream.

// ? public class BinaryStdOut:
// - void write(boolean b): Writes the specified bit.
// - void write(char c): Writes the specified 8-bit char.
//
// - void write(char c, int r): Writes the r (between 1 and 16) least
// significant bits of the specified char.
//
// - void close(): Closes the bit-stream.

// ! write definition point
// ! notes on BinaryDump class
// ? Binary dumps:
//

// ? Limitations:
//
// 1. No Universal Data Compression
// - No single algorithm can compress every bit-stream.
//
// - Continual compression of an already compressed bit-stream doesn't always
// lead to further size reduction.
//
// 2. The Undecidability Problem
//
// - Optimal data compression is an undecidable problem; there's no certain
// strategy for developing the best compression algorithm.
//
// - Some data sets can be significantly compressed because they were generated
// by a simple algorithm, but discovering that algorithm can be challenging.

// * ---RUN LENGTH ENCODING---
// - Used to compress bit-streams.
//
// - The simplest type of redundancy in a bit-stream is long runs of repeated
// bits (ie 0000000111111110000000)
//
// - Run length encoding takes advantage of this redundancy for data compression

// ! finish
// ? Bitmaps
//

// ? void compress()
//
// 1. Initialize variables:
// - char cnt = 0 -> Used to store run-length (int is cast into a char)
// - boolean b = false -> Used to store current bit (true = 1, false = 0)
// - boolean old = false -> Used to store prev. bit (true = 1, false = 0)
//
// 2. Start a loop that will continue until BinaryStdIn is empty:
//
// 2a. Read (current) boolean from the BinaryStdIn:
// - b = BinaryStdIn.readBoolean();
//
// 2b. Check if b equals old:
//
// 2bi. If b does NOT EQUAL old:
// - Write the value of cnt to BinaryStdOut
// - Reset the value of cnt to 0
// - Invert the value of old
//
// 2bii. If b EQUALS old, check if cnt = 255:
//
// 2bii(a). If count < 255:
// - Increment count by 1: cnt++
//
// 2bii(b). If count = 255:
// - Write the value of cnt to BinaryStdOut
// - Reset the value of cnt to 0
// - Write the (new) value of cnt to BinaryStdOut AGAIN
// - Increment count by 1: cnt++

// ! finish
// ? void expand():
//

// * ---HUFFMAN COMPRESSION---
// - Used to compress any text.
//
// - Huffman compression is based on the idea that we can save bits by encoding
// frequently used characters with fewer bits than rarely used characters.
//
// General process:
// 1. Choose a code (bit-string) to associate with each character.
// 2. Frequent char must have codes with fewer bits than non-frequent chars.
// 3. chars (keys) and codes (values) are stored in the codeword (symbol) table
// 4. Substitute characters with their corresponding codes

// ? Prefix-free codes:
//
// - A set of codes, in which no character code is the prefix of another, are
// called "prefix-free codes"
//
// eg:
// 1. {101,1011,0} IS NOT prefix-free (101 is a prefix of 1011)
// 2. {0,110,100,1110,101} IS prefix-free
//
// - All prefix-free codes are UNIQUELY decodable (without needing delimiters).

// ? Trie representation of prefix-free codes:
//
// 1. Links and nodes:
// - Each node in the tree has a key (char) and two children
// - Left links can be seen as 0s and right links can be seen as 1s
//
// 2. Leaf nodes:
// - Every node except the leaf nodes have keys = '' (empty)
// - Leaf node key's contain unique characters from the codeword table.
//
// 3. Paths:
// - Each character's (key's) code (value) is defined by the path from the root
// to the character (key)
// - That's why the data structure is a TRIE (Paths to keys define their values)

// ? class Node implements Comparable<Node>:
// - private char ch -> key
// - private int freq
// - private final Node left
// - private final Node right
//
// CompareTo():
// - Nodes are compared based on their frequencies
// - The "greater" node is the one with the highest frequency

// ? buildTrie():
//
// 1. Initialization:
// - Create an empty MinPQ of Node objects (ie Singleton trees)
// - Nodes with the LOWEST frequency are at the front of the MinPQ.
//
// 2. MinPQ insertion:
// - Insert nodes for all unique characters that occur in the text
// - pq.insert(new Node(c, freq[c], null, null))
//
// 3. WHILE the MinPQ contains one or more nodes:
// - while (pq.size() > 1)
//
// 3a. Merge the 2 smallest nodes:
//
// - Create an "empty" node and make the two smallest nodes in the MinPQ its
// left and right children (respectively)
//
// - Node x = pq.delMin()
// - Node y = pq.delMin()
// - Node parent = new Node('\0', x.freq + y.freq, x, y)
//
// 3b. Insert parent into MinPQ:
// - pq.insert(parent)
//
// 4. When the MinPQ is of size 1:
// - Return the only remaining node (root node)

// ? buildCode():
// - Method creates a lookup table (String[] st) from the given trie.
// - For every char 'x' (key), st['x'] = code (value).
// - The main buildCode() method uses a recursive helper method
//
// Helper method:
//
// 1. Leaf node check:
// - if(x.isLeaf)
//
// 1a. If the node is a leaf:
//
// - Assign the current code (String s) to corresponding index in st[] (index is
// based on the node's character).
//
// - return (IE BASE CASE)
//
// 2. Recursively traverse left:
// - Explore the left child of the current node and add '0' to String s.
// - buildCode(st, x.left, s + '0')
//
// 3. Recursively traverse right:
// - Explore the right child of the current node and add '1' to String s.
// - buildCode(st, x.right, s + '1')

// ? writeTrie():
//
// - The trie needs to be written (or serialized) into the compressed output so
// that it can be read (or deserialized) later when the data is decompressed.
//
// 1. Check if Node is Leaf:
//
// - If the current node x is a leaf node (i.e represents a character), write
// '1' to the output.
//
// - FOLLOWED by the 8-bit ASCII code of the character in the node.
//
// 2. Internal Node:
// - If x is not a leaf node it write '0' to the output.
//
// 3. Recursive Calls:
// - Method then recursively calls itself on the left and right children of x.
// - This continues until all nodes in the trie have been visited.

// ? compress():
//
// 1. Read input:
// - String s = BinaryStdIn.readString()
// - char[] input = s.toCharArray()
//
// 2. Tabulate frequency counts.
// - int[] freq = new int[R]
// - for (int i = 0; i < input.length; i++) freq[input[i]]++
//
// 3. Build Huffman code trie:
// - Node root = buildTrie(freq)
//
// 4. Build code table:
// - String[] st = new String[R]
// - buildCode(st, root, "")
//
// 5. Write trie for decoder:
// - writeTrie(root)
//
// 6. Print no. of characters:
// - BinaryStdOut.write(input.length)
//
// 7. Write Huffman code to BinaryStdOut:
//
// - Iterate over each character in the input, retrieves its corresponding
// Huffman code, and writes this code to the output as a sequence of bits.
//
// 8. Close BinaryStdOut:
// - BinaryStdOut.close()

// ! finish
// ? expand():
//

// * ---LZW COMPRESSION---
// - Used to compress any text.
// - Particularly effective for data with repeating patterns.
//
// - In LZW encoding, every prefix of an input-substring key is also a key,
// allowing it to effectively handle repeating patterns in the input data.

// ? Huffman vs LZW compression:
//
// 1. Static vs Dynamic Codeword Table:
// - Huffman compression uses a static codeword table
// - LZW compression uses a dynamic codeword table.
//
// 2. Compression Method:
//
// - Huffman compression is a prefix coding method that uses variable-length
// codes to represent the source symbols.
//
// - LZW compression is a dictionary-based method that replaces strings of
// characters with single codes.

// ? Codeword table:

// ? LZW tries:
//
// 1. Nodes:
// - Each node in the trie represents a String (key) in the symbol table
// - Each node is labelled with the codeword (value) corresponding to its key
//
// 2. Links:
//
// - Links between nodes represent the concatenation of characters to form
// longer Strings (keys).
//
// - A path from the root to a node in the trie represents the key associated
// with that node.
//
// 3. Longest Prefix Match:
//
// - Trie is used to find the longest prefix match of the input with a key in
// the symbol table.
//
// - This is done by traversing the trie from the root, matching node labels
// with input characters.
//

// * ---LZW DATA STRUCTURE---

// ? Attributes (no constructor needed):
// All attributes are private, static & final!
//
// - int R = 256 -> number of input chars
// - int L = 4096 -> number of codewords (2^12)
// - int W = 12 -> codeword width

// ? compress():
//
// 1. Read the Input:
// - Read the input string using BinaryStdIn.readString().
// - This string is the data that needs to be compressed.
// - String input = BinaryStdIn.readString()
//
// 2. Initialize the Symbol Table:
// - A trie data structure is used as a symbol table.
// - TST<Integer> st = new TST<Integer>()
//
// 3. Fill the Symbol Table:
// - The symbol table is filled single-character strings.
// - for (int i = 0; i < R; i++) st.put("" + (char) i, i)
//
// 4. Initialize the codeword:
//
// - The next available codeword is initialized to R+1 (R is reserved for the
// End of File (EOF) codeword)
//
// - int code = R+1
//
// 5. While there is un-scanned input:
// - while (input.length() > 0)
//
// 5a. Find the longest prefix match:
// - String s = st.longestPrefixOf(input)
//
// Write the codeword to BinaryStdOut:
// - BinaryStdOut.write(st.get(s), W)
//
// 5b. If input.length() > 0 AND TST is not of max size:
// - Add new string to symbol table
// - st.put(input.substring(0, t + 1), code++)
//
// 5c. Update the input:
// - Removes the processed characters from the input
// - input = input.substring(t)
//
// 6. Write EOF to BinaryStdOut:
// - Only after all characters in the input have been processed
// - BinaryStdOut.write(R, W)
//
// 7. Close BinaryStdOut:
// - BinaryStdOut.close()

// ? expand():
//
// 1. Initialize the symbol table:
// - An array of strings is used as a symbol table.
// - String[] st = new String[L]
//
// 2. Fill the symbol table:
// - The symbol table is filled with single-character strings.
// - for (i = 0; i < R; i++) st[i] = "" + (char) i
// - st[i++] = " " (lookahead for EOF)
//
// 3. Read the first codeword:
// - The first codeword is read from BinaryStdOut
// - int codeword = BinaryStdIn.readInt(W)
// - String val = st[codeword]
//
// 4. While there is un-scanned input:
// - while (true)
//
// 4a. Write the current string:
// - The string associated with current codeword is written to BinaryStdOut
// - BinaryStdOut.write(val)
//
// 4b. Read the Next Codeword:
// - The next codeword is read from the input.
// - codeword = BinaryStdIn.readInt(W)
// - If the codeword equals R (EOF), break the loop.
// - if (codeword == R) break
//
// 4c. Get the Next String:
// - The string associated with the next codeword is retrieved.
// - String s = st[codeword]
//
// - If the next codeword is not yet assigned, make a new string by appending
// the first character of the current string to itself.
//
// - if (i == codeword) s = val + val.charAt(0)
//
// 4d. Add New String to Symbol Table:
//
// - If the symbol table has not reached its maximum size, a new string is added
// to the symbol table. The new string is formed by appending the first
// character of the next string to the current string.
//
// - if (i < L) st[i++] = val + s.charAt(0)
//
// 4e. Update the Current String:
// - The current string is updated to the next string.
// - val = s
//
// 5. Close the Output Stream:
// - Finally, the output stream is closed.
// - BinaryStdOut.close()
