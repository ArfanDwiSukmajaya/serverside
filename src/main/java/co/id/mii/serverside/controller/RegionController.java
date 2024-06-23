package co.id.mii.serverside.controller;

import co.id.mii.serverside.model.Region;
import co.id.mii.serverside.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    private RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public ResponseEntity<List<Region>> getAll(){
//        return regionService.getAll();
        return new ResponseEntity(regionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getById(@PathVariable long id){
//        return regionService.getById(id);
        return new ResponseEntity(regionService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Region> create(@RequestBody Region region) {
//        return regionService.create(region);
        return new ResponseEntity(regionService.create(region), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> update(@PathVariable Long id, @RequestBody Region region) {
//        return regionService.update(id, region);
        return new ResponseEntity<>(regionService.update(id, region), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Region> delete(@PathVariable Long id) {
//        return regionService.delete(id);
        return new ResponseEntity<>(regionService.delete(id), HttpStatus.OK);
    }

}
