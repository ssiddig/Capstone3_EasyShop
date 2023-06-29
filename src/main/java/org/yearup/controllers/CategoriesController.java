package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping ("/categories")
public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao){
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryDao.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getById (@PathVariable int id) {
    return categoryDao.getById(id);

    }

    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        // get a list of product by categoryId
        return null;
    }

    // add annotation to call this method for a POST action
    // add annotation to ensure that only an ADMIN can call this function
    @PostMapping
    @ResponseStatus(value= HttpStatus.CREATED)
    public Category createCategory (@RequestBody Category category){
        return categoryDao.create(category);

    }

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @PutMapping("/id")
    public HashMap<String, String> updateCategory( @PathVariable int id, @RequestBody Category category){
        categoryDao.update(id, category);

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "Successful");
        response.put("message", "Category updated successfully");
        return response;
    }


    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping ("/id")
        public HashMap<String, String> deleteCategory (@PathVariable int id){
        categoryDao.delete(id);
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "Successful");
        response.put("message", "Category deleted successfully");
        return response;
    }

    {

    }
}
