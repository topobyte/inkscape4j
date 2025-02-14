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

public class LineTo extends BasePathElement
{

	@Override
	public Type getType()
	{
		return Type.LINE;
	}

	private double x;
	private double y;

	public LineTo(double x, double y)
	{
		this(true, x, y);
	}

	public LineTo(boolean isRelative, double x, double y)
	{
		super(isRelative);
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(x, y);
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
		LineTo other = (LineTo) obj;
		return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double
						.doubleToLongBits(other.y);
	}

}
