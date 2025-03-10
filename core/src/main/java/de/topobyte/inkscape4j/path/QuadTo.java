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

public class QuadTo extends BasePathElement
{

	@Override
	public Type getType()
	{
		return Type.QUAD;
	}

	private double cx;
	private double cy;
	private double x;
	private double y;

	public QuadTo(double cx, double cy, double x, double y)
	{
		this(true, cx, cy, x, y);
	}

	public QuadTo(boolean isRelative, double cx, double cy, double x, double y)
	{
		super(isRelative);
		this.cx = cx;
		this.cy = cy;
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

	public double getCX()
	{
		return cx;
	}

	public double getCY()
	{
		return cy;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cx, cy, x, y);
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
		QuadTo other = (QuadTo) obj;
		return Double.doubleToLongBits(cx) == Double.doubleToLongBits(other.cx)
				&& Double.doubleToLongBits(cy) == Double
						.doubleToLongBits(other.cy)
				&& Double.doubleToLongBits(x) == Double
						.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double
						.doubleToLongBits(other.y);
	}

}
