package testeconsultas.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testeconsultas.manytomany.entity.Lazer;

@Repository
public interface ILazerRepository extends JpaRepository<Lazer, Long> {
}
