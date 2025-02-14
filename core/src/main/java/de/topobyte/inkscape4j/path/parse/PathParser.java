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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CharMatcher;

import de.topobyte.inkscape4j.path.CubicTo;
import de.topobyte.inkscape4j.path.LineTo;
import de.topobyte.inkscape4j.path.MoveTo;
import de.topobyte.inkscape4j.path.PathElement;

public class PathParser
{

	final static Logger logger = LoggerFactory.getLogger(PathParser.class);

	private List<PathElement> result = new ArrayList<>();

	public List<PathElement> getResult()
	{
		return result;
	}

	private static CharMatcher WHITESPACE = CharMatcher.WHITESPACE;
	private static CharMatcher NUMBER = CharMatcher.JAVA_DIGIT
			.or(CharMatcher.anyOf(".-"));
	private static CharMatcher UPPERCASE = CharMatcher.JAVA_UPPER_CASE
			.and(CharMatcher.ASCII);

	public void parse(String d) throws ParsingException
	{
		Character lastCommand = null;

		while (!d.isEmpty()) {
			d = skipWhitespace(d);
			char c = d.charAt(0);
			boolean nextIsNumber = NUMBER.matches(c);
			boolean isUppercase = UPPERCASE.matches(c);
			boolean relative = !isUppercase;
			// Move command
			if (c == 'm' || c == 'M') {
				Result<MoveTo> moveTo = parseM(d.substring(1), relative);
				result.add(moveTo.thing);
				d = moveTo.remainder;
				lastCommand = c;
			}
			// Line
			else if (c == 'l' || (nextIsNumber && lastCommand == 'l')
					|| (nextIsNumber && lastCommand == 'm')) {
				// implicit line to after 'm'
				String part = nextIsNumber ? d : d.substring(1);
				Result<LineTo> lineTo = parseL(part, true);
				result.add(lineTo.thing);
				d = lineTo.remainder;
				lastCommand = 'l';
			} else if (c == 'L' || (nextIsNumber && lastCommand == 'L')
					|| (nextIsNumber && lastCommand == 'M')) {
				// implicit line to after 'M'
				String part = nextIsNumber ? d : d.substring(1);
				Result<LineTo> lineTo = parseL(part, false);
				result.add(lineTo.thing);
				d = lineTo.remainder;
				lastCommand = 'L';
			}
			// Vertical Line
			else if (c == 'v' || (nextIsNumber && lastCommand == 'v')) {
				String part = nextIsNumber ? d : d.substring(1);
				Result<LineTo> lineTo = parseV(part, true);
				result.add(lineTo.thing);
				d = lineTo.remainder;
				lastCommand = 'v';
			} else if (c == 'V' || (nextIsNumber && lastCommand == 'V')) {
				String part = nextIsNumber ? d : d.substring(1);
				Result<LineTo> lineTo = parseV(part, false);
				result.add(lineTo.thing);
				d = lineTo.remainder;
				lastCommand = 'V';
			}
			// Horizontal Line
			else if (c == 'h' || (nextIsNumber && lastCommand == 'h')) {
				String part = nextIsNumber ? d : d.substring(1);
				Result<LineTo> lineTo = parseH(part, true);
				result.add(lineTo.thing);
				d = lineTo.remainder;
				lastCommand = 'h';
			} else if (c == 'H' || (nextIsNumber && lastCommand == 'H')) {
				String part = nextIsNumber ? d : d.substring(1);
				Result<LineTo> lineTo = parseH(part, false);
				result.add(lineTo.thing);
				d = lineTo.remainder;
				lastCommand = 'H';
			}
			// Cubic
			else if (c == 'c' || (nextIsNumber && lastCommand == 'c')) {
				String part = nextIsNumber ? d : d.substring(1);
				Result<CubicTo> cubicTo = parseC(part, true);
				result.add(cubicTo.thing);
				d = cubicTo.remainder;
				lastCommand = 'c';
			} else if (c == 'C' || (nextIsNumber && lastCommand == 'C')) {
				String part = nextIsNumber ? d : d.substring(1);
				Result<CubicTo> cubicTo = parseC(part, false);
				result.add(cubicTo.thing);
				d = cubicTo.remainder;
				lastCommand = 'C';
			}
			// Nothing we recognize
			// TODO: parse close and quadTo commands
			else {
				throw new ParsingException(
						String.format("Don't know what to do with '%c'", c));
			}
		}
	}

	private Result<MoveTo> parseM(String d, boolean relative)
			throws ParsingException
	{
		logger.debug("Parsing 'm'");
		String part = skipWhitespace(d);
		Result<Coordinate> coordinate = parseCoordinate(part);
		Coordinate c = coordinate.thing;
		logger.debug("coordinate: " + c);
		part = coordinate.remainder;
		return new Result<>(new MoveTo(relative, c.x, c.y),
				coordinate.remainder);
	}

	private Result<LineTo> parseL(String d, boolean relative)
			throws ParsingException
	{
		logger.debug("Parsing 'l'");
		String part = skipWhitespace(d);
		Result<Coordinate> coordinate = parseCoordinate(part);
		Coordinate c = coordinate.thing;
		return new Result<>(new LineTo(relative, c.x, c.y),
				coordinate.remainder);
	}

	private Result<LineTo> parseV(String d, boolean relative)
			throws ParsingException
	{
		logger.debug("Parsing 'v'");
		String part = skipWhitespace(d);
		Result<Double> number = parseNumber(part);
		double value = number.thing;
		return new Result<>(new LineTo(relative, 0, value), number.remainder);
	}

	private Result<LineTo> parseH(String d, boolean relative)
			throws ParsingException
	{
		logger.debug("Parsing 'h'");
		String part = skipWhitespace(d);
		Result<Double> number = parseNumber(part);
		double value = number.thing;
		return new Result<>(new LineTo(relative, value, 0), number.remainder);
	}

	private Result<CubicTo> parseC(String d, boolean relative)
			throws ParsingException
	{
		logger.debug("Parsing 'm'");
		String part = skipWhitespace(d);
		Result<Coordinate> control1 = parseCoordinate(part);
		part = control1.remainder;
		part = skipWhitespace(part);
		Result<Coordinate> control2 = parseCoordinate(part);
		part = control2.remainder;
		part = skipWhitespace(part);
		Result<Coordinate> coordinate = parseCoordinate(part);

		Coordinate c1 = control1.thing;
		Coordinate c2 = control2.thing;
		Coordinate c = coordinate.thing;

		logger.debug("control 1: " + c1);
		logger.debug("control 2: " + c2);
		logger.debug("coordinate: " + c);

		part = coordinate.remainder;
		return new Result<>(
				new CubicTo(relative, c1.x, c1.y, c2.x, c2.y, c.x, c.y),
				coordinate.remainder);
	}

	private String skipWhitespace(String part)
	{
		int skip = 0;
		for (int i = 0; i < part.length(); i++) {
			if (WHITESPACE.matches(part.charAt(i))) {
				skip++;
			} else {
				break;
			}
		}
		return part.substring(skip);
	}

	private Result<Coordinate> parseCoordinate(String part)
			throws ParsingException
	{
		logger.debug("Parsing coordinate: " + part);
		Result<Double> x = parseNumber(part);
		part = x.remainder;
		part = skipWhitespace(part);
		char c = part.charAt(0);
		if (c != ',') {
			throw new ParsingException("Expected comma after first coordinate");
		}
		part = part.substring(1);
		part = skipWhitespace(part);
		Result<Double> y = parseNumber(part);
		return new Result<>(new Coordinate(x.thing, y.thing), y.remainder);
	}

	private Result<Double> parseNumber(String part) throws ParsingException
	{
		logger.debug("Parsing number: " + part);
		int take = 0;
		for (int i = 0; i < part.length(); i++) {
			char c = part.charAt(i);
			if (NUMBER.matches(c)) {
				take++;
			} else if (c == 'e') {
				take++;
			} else {
				break;
			}
		}
		String number = part.substring(0, take);
		String remainder = part.substring(take);
		try {
			double value = Double.parseDouble(number);
			return new Result<>(value, remainder);
		} catch (NumberFormatException e) {
			throw new ParsingException("Unable to parse number", e);
		}
	}

}
