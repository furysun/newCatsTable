package catsTable.model.dao.impl;

import catsTable.model.db.CatNotFoundException;
import catsTable.model.domain.Cat;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class ClassCatDaoTest {

    @Test
    public void shouldGetCat() throws Exception {
        // given
        ClassCatDao classCatDao = ClassCatDao.getInstance();

        // when
        int size = classCatDao.getCats().size();

        // then
        assertEquals(5, size);
    }

    @Test
    public void shouldDeleteCatByIdGetMessage() throws Exception {
        // given
        ClassCatDao classCatDao = ClassCatDao.getInstance();

       // when
        try {
            classCatDao.delete(3);
        } catch (CatNotFoundException e) {

       // then
            assertEquals("Cat not found", e.getMessage());
        }
    }

    @Test
    public void shouldDeleteCatById() throws Exception {
        // given
        ClassCatDao classCatDao = ClassCatDao.getInstance();

        int before = classCatDao.getCats().size();
        // when
        classCatDao.delete(3);

        int after = classCatDao.getCats().size();
        // then
        assertEquals(before - 1, after);
    }


    @Test
    public void shouldUpdateCat() throws Exception {
        // given
        ClassCatDao classCatDao = ClassCatDao.getInstance();

        Cat cat = new Cat();
        cat.setId(0);
        cat.setName("Tomt");

        // when
        classCatDao.update(cat);

        Cat result = classCatDao.getCats().get(0);

        //then
        assertEquals("Tomt", result.getName());
    }

    @Test
    public void shouldCreateCat() throws Exception {
        // given
        ClassCatDao classCatDao = ClassCatDao.getInstance();

        Cat cat = new Cat();
        cat.setId(10);
        cat.setName("Tommy");

        // when
        classCatDao.createCat(cat);

        // then
        List<Cat> catList = classCatDao.getCats();
        assertEquals(cat, catList.get(catList.size() - 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldUpdateEx(){
        // given
        ClassCatDao classCatDao = ClassCatDao.getInstance();

        //when
        classCatDao.update(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCreateEx() {
        // given
        ClassCatDao classCatDao = ClassCatDao.getInstance();

        // when
        classCatDao.createCat(null);
    }

}