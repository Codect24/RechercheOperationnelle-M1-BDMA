package com.alexscode.teaching;

import com.alexscode.teaching.tap.Instance;
import com.alexscode.teaching.tap.Objectives;
import com.alexscode.teaching.tap.TAPSolver;

import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TAPSolver solver = new TestHMax20();
        TAPSolver solverMax = new TestHMax();
        TAPSolver solverSimple = new TestHSimple();
        TAPSolver solverNaif = new TestNaif();
        TAPSolver solverTime = new TestHTime();

        Instance f4_small = Instance.readFile("./instances/f4_tap_0_20.dat", 330, 27);
        Instance f4_1_big = Instance.readFile("./instances/f4_tap_1_400.dat", 6600, 540);
        Instance f4_4_big = Instance.readFile("./instances/f4_tap_4_400.dat", 6600, 540);
        Instance f1_3_big = Instance.readFile("./instances/f1_tap_3_400.dat", 6600, 540);
        Instance f1_9_big = Instance.readFile("./instances/f1_tap_9_400.dat", 6600, 540);
        Instance tap_10_medium = Instance.readFile("./instances/tap_10_100.dat", 1650, 135);
        Instance tap_11_big = Instance.readFile("./instances/tap_11_250.dat", 4125, 338);
        Instance tap_13_midium = Instance.readFile("./instances/tap_13_150.dat", 4475, 203);
        Instance tap_14_big = Instance.readFile("./instances/tap_14_400.dat", 6600, 540);
        Instance tap_15_small = Instance.readFile("./instances/tap_15_60.dat", 990, 81);
        // ----------------- Selections des parametres -----------------
        Instance selected = f1_3_big;
        TAPSolver selected_solver = solverTime;

        Objectives obj = new Objectives(selected);
        List<Integer> solution = selected_solver.solve(selected);
        System.out.println("Solution : " + solution);
        // Nombre de queries
        System.out.println("Nombre de queries : " + solution.size());
        System.out.println("Interet: " + obj.interest(solution));
        System.out.println("Temps: " + obj.time(solution));
        System.out.println("Distance: " + obj.distance(solution));

        System.out.println("Feasible ? " + isSolutionFeasible(selected, solution));
    }

    public static boolean isSolutionFeasible(Instance ist, List<Integer> sol){
        Objectives obj = new Objectives(ist);
        return obj.time(sol) <= ist.getTimeBudget() && obj.distance(sol) <= ist.getMaxDistance() && sol.size() == (new TreeSet<>(sol)).size();
    }
}