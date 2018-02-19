package catsTable.model.dao.impl;

import catsTable.model.domain.Cat;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class ClassCatDaoTest {

    @Test
    public void testGetCat() throws Exception {
        ClassCatDao classCatDao = ClassCatDao.getInstance();

        int size = classCatDao.getCats().size();
        assertEquals(5, size);
    }

    @Test
    public void testDeleteCatByIdGetMessage() throws Exception {
        ClassCatDao classCatDao = ClassCatDao.getInstance();

        try {
            classCatDao.delete(3);
        }catch (NoSuchElementException e){
            assertEquals("Cat not found", e.getMessage());
        }
    }

    @Test
    public void testDeleteCatById() throws Exception {
        ClassCatDao classCatDao = ClassCatDao.getInstance();

        int before = classCatDao.getCats().size();

        classCatDao.delete(3);

        int after = classCatDao.getCats().size();

        assertEquals(before - 1, after);
    }


    @Test
    public void testUpdate() throws Exception {
        ClassCatDao classCatDao = ClassCatDao.getInstance();

        Cat cat = new Cat();
        cat.setId(0);
        cat.setName("Tomt");

        classCatDao.update(cat);

        Cat result = classCatDao.getCats().get(0);
        assertEquals("Tomt", result.getName());
    }

    @Test
    public void testCreate() throws Exception {
        ClassCatDao classCatDao = ClassCatDao.getInstance();

        Cat cat = new Cat();
        cat.setId(10);
        cat.setName("Tommy");

        classCatDao.createCat(cat);

        List<Cat> catList = classCatDao.getCats();
        assertEquals(cat, catList.get(catList.size() - 1));
    }
}