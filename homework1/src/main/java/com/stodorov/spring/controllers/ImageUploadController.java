package com.stodorov.spring.controllers;

import com.stodorov.spring.services.ImageStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ImageUploadController {

    @Autowired
    ImageStoreService imageStore;

    @GetMapping(
            value = "/images/{filename:.+}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = imageStore.loadAsResource(filename);
        return ResponseEntity.ok().body(file);
    }

    @PostMapping("/upload-image")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {

        String path = imageStore.store(file);

        return ResponseEntity.ok().body("/images/" + path);
    }
}
