package pe.edu.vallegrande.vg_ms_cetpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.vg_ms_cetpro.model.Profile;
import pe.edu.vallegrande.vg_ms_cetpro.service.ProfileService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/list/active")
    public ResponseEntity<Flux<Profile>> listActive() {
        return ResponseEntity.ok(profileService.getByStatus("A"));
    }

    @GetMapping("/list/inactive")
    public ResponseEntity<Flux<Profile>> listInactive() {
        return ResponseEntity.ok(profileService.getByStatus("I"));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Mono<Profile>> getProfileById(@PathVariable String id) {
        return ResponseEntity.ok(profileService.getById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<Mono<Profile>> create(@RequestBody Profile profile) {
        return ResponseEntity.ok(profileService.create(profile));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mono<Profile>> update(@PathVariable String id, @RequestBody Profile profile) {
        return ResponseEntity.ok(profileService.update(id, profile));
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Mono<Profile>> activate(@PathVariable String id) {
        return ResponseEntity.ok(profileService.changeStatus(id, "A"));
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Mono<Profile>> deactivate(@PathVariable String id) {
        return ResponseEntity.ok(profileService.changeStatus(id, "I"));
    }
}
