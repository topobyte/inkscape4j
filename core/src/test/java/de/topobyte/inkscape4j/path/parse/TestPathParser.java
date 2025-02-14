// Copyright 2025 Sebastian Kuerten
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

package de.topobyte.inkscape4j.path.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.topobyte.inkscape4j.path.FillRule;
import de.topobyte.inkscape4j.path.Path;
import de.topobyte.inkscape4j.path.PathBuilder;
import de.topobyte.inkscape4j.path.PathElement;
import de.topobyte.inkscape4j.w3c.XmlUtils;

public class TestPathParser
{

	@Test
	public void testImplicitLineTos() throws ParsingException
	{
		PathParser parser = new PathParser();
		parser.parse(
				"m 81.744273,269.68526 6.602707,-1.55532 1.95636,-0.40104");
		List<PathElement> result = parser.getResult();

		PathBuilder pb = new PathBuilder();
		pb.move(true, 81.744273, 269.68526);
		pb.line(true, 6.602707, -1.55532);
		pb.line(true, 1.95636, -0.40104);
		Path expected = pb.build("id", FillRule.EVEN_ODD);

		assertEquals(expected.getElements(), result);
	}

	@Test
	public void testVertical() throws ParsingException
	{
		PathParser parser = new PathParser();
		parser.parse("m 66.82705,251.58895 v 0.24454");
		List<PathElement> result = parser.getResult();

		PathBuilder pb = new PathBuilder();
		pb.move(true, 66.82705, 251.58895);
		pb.line(true, 0, 0.24454);
		Path expected = pb.build("id", FillRule.EVEN_ODD);

		assertEquals(expected.getElements(), result);
	}

	@Test
	public void testHorizontal() throws ParsingException
	{
		PathParser parser = new PathParser();
		parser.parse("m 66.82705,251.58895 h 0.24454");
		List<PathElement> result = parser.getResult();

		PathBuilder pb = new PathBuilder();
		pb.move(true, 66.82705, 251.58895);
		pb.line(true, 0.24454, 0);
		Path expected = pb.build("id", FillRule.EVEN_ODD);

		assertEquals(expected.getElements(), result);
	}

	@Test
	public void testCubic() throws ParsingException
	{
		PathParser parser = new PathParser();
		parser.parse(
				"m 128.20776,242.78534 c 1.68859,1.68736 4.02814,4.50207 5.62453,7.0918");
		List<PathElement> result = parser.getResult();

		PathBuilder pb = new PathBuilder();
		pb.move(true, 128.20776, 242.78534);
		pb.cubic(true, 1.68859, 1.68736, 4.02814, 4.50207, 5.62453, 7.0918);
		Path expected = pb.build("id", FillRule.EVEN_ODD);

		assertEquals(expected.getElements(), result);
	}

	@Test
	public void testBattery() throws ParserConfigurationException, SAXException,
			IOException, ParsingException
	{
		String path = getPath("material/battery.svg");

		PathParser parser = new PathParser();
		parser.parse(path);
		List<PathElement> result = parser.getResult();
		assertEquals(24, result.size());
	}

	@Test
	public void testRocket() throws ParserConfigurationException, SAXException,
			IOException, ParsingException
	{
		String path = getPath("material/rocket.svg");

		PathParser parser = new PathParser();
		parser.parse(path);
		List<PathElement> result = parser.getResult();
		assertEquals(72, result.size());
	}

	@Test
	public void testShare() throws ParserConfigurationException, SAXException,
			IOException, ParsingException
	{
		String path = getPath("material/share.svg");

		PathParser parser = new PathParser();
		parser.parse(path);
		List<PathElement> result = parser.getResult();
		assertEquals(74, result.size());
	}

	private static String getPath(String resource)
			throws ParserConfigurationException, SAXException, IOException
	{
		try (InputStream input = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(resource)) {
			Document doc = XmlUtils.parseSvg(input);
			NodeList paths = doc.getElementsByTagName("path");
			Node item = paths.item(0);
			Attr d = (Attr) item.getAttributes().getNamedItem("d");
			return d.getValue();
		}
	}

}
