package com.bethunter.bethunter_api.controller;

import com.bethunter.bethunter_api.dto.alternative.AlternativeRequestCreate;
import com.bethunter.bethunter_api.dto.alternative.AlternativeRequestUpdate;
import com.bethunter.bethunter_api.dto.alternative.AlternativeResponse;
import com.bethunter.bethunter_api.service.ServiceAlternative;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alternatives")
public class ControllerAlternative {

    @Autowired
    private ServiceAlternative serviceAlternative;

    @Operation(summary = "Create a new alternative")
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping
    public ResponseEntity<AlternativeResponse> createAlternative(@RequestBody AlternativeRequestCreate dto) {
        return ResponseEntity.status(201).body(serviceAlternative.createAlternative(dto));
    }

    @Operation(summary = "Find all alternatives in the database")
    @ApiResponse(responseCode = "200", description = "Found all")
    @GetMapping
    public ResponseEntity<List<AlternativeResponse>> findAll() {
        return ResponseEntity.ok(serviceAlternative.findAll());
    }

    @Operation(summary = "Find an alternative by his id in the database")
    @ApiResponse(responseCode = "200", description = "Found")
    @GetMapping("{id}")
    public ResponseEntity<AlternativeResponse> findById(@PathVariable String id) {
        return serviceAlternative.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<AlternativeResponse> updateAlternative(@PathVariable String id, @RequestBody AlternativeRequestUpdate dto) {
        return serviceAlternative.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean result = serviceAlternative.delete(id);
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
