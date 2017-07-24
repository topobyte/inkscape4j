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

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Polygon;

import de.topobyte.inkscape4j.path.FillRule;
import de.topobyte.inkscape4j.path.Path;

public class TestWriterJts
{

	public static void main(String[] args) throws IOException
	{
		SvgFile file = new SvgFile();
		file.setWidth("500px");
		file.setHeight("400px");

		Layer layer1 = new Layer("geometries");
		file.getLayers().add(layer1);
		layer1.setLabel("Geometries");

		GeometryFactory factory = new GeometryFactory();

		Polygon polygon = factory.createPolygon(new Coordinate[] {
				new Coordinate(100, 100), new Coordinate(200, 100),
				new Coordinate(150, 200), new Coordinate(100, 100) });

		LineString lineString = factory
				.createLineString(new Coordinate[] { new Coordinate(250, 100),
						new Coordinate(300, 150), new Coordinate(300, 250) });

		Path path1 = JtsToPath.convert("polygon1", FillRule.EVEN_ODD, polygon);
		layer1.getObjects().add(path1);
		path1.setStyle(style(color(0xff0000), color(0x333333), 1, 1, 1, 2));

		Path path2 = JtsToPath.convert("linestring1", FillRule.EVEN_ODD,
				lineString);
		layer1.getObjects().add(path2);
		path2.setStyle(style(null, color(0x333333), 1, 1, 1, 2));

		SvgFileWriting.write(file, System.out);
		FileOutputStream fos = new FileOutputStream("/tmp/test-jts.svg");
		SvgFileWriting.write(file, fos);
		fos.close();
	}

}
