package dev.antoniogrillo.primoprogettoesempio.repository;

import dev.antoniogrillo.primoprogettoesempio.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UtenteRepo extends JpaRepository<Utente,Long> {

    Optional<Utente> findByEmailAndPassword(String email,String password);

    @Query("select u from Utente u where u.email = :email and u.password = :miaPassword")
    Optional<Utente> loginJPQL(String email,@Param("miaPassword") String password);

    @Query(nativeQuery = true, value="select * from elenco_utenti_db where email = :email and password = :password")
    Optional<Utente> loginSQL(String email,String password);

    Optional<Utente> findByEmail(String email);
}
