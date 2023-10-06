// Copyright 2023 Sebastian Kuerten
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

import de.topobyte.inkscape4j.shape.Shape;

/**
 * A path that is not defined by a list of PathElements but instead simply by a
 * proper SVG path definition string.
 */
public class StringPath extends Shape
{

	private final FillRule fillRule;
	private final String definition;

	public StringPath(String id, FillRule fillRule, String definition)
	{
		super(id);
		this.fillRule = fillRule;
		this.definition = definition;
	}

	public FillRule getFillRule()
	{
		return fillRule;
	}

	public String getDefinition()
	{
		return definition;
	}

}
