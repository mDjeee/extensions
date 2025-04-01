package com.example.extensions.controller;

import com.example.extensions.model.Extension;
import com.example.extensions.service.ExtensionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/extensions")
@Tag(name = "Extensions", description = "API for managing browser extensions")
public class ExtensionController {

    private final ExtensionService extensionService;

    public ExtensionController(ExtensionService extensionService) {
        this.extensionService = extensionService;
    }

    @Order(1)
    @GetMapping
    @Operation(summary = "Get all extensions", description = "Returns a list of all extensions")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public List<Extension> getAllExtensions() {
        return extensionService.getAllExtensions();
    }

    @Order(2)
    @GetMapping("/{id}")
    @Operation(summary = "Get extension by ID", description = "Returns a single extension")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Extension found"),
            @ApiResponse(responseCode = "404", description = "Extension not found")
    })
    public ResponseEntity<Extension> getExtensionById(@PathVariable Long id) {
        Optional<Extension> extension = extensionService.getExtensionById(id);
        return extension.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Order(3)
    @PostMapping
    @Operation(summary = "Add a new extension", description = "Creates a new extension")
    @ApiResponse(responseCode = "200", description = "Extension created successfully")
    public ResponseEntity<Extension> addExtension(@RequestBody Extension extension) {
        return ResponseEntity.ok(extensionService.addExtension(extension));
    }

    @Order(4)
    @PutMapping("/{id}")
    @Operation(summary = "Update an extension", description = "Updates an existing extension")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Extension updated successfully"),
            @ApiResponse(responseCode = "404", description = "Extension not found")
    })
    public ResponseEntity<Extension> updateExtension(@PathVariable Long id, @RequestBody Extension extension) {
        return ResponseEntity.ok(extensionService.updateExtension(id, extension));
    }

    @Order(5)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an extension", description = "Deletes an existing extension")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Extension deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Extension not found")
    })
    public ResponseEntity<Extension> deleteExtension(@PathVariable Long id) {
        return ResponseEntity.ok(extensionService.deleteExtension(id));
    }
}
