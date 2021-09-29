// ***************************************
{
  var foo = { name: "foo", one: 1, two: 2 };

  var bar = { three: 3 };

  bar.__proto__ = foo; // foo is now the prototype of bar.

  // If we try to access foo's properties from bar
  // from now on, we'll succeed.
  console.log(bar.one); // Resolves to 1.

  // The child object's properties are also accessible.
  console.log(bar.three); // Resolves to 3.

  // Own properties shadow prototype properties
  bar.name = "bar";
  console.log(foo.name); // unaffected, resolves to "foo"
  console.log(bar.name); // Resolves to "bar"
}

// ***************************************
{
  var a = {
    x: 10,
    calculate: function (z) {
      return this.x + this.y + z;
    },
  };

  var b = {
    y: 20,
    __proto__: a,
  };

  var c = {
    y: 30,
    __proto__: a,
  };

  // call the inherited method
  console.log(b.calculate(30)); // 60
  console.log(c.calculate(40)); // 80
}

// ***************************************
{
  var b = Object.create(a, { y: { value: 20 } });
  var c = Object.create(a, { y: { value: 30 } });
}

// ***************************************
{
  // 一种构造函数写法
  function Foo(y) {
    this.y = y;
  }

  // 修改 Foo 的 prototype，加入一个成员变量 x
  Foo.prototype.x = 10;

  // 修改 Foo 的 prototype，加入一个成员函数 calculate
  Foo.prototype.calculate = function (z) {
    return this.x + this.y + z;
  };

  // 现在，我们用 Foo 这个原型来创建 b 和 c
  var b = new Foo(20);
  var c = new Foo(30);

  // 调用原型中的方法，可以得到正确的值
  console.log(b.calculate(30)); // 60
  console.log(c.calculate(40)); // 80

  console.log(b.__proto__ === Foo.prototype); // true
  console.log(c.__proto__ === Foo.prototype); // true
  console.log(b.constructor === Foo); // true
  console.log(c.constructor === Foo); // true
  console.log(Foo.prototype.constructor === Foo); // true
  console.log(b.calculate === b.__proto__.calculate); // true
  console.log(b.__proto__.calculate === Foo.prototype.calculate); // true
}

// ***************************************
{
  function Person() {}
  var p = new Person();

  Person.prototype.name = "Hao Chen";
  Person.prototype.sayHello = function () {
    console.log("Hi, I am " + this.name);
  };

  console.log(p.name); // "Hao Chen"
  p.sayHello(); // "Hi, I am Hao Chen"
}

// ***************************************
{
  //Define human class
  var Person = function (fullName, email) {
    this.fullName = fullName;
    this.email = email;

    this.speak = function () {
      console.log("I speak English!");
    };
    this.introduction = function () {
      console.log("Hi, I am " + this.fullName);
    };
  };

  //Define Student class
  var Student = function (fullName, email, school, courses) {
    Person.call(this, fullName, email);

    // Initialize our Student properties
    this.school = school;
    this.courses = courses;

    // override the "introduction" method
    this.introduction = function () {
      console.log(
        "Hi, I am " +
          this.fullName +
          ". I am a student of " +
          this.school +
          ", I study " +
          this.courses +
          "."
      );
    };

    // Add a "exams" method
    this.takeExams = function () {
      console.log("This is my exams time!");
    };
  };

  // Create a Student.prototype object that inherits
  // from Person.prototype.
  Student.prototype = Object.create(Person.prototype);

  // Set the "constructor" property to refer to Student
  Student.prototype.constructor = Student;

  var student = new Student(
    "Hao Chen",
    "haoel@hotmail.com",
    "XYZ University",
    "Computer Science"
  );
  student.introduction();
  student.speak();
  student.takeExams();

  // Check that instanceof works correctly
  console.log(student instanceof Person); // true
  console.log(student instanceof Student); // true
}
