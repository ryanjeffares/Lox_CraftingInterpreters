# Things to Implement
* Static methods
* Classes only have instances that are assigned in init, e.g.
```
class Person {
	init(name) {
		this.name = name;
	}
}

var ryan = Person("Ryan");
ryan.name = "Bob"; 	// fine
ryan.age = 22;		// should be a runtime error
```
* `LoxClass` needs a list for its member variables, so that `LoxInstance` can check if any variable that is being `set` is in that list.