package com.aakash.dsa.dynamic.programming;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubSequenceNaive {
    public static void main(String[] args) {
        String s1 = "AXYZ";
        String s2 = "BAZ";

        String longestCommonSubSequence = longestSubSequence(s1, s2);
        System.out.println(longestCommonSubSequence);
    }

    public static void generateSubSequences(String s, int index, String current, List<String> subsequences){
        // Base case : If we have reached the end of the string add the current subsequence
        if (index == s.length()){
            subsequences.add(current);
            return;
        }

        // Include the current character to the subsequence and move to the next character
        generateSubSequences(s, index + 1, current + s.charAt(index), subsequences);

        // Exclude the current character to the subsequence and move to the next character
        generateSubSequences(s, index + 1, current, subsequences);
    }

    public static String longestSubSequence(String s1, String s2){
        // Generate all the subsequences of s1 and s2
        List<String> subSequences1 = new ArrayList<>();
        List<String> subSequences2 = new ArrayList<>();
        generateSubSequences(s1,0, "", subSequences1);
        generateSubSequences(s2, 0, "", subSequences2);

        // Find the longest common subsequence between the two lists
        String longestCommonSubSequence = "";
        for(String seq1 : subSequences1){
            for (String seq2: subSequences2){
                // If common subSequence found and is greater than the longest so far update it
                if (seq1.equals(seq2) && seq1.length() > longestCommonSubSequence.length()){
                    longestCommonSubSequence = seq1;
                }
            }
        }

        return longestCommonSubSequence;
    }
}
