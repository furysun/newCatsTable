package catsTable.model.dao.impl.testData;

import catsTable.model.domain.Cat;
import catsTable.model.domain.CatColor;

import java.util.ArrayList;
import java.util.List;

public class TestDataLoader {
    private static TestDataLoader instance;

    private TestDataLoader() {

    }

    public static TestDataLoader getInstance() {
        if (instance == null) {
            instance = new TestDataLoader();
        }
        return instance;
    }


    public List<Cat> getTestCats() {
        List<Cat> cats = new ArrayList<>();

        cats.add(new Cat("Tom", 1, CatColor.RED, 1));
        cats.add(new Cat("Tim", 2, CatColor.BLACK, 2));
        cats.add(new Cat("Mia", 3, CatColor.STRIPED, 4));
        cats.add(new Cat("Rose", 2, CatColor.WHITE, 1));
        cats.add(new Cat("Pri", 4, CatColor.RED, 5));

        return cats;
    }
}
