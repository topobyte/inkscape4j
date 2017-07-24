// Copyright 2017 Sebastian Kuerten
//
// This file is part of inkscape4j.
//
// inkscape4j is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// inkscape4j is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with inkscape4j. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.inkscape4j;

import de.topobyte.chromaticity.ColorCode;

public class Styles
{

	public static Style style(ColorCode fill, ColorCode stroke, double opacity,
			double fillOpacity, double strokeOpacity, double strokeWidth)
	{
		Style style = new Style();
		style.setFill(fill);
		style.setStroke(stroke);
		style.setOpacity(opacity);
		style.setFillOpacity(fillOpacity);
		style.setStrokeOpacity(strokeOpacity);
		style.setStrokeWidth(strokeWidth);
		return style;
	}

	public static ColorCode color(int rgb)
	{
		return new ColorCode(rgb);
	}

}
