package co.id.mii.serverside.controller;
import co.id.mii.serverside.model.Country;
import co.id.mii.serverside.model.Region;
import co.id.mii.serverside.service.CountryService;
import co.id.mii.serverside.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/country")
public class CountryController {
    private CountryService countryService;
    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @GetMapping
    public ResponseEntity<List<Country>> getAll() {
        return new ResponseEntity(countryService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/regionId/{id}")
    public ResponseEntity<List<Country>> findByRegionId(@PathVariable Long id) {
        return new ResponseEntity(countryService.findByRegionId(id), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Country> getById(@PathVariable Long id) {
        return new ResponseEntity(countryService.getById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Country> create(@RequestBody Country country) {
        return new ResponseEntity(countryService.create(country), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> update(@PathVariable Long id, @RequestBody Country country) {
        return new ResponseEntity(countryService.update(id, country), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Country> delete(@PathVariable Long id) {
        return new ResponseEntity(countryService.delete(id), HttpStatus.OK);
    }

}
