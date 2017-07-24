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

public class Style
{

	private double opacity;

	private String fill;
	private double fillOpacity;

	private String stroke;
	private double strokeWidth;
	private double strokeOpacity;

	public Style()
	{

	}

	public double getOpacity()
	{
		return opacity;
	}

	public void setOpacity(double opacity)
	{
		this.opacity = opacity;
	}

	public String getFill()
	{
		return fill;
	}

	public void setFill(String fill)
	{
		this.fill = fill;
	}

	public double getFillOpacity()
	{
		return fillOpacity;
	}

	public void setFillOpacity(double fillOpacity)
	{
		this.fillOpacity = fillOpacity;
	}

	public String getStroke()
	{
		return stroke;
	}

	public void setStroke(String stroke)
	{
		this.stroke = stroke;
	}

	public double getStrokeWidth()
	{
		return strokeWidth;
	}

	public void setStrokeWidth(double strokeWidth)
	{
		this.strokeWidth = strokeWidth;
	}

	public double getStrokeOpacity()
	{
		return strokeOpacity;
	}

	public void setStrokeOpacity(double strokeOpacity)
	{
		this.strokeOpacity = strokeOpacity;
	}

}