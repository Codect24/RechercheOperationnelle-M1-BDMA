package com.alexscode.teaching;

import com.alexscode.teaching.tap.Instance;
import com.alexscode.teaching.tap.Objectives;
import com.alexscode.teaching.tap.TAPSolver;
import com.alexscode.teaching.utilities.Element;

import java.util.*;

public class TestHSortInterest4DistanceUltimateLastHope implements TAPSolver {

    public List<Integer> solve(Instance ist, int last, int hope) {
        // ist est une instance de donnée qui contient sa taille, l'interet de chaque queries, le temps de chaque queries, la distance de chaque queries.

        List<Element> demo = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        Objectives obj = new Objectives(ist);

        // on ajoute les queries dans une liste d'elements
        for (int i = 0; i < ist.getSize(); i++) {
            double ratio = ist.getInterest()[i];
            demo.add(new Element(i, ratio));
        }
        // on trie la liste d'elements par ordre decroissant de ratio
        Collections.sort(demo);
        // on reverse la liste pour avoir les queries dans l'ordre
        Collections.reverse(demo);

        // on ajoute ensuite les queries qui maximisent l'interet et qui respectent les contraintes de temps et de distance
        result.add(demo.get(0).getIndex());
        demo.remove(0);
        while (obj.distance(result) <= ist.getMaxDistance() && obj.time(result) <= ist.getTimeBudget()) {
            int index_min = 0;
            // On fait une map pour stocker toute les distance liées a i et on prend la plus petite
            Map<Integer, Double> map = new HashMap<>();
            for (int j = 0; j < demo.size(); j++) {
                if (ist.getDistances()[result.get(result.size()-1)][demo.get(j).getIndex()] != 0) {
                    map.put(demo.get(j).getIndex(), ist.getDistances()[result.get(result.size()-1)][demo.get(j).getIndex()]);
                }
            }

            // On prend l'index de la valeur minimale
            index_min = Collections.min(map.entrySet(), Map.Entry.comparingByValue()).getKey();
            // On regarde si il n'y a pas d'autre valeur avec max 50% de la valeur minimale
            List<Integer> list = new ArrayList<>();
            for (Map.Entry<Integer, Double> entry : map.entrySet()) {
                if (entry.getValue() <= Collections.min(map.values())) {
                    list.add(entry.getKey());
                }
            }
            // On prend l'index de la query qui maximise l'interet
            if (list.size() > 1) {
                for (int j = 0; j < list.size(); j++) {
                    if (Math.pow(ist.getInterest()[list.get(j)],hope)/Math.pow(ist.getCosts()[list.get(j)],last) >= Math.pow(ist.getInterest()[index_min],hope)/ Math.pow(ist.getCosts()[index_min],last)) {
                        index_min = list.get(j);
                    }
                }
            } else index_min = list.get(0);
            // On ajoute la query a la liste de resultat
            if (!result.contains(index_min)) {
                result.add(index_min);
                // entier qui est l'index de demo qui correspond a la query qui a été ajouté a result
                int index = 999;
                for (int j = 0; j < demo.size(); j++) {
                    if (demo.get(j).getIndex() == index_min) {
                        index = j;
                    }
                }
                demo.remove(index);
                map.clear();
            }
        }
        result.remove(result.size() - 1);
        return result;
    }
    @Override
    public List<Integer> solve(Instance instance) {
        // liste de liste de resultat
        List<List<Integer>> list = new ArrayList<>();
        // index de la liste de resultat qui maximise l'interet
        int index_max = 0;
        // appel de la fonction solve avec les differents parametres allant de 0 a 20 de manniere insyncrone
        for (int i = 0; i < 10; i = i+2) {
            for (int j = 0; j < 20; j=j+2) {
                list.add(solve(instance, i, j));
            }
        }
        // Calcul la valeur de l'interet de chaque query
        Objectives obj = new Objectives(instance);
        for (int i = 0; i < list.size(); i++) {
            if (obj.interest(list.get(i)) > obj.interest(list.get(index_max))) {
                index_max = i;
            }
        }
        return list.get(index_max);
    }
}

