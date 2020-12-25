# Poker-card-parser
Challenge and requirements:

- Given a lot of pictures
- It is necessary to write a program in Java that recognizes which cards are on the table (only in the center of the picture). For example, in this picture https://i.gyazo.com/65658f6ab114de07d5c08d5f81324dc7.png there are 4hQd7s cards on the table
- Recognition errors are allowed no more than 3% of the total number of recognized cards
- Recognition of one file should not take more than 1 second

To solve the problem, it is recommended to use the following functions built into Java:
- BufferedImage img = ImageIO.read(f); - read img
- ImageIO.write(img, "png", f); - write img to file
- img.getWidth(); img.getHeight(); - demension of the picture
- BufferedImage img1 = img.getSubimage(x, y, w, h); - get sub img 
- img.getRGB(x, y); - get color of a pixel in pincture
- Color c = new Color(img.getRGB(x, y)); c.getRed(); c.getGreen(); c.getBlue(); c.equals(c1) - work with color in the one point
