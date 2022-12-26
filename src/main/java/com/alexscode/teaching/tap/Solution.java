//package com.alexscode.teaching.tap;
//
//import java.io.*;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Solution {
//    List<Integer> seq;
//    String instance_id;
//    int timeBudget;
//    int maxDistance;
//    int nEtu;
//    double interest;
//
//    public Solution(Instance ist, List<Integer> sequence, int nEtudiant) {
//        this.seq = sequence;
//        nEtu = nEtudiant;
//        timeBudget = ist.getTimeBudget();
//        maxDistance = ist.getMaxDistance();
//        instance_id = ist.getFileUsed();
//    }
//
//    public void writeToFile(String path) {
//        // Create file
//        try {
//            FileWriter fstream = new FileWriter
//                    (path);
//            BufferedWriter out = new BufferedWriter(fstream
//            );
//            // write with the "," separator
//            out.write(instance_id + "," + nEtu + "," + timeBudget + "," + maxDistance + "," + interest + "," + seq.stream().map(Object::toString).collect(Collectors.joining("-"))+ "," + distance + "," + time + "," + interest);
//            // Close the output stream
//            out.close();
//        } catch (Exception e) {//Catch exception if any
//            System.err.println("Error: " + e.getMessage());
//        }
//    }
//}
//
