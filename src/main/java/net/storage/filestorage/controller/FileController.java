package net.storage.filestorage.controller;

import net.storage.filestorage.model.File;
import net.storage.filestorage.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<File> getFile(@PathVariable(name="id") Long fileId) {
        if (fileId == null) {
            return new ResponseEntity<File>(HttpStatus.BAD_REQUEST);
        }
        File file = fileService.getById(fileId);

        if (file == null) {
            return new ResponseEntity<File>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<File>(file, HttpStatus.OK);
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<File> updateFile(File file) {
        if (file == null) {
            return new ResponseEntity<File>(HttpStatus.NOT_FOUND);
        }
        this.fileService.update(file);

        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<File> deleteFile(Long id) {
        File file = this.fileService.getById(id);
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.fileService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseEntity<List<File>> getAllFiles() {
        List<File> files = this.fileService.getAll();

        if (files.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<File>>(files, HttpStatus.OK);
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public ResponseEntity<File> createFile(@RequestBody @Validated File file) {
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.fileService.create(file);
        return new ResponseEntity<>(file, HttpStatus.CREATED);
    }
}
