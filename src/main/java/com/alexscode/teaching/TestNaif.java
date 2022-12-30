package com.alexscode.teaching;

import com.alexscode.teaching.tap.Instance;
import com.alexscode.teaching.tap.Objectives;
import com.alexscode.teaching.tap.TAPSolver;

import java.util.ArrayList;
import java.util.List;

public class TestNaif implements TAPSolver {
    @Override
    public List<Integer> solve(Instance ist) {
        List<Integer> demo = new ArrayList<>();
        Objectives obj = new Objectives(ist);

        int q_idx = 0;

        while (obj.distance(demo) <= ist.getMaxDistance() && obj.time(demo) <= ist.getTimeBudget()){
            demo.add(q_idx++);
        }
        return demo.subList(0, demo.size() - 1);
    }

}
