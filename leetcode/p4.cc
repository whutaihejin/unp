#include <stdio.h>
#include <math.h>

#define min(a,b) a>b? b:a
#define max(a,b) a>b? a:b

int find_kth(int a[], int b[], int size_a, int size_b, int k){
        /* to maintain uniformaty, we will assume that size_a is smaller than size_b
                else we will swap array in call :) */
     //   printf("\n a = %d, b = %d, size_a = %d, size_b = %d, k = %d", a[0], b[0],size_a,size_b,k);
        if(size_a > size_b)
                return find_kth(b, a, size_b, size_a, k);
                
        
        /* Now case when size of smaller array is 0 i.e there is no elemt in one array*/
        if(size_a == 0 && size_b >0){
            return b[k-1]; // due to zero based index
        }
       /* case where K ==1 that means we have hit limit */
        if(k==1 ){
        	   return min(a[0], b[0]);
        }
             
        /* case where K ==1 that means we have hit limit */
        if(k==2 && size_a == 1 && size_b ==1 ){
        	   return max(a[0], b[0]);
        }
        /* Now the divide and conquer part */
        int i =  min(size_a, k/2) ; // K should be less than the size of array  
        int j =  min(size_b, k/2) ; // K should be less than the size of array 
   //     printf("\n i = %d, j = %d", i, j);

        if(a[i-1] > b[j-1]){
                // Now we need to find only K-j th element
              
                return find_kth(a, b+j, size_a, size_b-j, k-j);
        }
        else{
                return find_kth(a+i, b, size_a-i, size_b,  k-i);
        }

        return -1;
}

int main(){
  int a[] = {4, 5};
  int b[] = {2, 6};
  for (int i = 1; i <= 4; i++) {
    double value = find_kth(a, b, 2, 2, i);
    printf("median=%f\n", value);
  }
  return 0;
}
