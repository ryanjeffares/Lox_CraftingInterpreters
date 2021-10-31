# 4.7 - Reserved Words and Identifiers
* **Maximal Munch**: when two lexical grammar rules can both match a chunk of code the scanner is looking at, *whichever one matches the most characters wins*.
* E.g., if `or` is a keyword and we name a variable `orchid`, `orchid` wins. This rule also means that `<=` can pe parsed as a single token and not `<` followed by `=`.
