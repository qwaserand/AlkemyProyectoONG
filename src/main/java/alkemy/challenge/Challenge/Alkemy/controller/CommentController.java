package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.model.Comment;
import alkemy.challenge.Challenge.Alkemy.service.CommentService;
import alkemy.challenge.Challenge.Alkemy.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@ApiIgnore
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    @PostMapping()
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        commentService.save(comment);
        return new ResponseEntity(new Message("Comentario creado"), HttpStatus.OK);
    }
    
    //Devuelve todos los comentarios, solo el campo body
    @GetMapping
    public List<String> getBodies() {
        return commentService.ListBody();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> commentAct = commentService.findById(id);
        if (commentAct.isEmpty()) return new ResponseEntity("no se ha encontrado un comentario con el id: " + id, HttpStatus.NOT_FOUND);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        if (!username.equals(commentAct.get().getUserId().getUsername()) || !commentAct.get().getUserId().getRole().getName().equals("ROLE_ADMIN")) {
            return new ResponseEntity("el usuario no tiene permisos para editar el comentario" + id, HttpStatus.FORBIDDEN);
        }
        return commentService.update(comment, commentAct.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isEmpty()) return new ResponseEntity(new Message("no se ha encontrado un comentario con el id: "+id), HttpStatus.NOT_FOUND);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        if (!username.equals(comment.get().getUserId().getUsername()) || !comment.get().getUserId().getRole().getName().equals("ROLE_ADMIN")) {
            return new ResponseEntity("el usuario no tiene permisos para eliminar el comentario" + id, HttpStatus.FORBIDDEN);
        }
        commentService.delete(comment.get());
        return ResponseEntity.ok("News eliminada con exito");
    }
}

