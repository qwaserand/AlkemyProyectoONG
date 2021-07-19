package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.model.Testimony;
import alkemy.challenge.Challenge.Alkemy.repository.TestimonyRepository;
import alkemy.challenge.Challenge.Alkemy.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestimonyService {

    @Autowired
    private TestimonyRepository testimonyRepository;

    public ResponseEntity<?> createTestimony(Testimony testimony) {
        testimonyRepository.save(testimony);
        return ResponseEntity.ok("testimonio creado con éxito");
    }

    public Page<Testimony> getPageTestimony(Pageable pageable){
        return testimonyRepository.findByDeletedFalse(pageable);
    }

    public ResponseEntity<?> updateTestimony(Testimony testimony, Long id) {
        Optional<Testimony> testimonyAux = testimonyRepository.findByIdAndDeletedFalse(id);
        if (testimonyAux.isEmpty()){
            return new ResponseEntity(new Message("no se ha encontrado un testimonio con el id: "+id),
                    HttpStatus.NOT_FOUND);
        }
        testimonyAux.get().setName(testimony.getName());
        testimonyAux.get().setContent(testimony.getContent());
        testimonyAux.get().setImage(testimony.getImage());
        testimonyRepository.save(testimonyAux.get());
        return ResponseEntity.ok("testimonio actualizado con exito");
    }

    public ResponseEntity deleteTestimony(Long id) {
        Optional<Testimony> testimony = testimonyRepository.findByIdAndDeletedFalse(id);
        if (testimony.isEmpty()) return new ResponseEntity("no se ha encontrado un testimonio con el id: "+id,HttpStatus.NOT_FOUND);
        testimony.get().setDeleted(true);
        testimonyRepository.save(testimony.get());
        return new ResponseEntity("testimonio eliminado con exito",HttpStatus.ACCEPTED);
    }
}
