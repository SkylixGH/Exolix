import Rgb from "../formats/Rgb";
import Rgba from "../formats/Rgba";
import PixelBrightness from "./PixelBrightness";

/**
 * A class used to represent a color.
 */
export default class Color {
    /**
     * The method used to construct this color.
     */
    readonly initStyle: "rgb" | "rgba" | "hex";

    /**
     * The RGBA color.
     */
    readonly rgba: Rgba;

    /**
     * Create a new color instanced with the RGBA format.
     * 
     * @param red The red component of the color.
     * @param green The green component of the color.
     * @param blue The blue component of the color.
     * @param alpha The alpha component of the color.
     */
    public constructor(red: number, green: number, blue: number, alpha: number);

    /**
     * Create a new color instanced with the RGB format.
     * 
     * @param red The red component of the color.
     * @param green The green component of the color.
     * @param blue The blue component of the color.
     */
    public constructor(red: number, green: number, blue: number);

    /**
     * Create a new color instanced with the hexadecimal format.
     * 
     * @param hex The hexadecimal string representation of the color.
     */
    public constructor(hex: string);

    public constructor(...args: any[]) {
        switch (args.length) {
            case 4:
                this.initStyle = "rgba";
                this.rgba = new Rgba(args[0], args[1], args[2], args[3]);
                break;

            case 3:
                this.initStyle = "rgb";
                this.rgba = new Rgba(args[0], args[1], args[2], 255);
                break;

            default:
                this.initStyle = "hex";
                
                const hex = args[0];

                if (hex.startsWith("#")) {
                    const hx = hex.substring(1);

                    if (hex.length === 4 && /^#[0-9A-F]{3}$/i.test(hex)) {
                        // 3 digit hex
                        
                        const red = parseInt(hx.substring(0, 1), 16) as PixelBrightness;
                        const green = parseInt(hx.substring(1, 2), 16) as PixelBrightness;
                        const blue = parseInt(hx.substring(2, 3), 16) as PixelBrightness;

                        this.rgba = new Rgba(red, green, blue, 255);
                    } else if (hex.length === 7 && /^#[0-9A-F]{6}$/i.test(hex)) {
                        // 6 digit hex

                        const red = parseInt(hx.substring(0, 2), 16) as PixelBrightness;
                        const green = parseInt(hx.substring(2, 4), 16) as PixelBrightness;
                        const blue = parseInt(hx.substring(4, 6), 16) as PixelBrightness;

                        this.rgba = new Rgba(red, green, blue, 255);
                    } else if (hex.length === 9 && /^#[0-9A-F]{8}$/i.test(hex)) {
                        // 8 digit hex

                        const red = parseInt(hx.substring(0, 2), 16) as PixelBrightness;
                        const green = parseInt(hx.substring(2, 4), 16) as PixelBrightness;
                        const blue = parseInt(hx.substring(4, 6), 16) as PixelBrightness;
                        const alpha = parseInt(hx.substring(6, 8), 16) as PixelBrightness;

                        this.rgba = new Rgba(red, green, blue, alpha);
                    } else {
                        this.rgba = new Rgba(0, 0, 0, 255);
                    }
                } else {
                    if (hex.length === 3 && /^[0-9A-F]{3}$/i.test(hex)) {
                        // 3 digit hex

                        const red = parseInt(hex.substring(0, 1), 16) as PixelBrightness;
                        const green = parseInt(hex.substring(1, 2), 16) as PixelBrightness;
                        const blue = parseInt(hex.substring(2, 3), 16) as PixelBrightness;

                        this.rgba = new Rgba(red, green, blue, 255);
                    } else if (hex.length === 6 && /^[0-9A-F]{6}$/i.test(hex)) {
                        // 6 digit hex

                        const red = parseInt(hex.substring(0, 2), 16) as PixelBrightness;
                        const green = parseInt(hex.substring(2, 4), 16) as PixelBrightness;
                        const blue = parseInt(hex.substring(4, 6), 16) as PixelBrightness;

                        this.rgba = new Rgba(red, green, blue, 255);
                    } else if (hex.length === 8 && /^[0-9A-F]{8}$/i.test(hex)) {
                        // 8 digit hex

                        const red = parseInt(hex.substring(0, 2), 16) as PixelBrightness;
                        const green = parseInt(hex.substring(2, 4), 16) as PixelBrightness;
                        const blue = parseInt(hex.substring(4, 6), 16) as PixelBrightness;
                        const alpha = parseInt(hex.substring(6, 8), 16) as PixelBrightness;

                        this.rgba = new Rgba(red, green, blue, alpha);
                    } else {
                        this.rgba = new Rgba(0, 0, 0, 255);
                    }
                }

                break;
        }
    }

    /**
     * Get the RGB color.
     */
    public get rgb(): Rgb {
        return new Rgb(this.red, this.green, this.blue);
    }

    /**
     * Get the red color value;
     */
    public get red(): PixelBrightness {
        return this.rgba.red;
    }

    /**
     * Get the green color value;
     */
    public get green(): PixelBrightness {
        return this.rgba.green;
    }

    /**
     * Get the blue color value;
     */
    public get blue(): PixelBrightness {
        return this.rgba.blue;
    }

    /**
     * Get the alpha color value;
     */
    public get alpha(): PixelBrightness {
        return this.rgba.alpha;
    }
}
