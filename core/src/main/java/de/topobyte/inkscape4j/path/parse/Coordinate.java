// Copyright 2025 Sebastian Kuerten
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

package de.topobyte.inkscape4j.path.parse;

public class Coordinate
{

	double x;
	double y;

	public Coordinate(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString()
	{
		return String.format("(%f,%f)", x, y);
	}

}
