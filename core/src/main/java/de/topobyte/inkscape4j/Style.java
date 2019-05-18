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

import java.util.Optional;

import de.topobyte.chromaticity.ColorCode;
import de.topobyte.inkscape4j.style.LineCap;
import de.topobyte.inkscape4j.style.LineJoin;

public class Style
{

	private double opacity;

	private ColorCode fill;
	private double fillOpacity;

	private ColorCode stroke;
	private double strokeWidth;
	private double strokeOpacity;

	private Optional<float[]> dashArray = Optional.empty();
	private Optional<Float> dashOffset = Optional.empty();

	private LineJoin lineJoin = LineJoin.MITER;
	private LineCap lineCap = LineCap.BUTT;
	private Optional<Float> miterLimit = Optional.empty();

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

	public ColorCode getFill()
	{
		return fill;
	}

	public void setFill(ColorCode fill)
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

	public ColorCode getStroke()
	{
		return stroke;
	}

	public void setStroke(ColorCode stroke)
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

	public Optional<float[]> getDashArray()
	{
		return dashArray;
	}

	public void setDashArray(float[] dashArray)
	{
		this.dashArray = Optional.of(dashArray);
	}

	public Optional<Float> getDashOffset()
	{
		return dashOffset;
	}

	public void setDashOffset(float dashOffset)
	{
		this.dashOffset = Optional.of(dashOffset);
	}

	public LineJoin getLineJoin()
	{
		return lineJoin;
	}

	public void setLineJoin(LineJoin lineJoin)
	{
		this.lineJoin = lineJoin;
	}

	public LineCap getLineCap()
	{
		return lineCap;
	}

	public void setLineCap(LineCap lineCap)
	{
		this.lineCap = lineCap;
	}

	public void setMiterLimit(float miterLimit)
	{
		this.miterLimit = Optional.of(miterLimit);
	}

	public Optional<Float> getMiterLimit()
	{
		return miterLimit;
	}

}
