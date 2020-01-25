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

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;

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

	public static Polygon star(int n, double cx, double cy, double radius)
	{
		int nPoints = 1 + 2 * n;

		GeometryFactory factory = new GeometryFactory();
		Coordinate[] coordinates = new Coordinate[nPoints + 1];

		double angle = Math.PI * 2d / nPoints;

		coordinates[0] = coordinates[nPoints] = new Coordinate(cx + radius, cy);

		for (int i = 1; i < nPoints; i++) {
			int k = (i * 2) % nPoints;
			double x = cx + radius * Math.cos(angle * k);
			double y = cy + radius * Math.sin(angle * k);
			coordinates[i] = new Coordinate(x, y);
		}

		return factory.createPolygon(coordinates);
	}

}
