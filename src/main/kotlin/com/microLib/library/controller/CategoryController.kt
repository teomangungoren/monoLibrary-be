package com.microLib.library.controller

import com.microLib.library.domain.model.Category
import com.microLib.library.domain.request.CreateCategoryRequest
import com.microLib.library.domain.response.BookResponse
import com.microLib.library.domain.response.CategoryResponse
import com.microLib.library.service.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin("*")
class CategoryController(private val categoryService: CategoryService) {

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/create")
    fun createCategory(@RequestBody createCategoryRequest: CreateCategoryRequest):ResponseEntity<*> {
       return ResponseEntity(categoryService.create(createCategoryRequest),HttpStatus.CREATED)
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/{id}")
    fun getCategoryById(@PathVariable id:String):ResponseEntity<Category>{
        return ResponseEntity(categoryService.findById(id),HttpStatus.OK)
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/all")
    fun getCategories():ResponseEntity<List<CategoryResponse>>{
        return ResponseEntity(categoryService.getAll(),HttpStatus.OK)
    }



}