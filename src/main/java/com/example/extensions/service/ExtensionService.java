package com.example.extensions.service;

import com.example.extensions.exceptions.ResourceNotFoundException;
import com.example.extensions.model.Extension;
import com.example.extensions.repository.ExtensionRepository;
import jakarta.transaction.Transactional;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ExtensionService {

    private final ExtensionRepository extensionRepository;

    public ExtensionService(ExtensionRepository extensionRepository) {
        this.extensionRepository = extensionRepository;
    }

    public List<Extension> getAllExtensions() {
        return extensionRepository.findAll();
    }

    public Optional<Extension> getExtensionById(Long id) {
        return extensionRepository.findById(id);
    }

    public Extension addExtension(Extension extension) {
        return extensionRepository.save(extension);
    }

    public Extension updateExtension(Long id, Extension extensionDetails) {
        return extensionRepository.findById(id)
                .map(extension -> {
                    // Only update fields that are not null in extensionDetails
                    if (extensionDetails.getName() != null) {
                        extension.setName(extensionDetails.getName());
                    }
                    if (extensionDetails.getDescription() != null) {
                        extension.setDescription(extensionDetails.getDescription());
                    }
                    if (extensionDetails.getLogo() != null) {
                        extension.setLogo(extensionDetails.getLogo());
                    }
                    if (extensionDetails.getActive() != null) {
                        extension.setActive(extensionDetails.getActive());
                    }
                    return extensionRepository.save(extension);
                }).orElseThrow(() -> new RuntimeException("Extension not found"));
    }

    @Transactional
    public Extension deleteExtension(Long id) {
        Extension extension = extensionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Extension not found"));
        extensionRepository.delete(extension);
        return extension;
    }
}
