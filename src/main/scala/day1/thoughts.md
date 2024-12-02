# Day 1
https://adventofcode.com/2024/day/1

# Thoughts
We have to scan through each list to get the first smallest number, and the next, etc.
So the fastest operation would be to order both lists (2n), and then calculate distance element by element => 2n + n = O(3n) = O(n) 

Can we do this in place?
