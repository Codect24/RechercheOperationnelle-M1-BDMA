package com.alexscode.teaching;

import com.alexscode.teaching.tap.Instance;
import com.alexscode.teaching.tap.Objectives;
import com.alexscode.teaching.tap.TAPSolver;

import java.util.*;


public class Main {
    public static void main(String[] args) {

        // Compteur de temps d'execution
        long startTime = System.nanoTime();
        TAPSolver solver = new TestHMax20();
        TAPSolver solverMax = new TestHMax();
        TAPSolver solverSimple = new TestHSimple();
        TAPSolver solverNaif = new TestNaif();
        TAPSolver solverTime = new TestHTime();
        TAPSolver solverSimulatedAnnealing = new TestSimulatedAnnealing();
        TAPSolver solverInterest4Distance = new TestHSortInterest4Distance();
        TAPSolver solverInterest4Cost = new TestHSortInterest4Cost();
        TAPSolver solverInterest4Distance5 = new TestHSortInterest4DistanceTestOpt();
        TAPSolver solverCost4Distance5 = new TestHSortCost4DistanceTestOpt();
        TAPSolver solverInterest4DistanceUltimate = new TestHSortInterest4DistanceUltimate();
        TAPSolver solverInterest4DistanceUltimateEdit = new TestHSortInterest4DistanceUltimateEdit();
        TAPSolver solverInterest4DistanceUltimateLH = new TestHSortInterest4DistanceUltimateLastHope();

        // Add to a list of solvers
        List<TAPSolver> solvers = List.of(solverInterest4DistanceUltimateLH, solverCost4Distance5, solverInterest4DistanceUltimate, solverInterest4Distance5, solverInterest4Distance, solverInterest4Cost, solverTime, solverSimple, solverMax, solver, solverNaif, solverSimulatedAnnealing, solverInterest4DistanceUltimateEdit);

        Instance f4_small = Instance.readFile("./instances/f4_tap_0_20.dat", 330, 27);
        Instance f4_1_big = Instance.readFile("./instances/f4_tap_1_400.dat", 6600, 540);
        Instance f4_4_big = Instance.readFile("./instances/f4_tap_4_400.dat", 6600, 540);
        Instance f1_3_big = Instance.readFile("./instances/f1_tap_3_400.dat", 6600, 540);
        Instance f1_9_big = Instance.readFile("./instances/f1_tap_9_400.dat", 6600, 540);
        Instance tap_10_medium = Instance.readFile("./instances/tap_10_100.dat", 1200, 150);
        Instance tap_11_big = Instance.readFile("./instances/tap_11_250.dat", 1200, 250);
        Instance tap_13_medium = Instance.readFile("./instances/tap_13_150.dat", 1200, 150);
        Instance tap_14_big = Instance.readFile("./instances/tap_14_400.dat", 6600, 540);
        Instance tap_15_small = Instance.readFile("./instances/tap_15_60.dat", 330, 27);
        // Add to a list of instances
        List<Instance> instances = List.of(f4_4_big, f4_1_big, f1_3_big, f1_9_big, tap_10_medium, tap_11_big, tap_13_medium, tap_14_big, tap_15_small, f4_small);

        // ----------------- Selections des parametres -----------------
        // Pour chaque instance on va faire tourner chaque solveur
        // On va stocker les resultats dans une liste de liste
        Map<Instance, List<List<Double>>> results = new HashMap<>();
        for (Instance instance : instances) {
//            System.out.println("--------------- Instance : " + instance.getFileUsed()+ " ---------------");
            for (TAPSolver solverInstance : solvers) {
//                System.out.println("Solver : " + solverInstance.getClass().getSimpleName());
                List<Integer> solution = solverInstance.solve(instance);
                Objectives obj = new Objectives(instance);
//                System.out.println("Distance : " + obj.distance(solution));
//                System.out.println("Time : " + obj.time(solution));
//                System.out.println("Interest : " + obj.interest(solution));
//                System.out.println("Sequence : " + solution);
//                System.out.println("Feasible ? " + isSolutionFeasible(instance, solution));
//                System.out.println("--------------------------------------------------");
//                System.out.println();
                results.putIfAbsent(instance, new ArrayList<>());
                double feasibility = isSolutionFeasible(instance, solution) ? 1 : 0;
                results.get(instance).add(List.of(obj.distance(solution), obj.time(solution), obj.interest(solution), feasibility));
//                Solution sol = new Solution(instance, solution, 21903169);
//                sol.writeToFile("./solutions/result.sol");
            }
//            System.out.println();
        }

        // Faire un tableau avec les resultats
        System.out.println("Instance\tSolver\tDistance\tTime\tInterest\tFeasible");
        for (Instance instance : results.keySet()) {
            for (int i = 0; i < results.get(instance).size(); i++) {
                System.out.println(instance.getFileUsed() + "\t\t" + solvers.get(i).getClass().getSimpleName() + "\t" + results.get(instance).get(i).get(0) + "\t" + results.get(instance).get(i).get(1) + "\t" + results.get(instance).get(i).get(2) + "\t" + results.get(instance).get(i).get(3));
            }
            // Sort results by interest and print the best
            results.get(instance).sort(Comparator.comparingDouble(o -> o.get(2)));
            System.out.println("Best interest : " + results.get(instance).get(results.get(instance).size() - 1));
            System.out.println();
        }
        // Compteur de temps d'execution
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Temps d'execution : " + duration / 1000000000 + " secondes");
    }

    public static boolean isSolutionFeasible(Instance ist, List<Integer> sol){
        Objectives obj = new Objectives(ist);
        return obj.time(sol) <= ist.getTimeBudget() && obj.distance(sol) <= ist.getMaxDistance() && sol.size() == (new TreeSet<>(sol)).size();
    }
}