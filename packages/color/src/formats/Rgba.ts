import PixelBrightness from "../color/PixelBrightness";
import Rgb from "./Rgb";

/**
 * An extended class used for representing RGBA color space colors.
 */
export default class Rgba extends Rgb {
    /**
     * The alpha component of the color.
     */
    readonly alpha: PixelBrightness;

    /**
     * Create a new RGBA color.
     * 
     * @param red The red component of the color.
     * @param green The green component of the color.
     * @param blue The blue component of the color.
     * @param alpha The alpha component of the color.
     */
    public constructor(red: PixelBrightness, green: PixelBrightness, blue: PixelBrightness, alpha: PixelBrightness) {
        super(red, green, blue);
        this.alpha = alpha;
    }

    /**
     * Get the RGBA color in a hexadecimal string format.
     */
    public get hex(): string {
        return this.red.toString(16) + this.green.toString(16) + this.blue.toString(16) + this.alpha.toString(16);
    }
}
