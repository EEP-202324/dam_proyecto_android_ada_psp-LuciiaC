package com.example.Sorteo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/Sorteo")//??
class SorteoController {
    private final SorteoRepository sorteoRepository;

    private SorteoController(SorteoRepository sorteoRepository) {
        this.sorteoRepository = sorteoRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Sorteo> findById(@PathVariable Long requestedId, Principal principal) {
        Sorteo sorteo = findSorteo(requestedId, principal);
        if (sorteo != null) {
            return ResponseEntity.ok(sorteo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<Void> createSorteo(@RequestBody Sorteo newSorteoRequest, UriComponentsBuilder ucb, Principal principal) {
        Sorteo sorteoWithOwner = new Sorteo(null, newSorteoRequest.amount(), principal.getName());
        Sorteo savedSorteo = sorteoRepository.save(sorteoWithOwner);
        URI locationOfNewSorteo = ucb
                .path("Sorteo/{id}")//??
                .buildAndExpand(savedSorteo.id())
                .toUri();
        return ResponseEntity.created(locationOfNewSorteo).build();
    }

    @GetMapping
    private ResponseEntity<List<Sorteo>> findAll(Pageable pageable, Principal principal) {
        Page<Sorteo> page = sorteoRepository.findByOwner(principal.getName(),
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
                ));
        return ResponseEntity.ok(page.getContent());
    }

    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> putSorteo(@PathVariable Long requestedId, @RequestBody Sorteo sorteoUpdate, Principal principal) {
        Sorteo sorteo = findSorteo(requestedId, principal);
        if (sorteo != null) {
            Sorteo updatedSorteo = new Sorteo(requestedId, sorteoUpdate.amount(), principal.getName());
            sorteoRepository.save(updatedSorteo);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private Sorteo findSorteo(Long requestedId, Principal principal) {
        return sorteoRepository.findByIdAndOwner(requestedId, principal.getName());
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteSorteo(
        @PathVariable Long id,
        Principal principal // Add Principal to the parameter list
    ) {
    	if (sorteoRepository.existsByIdAndOwner(id, principal.getName())) {
    	    sorteoRepository.deleteById(id);
    	    return ResponseEntity.noContent().build();
    	}
    	return ResponseEntity.notFound().build();
}
}

