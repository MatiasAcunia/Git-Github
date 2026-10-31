#!/usr/bin/env python3
"""Define a square as a specialized rectangle."""


Rectangle = __import__('2-rectangle').Rectangle


class Square(Rectangle):
    """Represent a square with one positive dimension."""

    def __init__(self, size):
        """Validate and initialize a square's dimensions."""
        self.integer_validator("size", size)
        self.__size = size
        super().__init__(size, size)

    def area(self):
        """Return the area of the square."""
        return self.__size ** 2
