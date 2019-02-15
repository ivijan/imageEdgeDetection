package imageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ivijan Hadija on 27/02/17.
 */
public class ImageLoader {

    public static final String IMG_IN = "/Users/i330055/workbox/imageEdgeDetection/out/production/imageEdgeDetection/images/baboon.png";
    public static final String IMG_OUT = "/Users/i330055/workbox/imageEdgeDetection/out/production/imageEdgeDetection/images/baboon-out.png";
    static BufferedImage inputImg, outputImg;
    static final int MATRIX_SIZE = 3;
    static int[][] pixelMatrix = new int[MATRIX_SIZE][MATRIX_SIZE];

    public static void main(String[] args) {
        try {

            inputImg = ImageIO.read(new File(IMG_IN));
            outputImg = new BufferedImage(inputImg.getWidth(), inputImg.getHeight(), BufferedImage.TYPE_INT_RGB);

            for(int i=1; i<inputImg.getWidth()-1; i++){
                for(int j=1; j<inputImg.getHeight()-1; j++){
                    pixelMatrix[0][0] = new Color(inputImg.getRGB(i-1, j-1)).getRed();
                    pixelMatrix[0][1] = new Color(inputImg.getRGB(i-1, j)).getRed();
                    pixelMatrix[0][2] = new Color(inputImg.getRGB(i-1, j+1)).getRed();
                    pixelMatrix[1][0] = new Color(inputImg.getRGB(i, j-1)).getRed();
                    pixelMatrix[1][2] = new Color(inputImg.getRGB(i, j+1)).getRed();
                    pixelMatrix[2][0] = new Color(inputImg.getRGB(i+1, j-1)).getRed();
                    pixelMatrix[2][1] = new Color(inputImg.getRGB(i+1, j)).getRed();
                    pixelMatrix[2][2] = new Color(inputImg.getRGB(i+1, j+1)).getRed();

                    int edge = (int) convolution(pixelMatrix);
                    outputImg.setRGB(i, j, (edge<<16 | edge<<8 | edge));
                }
            }

            File outputfile = new File(IMG_OUT);
            ImageIO.write(outputImg, "jpg", outputfile);

        } catch (IOException ex) {
            System.err.println("Image width:height=" + inputImg.getWidth() + ":" + inputImg.getHeight());
        }
    }

    public static double convolution(int[][] pixelMatrix){

        int gy =  (pixelMatrix[0][0]*-1)
                + (pixelMatrix[0][1]*-2)
                + (pixelMatrix[0][2]*-1)
                + (pixelMatrix[2][0])
                + (pixelMatrix[2][1]*2)
                + (pixelMatrix[2][2]*1);

        int gx =  (pixelMatrix[0][0])
                + (pixelMatrix[0][2]*-1)
                + (pixelMatrix[1][0]*2)
                + (pixelMatrix[1][2]*-2)
                + (pixelMatrix[2][0])
                + (pixelMatrix[2][2]*-1);

        return Math.sqrt(Math.pow(gy,2)+Math.pow(gx,2));
    }
}
