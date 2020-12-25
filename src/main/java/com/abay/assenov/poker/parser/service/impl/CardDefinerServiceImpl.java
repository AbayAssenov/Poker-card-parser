package com.abay.assenov.poker.parser.service.impl;

import com.abay.assenov.poker.parser.service.CardDefinerService;
import com.abay.assenov.poker.parser.service.FileReaderService;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.abay.assenov.poker.parser.constant.Constant.*;

public class CardDefinerServiceImpl implements CardDefinerService {

    private final FileReaderService fileReaderService;
    private final Map<String, BufferedImage> buffer;

    public CardDefinerServiceImpl() {
        fileReaderService = new FileReaderServiceImpl();
        buffer = initData();
    }

    @Override
    public String defineCardNameByCardImage(BufferedImage img) {
        List<BufferedImage> extractedCards = splitCardImage(img);
        StringBuilder cardNames = new StringBuilder();

        for (BufferedImage sourceImage : extractedCards) {
            String nameMinimal = "";
            Double minimal = Double.MAX_VALUE;
            for (Map.Entry<String, BufferedImage> entry : buffer.entrySet()) { // using for-each loop for iteration over Map.entrySet()

                double value = compareTwoImage(sourceImage, entry.getValue());
                if (minimal > value) {
                    minimal = value;
                    nameMinimal = entry.getKey();
                }
            }
            cardNames.append(nameMinimal);
        }
        return cardNames.toString();
    }


    // compare two image
    @Override
    public double compareTwoImage(BufferedImage img1, BufferedImage img2) {
        double percentage = Double.MAX_VALUE;
        if ((img1.getWidth() != img2.getWidth()) || (img1.getHeight() != img2.getHeight())) {
            System.out.println("Both images should have same dimensions");
        } else {
            long diff = 0;
            for (int j = 0; j < img1.getHeight(); j++) {
                for (int i = 0; i < img1.getWidth(); i++) {
                    //Getting the RGB values of a pixel
                    int pixel1 = img1.getRGB(i, j);
                    Color color1 = new Color(pixel1, true);
                    int r1 = color1.getRed();
                    int g1 = color1.getGreen();
                    int b1 = color1.getBlue();
                    int pixel2 = img2.getRGB(i, j);
                    Color color2 = new Color(pixel2, true);
                    int r2 = color2.getRed();
                    int g2 = color2.getGreen();
                    int b2 = color2.getBlue();
                    //sum of differences of RGB values of the two images
                    long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
                    diff = diff + data;
                }
            }
            double avg = diff / (img1.getWidth() * img1.getHeight() * RGB_THREE_CHANEL);
            percentage = (avg / RGB_ONE_COLOR_MAX_COUNT) * 100;
//            System.out.println("Difference: " + percentage);
        }
        return percentage;
    }

    // extract and split images of the cards on table
    @Override
    public List<BufferedImage> splitCardImage(BufferedImage image) {
        List<BufferedImage> result = new ArrayList<>();
        int swift = X_POKER_CARD; // swift
        for (int i = 0; i < MAX_COUNT_POKER_CARD_ON_TABLE; i++) {
            BufferedImage card = image.getSubimage(swift, Y_POKER_CARD, WIDTH_POKER_CARD, HEIGHT_POKER_CARD);
            if (isCard(card)) { // TODO optimize
                card = cropImage(card); // crop image
                result.add(card);
            }
            swift += GAP_BETWEEN_POKER_CARD + WIDTH_POKER_CARD; // TODO Optimaze
        }

        return result;
    }

    // crop unuseful space
    private BufferedImage cropImage(BufferedImage img) {
        return img.getSubimage(DEFAULT_CROP_SIZE, DEFAULT_CROP_SIZE, img.getWidth() - (DEFAULT_CROP_SIZE * 2), img.getHeight() - (DEFAULT_CROP_SIZE * 2));
    }

    // define is card
    private Boolean isCard(BufferedImage img) {
        int xPoint = img.getWidth() - DEFAULT_SWIFT_TO_EMPTY_SPACE; // swift to empty space
        int yPoint = img.getHeight() - DEFAULT_SWIFT_TO_EMPTY_SPACE;  // swift to empty space
        int result = img.getRGB(xPoint, yPoint);
        return result == CARD_EMPTY_SPACE_COLOR;
    }

    // initialize data
    private Map<String, BufferedImage> initData() {
        Path resourceDirectory = Paths.get("src", "main", "resources", "images"); // get resource folder
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        List<Path> absolutePathAllFileInFolder = fileReaderService.getAbsolutePathAllFileInFolder(absolutePath);
        Map<String, BufferedImage> validBufferImages = new HashMap<>();

        for (Path pathToImage : absolutePathAllFileInFolder) { // get image name and image data
            validBufferImages.put(pathToImage.getFileName().toString().replaceFirst("[.][^.]+$", ""),
                    fileReaderService.readImageFile(pathToImage));
        }
        return validBufferImages;
    }
}
