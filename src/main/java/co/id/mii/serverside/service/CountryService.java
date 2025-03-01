package co.id.mii.serverside.service;
import co.id.mii.serverside.model.Country;
import co.id.mii.serverside.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class CountryService {
    private CountryRepository countryRepository;
    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country getById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country Not Found!"));
    }
    public Country create(Country country) {
        if (country.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country already exist");
        }
        findByName(country.getName());
        return countryRepository.save(country);
    }
    public Country update(Long id, Country country){
        getById(id);
        country.setId(id);
        return countryRepository.save(country);
    }
    public Country delete(Long id){
        Country country = getById(id);
        countryRepository.delete(country);
        return country;
    }
    public void findByName(String name) {
        if (countryRepository.findByName(name).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country already exists");
        }
    }
    public List<Country> findByRegionId(Long id){
        return countryRepository.findByRegionId(id);
    }
}
