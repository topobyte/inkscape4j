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

public class ParsingException extends Exception
{

	private static final long serialVersionUID = 1L;

	public ParsingException()
	{
		super();
	}

	public ParsingException(String message)
	{
		super(message);
	}

	public ParsingException(Throwable cause)
	{
		super(cause);
	}

	public ParsingException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
