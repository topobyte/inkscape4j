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

import org.locationtech.jts.geom.Polygon;

import de.topobyte.inkscape4j.path.FillRule;
import de.topobyte.inkscape4j.path.Path;

public class TestStars
{

	public static void main(String[] args) throws IOException
	{
		main("/tmp/test-stars-nonzero.svg", FillRule.NON_ZERO);
		main("/tmp/test-stars-evenodd.svg", FillRule.EVEN_ODD);
	}

	public static void main(String filename, FillRule fillRule)
			throws IOException
	{
		SvgFile file = new SvgFile();
		file.setWidth("500px");
		file.setHeight("500px");

		Layer layer1 = new Layer("geometries");
		file.getLayers().add(layer1);
		layer1.setLabel("Geometries");

		// A star with 3 corners

		Polygon polygon1 = RegularShapes.star(1, 125, 125, 100);

		Path path1 = JtsToPath.convert("star1", fillRule, polygon1);
		layer1.getObjects().add(path1);
		path1.setStyle(style(color(0xffaaaa), color(0x333333), 1, 1, 1, 2));

		// A star with 5 corners

		Polygon polygon2 = RegularShapes.star(2, 375, 125, 100);

		Path path2 = JtsToPath.convert("star2", fillRule, polygon2);
		layer1.getObjects().add(path2);
		path2.setStyle(style(color(0xffaaaa), color(0x333333), 1, 1, 1, 2));

		// A star with 7 corners

		Polygon polygon3 = RegularShapes.star(3, 125, 375, 100);

		Path path3 = JtsToPath.convert("star3", fillRule, polygon3);
		layer1.getObjects().add(path3);
		path3.setStyle(style(color(0xffaaaa), color(0x333333), 1, 1, 1, 2));

		// A star with 9 corners

		Polygon polygon4 = RegularShapes.star(4, 375, 375, 100);

		Path path4 = JtsToPath.convert("star4", fillRule, polygon4);
		layer1.getObjects().add(path4);
		path4.setStyle(style(color(0xffaaaa), color(0x333333), 1, 1, 1, 2));

		// ---

		SvgFileWriting.write(file, System.out);
		FileOutputStream fos = new FileOutputStream(filename);
		SvgFileWriting.write(file, fos);
		fos.close();
	}

}
