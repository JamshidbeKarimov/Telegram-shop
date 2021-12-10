package service;

import model.Category;
import model.User;
import repository.CategoryRepository;

import java.util.List;
import java.util.UUID;

public class CategoryService extends CategoryRepository {

    @Override
    public Category get(UUID id) {
        for (Category category : categoryList) {
            if (category.getId().equals(id))
                return category;
        }
        return null;
    }

    @Override
    public List<Category> getList() {
        return categoryList;
    }

    @Override
    public List<Category> getList(UUID id) {
        return null;
    }

    @Override
    public String add(Category category) {
        if (isCategoryExist(category.getName())){
            return ERROR_CATEGORY_ALREADY_EXIST;
        }

        categoryList.add(category);
        return SUCCESS;
    }

    @Override
    public String editById(UUID id, Category category) {
        for (Category existCategory : categoryList) {
            if (existCategory.getId().equals(id)) {
                existCategory.setName(category.getName());
                existCategory.setActive(category.isActive());
                existCategory.setCreatedBy(category.getCreatedBy());
                existCategory.setUpdatedBy(category.getUpdatedBy());
                existCategory.setCreatedDate(category.getCreatedDate());
                existCategory.setUpdatedDate(category.getUpdatedDate());

                return SUCCESS;
            }
        }
        return ERROR_USER_NOT_FOUND;
    }

    @Override
    public String toggleActivation(UUID id) {
        return null;
    }

    public boolean isCategoryExist(String categoryName) {
        for (Category category : categoryList) {
            if (category.getName().equals(categoryName)) {
                return true;
            }
        }
        return false;
    }
}
