package com.jaeyeonling.stl.list;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public interface MyListTest {
    // @Before
    void init();

    // @Test
    void T1_add();
    // @Test
    void T2_addAll();

    // @Test
    void T3_remove();
    // @Test
    void T4_removeAll();

    // @Test
    void T5_clear();

    // @Test
    void T6_get();

    // @Test
    void T7_toArray();
}
