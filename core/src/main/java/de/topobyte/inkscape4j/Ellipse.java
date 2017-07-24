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

public class Ellipse extends Shape
{

	private double cx;
	private double cy;

	private double radiusX;
	private double radiusY;

	public Ellipse(String id)
	{
		super(id);
	}

	public Ellipse(String id, double cx, double cy, double radiusX,
			double radiusY)
	{
		super(id);
		this.cx = cx;
		this.cy = cy;
		this.radiusX = radiusX;
		this.radiusY = radiusY;
	}

	public double getCx()
	{
		return cx;
	}

	public void setCx(double cx)
	{
		this.cx = cx;
	}

	public double getCy()
	{
		return cy;
	}

	public void setCy(double cy)
	{
		this.cy = cy;
	}

	public double getRadiusX()
	{
		return radiusX;
	}

	public void setRadiusX(double radiusX)
	{
		this.radiusX = radiusX;
	}

	public double getRadiusY()
	{
		return radiusY;
	}

	public void setRadiusY(double radiusY)
	{
		this.radiusY = radiusY;
	}

}
