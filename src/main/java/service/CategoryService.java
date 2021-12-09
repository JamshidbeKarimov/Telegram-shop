package service;

import model.Category;
import repository.CategoryRepository;

import java.util.List;
import java.util.UUID;

public class CategoryService extends CategoryRepository {

    @Override
    public Category get(UUID id) {
        return null;
    }

    @Override
    public List<Category> getList() {
        return null;
    }

    @Override
    public List<Category> getList(UUID id) {
        return null;
    }

    @Override
    public String add(Category category) {
        return null;
    }

    @Override
    public String editById(UUID id, Category category) {
        return null;
    }

    @Override
    public String toggleActivation(UUID id) {
        return null;
    }
}
