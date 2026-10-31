#!/usr/bin/env python3
"""Implement a square with its own string representation."""


Rectangle = __import__('2-rectangle').Rectangle


class Square(Rectangle):
    """Represent a square with a positive size and readable text."""

    def __init__(self, size):
        """Validate and initialize a square's dimensions."""
        self.integer_validator("size", size)
        self.__size = size
        super().__init__(size, size)

    def area(self):
        """Return the area of the square."""
        return self.__size ** 2

    def __str__(self):
        """Return the square dimensions in the required format."""
        return "[Square] {0}/{0}".format(self.__size)
