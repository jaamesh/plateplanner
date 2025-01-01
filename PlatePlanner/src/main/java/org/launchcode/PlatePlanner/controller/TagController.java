package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.Tag;
import org.launchcode.PlatePlanner.repository.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tag")
@CrossOrigin(origins = "http://localhost:5173")
public class TagController {

    Logger logger = LoggerFactory.getLogger(TagController.class);

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Tag>> getAllSavedTags() {
        logger.info("In getAllSavedTags...");
        return ResponseEntity.ok(tagRepository.findAll());
    }

    @GetMapping("/{tagId}")
    public ResponseEntity<Optional<Tag>> getSavedTag(@PathVariable("tagId") Long tagId) {
        logger.info("In getSavedTag...");
        if (tagRepository.existsById(tagId)) {
            logger.info("Tag with ID {} found...", tagId);
            return ResponseEntity.ok(tagRepository.findById(tagId));
        } else {
            logger.warn("Tag with ID {} not found...", tagId);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createTag(@RequestBody @Valid Tag tag, Errors errors) {
        logger.info("In createTag...");
        tagRepository.save(tag);
        if (errors.hasErrors()) {
            logger.error("Error creating tag: {}", errors);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update/{tagId}")
    public ResponseEntity<Object> updateTag(@PathVariable("tagId") Long tagId, @RequestBody @Valid Tag tag) {
        logger.info("In updateTag...");
        if (tagRepository.existsById(tagId)) {
            logger.info("Tag with ID {} found.  Updating...", tagId);
            tagRepository.save(tag);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Tag with ID {} not found...", tagId);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{tagId}")
    public ResponseEntity<Object> deleteTag(@PathVariable("tagId") Long tagId) {
        logger.info("In deleteTag...");
        if (tagRepository.existsById(tagId)) {
            logger.info("Tag with ID {} found.  Deleting...", tagId);
            tagRepository.deleteById(tagId);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Tag with ID {} not found...", tagId);
            return ResponseEntity.notFound().build();
        }
    }

}
