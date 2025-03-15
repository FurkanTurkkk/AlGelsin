package com.AlGelsin.category_service.service;

import com.AlGelsin.category_service.converter.CategoryDtoConverter;
import com.AlGelsin.category_service.dto.CreateCategoryRequestDto;
import com.AlGelsin.category_service.dto.UpdateCategoryRequestDto;
import com.AlGelsin.category_service.exception.CategoryAlreadyExist;
import com.AlGelsin.category_service.exception.CategoryNotFoundException;
import com.AlGelsin.category_service.model.Category;
import com.AlGelsin.category_service.repository.CategoryRepository;
import org.AlGelsin.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter converter;

    public CategoryService(CategoryRepository categoryRepository, CategoryDtoConverter converter) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
    }


    public CategoryDto createCategory(CreateCategoryRequestDto request) {
        checkByCategoryNameForCreateCategory(request.getName());
        Category category = categoryRepository.save(new Category(
                request.getName(),
                request.getParentCategory()
        ));
        return converter.convert(category);
    }

    public CategoryDto getCategoryWithProducts(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            throw new CategoryNotFoundException("Category could not found");
        }
        return converter.convert(category.get());
    }

    private void checkByCategoryNameForCreateCategory(String name) {
        Optional<Category> category = categoryRepository.findByName(name);
        if(category.isPresent()){
            throw new CategoryAlreadyExist("Category already exist by name : "+name);
        }
    }


    public void updateCategory(UpdateCategoryRequestDto request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()->new CategoryNotFoundException("Category could not found by id : "+request.getCategoryId()));
        category.setParentCategoryId(request.getParentCategoryId());
        categoryRepository.save(category);
    }
}
