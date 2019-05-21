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

import java.awt.Shape;
import java.awt.geom.PathIterator;

import de.topobyte.inkscape4j.path.FillRule;
import de.topobyte.inkscape4j.path.Path;
import de.topobyte.inkscape4j.path.PathBuilder;

public class ShapeToPath
{

	public static Path convert(String id, FillRule fillRule, Shape shape)
	{
		PathBuilder pb = new PathBuilder();

		convert(pb, shape);

		return pb.build(id, fillRule);
	}

	private static void convert(PathBuilder pb, Shape shape)
	{
		PathIterator it = shape.getPathIterator(null);
		float points[] = new float[6];
		int type = 0;

		while (!it.isDone()) {
			type = it.currentSegment(points);
			switch (type) {
			case PathIterator.SEG_MOVETO:
				pb.move(false, points[0], points[1]);
				break;
			case PathIterator.SEG_CLOSE:
				pb.close(false);
				break;
			case PathIterator.SEG_LINETO:
				pb.line(false, points[0], points[1]);
				break;
			case PathIterator.SEG_QUADTO:
				pb.quad(false, points[0], points[1], points[2], points[3]);
				break;
			case PathIterator.SEG_CUBICTO:
				pb.cubic(false, points[0], points[1], points[2], points[3],
						points[4], points[5]);
				break;
			}
			it.next();
		}
	}

}
