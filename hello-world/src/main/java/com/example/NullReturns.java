// Copyright (c) 2026, Black Duck Software, Inc. All rights reserved worldwide.

package com.example;

public class NullReturns {
    String callee(char [] c) {
        if (c == null) return null;
        else return new String(c);
    }

    void caller() {
        String str = callee(null);
        System.out.println(str.toString());
    }
}

