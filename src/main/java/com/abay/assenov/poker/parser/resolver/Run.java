package com.abay.assenov.poker.parser.resolver;

import com.abay.assenov.poker.parser.service.CardDefinerService;
import com.abay.assenov.poker.parser.service.FileReaderService;
import com.abay.assenov.poker.parser.service.impl.CardDefinerServiceImpl;
import com.abay.assenov.poker.parser.service.impl.FileReaderServiceImpl;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static com.abay.assenov.poker.parser.constant.Constant.DEFAULT_PATH_TO_IMAGES;

public class Run {

    private static final FileReaderService fileReaderService = new FileReaderServiceImpl();
    private static final CardDefinerService cardDefinerService = new CardDefinerServiceImpl();

    public static void main(String[] args) {

        String path = args.length > 0 && Objects.nonNull(args[0]) ? args[0] : DEFAULT_PATH_TO_IMAGES;
//
        List<Path> allImages = fileReaderService.getAbsolutePathAllFileInFolder(path);
        for (Path pathToImage : allImages) {
            BufferedImage image = fileReaderService.readImageFile(pathToImage);
            System.out.println(pathToImage.getFileName() + " - " + cardDefinerService.defineCardNameByCardImage(image));
        }
    }


}
