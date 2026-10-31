#!/usr/bin/env python3
"""Define a square with a validated size property."""


class Square:
    """Represent a square with controlled size access."""

    def __init__(self, size=0):
        """Initialize the square using the size property."""
        self.size = size

    @property
    def size(self):
        """Return the size of the square."""
        return self.__size

    @size.setter
    def size(self, value):
        """Set the size after validating its type and value."""
        if type(value) is not int:
            raise TypeError("size must be an integer")
        if value < 0:
            raise ValueError("size must be >= 0")
        self.__size = value

    def area(self):
        """Return the area of the square."""
        return self.__size ** 2
