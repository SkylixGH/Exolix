import PixelBrightness from "../color/PixelBrightness";
import rgbHex from "rgb-hex";

/**
 * A class used for representing colors in the RGB color space format.
 */
export default class Rgb {
    /**
     * The red component of the color.
     */
    readonly red: PixelBrightness;

    /**
     * The green component of the color.
     */
    readonly green: PixelBrightness;

    /**
     * The blue component of the color.
     */
    readonly blue: PixelBrightness;

    /**
     * Create a new RGB color.
     * 
     * @param red The red component of the color.
     * @param green The green component of the color.
     * @param blue The blue component of the color.
     */
    constructor(red: PixelBrightness, green: PixelBrightness, blue: PixelBrightness) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Get the RGB color in a hexadecimal string format.
     */
    public get hex(): string {
        return rgbHex(this.red, this.green, this.blue);
    }
}
