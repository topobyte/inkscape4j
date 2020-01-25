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

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;

import de.topobyte.inkscape4j.path.FillRule;
import de.topobyte.inkscape4j.path.Path;

public class TestPolygonsWithHoles
{

	public static void main(String[] args) throws IOException
	{
		SvgFile file = new SvgFile();
		file.setWidth("500px");
		file.setHeight("500px");

		Layer layer1 = new Layer("geometries");
		file.getLayers().add(layer1);
		layer1.setLabel("Geometries");

		// A polygon without any holes

		Polygon polygon1 = RegularShapes.polygon(10, 125, 125, 100);

		Path path1 = JtsToPath.convert("polygon1", FillRule.EVEN_ODD, polygon1);
		layer1.getObjects().add(path1);
		path1.setStyle(style(color(0xffaaaa), color(0x333333), 1, 1, 1, 2));

		// A polygon with a hole

		Polygon outer2 = RegularShapes.polygon(10, 375, 125, 100);
		Polygon hole2 = RegularShapes.polygon(10, 375, 125, 50);
		Geometry polygon2 = outer2.difference(hole2);

		Path path2 = JtsToPath.convert("polygon2", FillRule.EVEN_ODD, polygon2);
		layer1.getObjects().add(path2);
		path2.setStyle(style(color(0xffaaaa), color(0x333333), 1, 1, 1, 2));

		// A polygon with two holes

		Polygon outer3 = RegularShapes.polygon(10, 125, 375, 100);
		Polygon hole31 = RegularShapes.polygon(10, 90, 375, 30);
		Polygon hole32 = RegularShapes.polygon(10, 160, 375, 30);
		Geometry polygon3 = outer3.difference(hole31).difference(hole32);

		Path path3 = JtsToPath.convert("polygon3", FillRule.EVEN_ODD, polygon3);
		layer1.getObjects().add(path3);
		path3.setStyle(style(color(0xffaaaa), color(0x333333), 1, 1, 1, 2));

		// Multiple rings within each other

		int n = 11;
		int min = 10;
		int max = 100;
		int nPoints = 20;
		Geometry polygon4 = RegularShapes.polygon(nPoints, 375, 375, 100);
		for (int i = 0; i < n; i++) {
			double r = max - i * (max - min) / (double) n;
			Polygon inner = RegularShapes.polygon(nPoints, 375, 375, r);
			if ((i % 2) == 0) {
				polygon4 = polygon4.difference(inner);
			} else {
				polygon4 = polygon4.union(inner);
			}
		}

		Path path4 = JtsToPath.convert("polygon4", FillRule.EVEN_ODD, polygon4);
		layer1.getObjects().add(path4);
		path4.setStyle(style(color(0xffaaaa), color(0x333333), 1, 1, 1, 2));

		// ---

		SvgFileWriting.write(file, System.out);
		FileOutputStream fos = new FileOutputStream(
				"/tmp/test-polygons-with-holes.svg");
		SvgFileWriting.write(file, fos);
		fos.close();
	}

}
