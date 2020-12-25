# Poker-card-parser
Challenge and requirements:

- Given a lot of pictures
- It is necessary to write a program in Java that recognizes which cards are on the table (only in the center of the picture). For example, in this picture https://i.gyazo.com/65658f6ab114de07d5c08d5f81324dc7.png there are 4hQd7s cards on the table
- Testing of the program will be carried out on similar pictures that are not in the original set
- Recognition errors are allowed no more than 3% of the total number of recognized cards
- You cannot use ready-made libraries for text recognition. It is necessary to write your own card recognition algorithm
- Recognition of one file should not take more than 1 second
- The source code for solving the problem should not be longer than 500 lines with normal formatting

- The program must be provided in a form ready to run on a Windows desktop. The run.bat file as a parameter takes the path to the folder with pictures. The result is printed to the console as "file name - map" for all files in the folder
- The program must be provided with the source files
- The source files must contain ALL the code that was used to solve the problem
- The program must be provided as a link to a ZIP file. Links to repositories such as github are not accepted

To solve the problem, it is recommended to use the following functions built into Java:
- BufferedImage img = ImageIO.read(f); - read img
- ImageIO.write(img, "png", f); - write img to file
- img.getWidth(); img.getHeight(); - demension of the picture
- BufferedImage img1 = img.getSubimage(x, y, w, h); - get sub img 
- img.getRGB(x, y); - get color of a pixel in pincture
- Color c = new Color(img.getRGB(x, y)); c.getRed(); c.getGreen(); c.getBlue(); c.equals(c1) - work with color in the one point
