#include "stdio.h"
#include "string.h"

void swap1(int* x, int* y) 
{
  int tmp = *x;
  *x = *y;
  *y = tmp;
}

void swap2(void* x, void* y, size_t size)
{
  char tmp[size];
  memcpy(tmp, y, size);
  memcpy(y, x, size);
  memcpy(x, tmp, size);
}

#define swap3(x, y, size) {\
  char temp[size]; \
  memcpy(temp, &y, size); \
  memcpy(&y,   &x, size); \
  memcpy(&x, temp, size); \
}  

int search1(int* a, size_t size, int target) {
  for (int i = 0; i < size; i++) {
    if (a[i] == target) {
      return i;
    }
  }
  return -1;
}

int search2(void* a, size_t size, void* target, 
  size_t elem_size, int(*cmpFn)(void*, void*) )
{
  for(int i=0; i<size; i++) {
    // why not use memcmp()
    // use unsigned char * to calculate the address
    if ( cmpFn ((unsigned char *)a + elem_size * i, target) == 0 ) {
      return i;
    }
  }
  return -1;
}

int int_cmp(int* x, int* y)
{
  return *x - *y;
}

int string_cmp(char* x, char* y) {
  return strcmp(x, y);
}

typedef struct _account {
  char name[10];
  char id[20];
} Account;

int account_cmp(Account* x, Account* y) {
  int n = strcmp(x->name, y->name);
  if (n != 0) return n;
  return strcmp(x->id, y->id);
}

int main(int argc, char const *argv[])
{
  {
    int a = 1;
    int b = 2;
    size_t size = sizeof(int);
    swap1(&a, &b);
    swap2(&a, &b, size);
    swap3(a, b, size);
    printf("a = %d, b = %d\n", a, b);
  }

  {
    int a[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    size_t size = sizeof(a);
    int target = 7;
    printf("ret = %d\n", search1(a, size, target));
    size_t elem_size = sizeof(int);
    printf("ret = %d\n", search2(a, size, &target, elem_size, int_cmp));
  }
  return 0;
}
