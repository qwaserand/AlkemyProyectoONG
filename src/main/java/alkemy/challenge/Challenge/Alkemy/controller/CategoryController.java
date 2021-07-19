package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.model.Category;
import alkemy.challenge.Challenge.Alkemy.service.CategoryService;
import alkemy.challenge.Challenge.Alkemy.util.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<?> showCategory(@PathVariable Long id){
        Optional<Category> categoryAux = categoryService.findById(id);
        if (categoryAux.isEmpty()) {
            return new ResponseEntity(new Message("no se ha encontrado una categoria con el id: "+id),
                    HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(categoryAux.get());
    }

    @PostMapping
    public ResponseEntity<?> addCategories(@RequestBody @Valid Category category) {
        categoryService.createCategories(category);
        return new ResponseEntity(new Message("categoria creada."), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategories(@PathVariable("id") Long id, @RequestBody @Valid Category category) {
        if (!StringUtils.isAlpha(category.getName())) {
            return new ResponseEntity(new Message("Debe contener solo letras"),
                    HttpStatus.BAD_REQUEST);
        }
        Optional<Category> categoryAux = categoryService.findById(id);
        if (categoryAux.isEmpty()) {
            return new ResponseEntity(new Message("no se ha encontrado una categoria con el id: "+id),
                    HttpStatus.NOT_FOUND);
        }
        return categoryService.update(category, categoryAux.get());
    }

    @GetMapping
    public Page<Category> listCategories(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return categoryService.findAll(pageable);//el listado debería ser solo de nombres de categorias, usando el metedo categoryService.listCategoriesByName();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isEmpty()) return new ResponseEntity(new Message("no se ha encontrado una categoria con el id: "+id), HttpStatus.NOT_FOUND);
        categoryService.delete(category.get());
        return ResponseEntity.ok("News eliminada con exito");
    }
}
