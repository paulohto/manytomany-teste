package testeconsultas.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testeconsultas.manytomany.entity.Residencial;

@Repository
public interface IResidencialRepository extends JpaRepository<Residencial, Long> {
}
