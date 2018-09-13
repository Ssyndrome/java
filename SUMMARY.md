# Summary of Week Two
## exception
- `Exception`里面会传入`Throwable`类型的参数，是因为类似一个链表的结构，可以顺着索引找到相应的异常
- `try {} catch() {} finally {}`执行顺序，避免在`finally`里面写`return`值
- `AutoCloseble`是在`try-with-resource`语法中，退出该语句块，即会自动关闭在语法块头中继承了该接口的资源
- 有多个资源，关闭时按照初始化资源时的反序来运行（只关它能关的，非空资源不能被关闭）
- 打开一个资源时，可能打不开，所以在`try`里面进行尝试
- `Throwable`包括`Error`和`Exception`，`Exception`包括`RuntimeException`和`OtherException`。其中只有`OtherException`是需要定义和抛出的
- （**checked**）预料到的错误需要throw成exception，不要throw成`RuntimeException`，要`catch or specify`
- （**unchecked**）`RuntimeException`、`Error`是不可预见的，不满足`catch or specify`这个条件，不需要report，不需要throw
- 方法签名不包含Exception
- 没有throw **checked** `Exception`的方法不能被重写（override）为**checked** `Excption`的方法
- `unchecked`和`checked`的exception的区别 ⬇️ 
```
graph LR
throwable[Throwable]-->error[Error]
throwable-->exception[Exception]
exception-->runtime[RunTimeException]
exception-->other[OtherException]

```
# InnerClass
- 从内部类中得到外部类的成员变量，要直接指定是哪个位置的变量 `outerClass.this.outerParameter`
- 内部类中不可以使用static成员变量，除非在前面加上final并且赋给初值
- java使用innerclass实现了一种闭包
- closure使用本地变量时必须为final或者等价于final。但是用到成员变量时又是可以的
- inner class是实例上的一个成员，所以加上static是跟实例没有关系的
- 一些嵌套类之间的关系 ⬇️
```
graph TD
nested[Nested class]-->static
nested-->non-static[non-static/inner class]
non-static-->local[local class]
local-->anoymous[anoymous class]
```
- innerClass关键点
1. 不能在innerClass实例后再实例化outerClass
2. innerClass中不能创建static的变量 constant variable
3. 调用的本地变量必须是final 

## Generic
- `<T>`不能同时继承两个类。用`<T extend [Some Class]>`来继承`[Some Class]`，表示该泛型只能是该类或子类（表示一个上界）。同时想要实现一个接口的话，`<T extend [Some Class] & [Some Interface...]>`
- 通配符(wildcard)`?`可以用来表示类型的下界，`? super [Some Class]`
- 泛型运行时不知道是本类型是泛型，只能进行类型推断（通过类型推断可以判断传入的值是什么类型）
- 泛型用于存储（add、put），精确的类型用来取出（get）。需要小心从泛型对象里面取出的数据类型
- 下边界只能用在参数表和实参里面
- 对于通配符类型作为形参类型的方法，需要另外写一个方法帮助推断传进来的值的类型

## Collection
- iterable接口里面有一个iterator的方法需要实现它的类去实现该方法，来确定是哪种迭代方式
- iterator是根据它的`hasNext()`和`next()`方法来遍历的
- 集合中，`HashMap`是不重复的，`LinkedList`是有保序的
- 集合的子集进行操作时会映射到原集合中

## Stream
- 一个stream赋值之后只能进行一次操作
- terminal的操作不能关闭一个流
- 有必要存在基础类型的stream操作（Stream<T>表示只能传入一个对象）
- toArray()如果不传参数，默认的数组类型是Object数组类型。最好传一个参数
- concat不会混合排序（原生序）
- intermediate（中间操作）是lazy的
- `onClose()`监听流有没有关闭（一般操作不会关闭一个流）（不能对流进行操作不等于关闭了一个流）
- `generate()`属于lazy方法。注意`iterate`与它的区别（seed初值和`next()`判断）
- 尽量使用包装起来的方法，节省
- `parallel`能使一个流并行操作，能有多个子流同时进行下面的操作（加快运行速度）
- `peek`不对原steam的值进行改变（可以改变对象里面元素的值）
- Optional是一个相对安全操作的值
- Optional中`map()`与`flatMap()`的区别是返回的值方式不一样，其中`map()`中的值进行了`Optional.of()`的包装，而`flatMap()`直接返回的是原来的值（判断为Optional类型才会输出）
- `collect()`里使用的接口的返回及输入值类型推断
- `groupingBy()`里面执行的具体流程（通过类型推断和每个函数的名字推测）


# Summary of Week One

```
What's the purpose for testing
Why the test failed
How to modify
Why to modify this way
```

### 1. IntegerType & FloatingType & CharType

1.1 Range of primitive Integer type (byte, int, short, long, double, float)

1.2 The diff`erence between  `>>>` `>>` `<< `  

1.3 How to prove that first digit determine the symbol of hexadecimal number

1.4 Defult type of number is `int` . Number less than 4 bytes will automatically be filled up with 0

1.5 Avoid add negative sign to hexadecimal number 

1.6 Minus in computer is adding complement of minuend number

1.7 Overflow happens where exists two numbers of same sign 

1.8 Use `try{} catch(){}` to record exception

1.9 Recursion reaches to certain amount will  have stack overflow. Loop won't have stack overflow

1.10 Precision of double type is limited

1.11 Table of transform between Integer types

| \      | byte | short | int  | long | float | double |
| ------ | :--: | :---: | :--: | :--: | :---: | :----: |
| byte   |  \   |  隐   |  隐  |  隐  |  隐   |   隐   |
| short  |  显  |   \   |  隐  |  隐  |  隐   |   隐   |
| int    |  显  |  显   |  \   |  隐  |  隐   |   隐   |
| long   |  显  |  显   |  显  |  \   |  隐   |   隐   |
| float  |  显  |  显   |  显  |  显  |   \   |   隐   |
| double |  显  |  显   |  显  |  显  |  显   |   \    |

1.12 Relation between Unicode, UTF, ASCII

1.13 What kind of encoding Java use

1.14 Logic and bitwise operator

1.15 Default value of initialized number of Integer type

1.16 The length of Integer type

1.17 What is `final` value

1.18 Nan

1.19 Infinite value

1.20 Escaped character

## 2. BooleanOperator & Array & String

2.1 Logic and bitwise operator

2.2 Priority between `|`  `&`  `!` 

2.3 `~` 

2.4 To write a test, assertion should be first

2.5 Test avoid tendency

2.6 Default value of primitive type and object

2.7 Everytime new a string will produce a new area to store it, so avoid new a string too many times

2.8 A field variable have initialized value, a local variable doesn't have

2.9 `substring()` 

2.10 String is an object

2.11 Difference between StringBuffer and StringBuilder

2.12 The defination of codePoint

2.13 The char type has no sign

2.14 Encoding method in Java

2.15 `final` represents a relationship for that appointed memory cell can't change

2.16 Difference between `==` and `equal`

2.17 Argument and parameter

2.18 Method signature include method name and parameter type

2.19 When overload happens during operation, it will distinguish which method to execute in compile process using method signature 

2.20 Variable argument type

## 3. Object & Inheritance & Reflection

3.1 Subclass can directly invoke superclass method

3.2 When create a instance, in stack only stores its data. Its `class` stores its description message

3.3 Creating a subclass instance must ensure the superclass succeed creating

3.4 When instantiate a subclass, only one `super` can be invoked

3.5 Component type of array can't be inverted

3.6 Avoid invoke between inherited chain

3.7 `instanceof` 

3.8 Principle of  `equals()` in Java

3.9 Avoid invoking superclass' method from subclass

3.10 Using reflection to get a method by name even if it can't be invoked

3.11 Create an annotation

3.12 Difference between  `getMethod()`  and `getDeclaredMethod()`  

3.13 Access modifier

## 4. Interface & Lambda

4.1 What is perfect `equals()` in Java

> 1. `parameter != null`  if parameter = null, return false
> 2. `obj.equals(obj)`, reflexivity
> 3. `obj.equals(otherObj)` , permanent equality
> 4. `a == b, a == c => b == c` , transmissibility
> 5. `a == b, b == a` , symmetry
> 6. `hashCode()`
> 7. Can't compare between subclass and superclass

4.2 Points for interfac

>1. mutiple-implement 
>2. no self-implementation 
>3. except static | default 
>4. Field to be static, final 
>5. no instance 
>6. no implements, only extend interface Interface
>7. class implements an interface, the class must override all methods
>8. static method can't be inherited
>9. method to be public
>10. default method can be inherit
>11. to be abstract
>12. to be package-private
>13. can be type
>14. Interface abstract methods cannot have body
>15. Extension methods can only be used within an interface

4.3 Signature doesn't include return type

4.4 Default modifier for interface is abstract 

4.5 `final` can't be used in interface

4.6 Return type of `compareTo()` is better to be `-1` `1` `0` 

4.7 Overriding `compareTo()` is better within `equals()`  and `hashcode()` 

4.5 Ways to clone object

4.6 Lambda is a functional interface. An object can't be a lambda

4.7 Functional annotation is achieved by reflection

4.8 To use a method from OutClass in InnerClass, use `outerclass.this.method`

4.9 Lambda is achieved by anonymous class

4.10 Use of method reference `::` 

4.11 Variable captured in lambda can't be changed

4.12 Closure is environment which is for defination with function

4.13 Difference between immutable and unchangeble

4.14 Lambda is `lazy` 

4.15 A variable captured in lambda can expend out of lambda

### Shortcuts

- 编译选择的文件、包或模块 `command+shift+f9`
- 跳转到声明处 `command+b`
- 显示方法层次结构 `command+shift+h` `command+f12`
- 前往父类所在方法 `command+u`
- 打开项目结构对话框 `command+;`
- 大小写切换 `command+shift+u`
- 从工具窗口进入代码文件窗口 `esc`
- 自动缩进行 `ctrl+option+i`
- 基本代码补全 `ctl+space`
- 展开所有代码块 `command+shift++`
- 包围代码块 `control+option+T`
- 代码块左右移 `command+option+shift+←/→`
- inline `command+option+n`

- 插入自定义动态代码模板 `CMD+J`
- 显示意向动作和快速修复代码 `option+enter`
- 在类中查找用法 `command+option+F7` `command+f7`
- 跳转到类型声明处`(implementation)command+option+b`
- 删除到单词的开头
- 跳转到上/一个错误位置
- 覆盖/重写父类方法
- 提取参数
- 在代码块/文件/文件夹放置书签
- 显示调用层次结构 `ctl+option+h`
- 恢复程序运行，如果该断点下面代码还有断点则停在下一个断点上 `command+option+r`
- 全局查找 `cmd+shift+f`
- 切换断点 `cmd+f8`
- 左右切换打开的编辑tab页 `ctr+←/→`
- 切换全屏模式（mac）`cmd+ctl+f`
- 优化import `ctl+option+o`