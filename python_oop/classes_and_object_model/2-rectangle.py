#!/usr/bin/env python3
"""Define a rectangle with area and perimeter methods."""


class Rectangle:
    """Represent a rectangle with validated private dimensions."""

    def __init__(self, width=0, height=0):
        """Initialize the rectangle with validated dimensions."""
        self.width = width
        self.height = height

    @property
    def width(self):
        """Return the rectangle's width."""
        return self.__width

    @width.setter
    def width(self, value):
        """Set the width after validating it."""
        if type(value) is not int:
            raise TypeError("width must be an integer")
        if value < 0:
            raise ValueError("width must be >= 0")
        self.__width = value

    @property
    def height(self):
        """Return the rectangle's height."""
        return self.__height

    @height.setter
    def height(self, value):
        """Set the height after validating it."""
        if type(value) is not int:
            raise TypeError("height must be an integer")
        if value < 0:
            raise ValueError("height must be >= 0")
        self.__height = value

    def area(self):
        """Return the rectangle's area."""
        return self.__width * self.__height

    def perimeter(self):
        """Return the perimeter, or zero for a zero-sized dimension."""
        if self.__width == 0 or self.__height == 0:
            return 0
        return 2 * (self.__width + self.__height)
