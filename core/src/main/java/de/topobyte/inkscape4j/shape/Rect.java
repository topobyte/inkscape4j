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

package de.topobyte.inkscape4j.shape;

public class Rect extends Shape
{

	private double x;
	private double y;

	private double width;
	private double height;

	private double rx;
	private double ry;

	public Rect(String id)
	{
		super(id);
	}

	public Rect(String id, double x, double y, double width, double height)
	{
		super(id);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public double getRx()
	{
		return rx;
	}

	public void setRx(double rx)
	{
		this.rx = rx;
	}

	public double getRy()
	{
		return ry;
	}

	public void setRy(double ry)
	{
		this.ry = ry;
	}

}
