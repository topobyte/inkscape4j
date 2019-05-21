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

import java.util.ArrayList;
import java.util.List;

public class PathBuilder
{

	private List<PathElement> elements = new ArrayList<>();

	public PathBuilder move(double x, double y)
	{
		elements.add(new MoveTo(x, y));
		return this;
	}

	public PathBuilder move(boolean relative, double x, double y)
	{
		elements.add(new MoveTo(relative, x, y));
		return this;
	}

	public PathBuilder close()
	{
		elements.add(new Close());
		return this;
	}

	public PathBuilder close(boolean relative)
	{
		elements.add(new Close(relative));
		return this;
	}

	public PathBuilder line(double x, double y)
	{
		elements.add(new LineTo(x, y));
		return this;
	}

	public PathBuilder line(boolean relative, double x, double y)
	{
		elements.add(new LineTo(relative, x, y));
		return this;
	}

	public PathBuilder quad(double cx, double cy, double x, double y)
	{
		elements.add(new QuadTo(cx, cy, x, y));
		return this;
	}

	public PathBuilder cubic(double cx1, double cy1, double cx2, double cy2,
			double x, double y)
	{
		elements.add(new CubicTo(cx1, cy1, cx2, cy2, x, y));
		return this;
	}

	public PathBuilder cubic(boolean relative, double cx1, double cy1,
			double cx2, double cy2, double x, double y)
	{
		elements.add(new CubicTo(relative, cx1, cy1, cx2, cy2, x, y));
		return this;
	}

	public PathBuilder clear()
	{
		elements.clear();
		return this;
	}

	public Path build(String id, FillRule fillRule)
	{
		return new Path(id, fillRule, new ArrayList<>(elements));
	}

}
