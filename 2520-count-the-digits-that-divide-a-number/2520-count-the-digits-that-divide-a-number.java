class Solution {
    public int countDigits(int num) {
        int o = num ;
        int count = 0; 
        while(num > 0){
            int k = num % 10 ;
            if( o %k == 0){
                count++;
            }

            num = num /10; 
        }
        return count ;
    }
}