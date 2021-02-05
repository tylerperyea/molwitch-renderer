# molwitch-renderer

NCATS custom Molecule Renderer that uses Molwitch `Chemical` objects into image files.

## Customizable by JSON Configuration

The `RendererOptions` class has many configuration options to change how the renderer displays.  An instance can either be created by making a new `RendererOptions` instance
and then call the appriopriate setter methods or you can use JSON and create an instance using Jackson 

```java
 RendererOptions expected = RendererOptions.createUSPLike();

String json = mapper.writer().writeValueAsString(map);


RendererOptions renderOptions = mapper.readValue(json, RendererOptions.class);
```
### Example JSON

```json
{
  "options" : {
    "DEF_FONT_GAP_PERCENT" : 1.02,
    "DEF_WEDGE_ANG" : 0.1156106096521044,
    "PROP_KEY_DRAW_GREYSCALE" : true,
    "PROP_KEY_DRAW_WEDGE_AS_POINT" : false,
    "SUBSCRIPT_Y_DISPLACEMENT_FRACTION" : 0.17,
    "DEF_FONT_PERCENT" : 0.47918,
    "DEF_STROKE_PERCENT" : 0.032,
    "DEF_DBL_BOND_GAP" : 0.19949999999999998,
    "PROP_KEY_DRAW_STEREO_LAST_DASH_ON_NON_SYMBOLS" : false,
    "PROP_KEY_DRAW_STEREO_WEDGE_JOIN" : true,
    "DEF_NUM_DASH" : 8.0
  },
  "add-shadow" : true,
  "add-border" : true,
  "color-bg" : "0",
  "color-border" : "ff000000"
}
```


and you can be even more creative like you can change the colors for each atom like this:
```json
{
  "options" : {
    "DEF_FONT_GAP_PERCENT" : 1.02,
    "DEF_WEDGE_ANG" : 0.1156106096521044,
    "PROP_KEY_DRAW_GREYSCALE" : true,
    "PROP_KEY_DRAW_WEDGE_AS_POINT" : false,
    "SUBSCRIPT_Y_DISPLACEMENT_FRACTION" : 0.17,
    "DEF_FONT_PERCENT" : 0.47918,
    "DEF_STROKE_PERCENT" : 0.032,
    "DEF_DBL_BOND_GAP" : 0.19949999999999998,
    "PROP_KEY_DRAW_STEREO_LAST_DASH_ON_NON_SYMBOLS" : false,
    "PROP_KEY_DRAW_STEREO_WEDGE_JOIN" : true,
    "colorPalette" : {
      "atomColors" : {
        "P" : "ffe6db45",
        "Br" : "ff735423",
        "S" : "ff2d2d43",
        "C" : "ff3a3a3a",
        "Na" : "ff308fa0",
        "F" : "ff36b449",
        "H" : "ff3a3a3a",
        "Cl" : "ff36b449",
        "I" : "ffe645cd",
        "N" : "ff5d45e6",
        "O" : "ffe65d45"
      }
    },
    "DEF_NUM_DASH" : 8.0
  },
  "add-shadow" : false,
  "add-border" : false,
  "color-bg" : "ffff0000",
  "color-border" : "ff000000"
}
```

Once you have your `RenderOptions` you can create your `ChemicalRenderer` instance:

```java
RendererOptions renderOptions = ...

ChemicalRenderer renderer = new ChemicalRender( renderOptions);

 Chemical c = Chemical.parseMol(mol);
 int imageSize=500;
 ImageIO.write(renderer.createImage(c, imageSize), "PNG", new File("myRenderedImage.png"));
```
