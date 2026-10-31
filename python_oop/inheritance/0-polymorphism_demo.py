#!/usr/bin/env python3
"""Demonstrate inheritance and polymorphism with animals."""


class Animal:
    """Define shared behavior for animal instances."""

    def speak(self):
        """Return a general animal sound."""
        return "Some sound"


class Dog(Animal):
    """Represent a dog with its own sound."""

    def speak(self):
        """Return the sound made by a dog."""
        return "Woof"


class Cat(Animal):
    """Represent a cat with its own sound."""

    def speak(self):
        """Return the sound made by a cat."""
        return "Meow"


if __name__ == "__main__":
    animals = [Dog(), Cat(), Dog()]
    for animal in animals:
        print(animal.speak())

    dog = Dog()
    print(isinstance(dog, Dog))
    print(isinstance(dog, Animal))
    print(issubclass(Dog, Animal))
