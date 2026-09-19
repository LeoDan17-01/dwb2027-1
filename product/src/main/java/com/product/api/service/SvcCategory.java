package com.product.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;

public interface SvcCategory {
    List<Category> getCategories();
}