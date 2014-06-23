package com.soulmonk.ndfsm.service.jpa.angular;

import com.soulmonk.ndfsm.beans.Category;
import com.soulmonk.ndfsm.service.angular.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    private static List<Category> categoryList = new ArrayList<Category>();
    private static Long id = 0L;

    public CategoryServiceImpl() {
        initializeCategories();
    }

    private void initializeCategories() {
        for (long i = 0; i < 10; i++) {
            Category category = new Category();
            category.setId(i + 1);
            category.setName("Category " + (i + 1));
            categoryList.add(category);
        }
    }

    public List<Category> getAllCategories() {
        return categoryList;
    }

    public void addCategory(Category category) {
        category.setId(id);
        categoryList.add(category);
    }

    public void deleteCategory(Long id) {
        Category foundCategory = findCategoryById(id);
        if (foundCategory != null) {
            categoryList.remove(foundCategory);
        }
    }

    public void deleteAll() {
        categoryList.clear();
    }

    private Category findCategoryById(Long id) {
        for (Category category : categoryList) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

}
