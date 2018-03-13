# DeciberryNumber
A simple java program to find the Deciberry number for any given number. 
<br>
## What is a deciberry number?
Any number **N** can be written as: 
> **sum(x_i . pow(2,i)) ; for i={0,1,2....m}**
<br>
where, x_i is any positive integer in [0,9]

### For Example:
17 = (4 x 2^1) + (9 x 2^0)
<br>
Deciberry Number for 17 = 4x10^1 + 9 =**49**
<br><br>
37 = (3 x 2^2) + (8 x 2^1) + (9 x 2^0)
<br>
Deciberry Number for 37 = 3x10^2 + 8x10^1 + 9 = **389**

#### So, Deciberry number for any number N can de defined as:
##### If, N = x_n.2^n + .....+ x_2.2^2 + x_1.2^1 + x_0.2^0
##### DN(N) = x_n.10^n + ......+ x_2.10^2 + X_1.10^1 + x_0
