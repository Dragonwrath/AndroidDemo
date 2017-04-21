package com.joke.mock.junit;


import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.fail;

@RunWith(Categories.class)
@Categories.IncludeCategory(SlowTests.class)
@Suite.SuiteClasses({ g_A.class, g_B.class })
// Note that Categories is a kind of Suite
public class g_CategoriesTester {
    // Will run g_A.b and g_B.c, but not g_A.a
}

interface FastTests { /* category marker */
}

interface SlowTests { /* category marker */
}

