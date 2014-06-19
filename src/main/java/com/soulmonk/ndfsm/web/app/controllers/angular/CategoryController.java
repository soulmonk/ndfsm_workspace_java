package com.soulmonk.ndfsm.web.app.controllers.angular;

import com.soulmonk.ndfsm.beans.Category;
import com.soulmonk.ndfsm.service.angular.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/angular/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/categoryList.json")
    public @ResponseBody
    List<Category> getCategoryList() {
        return categoryService.getAllCategories();
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public @ResponseBody
    void addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }

    @RequestMapping(value = "/removeCategory/{category}", method = RequestMethod.DELETE)
    public @ResponseBody
    void removeCategory(@PathVariable("category") Long id) {
        categoryService.deleteCategory(id);
    }

    @RequestMapping(value = "/removeAllCategories", method = RequestMethod.DELETE)
    public @ResponseBody
    void removeAllCategorys() {
        categoryService.deleteAll();
    }

    @RequestMapping("/layout")
    public String getCategoryPartialPage() {
        return "angular/categories/layout";
    }
}
