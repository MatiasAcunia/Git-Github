#!/usr/bin/env python3
"""Define a printable square with configurable position."""


class Square:
    """Represent a square with validated size and position."""

    def __init__(self, size=0, position=(0, 0)):
        """Initialize the square's size and position."""
        self.size = size
        self.position = position

    @property
    def size(self):
        """Return the size of the square."""
        return self.__size

    @size.setter
    def size(self, value):
        """Set the size after validating it."""
        if type(value) is not int:
            raise TypeError("size must be an integer")
        if value < 0:
            raise ValueError("size must be >= 0")
        self.__size = value

    @property
    def position(self):
        """Return the square's horizontal and vertical offsets."""
        return self.__position

    @position.setter
    def position(self, value):
        """Validate and set a pair of nonnegative integer offsets."""
        if (type(value) is not tuple or len(value) != 2
                or any(type(item) is not int or item < 0
                       for item in value)):
            raise TypeError("position must be a tuple of 2 positive integer")
        self.__position = value

    def area(self):
        """Return the area of the square."""
        return self.__size ** 2

    def my_print(self):
        """Print the square at its configured position."""
        print(str(self))

    def __str__(self):
        """Return a string drawing of the square at its position."""
        if self.__size == 0:
            return ""
        rows = [" " * self.__position[0] + "#" * self.__size
                for _ in range(self.__size)]
        return "\n" * self.__position[1] + "\n".join(rows)
