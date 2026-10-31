#!/usr/bin/env python3
"""Define a square that can calculate its area."""


class Square:
    """Represent a square with a validated private size."""

    def __init__(self, size=0):
        """Initialize the square with a nonnegative integer size."""
        if type(size) is not int:
            raise TypeError("size must be an integer")
        if size < 0:
            raise ValueError("size must be >= 0")
        self.__size = size

    def area(self):
        """Return the area of the square."""
        return self.__size ** 2
