package com.games.demo.service.impl;

import com.games.demo.entity.Category;
import com.games.demo.repository.CategoryRepository;
import com.games.demo.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    //List<Category> getAllGameCategories();
}
