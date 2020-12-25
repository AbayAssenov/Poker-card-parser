package com.abay.assenov.poker.parser.service;

import java.awt.image.BufferedImage;
import java.util.List;

public interface CardDefinerService {

    String defineCardNameByCardImage(BufferedImage img);

    double compareTwoImage(BufferedImage img1, BufferedImage img2);

    List<BufferedImage> splitCardImage(BufferedImage image);
}
