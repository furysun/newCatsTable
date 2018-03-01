package catsTable.model.dao.impl.classDao;

import catsTable.model.dao.impl.testData.TestDataLoader;
import catsTable.model.domain.Cat;
import catsTable.model.exception.CatNotFoundException;

import java.util.List;

public class CatStorage {
    private static CatStorage catStorage;

    private List<Cat> cats;

    public static CatStorage getInstance() {
        if (catStorage == null) {
            catStorage = new CatStorage();
        }
        return catStorage;
    }

    private CatStorage() {
        TestDataLoader testDataLoader = TestDataLoader.getInstance();
        cats = testDataLoader.getTestCats();
    }

    public Cat findCatById(int id) {
        for (Cat cat : cats) {
            if (cat.getId() == id) {
                return cat;
            }
        }

        throw new CatNotFoundException("Cat not found");
    }

    public List<Cat> getCats() {
        return cats;
    }
}
