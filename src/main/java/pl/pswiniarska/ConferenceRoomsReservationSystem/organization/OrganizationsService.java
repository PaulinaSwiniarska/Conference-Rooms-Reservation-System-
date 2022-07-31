package pl.pswiniarska.ConferenceRoomsReservationSystem.organization;

import org.hibernate.annotations.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
class OrganizationsService {
    private final OrganizationsRepository organizationsRepository;

    @Autowired
    public OrganizationsService(OrganizationsRepository organizationsRepository) {
        this.organizationsRepository = organizationsRepository;
    }
    Organizations add(Organizations organizations) {
        organizationsRepository.findById(organizations.getName()).ifPresent(o -> {
            throw new IllegalArgumentException("Organization already exists!");
        });
        return organizationsRepository.save(organizations);

    }

    List<Organizations> findBy(Map<String, String> allParams) {
        if (allParams.containsKey("name")) {
            return findByName(allParams.get("name"));
        } else {
            return findAll();
        }
    }

    List<Organizations> findAll() {
        return organizationsRepository.findAll();
    }

    List<Organizations> findAllDSC() {
        return organizationsRepository.findAll(Sort.by("name").descending());
    }

    List<Organizations> findAllASC() {
        return organizationsRepository.findAll(Sort.by("name").ascending());
    }

    List<Organizations> findByName(String name) {
        return Collections.singletonList((organizationsRepository.findByName(name)
                .orElseThrow(NoSuchElementException::new)));
    }

    Organizations delete(String name) {
        Organizations organizations = organizationsRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException(""));
        organizationsRepository.deleteById(name);
        return organizations;
    }

    Organizations update(String name, Organizations organizations) {
        Organizations existingOrganizations = organizationsRepository
                .findByName(name)
                .orElseThrow(() -> new NoSuchElementException(""));
        if (organizations.getDescription() != null) {
            existingOrganizations.setDescription(organizations.getDescription());
        }
        if (organizations.getName() != null && !organizations.getName().equals(existingOrganizations.getName())) {
            existingOrganizations.setName(organizations.getName());
        }
        return organizationsRepository.save(existingOrganizations);
    }

}