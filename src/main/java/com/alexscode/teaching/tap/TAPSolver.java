package com.alexscode.teaching.tap;

import java.util.List;

public interface TAPSolver {
    public List<Integer> solve(Instance ist);

    List<Integer> solve(Instance instance, int i, int j);
}
