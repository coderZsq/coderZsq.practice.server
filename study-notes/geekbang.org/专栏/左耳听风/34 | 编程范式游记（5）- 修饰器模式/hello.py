#######################################
def hello(fn):
  def wrapper():
    print("hello, %s" % fn.__name__)
    fn();
    print("goodbye, %s" % fn.__name__)
  return wrapper

@hello
def Hao():
  print("i am Hao Chen")

Hao()


#######################################
def makeHtmlTag(tag, *args, **kwds):
  def real_decorator(fn):
    css_class = " class='{0}'".format(kwds["css_class"]) \
                                if "css_class" in kwds else ""
    def wrapped(*args, **kwds):
      return "<" + tag + css_class + ">" + fn(*args, **kwds) + "</" + tag + ">"
    return wrapped
  return real_decorator
    

@makeHtmlTag(tag="b", css_class="bold_css")
@makeHtmlTag(tag="i", css_class="italic_css")
def hello():
  return "hello world"

print(hello())


#######################################
from functools import wraps
def memoization(fn):
  cache = {}
  miss = object()

  @wraps(fn)
  def wrapper(*args):
    result = cache.get(args, miss)
    if result is miss:
      result = fn(*args)
      cache[args] = result
    return result
  
  return wrapper


@memoization
def fib(n):
  if n < 2:
    return n
  return fib(n - 1) + fib(n - 2)

print(fib(10))


#######################################
class myDecorator(object):
  def __init__(self, fn):
    print("inside myDecorator.__init__()")
    self.fn = fn

  def __call__(self):
    self.fn()
    print("inside myDecorator.__call__()")

@myDecorator
def aFunction():
  print("inside aFunction()")

print("Finished decorating aFunction()")

aFunction()


#######################################
class MyApp():
  def __init__(self):
    self.func_map = {}

  def register(self, name):
    def func_wrapper(func):
      self.func_map[name] = func
      return func
    return func_wrapper

  def call_method(self, name=None):
    func = self.func_map.get(name, None)
    if func is None:
      raise Exception("No function registered against - " + str(name))
    return func()

app = MyApp()

@app.register('/')
def main_page_func():
  return "This is the main page."

@app.register('/next_page')
def next_page_func():
  return "This is the next page."

print(app.call_method('/'))
print(app.call_method('/next_page'))
