JAVAC = javac
JAVA = java
SOURCES := $(wildcard src/*.java)
ENTRY := Manager

.PHONY: all run check clean
all: build/.compiled

build/.compiled: $(SOURCES)
	mkdir -p build
	$(JAVAC) --release 8 -Xlint:all -d build $(SOURCES)
	touch build/.compiled

run: all
	$(JAVA) -cp build $(ENTRY)

check: all
	python3 tests/smoke.py

clean:
	rm -rf build
