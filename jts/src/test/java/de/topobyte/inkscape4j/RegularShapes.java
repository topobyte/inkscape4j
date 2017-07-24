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

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;

public class RegularShapes
{

	public static Polygon polygon(int nPoints, double cx, double cy,
			double radius)
	{
		GeometryFactory factory = new GeometryFactory();
		Coordinate[] coordinates = new Coordinate[nPoints + 1];

		double angle = Math.PI * 2d / nPoints;

		coordinates[0] = coordinates[nPoints] = new Coordinate(cx + radius, cy);

		for (int i = 1; i < nPoints; i++) {
			double x = cx + radius * Math.cos(angle * i);
			double y = cy + radius * Math.sin(angle * i);
			coordinates[i] = new Coordinate(x, y);
		}

		return factory.createPolygon(coordinates);
	}

}
