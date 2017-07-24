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

	private String style(Style style)
	{
		StringBuilder buffer = new StringBuilder();
		append(buffer, "opacity", style.getOpacity());
		append(buffer, ";fill", style.getFill());
		append(buffer, ";fill-opacity", style.getFillOpacity());
		append(buffer, ";stroke", style.getStroke());
		append(buffer, ";stroke-opacity", style.getStrokeOpacity());
		append(buffer, ";stroke-width", style.getStrokeWidth());
		return buffer.toString();
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

}
