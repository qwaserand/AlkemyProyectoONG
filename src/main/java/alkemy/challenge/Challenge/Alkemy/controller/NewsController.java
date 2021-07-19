package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.model.Comment;
import alkemy.challenge.Challenge.Alkemy.model.News;
import alkemy.challenge.Challenge.Alkemy.service.CommentService;
import alkemy.challenge.Challenge.Alkemy.service.NewsService;
import alkemy.challenge.Challenge.Alkemy.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @Autowired
    CommentService commentService;

    @GetMapping
    public Page<News> listNews(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return newsService.findAll(pageable);
    }

    //@PreAuthorize("hasRole(ROLE_ADMIN)") en un comienzo, esto lo filtro con los antMatcher
    @GetMapping("/{id}")
    public ResponseEntity<?> bringNews(@PathVariable long id) {
        Optional<News> news = newsService.findById(id);
        if (news.isEmpty()) return new ResponseEntity(new Message("no se ha encontrado una news con el id: "+id), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(news.get());
    }

    //Listar todos los comentarios de un post
    @GetMapping("/{id}/comments")
    public List<Comment> listComments(@PathVariable Long id) {
        return commentService.listComments(id);
    }

    @PostMapping
    public News newNews(@RequestBody @Valid News news) {
        return newsService.create(news);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Optional<News> news = newsService.findById(id);
        if (news.isEmpty()) return new ResponseEntity(new Message("no se ha encontrado una news con el id: "+id), HttpStatus.NOT_FOUND);
        newsService.delete(news.get());
        return ResponseEntity.ok("News eliminada con exito");
    }

}
