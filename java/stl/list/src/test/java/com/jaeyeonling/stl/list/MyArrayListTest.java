package com.jaeyeonling.stl.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyArrayListTest implements MyListTest {
    private MyList<Integer> list;

    @Before @Override
    public void init() {
        this.list = new MyArrayList<>();
    }

    @Test @Override
    public void T1_add() {
        // given
        final int value = 100;
        this.list.add(value);

        // when
        final int size = this.list.size();
        final int addedValue = this.list.get(0);

        // then
        Assert.assertEquals(size, 1);
        Assert.assertEquals(addedValue,  value);
    }

    @Test @Override
    public void T2_addAll() {
        // given
        final MyList<Integer> newList = new MyArrayList<>();
        newList.add(100);
        newList.add(200);

        // when
        final boolean isAdd = this.list.addAll(newList);
        final int size = this.list.size();

        // then
        Assert.assertTrue(isAdd);
        Assert.assertEquals(newList.size(), size);
    }

    @Test @Override
    public void T3_remove() {
        // given
        final Integer value = 100;
        this.list.add(value);

        // when
        final int removeBeforeSize = this.list.size();
        final boolean isRemove = this.list.remove(value);
        final int removeAfterSize = this.list.size();

        // then
        Assert.assertEquals(removeBeforeSize, 1);
        Assert.assertTrue(isRemove);
        Assert.assertEquals(removeAfterSize, 0);
    }

    @Test @Override
    public void T4_removeAll() {
        // given
        final Integer value1 = 100;
        final Integer value2 = 200;
        final Integer value3 = 300;

        final MyList<Integer> newList = new MyArrayList<>();
        newList.add(value1);
        newList.add(value2);
        newList.add(value3);

        this.list.add(1000);

        // when
        final int addBeforeSize = this.list.size();
        final boolean isAdd = this.list.addAll(newList);
        final int addAfterSize = this.list.size();
        final boolean isRemove = this.list.removeAll(newList);
        final int removeAfterSize = this.list.size();

        // then
        Assert.assertEquals(addBeforeSize, 1);
        Assert.assertTrue(isAdd);
        Assert.assertEquals(addAfterSize, 4);
        Assert.assertTrue(isRemove);
        Assert.assertEquals(removeAfterSize, 1);
    }

    @Test @Override
    public void T5_clear() {
        // given
        this.list.add(100);

        // when
        final int clearBeforeSize = this.list.size();
        final boolean isClear = this.list.clear();
        final int clearAfterSize = this.list.size();

        // then
        Assert.assertEquals(clearBeforeSize, 1);
        Assert.assertTrue(isClear);
        Assert.assertEquals(clearAfterSize, 0);
    }

    @Test @Override
    public void T6_get() {
        // given
        final int value1 = 100;
        final int value2 = 100;
        final int value3 = 100;

        this.list.add(value1);
        this.list.add(value2);
        this.list.add(value3);

        // when
        final int addedValue1 = this.list.get(0);
        final int addedValue2 = this.list.get(1);
        final int addedValue3 = this.list.get(2);

        // then
        Assert.assertEquals(addedValue1, value1);
        Assert.assertEquals(addedValue2, value2);
        Assert.assertEquals(addedValue3, value3);
    }

    @Test @Override
    public void T7_toArray() {
        // given
        this.list.add(100);
        this.list.add(200);
        this.list.add(300);

        // when
        final Object[] array = this.list.toArray();

        // then
        Assert.assertEquals(this.list.size(), array.length);
        Assert.assertEquals(this.list.get(0), array[0]);
        Assert.assertEquals(this.list.get(1), array[1]);
        Assert.assertEquals(this.list.get(2), array[2]);
    }
}
