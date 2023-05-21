package com.sky.service;

import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> selectCategoryByPage(CategoryPageQueryDTO categoryPageQueryDTO);


}
