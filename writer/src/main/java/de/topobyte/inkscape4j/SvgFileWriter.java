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

import java.io.OutputStream;
import java.io.PrintWriter;

import de.topobyte.chromaticity.ColorCode;
import de.topobyte.inkscape4j.path.CubicTo;
import de.topobyte.inkscape4j.path.LineTo;
import de.topobyte.inkscape4j.path.MoveTo;
import de.topobyte.inkscape4j.path.Path;
import de.topobyte.inkscape4j.path.PathElement;
import de.topobyte.inkscape4j.path.QuadTo;

class SvgFileWriter
{

	private SvgFile svgFile;

	private PrintWriter writer;

	SvgFileWriter(SvgFile svgFile, OutputStream output)
	{
		this.svgFile = svgFile;

		writer = new PrintWriter(output);
	}

	void write()
	{
		writer.println(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");

		writer.println();
		writer.println("<svg");
		writer.println(
				"   xmlns:inkscape=\"http://www.inkscape.org/namespaces/inkscape\"");
		writer.println(String.format("   width=\"%s\"", svgFile.getWidth()));
		writer.println(String.format("   height=\"%s\"", svgFile.getHeight()));
		writer.println(">");

		for (Layer layer : svgFile.getLayers()) {
			writeLayer(layer);
		}

		writer.println("</svg>");

		writer.flush();
	}

	private void writeLayer(Layer layer)
	{
		writer.println("  <g");
		writer.println(
				String.format("     inkscape:label=\"%s\"", layer.getLabel()));
		writer.println("     inkscape:groupmode=\"layer\"");
		writer.println(String.format("     inkscape:id=\"%s\"", layer.getId()));
		writer.println("  >");

		for (Object object : layer.getObjects()) {
			writeObject(object);
		}

		writer.println("  </g>");
	}

	private void writeObject(Object object)
	{
		if (object instanceof Rect) {
			writeRect((Rect) object);
		} else if (object instanceof Circle) {
			writeCircle((Circle) object);
		} else if (object instanceof Ellipse) {
			writeEllipse((Ellipse) object);
		} else if (object instanceof Path) {
			writePath((Path) object);
		}
	}

	private void writeRect(Rect rect)
	{
		writer.println("    <rect");
		writer.println(
				String.format("       style=\"%s\"", style(rect.getStyle())));
		writer.println(String.format("       id=\"%s\"", rect.getId()));
		writer.println(String.format("       width=\"%f\"", rect.getWidth()));
		writer.println(String.format("       height=\"%f\"", rect.getHeight()));
		writer.println(String.format("       x=\"%f\"", rect.getX()));
		writer.println(String.format("       y=\"%f\"", rect.getY()));
		writer.println("    />");
	}

	private void writeCircle(Circle circle)
	{
		writer.println("    <circle");
		writer.println(
				String.format("       style=\"%s\"", style(circle.getStyle())));
		writer.println(String.format("       id=\"%s\"", circle.getId()));
		writer.println(String.format("       cx=\"%f\"", circle.getCx()));
		writer.println(String.format("       cy=\"%f\"", circle.getCy()));
		writer.println(String.format("       r=\"%f\"", circle.getRadius()));
		writer.println("    />");
	}

	private void writeEllipse(Ellipse ellipse)
	{
		writer.println("    <ellipse");
		writer.println(String.format("       style=\"%s\"",
				style(ellipse.getStyle())));
		writer.println(String.format("       id=\"%s\"", ellipse.getId()));
		writer.println(String.format("       cx=\"%f\"", ellipse.getCx()));
		writer.println(String.format("       cy=\"%f\"", ellipse.getCy()));
		writer.println(String.format("       rx=\"%f\"", ellipse.getRadiusX()));
		writer.println(String.format("       ry=\"%f\"", ellipse.getRadiusY()));
		writer.println("    />");
	}

	private void writePath(Path path)
	{
		writer.println("    <path");
		writer.println(
				String.format("       style=\"%s\"", style(path.getStyle())));
		writer.println(String.format("       id=\"%s\"", path.getId()));
		writer.println(String.format("       d=\"%s\"", path(path)));
		writer.println("    />");
	}

	private String style(Style style)
	{
		StringBuilder buffer = new StringBuilder();
		append(buffer, "opacity", style.getOpacity());
		append(buffer, ";fill", color(style.getFill()));
		append(buffer, ";fill-opacity", style.getFillOpacity());
		append(buffer, ";stroke", color(style.getStroke()));
		append(buffer, ";stroke-opacity", style.getStrokeOpacity());
		append(buffer, ";stroke-width", style.getStrokeWidth());
		return buffer.toString();
	}

	private String color(ColorCode color)
	{
		if (color == null) {
			return "none";
		}
		return String.format("#%06x", color.getValue() & 0xFFFFFF);
	}

	private void append(StringBuilder buffer, String name, String value)
	{
		buffer.append(name);
		buffer.append(":");
		buffer.append(value);
	}

	private void append(StringBuilder buffer, String name, double value)
	{
		buffer.append(name);
		buffer.append(":");
		buffer.append(Double.toString(value));
	}

	private String path(Path path)
	{
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < path.getElements().size(); i++) {
			PathElement element = path.getElements().get(i);
			if (i > 0) {
				buffer.append(" ");
			}
			switch (element.getType()) {
			default:
			case MOVE:
				MoveTo move = (MoveTo) element;
				buffer.append(
						String.format("M %f,%f", move.getX(), move.getY()));
				break;
			case CLOSE:
				buffer.append("Z");
				break;
			case LINE:
				LineTo line = (LineTo) element;
				buffer.append(
						String.format("l %f,%f", line.getX(), line.getY()));
				break;
			case QUAD:
				QuadTo quad = (QuadTo) element;
				buffer.append(String.format("q %f,%f %f,%f", quad.getCX(),
						quad.getCY(), quad.getX(), quad.getY()));
				break;
			case CUBIC:
				CubicTo cubic = (CubicTo) element;
				buffer.append(String.format("c %f,%f %f,%f %f,%f",
						cubic.getCX1(), cubic.getCY1(), cubic.getCX2(),
						cubic.getCY2(), cubic.getX(), cubic.getY()));
				break;
			}
		}
		return buffer.toString();
	}

}
