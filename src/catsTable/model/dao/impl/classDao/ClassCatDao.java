package catsTable.model.dao.impl.classDao;

import catsTable.model.dao.CatDao;
import catsTable.model.domain.Cat;

import java.util.List;


public class ClassCatDao implements CatDao {
    private static ClassCatDao instance;
    private CatStorage catStorage;

    private ClassCatDao() {
        catStorage = CatStorage.getInstance();
    }

    public static ClassCatDao getInstance() {
        if (instance == null) {
            instance = new ClassCatDao();
        }

        return instance;
    }

    @Override
    public List<Cat> getCats() {
        return catStorage.getCats();
    }

    @Override
    public void createCat(Cat cat) {
        if (cat == null) {
            throw new IllegalArgumentException();
        }

        List<Cat> cats = catStorage.getCats();
        cats.add(cat);
    }

    @Override
    public void delete(int id) {
        Cat cat = catStorage.findCatById(id);

        List<Cat> cats = catStorage.getCats();
        cats.remove(cat);
    }

    @Override
    public void update(Cat cat) {
        if (cat == null) {
            throw new IllegalArgumentException();
        }

        Cat existingCat = catStorage.findCatById(cat.getId());

        existingCat.setName(cat.getName());
        existingCat.setColor(cat.getColor());
        existingCat.setWeigh(cat.getWeigh());
        existingCat.setAge(cat.getAge());
    }
}
