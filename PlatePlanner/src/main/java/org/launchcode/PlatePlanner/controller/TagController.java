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

    @GetMapping("/all")
    public List<Tag> getAllTags() {
        return (List<Tag>) tagRepository.findAll();
    }

    @GetMapping("/{tagId}")
    public Optional<Tag> getSavedTags(@PathVariable Long tagId) {
        return tagRepository.findById(tagId);
    }

    @PostMapping("/create")
    public Tag createTag(@RequestBody @Valid Tag tag, Errors errors) {
        if(errors.hasErrors()) {
            System.out.println(errors);
        }
        return tagRepository.save(tag);
    }

    @PostMapping("/update/{tagId}")
    public void updateTag(@PathVariable Long tagId, @RequestBody @Valid Tag tag) {
        if (tagRepository.existsById(tagId)) {
            tagRepository.save(tag);
        } else {
//            TODO: throw error;
        }
    }

    @DeleteMapping("/delete/{tagId}")
    public void deleteTag(@PathVariable Long tagId) {
        tagRepository.deleteById(tagId);
    }

}
