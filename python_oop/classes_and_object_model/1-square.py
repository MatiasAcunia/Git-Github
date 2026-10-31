#!/usr/bin/env python3
"""Define a square with a private size attribute."""


class Square:
    """Represent a square with a privately stored size."""

    def __init__(self, size):
        """Initialize the square with its size."""
        self.__size = size
