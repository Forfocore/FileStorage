package net.storage.filestorage.service;

import net.storage.filestorage.repository.FileRepository;
import net.storage.filestorage.model.File;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File getById(Long id) {
        return fileRepository.getOne(id);
    }

    public File update(File file) {

        return fileRepository.save(file);
    }

    public List<File> getAll() {
        return fileRepository.findAll();
    }

    public void deleteById(Long id) {
        fileRepository.deleteById(id);
    }

    public void create(File file) {
        fileRepository.save(file);
    }
}
