
Code Formatter :Eclipse formatter can import and export in intelieJ
Security code scan : By plugin eclipse can connect with CheckMarx

Git Stream 
Custom,QA,Master
Feature Branch alwys should creasted from Custom branch 
Code Branc stratigy : 
Need to discuss from whic branch QA ,UAT pipleline take code 
one option crate JAR from Custom deploy in QA and after QA passed then merge in QA branch then create jar from QA and deploy in UAT and once UAT passed merge in UAT branch and one UAT pass then merge in UAT ..
and final JAR will fo Nexus repository 


collection

List -->ArrayList ,LinkedList
Set -->HashSet,LinkedHashSet,TreeSet ( sorted Set)
Map-->Hashmap,TreeMap,ConcurenacehashMap( Bucket level Lock) 

HashSet: If you don’t want to maintain insertion order but want to store unique objects. 
LinkedHashSet: If you want to maintain the insertion order of elements then you can use LinkedHashSet. 
TreeSet: If you want to sort the elements according to some Comparator then use TreeSet.

266


TreeMap is an example of a SortedMap, which means that the order of the keys can be sorted, and when iterating over the keys, you can expect that they will be in order.

HashMap on the other hand, makes no such guarantee. Therefore, when iterating over the keys of a HashMap, you can't be sure what order they will be in.

HashMap will be more efficient in general, so use it whenever you don't care about the order of the keys.
