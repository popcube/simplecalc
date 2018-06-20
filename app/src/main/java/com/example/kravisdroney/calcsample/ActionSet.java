package com.example.kravisdroney.calcsample;

import android.view.View;

public interface ActionSet {
    public abstract State inNumber(View view);
    public abstract State inOperator(View view);
    public abstract State inEqual(View view);
    public abstract State inDot(View view);
}
