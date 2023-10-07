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

package de.topobyte.inkscape4j;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.util.AffineTransformation;

public class Group
{

	private String id;

	private AffineTransformation transform;

	private List<Object> objects = new ArrayList<>();

	public Group(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public List<Object> getObjects()
	{
		return objects;
	}

	public void setObjects(List<Object> objects)
	{
		this.objects = objects;
	}

	public AffineTransformation getTransform()
	{
		return transform;
	}

	public void setTransform(AffineTransformation transform)
	{
		this.transform = transform;
	}

}
