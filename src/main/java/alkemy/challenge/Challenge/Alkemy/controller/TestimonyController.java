package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.model.Testimony;
import alkemy.challenge.Challenge.Alkemy.service.TestimonyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/testimonials")
public class TestimonyController {

    @Autowired
    private TestimonyService testimonyService;

    @GetMapping
    public Page<Testimony> listTestimony(@PageableDefault(size = 10, page = 0) Pageable pageable){
        Page<Testimony> list = testimonyService.getPageTestimony(pageable);
        return list;
    }

    @PostMapping
    public ResponseEntity<?> newTestimony(@RequestBody @Valid Testimony testimony) {
        return testimonyService.createTestimony(testimony);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> saveResource(@RequestBody Testimony testimony, @PathVariable Long id) {
       return testimonyService.updateTestimony(testimony, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTestimony(@PathVariable Long id) {
        return testimonyService.deleteTestimony(id);
    }

}
