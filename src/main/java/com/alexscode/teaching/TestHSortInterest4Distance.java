package com.alexscode.teaching;

        import com.alexscode.teaching.tap.Instance;
        import com.alexscode.teaching.tap.Objectives;
        import com.alexscode.teaching.tap.TAPSolver;
        import com.alexscode.teaching.utilities.Element;

        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;

public class TestHSortInterest4Distance implements TAPSolver {
    @Override
    public List<Integer> solve(Instance ist) {
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

        // on ajoute les queries dans la liste de resultats qui ont la distance la plus faible
//        while (obj.distance(result) <= ist.getMaxDistance() && obj.time(result) <= ist.getTimeBudget()) {
//        for (int i = 0; i < demo.size() && obj.distance(result) <= ist.getMaxDistance() && obj.time(result) <= ist.getTimeBudget(); i++) {
//            double bestDistination = 9;
//            int bestIndex = demo.get(i).getIndex();
//            for (int j = 0; j < demo.size() && obj.distance(result) <= ist.getMaxDistance() && obj.time(result) <= ist.getTimeBudget(); j++) {
//                if (ist.getDistances()[demo.get(i).getIndex()][demo.get(j).getIndex()] <= bestDistination && obj.distance(result) <= ist.getMaxDistance() && obj.time(result) <= ist.getTimeBudget()) {
//                    bestDistination = ist.getDistances()[demo.get(i).getIndex()][demo.get(j).getIndex()];
//                    bestIndex = demo.get(j).getIndex();
//                }
//            }
//            result.add(bestIndex);
//        }
//        }
//        result.remove(result.size() - 1);

        // on ajoute ensuite les queries qui maximisent l'interet et qui respectent les contraintes de temps et de distance
        while (obj.distance(result) <= ist.getMaxDistance() && obj.time(result) <= ist.getTimeBudget()){
            if (ist.getDistances()[demo.get(0).getIndex()][demo.get(1).getIndex()] > ist.getDistances()[demo.get(1).getIndex()][demo.get(2).getIndex()]){
                checkIsExist(demo, result, 1, 2);
            } else checkIsExist(demo, result, 0, 1);
        }
        result.remove(result.size() - 1);
        result.remove(result.size() - 1);


        return result;
    }

    static void checkIsExist(List<Element> demo, List<Integer> result, int low, int high) {
        if (!result.contains(demo.get(low).getIndex()) && !result.contains(demo.get(high).getIndex())) {
            result.add(demo.get(low).getIndex());
            result.add(demo.get(high).getIndex());
            demo.remove(low);
            demo.remove(low);
        }
    }
}

