package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.Tag;
import org.launchcode.PlatePlanner.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tag")
@CrossOrigin(origins = "http://localhost:5173")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public List<Tag> getAllTags() {
        return (List<Tag>) tagRepository.findAll();
    }

    @GetMapping
    public Optional<Tag> getSavedTags(Long tagId) {
        return tagRepository.findById(tagId);
    }

    @PostMapping()
    public Tag createTag(@RequestBody @Valid Tag tag, Errors errors) {
        if(errors.hasErrors()) {
            System.out.println(errors);
        }
        return tagRepository.save(tag);
    }

    @PostMapping()
    public void updateTag(@RequestBody @Valid Tag tag, Long tagId) {
        if (tagRepository.existsById(tagId)) {
            tagRepository.save(tag);
        } else {
//            TODO: throw error;
        }
    }

    @DeleteMapping
    public void deleteTag(Long tagId) {
        tagRepository.deleteById(tagId);
    }

}
