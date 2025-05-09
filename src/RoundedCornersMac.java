import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;

/**
 * Applies pixel-perfect alpha mask shape to JFrame only on macOS.
 * Usage: new RoundedCornersMac(this);
 */
public class RoundedCornersMac {

    public RoundedCornersMac(JFrame frame) {
        if (!isMacOS()) {
            // Silently ignore on non-mac systems
            return;
        }

        try {
            BufferedImage maskImg = ImageIO.read(getClass().getResource("/assets/rounded_corners_alpha.png"));
            Raster alphaRaster = maskImg.getAlphaRaster();
            Area shape = new Area();

            for (int y = 0; y < maskImg.getHeight(); y++) {
                int startX = -1;
                for (int x = 0; x < maskImg.getWidth(); x++) {
                    int alpha = alphaRaster.getSample(x, y, 0);
                    if (alpha > 0) {
                        if (startX == -1) startX = x;
                    } else {
                        if (startX != -1) {
                            shape.add(new Area(new Rectangle(startX, y, x - startX, 1)));
                            startX = -1;
                        }
                    }
                }
                if (startX != -1) {
                    shape.add(new Area(new Rectangle(startX, y, maskImg.getWidth() - startX, 1)));
                }
            }

            frame.setShape(shape);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private boolean isMacOS() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }
}