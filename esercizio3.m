s=zpk('s');
C=2.5;
G=1/(0.2*s+1)^2*(2*s+1);
L=series(C,G);  

delta_cr=smorz_Mr(3);

%smorzamento > 0.39
margine_cr= delta_cr*100;

omega_new=0.2;

[modulo, argomento]= bode(L,omega_new);

margine_eff=180-abs(argomento);
%il margine effettivo è maggiore del margine obiettivo mentre il modulo è
%maggiore dell'unità quindi la rete sarà attenuatrice, taup deve essere
%maggiore di tauz 

m=1/modulo;
theta =-13 ;

[tauz,taup]=generica (omega_new,m,theta);

Cd= (1+s*tauz)/(1+s*taup);

Lhat= series(L,Cd);



That= feedback(Lhat,1);
omegab=bandwidth(That);

bodemag(That);

