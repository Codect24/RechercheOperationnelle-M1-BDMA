package com.alexscode.teaching;

import com.alexscode.teaching.tap.Instance;
import com.alexscode.teaching.tap.Objectives;
import com.alexscode.teaching.tap.TAPSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestSimulatedAnnealing implements TAPSolver {
    @Override
    public List<Integer> solve(Instance ist) {
        // ist est une instance de donnée qui contient sa taille, l'interet de chaque queries, le temps de chaque queries, la distance de chaque queries.
        // Implementation of the simulated annealing algorithm
        TAPSolver solver = new TestHMax20();
        List<Integer> demo = solver.solve(ist);
        Objectives obj = new Objectives(ist);
        double t = 100;
        double bestDistance = obj.interest(demo);
        List<Integer> previousTravel = new ArrayList<>(demo);
        System.out.println("Initial distance of travel: " + obj.interest(demo));
        while (t > 0.1) {
            for (int i = 0; i < demo.size(); i++) {
                if (t > 0.1) {
                    // Si la i est la dernière query de la liste, on la remplace par la première query
                    if (i == demo.size() - 1) {
                        demo = swap(demo, i, 0);
                    } else {
                        demo = swap(demo, i, i + 1);
                    }
                    double currentDistance = obj.interest(demo);
                    if (currentDistance < bestDistance) {
                        bestDistance = currentDistance;
                    } else if (Math.exp((bestDistance - currentDistance) / t) < Math.random()) {
                        demo = previousTravel;
                    }
                    t *= 0.999;
                } else {
                    continue;
                }
                if (i % 100 == 0) {
                    System.out.println("Iteration #" + i);
                }
            }
        }
        return demo;
    }

    private List<Integer> swap(List<Integer> demo, int i, int i1) {
        int temp = demo.get(i);
        demo.set(i, demo.get(i1));
        demo.set(i1, temp);
        return demo;
    }
}
