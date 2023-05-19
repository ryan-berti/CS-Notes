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

// ! finish
// ? buildCode():
//

// ! finish
// ? buildTrie():
//

// ! finish
// ? writeTrie():
//

// ! finish
// ? compress():
//

// ! finish
// ? expand():
//

// ! finish
// * ---LZW COMPRESSION---
//
