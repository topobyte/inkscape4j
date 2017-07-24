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

		Rect rect1 = new Rect("rect1");
		layer1.getObjects().add(rect1);
		rect1.setX(10);
		rect1.setY(20);
		rect1.setWidth(100);
		rect1.setHeight(50);

		Style style = new Style();
		style.setFill("#ffaaaa");
		style.setStroke("#333333");
		style.setOpacity(1);
		style.setFillOpacity(1);
		style.setStrokeOpacity(1);
		style.setStrokeWidth(2);
		rect1.setStyle(style);

		SvgFileWriting.write(file, System.out);
		FileOutputStream fos = new FileOutputStream("/tmp/test.svg");
		SvgFileWriting.write(file, fos);
		fos.close();
	}

}
