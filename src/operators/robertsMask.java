package operators;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by i330055 on 28/02/17.
 */
public class robertsMask implements Mask {
    int nx;
    int ny;
    double v;
    int[][] gx = new int[][] {
        {1, 0},
        {0, -1}
    };
    int[][] gy = new int[][] {
            {0, 1},
            {-1, 0}
    };

    public List apply(BufferedImage image, int nx, int ny) {
        List positions = new ArrayList(nx * ny);
        int position;

        for(int x=0; x < nx; x++)
        {
            for(int y=0; y < ny; y++)
            {
                position = (x*ny)+y;
                positions.set(position, image.getRGB(x,y));
            }
        }

        return positions;
    }
}
