# Inkscape4j

Inkscape4j is a set of libraries that can be used for producing SVG files
using Java. In contrast to other methods of creating SVG files
programmatically, emphasis is put on creating SVG files that can be nicely
edited using Inkscape afterwards.
To that end, it is possible to create layers and put individual SVG
elements into them.
That is useful if the producer of the image file is creating the image
from some kind of structured data and wants to preserve part of that
structure in the resulting SVG file.
As an example, consider a tool that generates SVG maps from geographic data.
With inkscape4j it is possible to create one layer for the rendered streets,
another one for buildings, and yet more layers for lakes, rivers etc.
It would then be relatively easy, or at least possible, for a user to
work on the map image afterwards and apply operations such as hiding a
set of features or selecting all buildings and changing their color.

## TODO

* Make sure there are not duplicate ids when embedding SVG images
* Improve formatting of resulting files (indent) with embedded SVG images
