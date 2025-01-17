package com.example.gestox.service.service_famille;

import com.example.gestox.dao.FamilleRepository;
import com.example.gestox.entity.Famille_Produit;
import com.example.gestox.entity.Produit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Service_Categorie implements IService_Categorie{
    private FamilleRepository familleRepository ;

    @Override
    public Famille_Produit getCat(Long id) {
        System.out.println("Fetching Famille_Produit with ID: " + id);
        Famille_Produit cat = familleRepository.findById(id).orElse(null);
        System.out.println("Retrieved Famille_Produit: " + cat);
        return cat;
    }

    @Override
    public Famille_Produit saveCat(Famille_Produit f) {
        if (f.getDesignation() == null || f.getDesignation().isEmpty()) {
            throw new IllegalArgumentException("Product ref cannot be empty");
        }
        return familleRepository.save(f);
    }

    @Override
    public List<Famille_Produit> getAllCat() {
        return familleRepository.findAll();
    }

    @Override
    public void deleteCat(Long id) {
        Optional<Famille_Produit> cat = familleRepository.findById(id);

        cat.ifPresent(f -> {
            familleRepository.delete(f);
        });
    }

    @Override
    public Famille_Produit editCat(Famille_Produit f) {
        if (f.getId() == null) {
            throw new IllegalArgumentException("categorie ID cannot be null");
        }
        if (f.getDesignation() == null || f.getDesignation().isEmpty()) {
            throw new IllegalArgumentException("Product designation  cannot be empty");
        }
        try {
            return familleRepository.save(f);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update categorie", e);
        }
    }

}