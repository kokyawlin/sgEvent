package com.nus.sgevent.extservices;

import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init();

	void store(MultipartFile file);

	Stream<java.nio.file.Path> loadAll();

	java.nio.file.Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

}

