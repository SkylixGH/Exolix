package net.skylix.elixor.terminal.color;

import net.skylix.elixor.terminal.color.errors.InvalidHexCode;
import net.skylix.elixor.terminal.color.errors.InvalidRGBAlpha;
import net.skylix.elixor.terminal.color.errors.InvalidRGBValues;

import java.util.HashMap;
import java.util.Map;

/**
 * A class containing all the hex codes for the 256 colors of the CLI.
 */
public class ColorsCLI256 {
    /**
     * The color access map.
     */
    private static final HashMap<Integer, String> colorMap = new HashMap<>();
    /**
     * Number 0 in 256 table.
     */
    private static final String CODE0 = "#000000";
    /**
     * Number 1 in 256 table.
     */
    private static final String CODE1 = "#cd0000";
    /**
     * Number 2 in 256 table.
     */
    private static final String CODE2 = "#00d900";
    /**
     * Number 3 in 256 table.
     */
    private static final String CODE3 = "#cdcd00";
    /**
     * Number 4 in 256 table.
     */
    private static final String CODE4 = "#1e90ff";
    /**
     * Number 5 in 256 table.
     */
    private static final String CODE5 = "#cd00cd";
    /**
     * Number 6 in 256 table.
     */
    private static final String CODE6 = "#00cdcd";
    /**
     * Number 7 in 256 table.
     */
    private static final String CODE7 = "#e5e5e5";
    /**
     * Number 8 in 256 table.
     */
    private static final String CODE8 = "#4c4c4c";
    /**
     * Number 9 in 256 table.
     */
    private static final String CODE9 = "#ff0000";
    /**
     * Number 10 in 256 table.
     */
    private static final String CODE10 = "#00ff00";
    /**
     * Number 11 in 256 table.
     */
    private static final String CODE11 = "#ffff00";
    /**
     * Number 12 in 256 table.
     */
    private static final String CODE12 = "#1e90ff";
    /**
     * Number 13 in 256 table.
     */
    private static final String CODE13 = "#ff00ff";
    /**
     * Number 14 in 256 table.
     */
    private static final String CODE14 = "#00ffff";
    /**
     * Number 15 in 256 table.
     */
    private static final String CODE15 = "#ffffff";
    /**
     * Number 16 in 256 table.
     */
    private static final String CODE16 = "#000000";
    /**
     * Number 17 in 256 table.
     */
    private static final String CODE17 = "#00005f";
    /**
     * Number 18 in 256 table.
     */
    private static final String CODE18 = "#000087";
    /**
     * Number 19 in 256 table.
     */
    private static final String CODE19 = "#0000af";
    /**
     * Number 20 in 256 table.
     */
    private static final String CODE20 = "#0000d7";
    /**
     * Number 21 in 256 table.
     */
    private static final String CODE21 = "#0000ff";
    /**
     * Number 22 in 256 table.
     */
    private static final String CODE22 = "#005f00";
    /**
     * Number 23 in 256 table.
     */
    private static final String CODE23 = "#005f5f";
    /**
     * Number 24 in 256 table.
     */
    private static final String CODE24 = "#005f87";
    /**
     * Number 25 in 256 table.
     */
    private static final String CODE25 = "#005faf";
    /**
     * Number 26 in 256 table.
     */
    private static final String CODE26 = "#005fd7";
    /**
     * Number 27 in 256 table.
     */
    private static final String CODE27 = "#005fff";
    /**
     * Number 28 in 256 table.
     */
    private static final String CODE28 = "#008700";
    /**
     * Number 29 in 256 table.
     */
    private static final String CODE29 = "#00875f";
    /**
     * Number 30 in 256 table.
     */
    private static final String CODE30 = "#008787";
    /**
     * Number 31 in 256 table.
     */
    private static final String CODE31 = "#0087af";
    /**
     * Number 32 in 256 table.
     */
    private static final String CODE32 = "#0087d7";
    /**
     * Number 33 in 256 table.
     */
    private static final String CODE33 = "#0087ff";
    /**
     * Number 34 in 256 table.
     */
    private static final String CODE34 = "#00af00";
    /**
     * Number 35 in 256 table.
     */
    private static final String CODE35 = "#00af5f";
    /**
     * Number 36 in 256 table.
     */
    private static final String CODE36 = "#00af87";
    /**
     * Number 37 in 256 table.
     */
    private static final String CODE37 = "#00afaf";
    /**
     * Number 38 in 256 table.
     */
    private static final String CODE38 = "#00afd7";
    /**
     * Number 39 in 256 table.
     */
    private static final String CODE39 = "#00afff";
    /**
     * Number 40 in 256 table.
     */
    private static final String CODE40 = "#00d700";
    /**
     * Number 41 in 256 table.
     */
    private static final String CODE41 = "#00d75f";
    /**
     * Number 42 in 256 table.
     */
    private static final String CODE42 = "#00d787";
    /**
     * Number 43 in 256 table.
     */
    private static final String CODE43 = "#00d7af";
    /**
     * Number 44 in 256 table.
     */
    private static final String CODE44 = "#00d7d7";
    /**
     * Number 45 in 256 table.
     */
    private static final String CODE45 = "#00d7ff";
    /**
     * Number 46 in 256 table.
     */
    private static final String CODE46 = "#00ff00";
    /**
     * Number 47 in 256 table.
     */
    private static final String CODE47 = "#00ff5f";
    /**
     * Number 48 in 256 table.
     */
    private static final String CODE48 = "#00ff87";
    /**
     * Number 49 in 256 table.
     */
    private static final String CODE49 = "#00ffaf";
    /**
     * Number 50 in 256 table.
     */
    private static final String CODE50 = "#00ffd7";
    /**
     * Number 51 in 256 table.
     */
    private static final String CODE51 = "#00ffff";
    /**
     * Number 52 in 256 table.
     */
    private static final String CODE52 = "#5f0000";
    /**
     * Number 53 in 256 table.
     */
    private static final String CODE53 = "#5f005f";
    /**
     * Number 54 in 256 table.
     */
    private static final String CODE54 = "#5f0087";
    /**
     * Number 55 in 256 table.
     */
    private static final String CODE55 = "#5f00af";
    /**
     * Number 56 in 256 table.
     */
    private static final String CODE56 = "#5f00d7";
    /**
     * Number 57 in 256 table.
     */
    private static final String CODE57 = "#5f00ff";
    /**
     * Number 58 in 256 table.
     */
    private static final String CODE58 = "#5f5f00";
    /**
     * Number 59 in 256 table.
     */
    private static final String CODE59 = "#5f5f00";
    /**
     * Number 60 in 256 table.
     */
    private static final String CODE60 = "#5f5f87";
    /**
     * Number 61 in 256 table.
     */
    private static final String CODE61 = "#5f5faf";
    /**
     * Number 62 in 256 table.
     */
    private static final String CODE62 = "#5f5fd7";
    /**
     * Number 63 in 256 table.
     */
    private static final String CODE63 = "#5f5fff";
    /**
     * Number 64 in 256 table.
     */
    private static final String CODE64 = "#5f8700";
    /**
     * Number 65 in 256 table.
     */
    private static final String CODE65 = "#5f875f";
    /**
     * Number 66 in 256 table.
     */
    private static final String CODE66 = "#5f8787";
    /**
     * Number 67 in 256 table.
     */
    private static final String CODE67 = "#5f87af";
    /**
     * Number 68 in 256 table.
     */
    private static final String CODE68 = "#5f87d7";
    /**
     * Number 69 in 256 table.
     */
    private static final String CODE69 = "#5f87ff";
    /**
     * Number 70 in 256 table.
     */
    private static final String CODE70 = "#5faf00";
    /**
     * Number 71 in 256 table.
     */
    private static final String CODE71 = "#5faf5f";
    /**
     * Number 72 in 256 table.
     */
    private static final String CODE72 = "#5faf87";
    /**
     * Number 73 in 256 table.
     */
    private static final String CODE73 = "#5fafaf";
    /**
     * Number 74 in 256 table.
     */
    private static final String CODE74 = "#5fafd7";
    /**
     * Number 75 in 256 table.
     */
    private static final String CODE75 = "#5fafff";
    /**
     * Number 76 in 256 table.
     */
    private static final String CODE76 = "#5fd700";
    /**
     * Number 77 in 256 table.
     */
    private static final String CODE77 = "#5fd75f";
    /**
     * Number 78 in 256 table.
     */
    private static final String CODE78 = "#5fd787";
    /**
     * Number 79 in 256 table.
     */
    private static final String CODE79 = "#5fd7af";
    /**
     * Number 80 in 256 table.
     */
    private static final String CODE80 = "#5fd7d7";
    /**
     * Number 81 in 256 table.
     */
    private static final String CODE81 = "#5fd7ff";
    /**
     * Number 82 in 256 table.
     */
    private static final String CODE82 = "#5fff00";
    /**
     * Number 83 in 256 table.
     */
    private static final String CODE83 = "#5fff5f";
    /**
     * Number 84 in 256 table.
     */
    private static final String CODE84 = "#5fff87";
    /**
     * Number 85 in 256 table.
     */
    private static final String CODE85 = "#5fffaf";
    /**
     * Number 86 in 256 table.
     */
    private static final String CODE86 = "#5fffd7";
    /**
     * Number 87 in 256 table.
     */
    private static final String CODE87 = "#5fffff";
    /**
     * Number 88 in 256 table.
     */
    private static final String CODE88 = "#870000";
    /**
     * Number 89 in 256 table.
     */
    private static final String CODE89 = "#87005f";
    /**
     * Number 90 in 256 table.
     */
    private static final String CODE90 = "#870087";
    /**
     * Number 91 in 256 table.
     */
    private static final String CODE91 = "#8700af";
    /**
     * Number 92 in 256 table.
     */
    private static final String CODE92 = "#8700d7";
    /**
     * Number 93 in 256 table.
     */
    private static final String CODE93 = "#8700ff";
    /**
     * Number 94 in 256 table.
     */
    private static final String CODE94 = "#875f00";
    /**
     * Number 95 in 256 table.
     */
    private static final String CODE95 = "#875f5f";
    /**
     * Number 96 in 256 table.
     */
    private static final String CODE96 = "#875f87";
    /**
     * Number 97 in 256 table.
     */
    private static final String CODE97 = "#875faf";
    /**
     * Number 98 in 256 table.
     */
    private static final String CODE98 = "#875fd7";
    /**
     * Number 99 in 256 table.
     */
    private static final String CODE99 = "#875fff";
    /**
     * Number 100 in 256 table.
     */
    private static final String CODE100 = "#878700";
    /**
     * Number 101 in 256 table.
     */
    private static final String CODE101 = "#87875f";
    /**
     * Number 102 in 256 table.
     */
    private static final String CODE102 = "#878787";
    /**
     * Number 103 in 256 table.
     */
    private static final String CODE103 = "#8787af";
    /**
     * Number 104 in 256 table.
     */
    private static final String CODE104 = "#8787d7";
    /**
     * Number 105 in 256 table.
     */
    private static final String CODE105 = "#8787ff";
    /**
     * Number 106 in 256 table.
     */
    private static final String CODE106 = "#87af00";
    /**
     * Number 107 in 256 table.
     */
    private static final String CODE107 = "#87af5f";
    /**
     * Number 108 in 256 table.
     */
    private static final String CODE108 = "#87af87";
    /**
     * Number 109 in 256 table.
     */
    private static final String CODE109 = "#87afaf";
    /**
     * Number 110 in 256 table.
     */
    private static final String CODE110 = "#87afd7";
    /**
     * Number 111 in 256 table.
     */
    private static final String CODE111 = "#87afff";
    /**
     * Number 112 in 256 table.
     */
    private static final String CODE112 = "#87d700";
    /**
     * Number 113 in 256 table.
     */
    private static final String CODE113 = "#87d75f";
    /**
     * Number 114 in 256 table.
     */
    private static final String CODE114 = "#87d787";
    /**
     * Number 115 in 256 table.
     */
    private static final String CODE115 = "#87d7af";
    /**
     * Number 116 in 256 table.
     */
    private static final String CODE116 = "#87d7d7";
    /**
     * Number 117 in 256 table.
     */
    private static final String CODE117 = "#87d7ff";
    /**
     * Number 118 in 256 table.
     */
    private static final String CODE118 = "#87ff00";
    /**
     * Number 119 in 256 table.
     */
    private static final String CODE119 = "#87ff5f";
    /**
     * Number 120 in 256 table.
     */
    private static final String CODE120 = "#87ff87";
    /**
     * Number 121 in 256 table.
     */
    private static final String CODE121 = "#87ffaf";
    /**
     * Number 122 in 256 table.
     */
    private static final String CODE122 = "#87ffd7";
    /**
     * Number 123 in 256 table.
     */
    private static final String CODE123 = "#87ffff";
    /**
     * Number 124 in 256 table.
     */
    private static final String CODE124 = "#af0000";
    /**
     * Number 125 in 256 table.
     */
    private static final String CODE125 = "#af005f";
    /**
     * Number 126 in 256 table.
     */
    private static final String CODE126 = "#af0087";
    /**
     * Number 127 in 256 table.
     */
    private static final String CODE127 = "#af00af";
    /**
     * Number 128 in 256 table.
     */
    private static final String CODE128 = "#af00d7";
    /**
     * Number 129 in 256 table.
     */
    private static final String CODE129 = "#af00ff";
    /**
     * Number 130 in 256 table.
     */
    private static final String CODE130 = "#af5f00";
    /**
     * Number 131 in 256 table.
     */
    private static final String CODE131 = "#af5f5f";
    /**
     * Number 132 in 256 table.
     */
    private static final String CODE132 = "#af5f87";
    /**
     * Number 133 in 256 table.
     */
    private static final String CODE133 = "#af5faf";
    /**
     * Number 134 in 256 table.
     */
    private static final String CODE134 = "#af5fd7";
    /**
     * Number 135 in 256 table.
     */
    private static final String CODE135 = "#af5fff";
    /**
     * Number 136 in 256 table.
     */
    private static final String CODE136 = "#af8700";
    /**
     * Number 137 in 256 table.
     */
    private static final String CODE137 = "#af875f";
    /**
     * Number 138 in 256 table.
     */
    private static final String CODE138 = "#af8787";
    /**
     * Number 139 in 256 table.
     */
    private static final String CODE139 = "#af87af";
    /**
     * Number 140 in 256 table.
     */
    private static final String CODE140 = "#af87d7";
    /**
     * Number 141 in 256 table.
     */
    private static final String CODE141 = "#af87ff";
    /**
     * Number 142 in 256 table.
     */
    private static final String CODE142 = "#afaf00";
    /**
     * Number 143 in 256 table.
     */
    private static final String CODE143 = "#afaf5f";
    /**
     * Number 144 in 256 table.
     */
    private static final String CODE144 = "#afaf87";
    /**
     * Number 145 in 256 table.
     */
    private static final String CODE145 = "#afafaf";
    /**
     * Number 146 in 256 table.
     */
    private static final String CODE146 = "#afafd7";
    /**
     * Number 147 in 256 table.
     */
    private static final String CODE147 = "#afafff";
    /**
     * Number 148 in 256 table.
     */
    private static final String CODE148 = "#afd700";
    /**
     * Number 149 in 256 table.
     */
    private static final String CODE149 = "#afd75f";
    /**
     * Number 150 in 256 table.
     */
    private static final String CODE150 = "#afd787";
    /**
     * Number 151 in 256 table.
     */
    private static final String CODE151 = "#afd7af";
    /**
     * Number 152 in 256 table.
     */
    private static final String CODE152 = "#afd7d7";
    /**
     * Number 153 in 256 table.
     */
    private static final String CODE153 = "#afd7ff";
    /**
     * Number 154 in 256 table.
     */
    private static final String CODE154 = "#afff00";
    /**
     * Number 155 in 256 table.
     */
    private static final String CODE155 = "#afff5f";
    /**
     * Number 156 in 256 table.
     */
    private static final String CODE156 = "#afff87";
    /**
     * Number 157 in 256 table.
     */
    private static final String CODE157 = "#afffaf";
    /**
     * Number 158 in 256 table.
     */
    private static final String CODE158 = "#afffd7";
    /**
     * Number 159 in 256 table.
     */
    private static final String CODE159 = "#afffff";
    /**
     * Number 160 in 256 table.
     */
    private static final String CODE160 = "#d70000";
    /**
     * Number 161 in 256 table.
     */
    private static final String CODE161 = "#d7005f";
    /**
     * Number 162 in 256 table.
     */
    private static final String CODE162 = "#d70087";
    /**
     * Number 163 in 256 table.
     */
    private static final String CODE163 = "#d700af";
    /**
     * Number 164 in 256 table.
     */
    private static final String CODE164 = "#d700d7";
    /**
     * Number 165 in 256 table.
     */
    private static final String CODE165 = "#d700ff";
    /**
     * Number 166 in 256 table.
     */
    private static final String CODE166 = "#d75f00";
    /**
     * Number 167 in 256 table.
     */
    private static final String CODE167 = "#d75f5f";
    /**
     * Number 168 in 256 table.
     */
    private static final String CODE168 = "#d75f87";
    /**
     * Number 169 in 256 table.
     */
    private static final String CODE169 = "#d75faf";
    /**
     * Number 170 in 256 table.
     */
    private static final String CODE170 = "#d75fd7";
    /**
     * Number 171 in 256 table.
     */
    private static final String CODE171 = "#d75fff";
    /**
     * Number 172 in 256 table.
     */
    private static final String CODE172 = "#d78700";
    /**
     * Number 173 in 256 table.
     */
    private static final String CODE173 = "#d7875f";
    /**
     * Number 174 in 256 table.
     */
    private static final String CODE174 = "#d78787";
    /**
     * Number 175 in 256 table.
     */
    private static final String CODE175 = "#d787af";
    /**
     * Number 176 in 256 table.
     */
    private static final String CODE176 = "#d787d7";
    /**
     * Number 177 in 256 table.
     */
    private static final String CODE177 = "#d787ff";
    /**
     * Number 178 in 256 table.
     */
    private static final String CODE178 = "#d7af00";
    /**
     * Number 179 in 256 table.
     */
    private static final String CODE179 = "#d7af5f";
    /**
     * Number 180 in 256 table.
     */
    private static final String CODE180 = "#d7af87";
    /**
     * Number 181 in 256 table.
     */
    private static final String CODE181 = "#d7afaf";
    /**
     * Number 182 in 256 table.
     */
    private static final String CODE182 = "#d7afd7";
    /**
     * Number 183 in 256 table.
     */
    private static final String CODE183 = "#d7afff";
    /**
     * Number 184 in 256 table.
     */
    private static final String CODE184 = "#d7d700";
    /**
     * Number 185 in 256 table.
     */
    private static final String CODE185 = "#d7d75f";
    /**
     * Number 186 in 256 table.
     */
    private static final String CODE186 = "#d7d787";
    /**
     * Number 187 in 256 table.
     */
    private static final String CODE187 = "#d7d7af";
    /**
     * Number 188 in 256 table.
     */
    private static final String CODE188 = "#d7d7d7";
    /**
     * Number 189 in 256 table.
     */
    private static final String CODE189 = "#d7d7ff";
    /**
     * Number 190 in 256 table.
     */
    private static final String CODE190 = "#d7ff00";
    /**
     * Number 191 in 256 table.
     */
    private static final String CODE191 = "#d7ff5f";
    /**
     * Number 192 in 256 table.
     */
    private static final String CODE192 = "#d7ff87";
    /**
     * Number 193 in 256 table.
     */
    private static final String CODE193 = "#d7ffaf";
    /**
     * Number 194 in 256 table.
     */
    private static final String CODE194 = "#d7ffd7";
    /**
     * Number 195 in 256 table.
     */
    private static final String CODE195 = "#d7ffff";
    /**
     * Number 196 in 256 table.
     */
    private static final String CODE196 = "#ff0000";
    /**
     * Number 197 in 256 table.
     */
    private static final String CODE197 = "#ff005f";
    /**
     * Number 198 in 256 table.
     */
    private static final String CODE198 = "#ff0087";
    /**
     * Number 199 in 256 table.
     */
    private static final String CODE199 = "#ff00af";
    /**
     * Number 200 in 256 table.
     */
    private static final String CODE200 = "#ff00d7";
    /**
     * Number 201 in 256 table.
     */
    private static final String CODE201 = "#ff00ff";
    /**
     * Number 202 in 256 table.
     */
    private static final String CODE202 = "#ff5f00";
    /**
     * Number 203 in 256 table.
     */
    private static final String CODE203 = "#ff5f5f";
    /**
     * Number 204 in 256 table.
     */
    private static final String CODE204 = "#ff5f87";
    /**
     * Number 205 in 256 table.
     */
    private static final String CODE205 = "#ff5faf";
    /**
     * Number 206 in 256 table.
     */
    private static final String CODE206 = "#ff5fd7";
    /**
     * Number 207 in 256 table.
     */
    private static final String CODE207 = "#ff5fff";
    /**
     * Number 208 in 256 table.
     */
    private static final String CODE208 = "#ff8700";
    /**
     * Number 209 in 256 table.
     */
    private static final String CODE209 = "#ff875f";
    /**
     * Number 210 in 256 table.
     */
    private static final String CODE210 = "#ff8787";
    /**
     * Number 211 in 256 table.
     */
    private static final String CODE211 = "#ff87af";
    /**
     * Number 212 in 256 table.
     */
    private static final String CODE212 = "#ff87d7";
    /**
     * Number 213 in 256 table.
     */
    private static final String CODE213 = "#ff87ff";
    /**
     * Number 214 in 256 table.
     */
    private static final String CODE214 = "#ffaf00";
    /**
     * Number 215 in 256 table.
     */
    private static final String CODE215 = "#ffaf5f";
    /**
     * Number 216 in 256 table.
     */
    private static final String CODE216 = "#ffaf87";
    /**
     * Number 217 in 256 table.
     */
    private static final String CODE217 = "#ffafaf";
    /**
     * Number 218 in 256 table.
     */
    private static final String CODE218 = "#ffafd7";
    /**
     * Number 219 in 256 table.
     */
    private static final String CODE219 = "#ffafff";
    /**
     * Number 220 in 256 table.
     */
    private static final String CODE220 = "#ffd700";
    /**
     * Number 221 in 256 table.
     */
    private static final String CODE221 = "#ffd75f";
    /**
     * Number 222 in 256 table.
     */
    private static final String CODE222 = "#ffd787";
    /**
     * Number 223 in 256 table.
     */
    private static final String CODE223 = "#ffd7af";
    /**
     * Number 224 in 256 table.
     */
    private static final String CODE224 = "#ffd7d7";
    /**
     * Number 225 in 256 table.
     */
    private static final String CODE225 = "#ffd7ff";
    /**
     * Number 226 in 256 table.
     */
    private static final String CODE226 = "#ffff00";
    /**
     * Number 227 in 256 table.
     */
    private static final String CODE227 = "#ffff5f";
    /**
     * Number 228 in 256 table.
     */
    private static final String CODE228 = "#ffff87";
    /**
     * Number 229 in 256 table.
     */
    private static final String CODE229 = "#ffffaf";
    /**
     * Number 230 in 256 table.
     */
    private static final String CODE230 = "#ffffd7";
    /**
     * Number 231 in 256 table.
     */
    private static final String CODE231 = "#ffffff";
    /**
     * Number 232 in 256 table.
     */
    private static final String CODE232 = "#080808";
    /**
     * Number 233 in 256 table.
     */
    private static final String CODE233 = "#121212";
    /**
     * Number 234 in 256 table.
     */
    private static final String CODE234 = "#1c1c1c";
    /**
     * Number 235 in 256 table.
     */
    private static final String CODE235 = "#262626";
    /**
     * Number 236 in 256 table.
     */
    private static final String CODE236 = "#303030";
    /**
     * Number 237 in 256 table.
     */
    private static final String CODE237 = "#3a3a3a";
    /**
     * Number 238 in 256 table.
     */
    private static final String CODE238 = "#444444";
    /**
     * Number 239 in 256 table.
     */
    private static final String CODE239 = "#4e4e4e";
    /**
     * Number 240 in 256 table.
     */
    private static final String CODE240 = "#585858";
    /**
     * Number 241 in 256 table.
     */
    private static final String CODE241 = "#626262";
    /**
     * Number 242 in 256 table.
     */
    private static final String CODE242 = "#6c6c6c";
    /**
     * Number 243 in 256 table.
     */
    private static final String CODE243 = "#767676";
    /**
     * Number 244 in 256 table.
     */
    private static final String CODE244 = "#808080";
    /**
     * Number 245 in 256 table.
     */
    private static final String CODE245 = "#8a8a8a";
    /**
     * Number 246 in 256 table.
     */
    private static final String CODE246 = "#949494";
    /**
     * Number 247 in 256 table.
     */
    private static final String CODE247 = "#9e9e9e";
    /**
     * Number 248 in 256 table.
     */
    private static final String CODE248 = "#a8a8a8";
    /**
     * Number 249 in 256 table.
     */
    private static final String CODE249 = "#b2b2b2";
    /**
     * Number 250 in 256 table.
     */
    private static final String CODE250 = "#bcbcbc";
    /**
     * Number 251 in 256 table.
     */
    private static final String CODE251 = "#c6c6c6";
    /**
     * Number 252 in 256 table.
     */
    private static final String CODE252 = "#d0d0d0";
    /**
     * Number 253 in 256 table.
     */
    private static final String CODE253 = "#dadada";
    /**
     * Number 254 in 256 table.
     */
    private static final String CODE254 = "#e4e4e4";
    /**
     * Number 255 in 256 table.
     */
    private static final String CODE255 = "#eeeeee";
    /**
     * Number 256 in 256 table.
     */
    private static final String CODE256 = "#ffffff";

    /**
     * Get the hash map full of colors correctly.
     *
     * @return The hash map full of colors.
     */
    private static HashMap<Integer, String> getColorMap() {
        if (colorMap.isEmpty()) {
            colorMap.put(0, CODE0);
            colorMap.put(1, CODE1);
            colorMap.put(2, CODE2);
            colorMap.put(3, CODE3);
            colorMap.put(4, CODE4);
            colorMap.put(5, CODE5);
            colorMap.put(6, CODE6);
            colorMap.put(7, CODE7);
            colorMap.put(8, CODE8);
            colorMap.put(9, CODE9);
            colorMap.put(10, CODE10);
            colorMap.put(11, CODE11);
            colorMap.put(12, CODE12);
            colorMap.put(13, CODE13);
            colorMap.put(14, CODE14);
            colorMap.put(15, CODE15);
            colorMap.put(16, CODE16);
            colorMap.put(17, CODE17);
            colorMap.put(18, CODE18);
            colorMap.put(19, CODE19);
            colorMap.put(20, CODE20);
            colorMap.put(21, CODE21);
            colorMap.put(22, CODE22);
            colorMap.put(23, CODE23);
            colorMap.put(24, CODE24);
            colorMap.put(25, CODE25);
            colorMap.put(26, CODE26);
            colorMap.put(27, CODE27);
            colorMap.put(28, CODE28);
            colorMap.put(29, CODE29);
            colorMap.put(30, CODE30);
            colorMap.put(31, CODE31);
            colorMap.put(32, CODE32);
            colorMap.put(33, CODE33);
            colorMap.put(34, CODE34);
            colorMap.put(35, CODE35);
            colorMap.put(36, CODE36);
            colorMap.put(37, CODE37);
            colorMap.put(38, CODE38);
            colorMap.put(39, CODE39);
            colorMap.put(40, CODE40);
            colorMap.put(41, CODE41);
            colorMap.put(42, CODE42);
            colorMap.put(43, CODE43);
            colorMap.put(44, CODE44);
            colorMap.put(45, CODE45);
            colorMap.put(46, CODE46);
            colorMap.put(47, CODE47);
            colorMap.put(48, CODE48);
            colorMap.put(49, CODE49);
            colorMap.put(50, CODE50);
            colorMap.put(51, CODE51);
            colorMap.put(52, CODE52);
            colorMap.put(53, CODE53);
            colorMap.put(54, CODE54);
            colorMap.put(55, CODE55);
            colorMap.put(56, CODE56);
            colorMap.put(57, CODE57);
            colorMap.put(58, CODE58);
            colorMap.put(59, CODE59);
            colorMap.put(60, CODE60);
            colorMap.put(61, CODE61);
            colorMap.put(62, CODE62);
            colorMap.put(63, CODE63);
            colorMap.put(64, CODE64);
            colorMap.put(65, CODE65);
            colorMap.put(66, CODE66);
            colorMap.put(67, CODE67);
            colorMap.put(68, CODE68);
            colorMap.put(69, CODE69);
            colorMap.put(70, CODE70);
            colorMap.put(71, CODE71);
            colorMap.put(72, CODE72);
            colorMap.put(73, CODE73);
            colorMap.put(74, CODE74);
            colorMap.put(75, CODE75);
            colorMap.put(76, CODE76);
            colorMap.put(77, CODE77);
            colorMap.put(78, CODE78);
            colorMap.put(79, CODE79);
            colorMap.put(80, CODE80);
            colorMap.put(81, CODE81);
            colorMap.put(82, CODE82);
            colorMap.put(83, CODE83);
            colorMap.put(84, CODE84);
            colorMap.put(85, CODE85);
            colorMap.put(86, CODE86);
            colorMap.put(87, CODE87);
            colorMap.put(88, CODE88);
            colorMap.put(89, CODE89);
            colorMap.put(90, CODE90);
            colorMap.put(91, CODE91);
            colorMap.put(92, CODE92);
            colorMap.put(93, CODE93);
            colorMap.put(94, CODE94);
            colorMap.put(95, CODE95);
            colorMap.put(96, CODE96);
            colorMap.put(97, CODE97);
            colorMap.put(98, CODE98);
            colorMap.put(99, CODE99);
            colorMap.put(100, CODE100);
            colorMap.put(101, CODE101);
            colorMap.put(102, CODE102);
            colorMap.put(103, CODE103);
            colorMap.put(104, CODE104);
            colorMap.put(105, CODE105);
            colorMap.put(106, CODE106);
            colorMap.put(107, CODE107);
            colorMap.put(108, CODE108);
            colorMap.put(109, CODE109);
            colorMap.put(110, CODE110);
            colorMap.put(111, CODE111);
            colorMap.put(112, CODE112);
            colorMap.put(113, CODE113);
            colorMap.put(114, CODE114);
            colorMap.put(115, CODE115);
            colorMap.put(116, CODE116);
            colorMap.put(117, CODE117);
            colorMap.put(118, CODE118);
            colorMap.put(119, CODE119);
            colorMap.put(120, CODE120);
            colorMap.put(121, CODE121);
            colorMap.put(122, CODE122);
            colorMap.put(123, CODE123);
            colorMap.put(124, CODE124);
            colorMap.put(125, CODE125);
            colorMap.put(126, CODE126);
            colorMap.put(127, CODE127);
            colorMap.put(128, CODE128);
            colorMap.put(129, CODE129);
            colorMap.put(130, CODE130);
            colorMap.put(131, CODE131);
            colorMap.put(132, CODE132);
            colorMap.put(133, CODE133);
            colorMap.put(134, CODE134);
            colorMap.put(135, CODE135);
            colorMap.put(136, CODE136);
            colorMap.put(137, CODE137);
            colorMap.put(138, CODE138);
            colorMap.put(139, CODE139);
            colorMap.put(140, CODE140);
            colorMap.put(141, CODE141);
            colorMap.put(142, CODE142);
            colorMap.put(143, CODE143);
            colorMap.put(144, CODE144);
            colorMap.put(145, CODE145);
            colorMap.put(146, CODE146);
            colorMap.put(147, CODE147);
            colorMap.put(148, CODE148);
            colorMap.put(149, CODE149);
            colorMap.put(150, CODE150);
            colorMap.put(151, CODE151);
            colorMap.put(152, CODE152);
            colorMap.put(153, CODE153);
            colorMap.put(154, CODE154);
            colorMap.put(155, CODE155);
            colorMap.put(156, CODE156);
            colorMap.put(157, CODE157);
            colorMap.put(158, CODE158);
            colorMap.put(159, CODE159);
            colorMap.put(160, CODE160);
            colorMap.put(161, CODE161);
            colorMap.put(162, CODE162);
            colorMap.put(163, CODE163);
            colorMap.put(164, CODE164);
            colorMap.put(165, CODE165);
            colorMap.put(166, CODE166);
            colorMap.put(167, CODE167);
            colorMap.put(168, CODE168);
            colorMap.put(169, CODE169);
            colorMap.put(170, CODE170);
            colorMap.put(171, CODE171);
            colorMap.put(172, CODE172);
            colorMap.put(173, CODE173);
            colorMap.put(174, CODE174);
            colorMap.put(175, CODE175);
            colorMap.put(176, CODE176);
            colorMap.put(177, CODE177);
            colorMap.put(178, CODE178);
            colorMap.put(179, CODE179);
            colorMap.put(180, CODE180);
            colorMap.put(181, CODE181);
            colorMap.put(182, CODE182);
            colorMap.put(183, CODE183);
            colorMap.put(184, CODE184);
            colorMap.put(185, CODE185);
            colorMap.put(186, CODE186);
            colorMap.put(187, CODE187);
            colorMap.put(188, CODE188);
            colorMap.put(189, CODE189);
            colorMap.put(190, CODE190);
            colorMap.put(191, CODE191);
            colorMap.put(192, CODE192);
            colorMap.put(193, CODE193);
            colorMap.put(194, CODE194);
            colorMap.put(195, CODE195);
            colorMap.put(196, CODE196);
            colorMap.put(197, CODE197);
            colorMap.put(198, CODE198);
            colorMap.put(199, CODE199);
            colorMap.put(200, CODE200);
            colorMap.put(201, CODE201);
            colorMap.put(202, CODE202);
            colorMap.put(203, CODE203);
            colorMap.put(204, CODE204);
            colorMap.put(205, CODE205);
            colorMap.put(206, CODE206);
            colorMap.put(207, CODE207);
            colorMap.put(208, CODE208);
            colorMap.put(209, CODE209);
            colorMap.put(210, CODE210);
            colorMap.put(211, CODE211);
            colorMap.put(212, CODE212);
            colorMap.put(213, CODE213);
            colorMap.put(214, CODE214);
            colorMap.put(215, CODE215);
            colorMap.put(216, CODE216);
            colorMap.put(217, CODE217);
            colorMap.put(218, CODE218);
            colorMap.put(219, CODE219);
            colorMap.put(220, CODE220);
            colorMap.put(221, CODE221);
            colorMap.put(222, CODE222);
            colorMap.put(223, CODE223);
            colorMap.put(224, CODE224);
            colorMap.put(225, CODE225);
            colorMap.put(226, CODE226);
            colorMap.put(227, CODE227);
            colorMap.put(228, CODE228);
            colorMap.put(229, CODE229);
            colorMap.put(230, CODE230);
            colorMap.put(231, CODE231);
            colorMap.put(232, CODE232);
            colorMap.put(233, CODE233);
            colorMap.put(234, CODE234);
            colorMap.put(235, CODE235);
            colorMap.put(236, CODE236);
            colorMap.put(237, CODE237);
            colorMap.put(238, CODE238);
            colorMap.put(239, CODE239);
            colorMap.put(240, CODE240);
            colorMap.put(241, CODE241);
            colorMap.put(242, CODE242);
            colorMap.put(243, CODE243);
            colorMap.put(244, CODE244);
            colorMap.put(245, CODE245);
            colorMap.put(246, CODE246);
            colorMap.put(247, CODE247);
            colorMap.put(248, CODE248);
            colorMap.put(249, CODE249);
            colorMap.put(250, CODE250);
            colorMap.put(251, CODE251);
            colorMap.put(252, CODE252);
            colorMap.put(253, CODE253);
            colorMap.put(254, CODE254);
            colorMap.put(255, CODE255);
            colorMap.put(256, CODE256);
        }

        return colorMap;
    }

    /**
     * Get all the colors in an RGB format.
     *
     * @return the map of all the colors in an RGB format.
     */
    public static Integer[][] getAllColorsRGB() throws InvalidHexCode {
        String[] colors = getAllColors();
        HashMap<Integer, Integer[]> rgbColorMap = new HashMap<>();
        int index = 0;

        for (String color : colors) {
            rgbColorMap.put(index, ColorConversion.hexToRGB(color));
            index++;
        }

        return rgbColorMap.values().toArray(new Integer[0][0]);
    }

    /**
     * Get a specific color from the 256 color palette.
     *
     * @param color The color to get.
     * @return The color.
     */
    public static String getColor(int color) {
        if (color < 0 || color > 255) {
            return "#FFFFFF";
        }

        return getColorMap().get(color);
    }

    /**
     * Get the color ID index out of the 256 color palette.
     *
     * @param color The color of the ID index.
     * @return The ID's index.
     */
    public static Integer getCode(String color) {
        String colorPrefix = "";

        if (!color.startsWith("#")) {
            colorPrefix = "#";
        }

        for (Map.Entry<Integer, String> entry : getColorMap().entrySet()) {
            if ((entry.getValue().toUpperCase() + "FF").equals(colorPrefix + color)) {
                return entry.getKey();
            }
        }

        return null;
    }

    /**
     * Get the color ID index out of the 256 color palette.
     *
     * @param red   The red value of the color.
     * @param green The green value of the color.
     * @param blue  The blue value of the color.
     * @return The ID's index.
     */
    public static Integer getCode(Integer red, Integer green, Integer blue) throws InvalidRGBAlpha, InvalidRGBValues {
        final String hexColor = ColorConversion.rgbToHex(red, green, blue, 1);
        final String color = "#" + hexColor;

        for (Map.Entry<Integer, String> entry : getColorMap().entrySet()) {
            if ((entry.getValue().toUpperCase() + "FF").equals(color)) {
                return entry.getKey();
            }
        }

        return null;
    }

    /**
     * Get all the colors from the 256 color palette.
     *
     * @return The colors.
     */
    public static String[] getAllColors() {
        return getColorMap().values().toArray(new String[0]);
    }
}
