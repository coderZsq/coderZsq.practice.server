我们再来看 Go 语言这个模式，Go 语言的这个模式挺好玩儿的。声明一个 struct，跟 C 很一样，然后直接把这个 struct 类型放到另一个 struct 里。

### 委托的简单示例

我们来看几个示例：

```go
type Widget struct {
    X, Y int
}

type Label struct {
    Widget        // Embedding (delegation)
    Text   string // Aggregation
    X int         // Override
}

func (label Label) Paint() {
  // [0xc4200141e0] - Label.Paint("State")
    fmt.Printf("[%p] - Label.Paint(%q)\n",
      &label, label.Text)
}
```

由上面可知：

- 我们声明了一个 Widget，其有 X 和 Y；
- 然后用它来声明一个 Label，直接把 Widget 委托进去；
- 然后再给 Label 声明并实现了一个 Paint() 方法。

于是，我们就可以这样编程了：

```go
label := Label{Widget{10, 10}, "State", 100}

// X=100, Y=10, Text=State, Widget.X=10
fmt.Printf("X=%d, Y=%d, Text=%s Widget.X=%d\n",
  label.X, label.Y, label.Text,
  label.Widget.X)
fmt.Println()
// {Widget:{X:10 Y:10} Text:State X:100}
// {{10 10} State 100}
fmt.Printf("%+v\n%v\n", label, label)

label.Paint()
```

我们可以看到，如果有成员变量重名，则需要手动地解决冲突。

我们继续扩展代码。

先来一个 Button：

```go
type Button struct {
    Label // Embedding (delegation)
}

func NewButton(x, y int, text string) Button {
    return Button{Label{Widget{x, y}, text, x}}
}
func (button Button) Paint() { // Override
    fmt.Printf("[%p] - Button.Paint(%q)\n",
      &button, button.Text)
}
func (button Button) Click() {
    fmt.Printf("[%p] - Button.Click()\n", &button)
}
```

再来一个 ListBox：

```go
type ListBox struct {
    Widget          // Embedding (delegation)
    Texts  []string // Aggregation
    Index  int      // Aggregation
}
func (listBox ListBox) Paint() {
    fmt.Printf("[%p] - ListBox.Paint(%q)\n",
      &listBox, listBox.Texts)
}
func (listBox ListBox) Click() {
    fmt.Printf("[%p] - ListBox.Click()\n", &listBox)
}
```

然后，声明两个接口用于多态：

```go
type Painter interface {
    Paint()
}

type Clicker interface {
    Click()
}
```

于是我们就可以这样泛型地使用（注意其中的两个 for 循环）：

```go
button1 := Button{Label{Widget{10, 70}, "OK", 10}}
button2 := NewButton(50, 70, "Cancel")
listBox := ListBox{Widget{10, 40},
    []string{"AL", "AK", "AZ", "AR"}, 0}

fmt.Println()
//[0xc4200142d0] - Label.Paint("State")
//[0xc420014300] - ListBox.Paint(["AL" "AK" "AZ" "AR"])
//[0xc420014330] - Button.Paint("OK")
//[0xc420014360] - Button.Paint("Cancel")
for _, painter := range []Painter{label, listBox, button1, button2} {
  painter.Paint()
}

fmt.Println()
//[0xc420014450] - ListBox.Click()
//[0xc420014480] - Button.Click()
//[0xc4200144b0] - Button.Click()
for _, widget := range []interface{}{label, listBox, button1, button2} {
    if clicker, ok := widget.(Clicker); ok {
      clicker.Click()
    }
}
```
