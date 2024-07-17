package com.example.empresa.controller;

import com.example.empresa.model.PQRS;
import com.example.empresa.services.PQRSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pqrs")
public class PQRSController {

    @Autowired
    private PQRSService pqrsService;

    @PostMapping("/create")
    public ResponseEntity<PQRS> createPQRS(@RequestBody PQRS pqrs) {
        PQRS createdPQRS = pqrsService.createPQRS(pqrs.getType(), pqrs.getDescription(), pqrs.getUser().getUserId());
        return new ResponseEntity<>(createdPQRS, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PQRS>> getAllPQRS() {
        List<PQRS> pqrsList = pqrsService.getAllPQRS();
        return new ResponseEntity<>(pqrsList, HttpStatus.OK);
    }

    @GetMapping("/{pqrsId}")
    public ResponseEntity<PQRS> getPQRSById(@PathVariable Long pqrsId) {
        Optional<PQRS> pqrsOptional = pqrsService.getPQRSById(pqrsId);
        return pqrsOptional.map(pqrs -> new ResponseEntity<>(pqrs, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{pqrsId}/update-status")
    public ResponseEntity<PQRS> updatePQRSStatus(@PathVariable Long pqrsId, @RequestParam String status) {
        PQRS updatedPQRS = pqrsService.updatePQRSStatus(pqrsId, status);
        if (updatedPQRS != null) {
            return new ResponseEntity<>(updatedPQRS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{pqrsId}/add-comment")
    public ResponseEntity<PQRS> addCommentToPQRS(@PathVariable Long pqrsId, @RequestParam String comment) {
        PQRS pqrsWithComment = pqrsService.addCommentToPQRS(pqrsId, comment);
        if (pqrsWithComment != null) {
            return new ResponseEntity<>(pqrsWithComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
