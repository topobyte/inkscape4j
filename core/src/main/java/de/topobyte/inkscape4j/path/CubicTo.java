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

package de.topobyte.inkscape4j.path;

import java.util.Objects;

public class CubicTo extends BasePathElement
{

	@Override
	public Type getType()
	{
		return Type.CUBIC;
	}

	private double cx1;
	private double cy1;
	private double cx2;
	private double cy2;
	private double x;
	private double y;

	public CubicTo(double cx1, double cy1, double cx2, double cy2, double x,
			double y)
	{
		this(true, cx1, cy1, cx2, cy2, x, y);
	}

	public CubicTo(boolean isRelative, double cx1, double cy1, double cx2,
			double cy2, double x, double y)
	{
		super(isRelative);
		this.cx1 = cx1;
		this.cy1 = cy1;
		this.cx2 = cx2;
		this.cy2 = cy2;
		this.x = x;
		this.y = y;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getCX1()
	{
		return cx1;
	}

	public double getCY1()
	{
		return cy1;
	}

	public double getCX2()
	{
		return cx2;
	}

	public double getCY2()
	{
		return cy2;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cx1, cx2, cy1, cy2, x, y);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CubicTo other = (CubicTo) obj;
		return Double.doubleToLongBits(cx1) == Double
				.doubleToLongBits(other.cx1)
				&& Double.doubleToLongBits(cx2) == Double
						.doubleToLongBits(other.cx2)
				&& Double.doubleToLongBits(cy1) == Double
						.doubleToLongBits(other.cy1)
				&& Double.doubleToLongBits(cy2) == Double
						.doubleToLongBits(other.cy2)
				&& Double.doubleToLongBits(x) == Double
						.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double
						.doubleToLongBits(other.y);
	}

}
