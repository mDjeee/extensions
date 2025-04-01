package com.example.extensions.repository;

import com.example.extensions.model.Extension;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ExtensionRepository extends JpaRepository<Extension, Long> {
    Optional<Extension> findByName(String name);
}
