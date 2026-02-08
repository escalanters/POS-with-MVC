package styles;
import java.awt.*;
import java.io.InputStream;

/**
 * The type Font util.
 */
public class FontUtil {
    private static final String FALLBACK_FONT = "SansSerif";

    /**
     * Load font font.
     *
     * @param tamano       the tamano
     * @param nombreFuente the nombre fuente
     * @return the font
     */
    public static Font loadFont(float tamano, String nombreFuente) {
        try {
            String path = "fonts/" + nombreFuente + ".ttf";
            InputStream is = FontUtil.class.getClassLoader().getResourceAsStream(path);
            if (is == null) {
                return new Font(FALLBACK_FONT, Font.PLAIN, (int) tamano);
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(tamano);
        } catch (Exception e) {
            return new Font(FALLBACK_FONT, Font.PLAIN, (int) tamano);
        }
    }
}