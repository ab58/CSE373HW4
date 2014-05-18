Enter file contents hereCSE 373 Homework 4 Write up


1.	Who is in your group (Give name, UW NetID & student number of each person)?

Patrick Harper-Joles (hatrick42) and Arjun Bhalla (ab58) 1363119	

2.	a) How did you design your tests & what properties did you test? 

We tested with the files given to us, mainly with hamlet.txt. We tested the full integration of our system, from the iterators returning the proper values to the values themselves getting inserted into the right places according to the hash function.

b) What boundary cases did you consider?

In the case of hashing strings and linked lists of strings (literally DataCount methods, but essentially strings), we considered cases such as the load factor being exceeded. For iterator methods, we traversed the implementing array, and had to consider the case of the array being at the last index, as well as some indexes containing null but subsequent indexes containing meaningful objects.


3.	Conduct an experiment to determine which DataCounter implementation (HashTable_SC, HashTable_OP) is better for large input texts.
a) Describe your experimental setup: 
    1) Inputs used
    2) How you collected timing information
    3) Any details that would be needed to replicate your experiments

b) Experimental Results (Place your graphs and tables of results here).
    
c) Interpretation of Experimental Results
    1) What did you expect about the results and why?
    2) Did your results agree with your expectations? Why or Why not?
    3) According to your experiment, which Hashtable implementation, separate chaining or open addressing, is better?


4.	Conduct experiments to determine if changing the hash function affects the runtime of your HashTable.
a)	Brief description of your hash functions

We could not run the code to completion, but we had one hash function. This hash function is simple; it takes the string, splits it into a character array, and sums up the ASCII values of all the characters in 

b) Experimental Results (Place your graphs and tables of results here).
     Experiment with at least 2 hash functions (2 Hashing functions = 2 experiments depending on how you measured the runtime)
     Don’t forget to give each graph a title and label the axes.

c) Interpretation (Your expectations and why? Did it match your results? If not, why?)


5.	Using Correlator, does your experimentation suggest that Bacon wrote Shakespeare's plays? 
Show at least one (you can experiment with more texts if you want) correlation value for each of:
a) Shakespeare's work compared to Shakespeare's work
b) Bacon's work compared to Bacon's work
c) Shakespeare's work compared to Bacon's work
According to the results of your experiments, did Bacon write Shakespeare's plays?


6.	Include a description of how your project goes "above and beyond" the basic requirements (if it does).


7.	If you worked with a partner: 
            a) Describe the process you used for developing and testing your code. If you divided it, describe 
                that. If you did everything together, describe the actual process used (eg. how long you talked 
                about what, what order you wrote and tested, and how long it took).
            b) Describe each group member's contributions/responsibilities in the project.
            c) Describe at least one good thing and one bad thing about the process of working together.

a)	For developing, we split up the tasks. Patrick implemented HashTable_OA.java as well as getCountsArray in WordCount.java, Correlator.java, did most of the debugging, and ran most of the testing. Arjun implemented HashTable_SC.java, StringComparator.java, StringHasher.java, and wrote most of the writeup. We communicated primarily through e-mail, and sometimes met in person to talk about our issues and help each other come up with implementation ideas, and also helped each other debug. Overall it took just over a week.
b)	Arjun’s contributions were the initial stages of the core methods for comparing and hashing, and he also wrote the first Hash Table class to get the project off the ground. Patrick took it from there and coded the remainder of the implementations and ran the testing and debugging. Arjun was entrusted with the writeup.
c)	We had a fairly good interaction with helping each other understand the task(s) at hand. For the actual implementation and testing, it was very difficult. In pair programming, vastly different coding styles is a disadvantage, and that was what we ran into. Trying to debug each other’s code was not the easiest thing and we had to take a lot of extra time explaining to each other what was going on, and ultimately did not figure out everything we needed to.


8.	a) Which parts of the project were most difficult? 
Coding the iterators was by far the most difficult part of the project. Neither of us had ever had to code our own iterators and we found this task tedious and were getting confused by all the steps and conditions we had to consider.
b) How could the project be better?
The purpose of this project was to learn to code Hashtables, but we also needed to think on a whole different school of procedural thought to code iterators, as well as our own unique comparison methods. Neither of us is used to much procedural programming, and knowledge of C-type tasks should be beyond the scope of this course, and that is what I felt it was like. There should not be this much emphasis/reliance on this since it is not intended to be the purpose of the assignment.





Appendix

Place anything else that you want to add here.
