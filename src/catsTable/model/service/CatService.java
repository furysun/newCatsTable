package catsTable.model.service;

import catsTable.model.dao.CatDao;
import catsTable.model.dao.impl.classDao.CatStorage;
import catsTable.model.dao.impl.classDao.ClassCatDao;
import catsTable.model.dao.impl.fileDao.FileCatDao;
import catsTable.model.domain.Cat;

import java.util.List;

public class CatService {
    private CatDao catDao;
    private static CatService catService;

    public static CatService getInstance() {
        if (catService == null) {
            catService = new CatService();
        }
        return catService;
    }

    private CatService() {
        catDao = FileCatDao.getInstance();
    }

    public List<Cat> getCats() {
        return catDao.getCats();
    }

    public void createCat(Cat cat) {
        catDao.createCat(cat);
    }

    public void delete(int id) {
        catDao.delete(id);
    }

    public void update(Cat cat) {
        catDao.update(cat);
    }
}
