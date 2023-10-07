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

import static de.topobyte.inkscape4j.Styles.color;
import static de.topobyte.inkscape4j.Styles.style;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.locationtech.jts.geom.util.AffineTransformation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import de.topobyte.inkscape4j.shape.Rect;
import de.topobyte.inkscape4j.w3c.ChildDocument;
import de.topobyte.inkscape4j.w3c.XmlUtils;

public class TestEmbedImage
{

	public static void main(String[] args)
			throws IOException, ParserConfigurationException, SAXException
	{
		SvgFile file = new SvgFile();
		file.setWidth("500px");
		file.setHeight("400px");

		Layer layer1 = new Layer("images");
		file.getLayers().add(layer1);
		layer1.setLabel("Embedded images");

		Layer layer2 = new Layer("frames");
		file.getLayers().add(layer2);
		layer2.setLabel("Frames");

		Rect rect1 = new Rect("rect1", 10, 20, 200, 200);
		layer2.getObjects().add(rect1);

		Rect rect2 = new Rect("rect2", 220, 20, 200, 200);
		layer2.getObjects().add(rect2);

		rect1.setStyle(style(null, color(0x000000), 1, 1, 1, 2));
		rect2.setStyle(style(null, color(0x000000), 1, 1, 1, 2));

		Document document = XmlUtils.parseSvg(Paths.get("/tmp/test.svg"));
		convertToGroup(document);
		ChildDocument child = new ChildDocument(document);

		double factor = 200 / 500.;

		Group svg1 = new Group("svg-1");
		layer1.getObjects().add(svg1);

		svg1.setTransform(new AffineTransformation().scale(factor, factor)
				.translate(10, 20));
		svg1.getObjects().add(child);

		Group svg2 = new Group("svg-2");
		layer1.getObjects().add(svg2);

		svg2.setTransform(new AffineTransformation().scale(factor, factor)
				.translate(220, 20));
		svg2.getObjects().add(child);

		SvgFileWriting.write(file, System.out);
		FileOutputStream fos = new FileOutputStream("/tmp/embed.svg");
		SvgFileWriting.write(file, fos);
		fos.close();
	}

	private static void convertToGroup(Document document)
	{
		Element svg = document.getDocumentElement();
		document.renameNode(svg, null, "g");

		Set<String> attributeNames = new HashSet<>();
		NamedNodeMap attributes = svg.getAttributes();
		for (int i = 0; i < attributes.getLength(); i++) {
			Node item = attributes.item(i);
			attributeNames.add(item.getNodeName());
		}

		for (String attribute : attributeNames) {
			attributes.removeNamedItem(attribute);
		}
	}

}
