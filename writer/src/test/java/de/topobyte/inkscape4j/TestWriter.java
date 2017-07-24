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

import static de.topobyte.inkscape4j.Styles.color;
import static de.topobyte.inkscape4j.Styles.style;

import java.io.FileOutputStream;
import java.io.IOException;

import de.topobyte.inkscape4j.path.FillRule;
import de.topobyte.inkscape4j.path.Path;
import de.topobyte.inkscape4j.path.PathBuilder;
import de.topobyte.inkscape4j.shape.Circle;
import de.topobyte.inkscape4j.shape.Ellipse;
import de.topobyte.inkscape4j.shape.Rect;

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

		PathBuilder pb = new PathBuilder();
		pb.move(300, 100).line(200, 150).line(-50, 0).quad(0, 50, -50, 50)
				.cubic(-50, 0, -100, 100, -100, 0).close();
		Path path1 = pb.build("path1", FillRule.EVEN_ODD);
		layer3.getObjects().add(path1);
		path1.setStyle(style(color(0xffaaaa), color(0x000000), 1, 0.6, 1, 2));

		SvgFileWriting.write(file, System.out);
		FileOutputStream fos = new FileOutputStream("/tmp/test.svg");
		SvgFileWriting.write(file, fos);
		fos.close();
	}

}
