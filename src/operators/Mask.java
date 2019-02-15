package operators;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by i330055 on 10/03/17.
 */
public interface Mask {

    List<Integer> apply(BufferedImage image, int x, int y);
}
