package com.tarenda.league;

public enum Points {
    WIN(3),
    DRAW(1),
    LOSS(0);

    public final Integer point;

    Points(Integer point) {
        this.point = point;
    }
}
