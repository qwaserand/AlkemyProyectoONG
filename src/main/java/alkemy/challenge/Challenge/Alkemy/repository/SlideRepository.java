package alkemy.challenge.Challenge.Alkemy.repository;

import alkemy.challenge.Challenge.Alkemy.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {

    List<Slide> findByOrganizationId_idOrderBySequence(Long id);

    List<Slide> findAllByOrderBySequence();

}
