package catsTable.model.dao.impl.fileDao;

import catsTable.model.dao.CatDao;
import catsTable.model.dao.impl.testData.TestDataLoader;
import catsTable.model.domain.Cat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class FileCatDao implements CatDao {
    private static FileCatDao instance;

    private FileCatDao() {
        TestDataLoader testDataLoader = TestDataLoader.getInstance();
        List<Cat> testCats = testDataLoader.getTestCats();
        write(testCats);
    }

    public static FileCatDao getInstance() {
        if (instance == null) {
            instance = new FileCatDao();
        }
        return instance;
    }

    @Override
    public List<Cat> getCats() {
        return read();
    }

    @Override
    public void createCat(Cat cat) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Cat cat) {

    }

    private void write(List<Cat> cats) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/yana/Desktop/cats.dat"))) {
            oos.writeObject(cats);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private List<Cat> read() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/yana/Desktop/cats.dat"))) {
            return  (List<Cat>) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }
}
