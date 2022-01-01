package ru.fmt.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fmt.university.dto.Course;
import ru.fmt.university.dto.Group;
import ru.fmt.university.service.GroupService;

import java.util.List;

@RestController
public class GroupController {
    @Autowired
    GroupService groupService;

    @PostMapping(value = "/groups")
    public ResponseEntity<?> create(@RequestBody Group group) {
        groupService.create(group);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getAll() {
        final List<Group> groups = groupService.getAll();

        return groups != null &&  !groups.isEmpty()
                ? new ResponseEntity<>(groups, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Group> getById(@PathVariable(name = "id") int id) {
        final Group group = groupService.getById(id);

        return group != null
                ? new ResponseEntity<>(group, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/groups/{id}")
    public ResponseEntity<?> update(@RequestBody Group group) {
        final Group updated = groupService.update(group);
        return updated != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/groups/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = groupService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/courses/{course}")
    public ResponseEntity<List<Group>> getByGroupId(@PathVariable(name = "course") Course course) {
        final List<Group> groups = groupService.getByCourse(course);

        return groups != null && !groups.isEmpty()
                ? new ResponseEntity<>(groups, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
