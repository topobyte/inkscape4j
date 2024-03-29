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

import static de.topobyte.inkscape4j.Styles.color;
import static de.topobyte.inkscape4j.Styles.style;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.locationtech.jts.geom.util.AffineTransformation;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.topobyte.inkscape4j.path.FillRule;
import de.topobyte.inkscape4j.path.Path;
import de.topobyte.inkscape4j.path.PathBuilder;
import de.topobyte.inkscape4j.path.StringPath;
import de.topobyte.inkscape4j.shape.Circle;
import de.topobyte.inkscape4j.shape.Ellipse;
import de.topobyte.inkscape4j.shape.Rect;
import de.topobyte.inkscape4j.w3c.XmlUtils;

public class TestWriter
{

	public static void main(String[] args)
			throws IOException, ParserConfigurationException, SAXException
	{
		SvgFile file = new SvgFile();
		file.setWidth("500px");
		file.setHeight("460px");

		Layer layer1 = new Layer("rects");
		file.getLayers().add(layer1);
		layer1.setLabel("Some rectangles");

		Layer layer2 = new Layer("circles");
		file.getLayers().add(layer2);
		layer2.setLabel("Circles / Ellipses");

		Layer layer3 = new Layer("paths");
		file.getLayers().add(layer3);
		layer3.setLabel("Some paths");

		Layer layer4 = new Layer("icons");
		file.getLayers().add(layer4);
		layer4.setLabel("Icons");

		Rect rect1 = new Rect("rect1", 10, 20, 100, 50);
		layer1.getObjects().add(rect1);

		Rect rect2 = new Rect("rect2", 120, 20, 50, 100);
		layer1.getObjects().add(rect2);

		Rect rect3 = new Rect("rect3", 180, 20, 50, 50);
		layer1.getObjects().add(rect3);

		Rect rect4 = new Rect("rect4", 240, 20, 50, 50);
		rect4.setRx(10);
		rect4.setRy(5);
		layer1.getObjects().add(rect4);

		rect1.setStyle(style(color(0xffaaaa), color(0x333333), 1, 1, 1, 2));
		rect2.setStyle(style(color(0xaaaaff), color(0x666666), 1, 1, 1, 2));
		rect3.setStyle(style(color(0xaaaaff), null, 1, 1, 1, 2));
		rect4.setStyle(style(null, color(0x000000), 1, 1, 1, 2));

		Circle circle1 = new Circle("circle1", 60, 150, 50);
		layer2.getObjects().add(circle1);
		circle1.setStyle(style(color(0xaaffaa), color(0x999999), 1, 1, 1, 2));

		Ellipse ellipse1 = new Ellipse("ellipse1", 200, 180, 80, 50);
		layer2.getObjects().add(ellipse1);
		ellipse1.setStyle(style(color(0xaaffaa), color(0x999999), 1, 1, 1, 2));

		Ellipse ellipse2 = new Ellipse("ellipse2", 200, 300, 80, 50);
		layer2.getObjects().add(ellipse2);
		ellipse2.setStyle(style(color(0xaaffaa), color(0x333333), 1, 1, 1, 2));
		ellipse2.getStyle().setDashArray(new float[] { 3, 2 });

		PathBuilder pb = new PathBuilder();
		pb.move(300, 100).line(200, 150).line(-50, 0).quad(0, 50, -50, 50)
				.cubic(-50, 0, -100, 100, -100, 0).close();
		Path path1 = pb.build("path1", FillRule.EVEN_ODD);
		layer3.getObjects().add(path1);
		path1.setStyle(style(color(0xffaaaa), color(0x000000), 1, 0.6, 1, 2));

		// viewbox for Material icons is 960x960. Scale to 100px size.
		double factor = 100 / 960.;

		Group groupRocket = new Group("group-rocket");
		groupRocket.setTransform(new AffineTransformation()
				.scale(factor, factor).translate(0, 460));
		layer4.getObjects().add(groupRocket);

		StringPath pathRocket = new StringPath("rocket", FillRule.EVEN_ODD,
				getPath("material/rocket.svg"));
		pathRocket.setStyle(style(color(0xaaaaff), null, 1, 1, 1, 2));
		groupRocket.getObjects().add(pathRocket);

		Group groupShare = new Group("group-share");
		groupShare.setTransform(new AffineTransformation().scale(factor, factor)
				.translate(100, 460));
		layer4.getObjects().add(groupShare);

		StringPath pathShare = new StringPath("share", FillRule.EVEN_ODD,
				getPath("material/share.svg"));
		pathShare.setStyle(style(color(0xffaaff), null, 1, 1, 1, 2));
		groupShare.getObjects().add(pathShare);

		Group groupBattery = new Group("group-battery");
		groupBattery.setTransform(new AffineTransformation()
				.scale(factor, factor).translate(200, 460));
		layer4.getObjects().add(groupBattery);

		StringPath pathBattery = new StringPath("share", FillRule.EVEN_ODD,
				getPath("material/battery.svg"));
		pathBattery.setStyle(style(color(0xaaffff), null, 1, 1, 1, 2));
		groupBattery.getObjects().add(pathBattery);

		SvgFileWriting.write(file, System.out);
		FileOutputStream fos = new FileOutputStream("/tmp/test.svg");
		SvgFileWriting.write(file, fos);
		fos.close();
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
