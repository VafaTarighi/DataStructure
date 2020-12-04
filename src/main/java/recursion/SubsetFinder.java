package recursion;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SuppressWarnings("unchecked")
public class SubsetFinder<T> {

    private Set<T> set;
    private Iterator<T> setItr;

    public Set<HashSet<T>> getSubsets(HashSet<T> set) {
        // assign a clone of the given set to a field to easy access and modify
        this.set = (Set<T>) set.clone();
        // create an iterator for the set for element removal
        setItr = this.set.iterator();
        // a set of subsets of the the original set
        HashSet<HashSet<T>> subsets = new HashSet<>();

        collectSubsets(subsets);
        // return collected subsets
        return subsets;
    }

    private void collectSubsets(HashSet<HashSet<T>> subsets) {
        // Base Case: if the given set is empty then return a empty set az it's sub set
        if (!setItr.hasNext()) {
            subsets.add(new HashSet<>(0));
            return;
        }

        // remove an element A from the set
        T A = setItr.next();
        setItr.remove();

        // collect subsets of smaller set
        collectSubsets(subsets);

        // set of subsets that do not include A
        Set<HashSet<T>> subsetsANotIncluded = new HashSet<>();
        // copying all the collected subsets that do not include A to the temporary set(subsetsANotIncluded)
        subsets.forEach(set -> subsetsANotIncluded.add((HashSet<T>) set.clone()));

        // add A to all of sets in subsets
        subsets.forEach(set -> set.add(A));

        // add all of sets that not contain A to subsets
        subsets.addAll(subsetsANotIncluded);

    }

    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();

        set.add("a");
        set.add("b");
        set.add("c");

        SubsetFinder<String> sf = new SubsetFinder<>();
        System.out.println(sf.getSubsets(set));
    }

}
