#!/usr/bin/env python3
"""Provide shared behavior for geometric figures."""


class BaseGeometry:
    """Define the common interface for geometric figures."""

    def area(self):
        """Report that area is not implemented by the base class."""
        raise Exception("area() is not implemented")

    def integer_validator(self, name, value):
        """Require a strictly positive integer for the named value."""
        if type(value) is not int:
            raise TypeError("{} must be an integer".format(name))
        if value <= 0:
            raise ValueError("{} must be greater than 0".format(name))
