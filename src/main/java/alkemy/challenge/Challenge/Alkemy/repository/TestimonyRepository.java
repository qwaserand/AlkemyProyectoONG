package alkemy.challenge.Challenge.Alkemy.repository;

import alkemy.challenge.Challenge.Alkemy.model.Testimony;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestimonyRepository extends JpaRepository<Testimony, String> {

    Optional<Testimony> findByIdAndDeletedFalse(Long id);

    Page<Testimony> findByDeletedFalse(Pageable pageable);
}
