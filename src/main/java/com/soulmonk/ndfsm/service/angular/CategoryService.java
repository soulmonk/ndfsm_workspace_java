package com.soulmonk.ndfsm.service.angular;

import com.soulmonk.ndfsm.beans.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    void addCategory(Category category);

    void deleteCategory(Long category);

    void deleteAll();

}
