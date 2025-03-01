package co.id.mii.serverside.repository;

import co.id.mii.serverside.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByName(String name);

    // JPQL for Entity
    @Query("SELECT c FROM Country c WHERE c.region.id = ?1")
    List<Country> findByRegionId(Long id);


    // JPQL for Native Query
    @Query(value = "SELECT * FROM tb_country WHERE region_id = ?1", nativeQuery = true)
    List<Country> findByRegionIdNative(Long id);

}
