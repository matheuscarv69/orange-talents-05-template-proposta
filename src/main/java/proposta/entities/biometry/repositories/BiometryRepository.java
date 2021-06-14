package proposta.entities.biometry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proposta.entities.biometry.entities.Biometry;

public interface BiometryRepository extends JpaRepository<Biometry, Long> {
}
