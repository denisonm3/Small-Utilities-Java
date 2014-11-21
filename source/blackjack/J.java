import java.util.*;
class J{public static void main(String[]b){
int f,t,r,e=0,c[]=new int[9];Random x=new Random();String w="\nlose",y="\nJ1=",s,a="A23456789➓JQK";
while(e>-1){
while(e<9){c[e]=x.nextInt(51);for(f=0;f<e;f++)if(c[f]==c[e])e--;e++;}t=r=f=e=0;
while(f==0&e>-1){
if(e>0){e=x.nextInt(4)+17;s=t>e?"win":w;s+=y+t+"\nM="+e;f=-1;
}else{
 t+=c[r]<36?c[r]/4+1:10;r++;s=y;
 for(e=0;e<r;e++)s+=" ["+a.charAt(c[e]/4)+"]";
 if(t>21){s+=w;f=-1;}
}e=javax.swing.JOptionPane.showConfirmDialog(null,s,"21",f);}}}}