package com.epileftro.dojo.Manhattan.utils;

import com.epileftro.dojo.Manhattan.model.Point;

public class Calculations {
    private Calculations() {
    }

    public static int manhattanCal(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
