package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.model.Slide;
import alkemy.challenge.Challenge.Alkemy.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@ApiIgnore
@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    private SlideService slideService;
    
    /*listado de slides con la imagen y el orden del mismo*/
    @GetMapping
    public List<Slide> listSlides(){
        return slideService.listSlides();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> detailSlide(@PathVariable(value = "id") Long id){
        return slideService.detailSlide(id);
    }

    @PostMapping
    public ResponseEntity<Slide> createSlide(@RequestBody @Valid Slide slide, byte[] file){
        slideService.createSlide(file, slide);
        return ResponseEntity.ok(slide);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Slide> updateSlide(@PathVariable(value = "id")Long id,
                                             @Valid @RequestBody Slide slideDetails){
                return slideService.updateSlide(id, slideDetails);
            }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSlide(@PathVariable(value = "id") Long id){
        return slideService.deletedSlide(id);
    }
}
