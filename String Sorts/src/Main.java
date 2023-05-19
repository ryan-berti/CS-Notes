// * ---SPECIAL ALGORITHMIC PROPERTIES OF STRINGS---
//
// 1. Can be seen as sequences from specific alphabets (ie ASCII)
// - Allows for more efficient processing & data representation
//
// 2. Character access and length determination have a time complexity of O(1)
//
// 3. Immutability
// - Allows for safe use in assignment statements and arguments/return values
// from methods without worrying about their values changing.

// * ---SIDE NOTE: STABLE VS NON-STABLE---
// - A sorting algorithm is STABLE if two objects with equal keys appear in the
// same order in the sorted output as they appear in the input to be sorted.
//
// - If the input order is not preserved, the sorting algorithm is UNSTABLE.

// * ---KEY INDEXED COUNTING---
// - Particularly efficient for sorting when keys are small integers
//
// - General process:
// 1. Compute frequency counts
// 2. Transform counts to indices
// 3. Distribute the data
// 4. Copy back

// ? Compute frequency counts:
// - Calculate the frequency of each key using count[]
//
// - for (i = 0; i < N; i++) count[a[i].key() + 1]++;
//
// - If the key value is r, we increment count[r+1] (where r is the key value)
//
// - count[0] = 0 (always) so that we can handle all keys uniformly in a loop:
// count[r] is always the sum of count[0,1,2...r-1], EVEN WHEN r = 1

// ? Transform counts to indices:
//
// - Transform the count array such that count[r] doesn't represent the
// frequency of r, but rather the starting index in the final, sorted array for
// the items with key r-1.
//
// - for (int r = 0; r < R; r++) count[r+1] += count[r];
//
// - count[r] = count[0] + count[1] + ... + count[r-1]

// ? Distribute the data:
// - Distribute the data into a temporary auxiliary array
//
// - Each item to be sorted is placed into aux[] array at the index specified by
// its key in count[]
//
// - for (int i = 0; i < N; i++) aux[count[a[i].key()]++] = a[i];

// ? Copy back:
// - Copy the sorted data from the auxiliary array back to the original array.
//
// - for (int i = 0; i < N; i++) a[i] = aux[i];

// * ---LSD STRING SORTS---
// - LSD: least-significant-digit first
//
// - Designed to sort strings of equal length, but alterations can be made
// (ie pad shorter strings with "smallest" character

// ? Stability:
// - LSD string-sort STABLY sorts fixed-length strings.
// - Uses ~ 7WN + 3WR array accesses
//
// - Uses extra space proportional to N + R to sort N items whose keys are
// W-character strings taken from an R-character alphabet.

// ? Algorithm:
//
// 1. Initialize variables:
// - N: length of array to be sorted
// - R: Radix = 256 (suitable for ASCII characters)
// - String[] aux = new String[N]
//
// 2. Iterate from d = W-1 to d = 0; where W = string length
//
// 2a. Initialize count[]:
// - int[] count = new int[R+1]
// - Length = R+1 so that count[0] = 0 (always)
//
// 2b. Transform counts to indices:
// - Convert the count[] to hold the cumulative counts.
// - count[r] now holds the total count of characters that are less than r.
// - for (int r = 0; r < R; r++) count[r+1] += count[r]
//
// 2c. Distribution into auxillary array:
// - Distribute the strings into aux[] based on their current character.
// - for (int i = 0; i < N; i++) aux[count[a[i].charAt(d)]++] = a[i]
//
// 2d. Copy Back:
// - Copy strings from the aux[] back to the original array.
// - for (int i = 0; i < N; i++) a[i] = aux[i]
//
// 3. Algorithm complete:
// - Original array is sorted based on the character at the dth position.

// * ---MSD STRING SORTS---
// - MSD: most-significant-digit first
//
// - Characters are considered in left-to-right order, this allows for strings
// to be different lengths.
//
// - We use key-indexed counting to sort strings according to their 1st
// character, then RECURSIVELY sort the sub-arrays corresponding to each
// character, excluding the 1st character (since it is the same for each string
// in each sub-array).

// ? End of string convention:
// - Sub-arrays for strings whose characters have all been examined should
// appear as the first sub-array, and should not be recursively sorted.
//
// To facilitate this:
//
// 1. Use toChar() to convert from an indexed string character to an array index
// (returns -1 if the specified character position is past the end of the
// string).
//
// 2. Add 1 to each returned value, to get a nonnegative int to index count[].

// ? Algorithm:
//
// 1. Initialize variables:
// - N: length of array to be sorted
// - R: Radix = 256 (suitable for ASCII characters)
// - M: Cutoff for small sub-arrays
// - String[] aux = new String[N]
// - int[] count = new int[R+2]
//
// 2. Start sorting based on the most significant character/digit
// - sort(String[] a, int lo, int hi, int d)
//
// 2a. Compute frequency counts:
// - for (int i = lo; i <= hi; i++) count[charAt(a[i], d) + 2]++;
//
// 2b. Transform counts to indices:
// - Convert the count[] to hold the cumulative counts.
//
// - count[r+1] holds total count of characters less than or
// equal to r.
//
// - for (int r = 0; r < R+1; r++) count[r+1] += count[r]
//
// 2c. Distribute the data into the auxiliary array:
// - Distribute the strings into aux[] based on their current character.
// - for (int i = lo; i <= hi; i++) aux[count[charAt(a[i], d) + 1]++] = a[i]
//
// 2d. Copy Back:
// - Copy strings from the aux[] back to the original array.
// - for (int i = lo; i <= hi; i++) a[i] = aux[i - lo]
//
// 2e. Recursively sort for each character value:
// - for (int r = 0; r < R; r++) sort(a, lo + count[r], lo + count[r+1] - 1,
// d+1)
//
// 3. Algorithm complete:
// - Original array is sorted based on the most significant character/digit.

// ? Equal keys (issue)
// - MSD sort can be slow for sub-arrays containing large numbers of equal keys.
//
// - Worst case for MSD sort occurs when all keys are equal (the same problem
// arises when large numbers of keys have long common prefixes)

// ? Performance
// - The order of keys is irrelevant but the values themselves are important.
// - For RANDOM inputs, MSD has a sub-linear runtime.
//
// - For NON-RANDOM inputs, MSD string MAY still could be sub-linear (Worst-case
// input is one with all strings equal)

// ? Propositions
// - MSD string sort uses between 8N + 3R and ~7wN + 3WR array accesses.
//
// - the amount of space needed by MSD string sort is proportional to R times
// the length of the longest string (plus N ), in the worst case.