package com.abay.assenov.poker.parser.service;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.List;

public interface FileReaderService {

    List<Path> getAbsolutePathAllFileInFolder(String pathToFolder);

    BufferedImage readImageFile(Path pathToFile);
}
