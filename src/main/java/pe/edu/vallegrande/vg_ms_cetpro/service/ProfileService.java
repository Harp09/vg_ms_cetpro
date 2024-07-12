package pe.edu.vallegrande.vg_ms_cetpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.vg_ms_cetpro.model.Profile;
import pe.edu.vallegrande.vg_ms_cetpro.repository.ProfileRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Flux<Profile> getByStatus(String status) {
        return profileRepository.findByStatus(status);
    }

    public Mono<Profile> create(Profile profile) {
        profile.setStatus("A");
        return profileRepository.save(profile);
    }

    public Mono<Profile> update(String id, Profile profile) {
        return profileRepository.findById(id)
                .flatMap(p -> {
                    p.setName(profile.getName());
                    p.setModularCode(profile.getModularCode());
                    p.setDreGre(profile.getDreGre());
                    p.setManagementType(profile.getManagementType());
                    p.setStatus("A");
                    return profileRepository.save(p);
                });
    }

    public Mono<Profile> changeStatus(String id, String status) {
        return profileRepository.findById(id)
                .flatMap(p -> {
                    p.setStatus(status);
                    return profileRepository.save(p);
                });
    }

    public Mono<Profile> getById(String id) {  // Método añadido
        return profileRepository.findById(id);
    }
}