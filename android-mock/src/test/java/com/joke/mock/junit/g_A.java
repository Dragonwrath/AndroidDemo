package com.joke.mock.junit;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class g_A {
    @Test
    public void a() {
        System.out.println("Class g_A ---Method a");
    }

    @Category(SlowTests.class)
    @Test
    public void b() {
        System.out.println("Class g_A ---Method b");
    }
}
