package com.alexscode.teaching;

import com.alexscode.teaching.tap.Instance;
import com.alexscode.teaching.tap.Objectives;
import com.alexscode.teaching.tap.TAPSolver;
import com.alexscode.teaching.utilities.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestHMax implements TAPSolver {
    @Override
    public List<Integer> solve(Instance ist) {
        // ist est une instance de donnée qui contient sa taille, l'interet de chaque queries, le temps de chaque queries, la distance de chaque queries.

        List<Element> demo = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        Objectives obj = new Objectives(ist);

        // on ajoute les queries dans une liste d'elements
        for (int i = 0; i < ist.getSize(); i++) {
            demo.add(new Element(i, Math.pow(ist.getInterest()[i],12)*Math.log(ist.getCosts()[i])));
        }
        // on trie la liste d'elements
        Collections.sort(demo);
        // on reverse la liste pour avoir les queries dans l'ordre
        Collections.reverse(demo);

        // on ajoute ensuite les queries qui maximisent l'interet et qui respectent les contraintes de temps et de distance
        while (obj.distance(result) < ist.getMaxDistance() && obj.time(result) < ist.getTimeBudget()){
            for (int i = 0; i < demo.size(); i++) {
                if (!result.contains(demo.get(i).getIndex())) {
                    result.add(demo.get(i).getIndex());
                    break;
                }
            }
        }

        result.remove(result.size() - 1);
        // on reverse la liste pour avoir les queries dans l'ordre
        System.out.println("Distance max : "+ ist.getMaxDistance());
        System.out.println("Time max : "+ ist.getTimeBudget());
        return result;
    }
}
