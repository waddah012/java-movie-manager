# Movie manager

This is another version of my Java movie project. The Movie, MovieManager, and Manager classes are in one file.

You can add movies, search by title, remove a movie, and sort the list by title, actor, actress, genre, or year. I used an ArrayList to hold the movies and comparators to sort them.

The example manager password is password. Choose 9 to quit. The movies are only stored while the program is running; this version does not load or save a CSV file.

To run it, you need a JDK version 11 or newer and Make. Open a terminal in this folder and run:

```sh
make
make run
```

You can run `make check` to try the sample menu actions. That also needs Python 3. Use `make clean` to remove the compiled files.
