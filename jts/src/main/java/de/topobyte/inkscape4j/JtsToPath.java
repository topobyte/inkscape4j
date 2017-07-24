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
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;

import de.topobyte.inkscape4j.path.FillRule;
import de.topobyte.inkscape4j.path.Path;
import de.topobyte.inkscape4j.path.PathBuilder;

public class JtsToPath
{

	public static Path convert(String id, FillRule fillRule, Geometry geometry)
	{
		if (geometry instanceof LineString) {
			return convert(id, fillRule, (LineString) geometry);
		} else if (geometry instanceof Polygon) {
			return convert(id, fillRule, (Polygon) geometry);
		} else if (geometry instanceof MultiPolygon) {
			return convert(id, fillRule, (MultiPolygon) geometry);
		}
		return null;
	}

	public static Path convert(String id, FillRule fillRule,
			LineString lineString)
	{
		PathBuilder pb = new PathBuilder();

		convert(pb, lineString);

		return pb.build(id, fillRule);
	}

	public static Path convert(String id, FillRule fillRule, Polygon polygon)
	{
		PathBuilder pb = new PathBuilder();

		convert(pb, polygon);

		return pb.build(id, fillRule);
	}

	public static Path convert(String id, FillRule fillRule,
			MultiPolygon polygon)
	{
		PathBuilder pb = new PathBuilder();

		for (int i = 0; i < polygon.getNumGeometries(); i++) {
			Polygon part = (Polygon) polygon.getGeometryN(i);
			convert(pb, part);
		}

		return pb.build(id, fillRule);
	}

	private static void convert(PathBuilder pb, Polygon polygon)
	{
		LineString exterior = polygon.getExteriorRing();
		convert(pb, exterior);

		for (int i = 0; i < polygon.getNumInteriorRing(); i++) {
			LineString inner = polygon.getInteriorRingN(i);
			convert(pb, inner);
		}
	}

	private static void convert(PathBuilder pb, LineString ls)
	{
		Coordinate c0 = ls.getCoordinateN(0);
		pb.move(c0.x, c0.y);

		Coordinate p = c0;
		for (int i = 1; i < ls.getNumPoints() - 1; i++) {
			Coordinate c = ls.getCoordinateN(i);
			pb.line(c.x - p.x, c.y - p.y);
			p = c;
		}

		if (ls.isClosed()) {
			pb.close();
		} else {
			Coordinate c = ls.getCoordinateN(ls.getNumPoints() - 1);
			pb.line(c.x - p.x, c.y - p.y);
		}
	}

}
