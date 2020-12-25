package com.abay.assenov.poker.parser.service.impl;

import com.abay.assenov.poker.parser.service.FileReaderService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileReaderServiceImpl implements FileReaderService {
    @Override
    public List<Path> getAbsolutePathAllFileInFolder(String pathToFolder) {
        List<Path> pathToFileList = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(pathToFolder))) {
            paths.filter(Files::isRegularFile).forEach(pathToFileList::add);
        } catch (IOException e) {
            System.err.println("Wrong path or incorrect access : ");
            e.printStackTrace();
        }
        return pathToFileList;
    }

    @Override
    public BufferedImage readImageFile(Path pathToFile) {
        BufferedImage imageSource = null;
        try {
            imageSource = ImageIO.read(pathToFile.toFile());
        } catch (IOException e) {
            System.err.println("Cannot read the image file : ");
            e.printStackTrace();
        }
        return imageSource;
    }
}
