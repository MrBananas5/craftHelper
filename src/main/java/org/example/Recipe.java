package org.example;

import org.example.MatQ;

public class Recipe {
    private float outQ;
    private MatQ[] in;

    public Recipe(float outQ, MatQ[] in) {
        this.outQ = outQ;
        this.in = in;
    }

    public float getOutQ() {
        return outQ;
    }

    public void setOutQ(float outQ) {
        this.outQ = outQ;
    }

    public MatQ[] getIn() {
        return in;
    }

    public void setIn(MatQ[] in) {
        this.in = in;
    }
}
