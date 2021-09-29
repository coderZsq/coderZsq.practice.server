from functools import reduce
from random import random


def run1():
    time = 5
    car_positions = [1, 1, 1]

    while time:
        # decrease time
        time -= 1

        print('')
        for i in range(len(car_positions)):
            # move car
            if random() > 0.3:
                car_positions[i] += 1

            # draw car
            print('-' * car_positions[i])


time = 5
car_positions = [1, 1, 1]


def run2():
    def move_cars():
        for i, _ in enumerate(car_positions):
            if random() > 0.3:
                car_positions[i] += 1

    def draw_car(car_position):
        print('-' * car_position)

    def run_step_of_race():
        global time
        time -= 1
        move_cars()

    def draw():
        print('')
        for car_position in car_positions:
            draw_car(car_position)

    while time:
        run_step_of_race()
        draw()


def run3():
    def move_cars(car_positions):
        return list(map(lambda x: x + 1 if random() > 0.3 else x,
                        car_positions))

    def output_car(car_position):
        return '-' * car_position

    def run_step_of_race(state):
        return {'time': state['time'] - 1,
                'car_positions': move_cars(state['car_positions'])}

    def draw(state):
        print('')
        print('\n'.join(list(map(output_car, state['car_positions']))))

    def race(state):
        draw(state)
        if state['time']:
            race(run_step_of_race(state))

    race({'time': 5,
          'car_positions': [1, 1, 1]})


def lower():
    # 传统的非函数式
    upname = ['HAO', 'CHEN', 'COOLSHELL']
    lowname = []
    for i in range(len(upname)):
        lowname.append(upname[i].lower())
    print(lowname)


def upper():
    # 函数式
    def toUpper(item):
        return item.upper()

    user_name = list(map(toUpper, ['hao', 'chen', 'coolshell']))
    print(user_name)
    # 输出 ['HAO', 'CHEN', 'COOLSHELL']


def avg1():
    # 计算数组中正数的平均值
    num = [2, -5, 9, 7, -2, 5, 3, 1, 0, -3, 8]
    positive_num_cnt = 0
    positive_num_sum = 0
    for i in range(len(num)):
        if num[i] > 0:
            positive_num_cnt += 1
            positive_num_sum += num[i]

    if positive_num_cnt > 0:
        average = positive_num_sum / positive_num_cnt

    print(average)


def avg2():
    # 计算数组中正数的平均值
    num = [2, -5, 9, 7, -2, 5, 3, 1, 0, -3, 8]
    positive_num = list(filter(lambda x: x > 0, num))
    average = reduce(lambda x, y: x + y, positive_num) / len(positive_num)
    print(average)


def pip1():
    def process(num):
        # filter out non-evens
        if num % 2 != 0:
            return
        num = num * 3
        num = 'The Number: %s' % num
        return num

    nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    for num in nums:
        print(process(num))

    # 输出
    # None
    # The
    # Number: 6
    # None
    # The
    # Number: 12
    # None
    # The
    # Number: 18
    # None
    # The
    # Number: 24
    # None
    # The
    # Number: 30


def pip2():
    def even_filter(nums):
        for num in nums:
            if num % 2 == 0:
                yield num

    def multiply_by_three(nums):
        for num in nums:
            yield num * 3

    def convert_to_string(nums):
        for num in nums:
            yield 'The Number: %s' % num

    nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    pipeline = convert_to_string(multiply_by_three(even_filter(nums)))
    for num in pipeline:
        print(num)


def pip3():
    def even_filter(nums):
        return filter(lambda x: x % 2 == 0, nums)

    def multiply_by_three(nums):
        return map(lambda x: x * 3, nums)

    def convert_to_string(nums):
        return map(lambda x: 'The Number: %s' % x, nums)

    nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    pipeline = convert_to_string(
        multiply_by_three(
            even_filter(nums)
        )
    )
    for num in pipeline:
        print(num)


def pip4():
    def even_filter(nums):
        return filter(lambda x: x % 2 == 0, nums)

    def multiply_by_three(nums):
        return map(lambda x: x * 3, nums)

    def convert_to_string(nums):
        return map(lambda x: 'The Number: %s' % x, nums)

    def pipeline_func(data, fns):
        return reduce(lambda a, x: x(a), fns, data)

    nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    pipeline = pipeline_func(nums, [even_filter,
                                    multiply_by_three,
                                    convert_to_string])
    for num in pipeline:
        print(num)


def pip5():
    class Pipe(object):
        def __init__(self, func):
            self.func = func

        def __ror__(self, other):
            def generator():
                for obj in other:
                    if obj is not None:
                        yield self.func(obj)

            return generator()

    @Pipe
    def even_filter(num):
        return num if num % 2 == 0 else None

    @Pipe
    def multiply_by_three(num):
        return num * 3

    @Pipe
    def convert_to_string(num):
        return 'The Number: %s' % num

    @Pipe
    def echo(item):
        print(item)
        return item

    def force(sqs):
        for item in sqs: pass

    nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    force(nums | even_filter | multiply_by_three | convert_to_string | echo)


if __name__ == '__main__':
    # run1()
    # run2()
    # run3()
    # lower()
    # upper()
    # avg1()
    # avg2()
    # pip1()
    # pip2()
    # pip3()
    # pip4()
    pip5()