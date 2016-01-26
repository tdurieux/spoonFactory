# Spoon Factory

This project tries to simplify the usage of Spoon Factory.
This project creates a new Class `F` with all spoon factory methods.


## Usage
```Java
import com.github.tdurieux.spoon.factory.F

public class TestSpoonFactory () {
    public void usage() {
        CtClass<Object> FClass = F.createClass("com.github.tdurieux.spoon.factory.F")
    }
}
```