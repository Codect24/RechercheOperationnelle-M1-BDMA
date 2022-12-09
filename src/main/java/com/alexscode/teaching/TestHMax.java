package com.alexscode.teaching;

import com.alexscode.teaching.tap.Instance;
import com.alexscode.teaching.tap.Objectives;
import com.alexscode.teaching.tap.TAPSolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestHMax implements TAPSolver {
    @Override
    public List<Integer> solve(Instance ist) {
        // ist est une instance de donnée qui contient sa taille, l'interet de chaque queries, le temps de chaque queries, la distance de chaque queries.

        List<Integer> demo = new ArrayList<>();
        Objectives obj = new Objectives(ist);

        // on commence par ajouter la query qui maximise l'interet
        int q_idx = 0;


        // on ajoute ensuite les queries qui maximisent l'interet et qui respectent les contraintes de temps et de distance
        while (obj.distance(demo) < ist.getMaxDistance() && obj.time(demo) < ist.getTimeBudget()){
            double max_ratio = 0;
            for (int i = 0; i < ist.getSize(); i++) {
                for (int j = 0; j < ist.getSize(); j++) {
                    if (Math.pow(ist.getInterest()[i],2)*Math.pow(ist.getDistances()[i][j]*ist.getTimeBudget(),2) > max_ratio && !demo.contains(i)) {
                        max_ratio = Math.pow(ist.getInterest()[i],2)*Math.pow(ist.getDistances()[i][j]*ist.getTimeBudget(),2);
                        q_idx = i;
                    }
                }
            }
            demo.add(q_idx);
        }
        demo.remove(demo.size() - 1);
        // on reverse la liste pour avoir les queries dans l'ordre
        System.out.println("Distance max : "+ ist.getMaxDistance());
        System.out.println("Time max : "+ ist.getTimeBudget());
        return demo;
    }
}
