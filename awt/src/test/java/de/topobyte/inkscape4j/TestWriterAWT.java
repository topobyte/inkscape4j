// Copyright 2019 Sebastian Kuerten
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

import java.awt.geom.Ellipse2D;
import java.io.FileOutputStream;
import java.io.IOException;

import de.topobyte.inkscape4j.path.FillRule;
import de.topobyte.inkscape4j.path.Path;

public class TestWriterAWT
{

	public static void main(String[] args) throws IOException
	{
		SvgFile file = new SvgFile();
		file.setWidth("500px");
		file.setHeight("400px");

		Layer layer1 = new Layer("geometries");
		file.getLayers().add(layer1);
		layer1.setLabel("Geometries");

		Ellipse2D ellipse = new Ellipse2D.Double(100, 100, 200, 150);

		Path path1 = ShapeToPath.convert("ellipse", FillRule.EVEN_ODD, ellipse);
		layer1.getObjects().add(path1);
		path1.setStyle(style(color(0xff0000), color(0x333333), 1, 1, 1, 2));

		SvgFileWriting.write(file, System.out);
		FileOutputStream fos = new FileOutputStream("/tmp/test-awt.svg");
		SvgFileWriting.write(file, fos);
		fos.close();
	}

}
