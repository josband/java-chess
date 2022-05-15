import java.io.File;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;

public class ImageConvert {
    static String[] filenames = {
        "wK.png", "wQ.png", "wB.png", "wN.png", "wR.png", "wP.png",
        "bK.png", "bQ.png", "bB.png", "bN.png", "bR.png", "bP.png"
    };
    static String filePath = "./src/chess/pieces/piece PNGs/";
        public static void main(String[] args) throws Exception {
            BufferedImage all = ImageIO.read(new File(filePath + "chess.png"));
            int ind=0;
            for(int y=0;y<400;y+=200) {
                for(int x=0;x<1200;x+=200){
                    Image img = all.getSubimage(x, y, 200, 200).getScaledInstance(75, 75, BufferedImage.SCALE_SMOOTH);
                    //ind++;

                    BufferedImage bufimg = toBufferedImage(img);
                    File out = new File("./src/chess/pieces/piece PNGs/" + filenames[ind++]);
                    ImageIO.write(bufimg, "png", out);
            }    
        }
    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
