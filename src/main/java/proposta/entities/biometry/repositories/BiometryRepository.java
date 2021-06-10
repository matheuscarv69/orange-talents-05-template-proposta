package proposta.entities.biometry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proposta.entities.biometry.controllers.Biometry;

public interface BiometryRepository extends JpaRepository<Biometry, Long> {
}
