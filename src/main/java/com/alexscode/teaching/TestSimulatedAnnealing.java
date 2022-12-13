package com.alexscode.teaching;

import com.alexscode.teaching.tap.Instance;
import com.alexscode.teaching.tap.Objectives;
import com.alexscode.teaching.tap.TAPSolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TestSimulatedAnnealing implements TAPSolver {

    @Override
    public List<Integer> solve(Instance ist) {
        Objectives obj = new Objectives(ist);
        double temperature = 1000;
        double coolingFactor = 0.995;
        List<Integer> current = new TestNaif().solve(ist);
        List<Integer> best = current;

        for (double t = temperature; t > 1; t *= coolingFactor) {
            List<Integer> neighbor = current;

            int index1 = (int) (neighbor.size() * Math.random());
            int index2 = (int) (neighbor.size() * Math.random());

            Collections.swap(current, index1, index2);

            double currentLength = obj.time(current);
            double neighborLength = obj.time(neighbor);

            if (Math.random() < probability(currentLength, neighborLength, t)) {
                current = neighbor;
            }

            if (obj.time(current) < obj.time(best)) {
                best = current;
            }
        }

        System.out.println("Final tour length: " + obj.time(best));
        System.out.println("Tour: " + best);

        return best;
    }
    public static double probability(double f1, double f2, double temp) {
        if (f2 < f1) return 1;
        return Math.exp((f1 - f2) / temp);
    }
}
