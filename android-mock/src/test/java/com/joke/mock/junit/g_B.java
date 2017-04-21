package com.joke.mock.junit;

import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category({ SlowTests.class, FastTests.class })
public class g_B {
    @Test
    public void c() {
        System.out.println("Class g_B ---Method c");
    }
}
