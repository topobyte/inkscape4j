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

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.topobyte.chromaticity.ColorCode;
import de.topobyte.inkscape4j.path.Close;
import de.topobyte.inkscape4j.path.CubicTo;
import de.topobyte.inkscape4j.path.FillRule;
import de.topobyte.inkscape4j.path.LineTo;
import de.topobyte.inkscape4j.path.MoveTo;
import de.topobyte.inkscape4j.path.Path;
import de.topobyte.inkscape4j.path.PathElement;
import de.topobyte.inkscape4j.path.QuadTo;

public class TestWriter
{

	public static void main(String[] args) throws IOException
	{
		SvgFile file = new SvgFile();
		file.setWidth("500px");
		file.setHeight("400px");

		Layer layer1 = new Layer("rects");
		file.getLayers().add(layer1);
		layer1.setLabel("Some rectangles");

		Layer layer2 = new Layer("circles");
		file.getLayers().add(layer2);
		layer2.setLabel("Circles / Ellipses");

		Layer layer3 = new Layer("paths");
		file.getLayers().add(layer3);
		layer3.setLabel("Some paths");

		Rect rect1 = new Rect("rect1", 10, 20, 100, 50);
		layer1.getObjects().add(rect1);

		Rect rect2 = new Rect("rect2", 120, 20, 50, 100);
		layer1.getObjects().add(rect2);

		Rect rect3 = new Rect("rect3", 180, 20, 50, 50);
		layer1.getObjects().add(rect3);

		Rect rect4 = new Rect("rect4", 240, 20, 50, 50);
		layer1.getObjects().add(rect4);

		rect1.setStyle(style(color(0xffaaaa), color(0x333333), 1, 1, 1, 2));
		rect2.setStyle(style(color(0xaaaaff), color(0x666666), 1, 1, 1, 2));
		rect3.setStyle(style(color(0xaaaaff), null, 1, 1, 1, 2));
		rect4.setStyle(style(null, color(0x000000), 1, 1, 1, 2));

		Circle circle1 = new Circle("circle1", 60, 150, 50);
		layer2.getObjects().add(circle1);
		circle1.setStyle(style(color(0xaaffaa), color(0x999999), 1, 1, 1, 2));

		Ellipse ellipse1 = new Ellipse("ellipse1", 200, 180, 80, 50);
		layer2.getObjects().add(ellipse1);
		ellipse1.setStyle(style(color(0xaaffaa), color(0x999999), 1, 1, 1, 2));

		List<PathElement> elements1 = new ArrayList<>();
		Path path1 = new Path("path1", FillRule.EVEN_ODD, elements1);
		layer3.getObjects().add(path1);
		path1.setStyle(style(color(0xffaaaa), color(0x000000), 1, 0.6, 1, 2));

		elements1.add(new MoveTo(300, 100));
		elements1.add(new LineTo(200, 150));
		elements1.add(new LineTo(-50, 0));
		elements1.add(new QuadTo(0, 50, -50, 50));
		elements1.add(new CubicTo(-50, 0, -100, 100, -100, 0));
		elements1.add(new Close());

		SvgFileWriting.write(file, System.out);
		FileOutputStream fos = new FileOutputStream("/tmp/test.svg");
		SvgFileWriting.write(file, fos);
		fos.close();
	}

	private static Style style(ColorCode fill, ColorCode stroke, double opacity,
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

	private static ColorCode color(int rgb)
	{
		return new ColorCode(rgb);
	}

}
