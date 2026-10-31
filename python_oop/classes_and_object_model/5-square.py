#!/usr/bin/env python3
"""Define a printable square with a size property."""


class Square:
    """Represent a square with validated size and drawing support."""

    def __init__(self, size=0):
        """Initialize the square using the size property."""
        self.size = size

    @property
    def size(self):
        """Return the size of the square."""
        return self.__size

    @size.setter
    def size(self, value):
        """Set the size after checking its type and value."""
        if type(value) is not int:
            raise TypeError("size must be an integer")
        if value < 0:
            raise ValueError("size must be >= 0")
        self.__size = value

    def area(self):
        """Return the area of the square."""
        return self.__size ** 2

    def my_print(self):
        """Print the square with hash characters."""
        if self.__size == 0:
            print()
            return
        for _ in range(self.__size):
            print("#" * self.__size)
