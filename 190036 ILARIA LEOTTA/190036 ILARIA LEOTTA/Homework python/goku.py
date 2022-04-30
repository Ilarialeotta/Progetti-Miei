import cImage

original= cImage.Image("goku piccolomod.gif") #Inseriamo la gif che poi andremo a modificare
L= original.getWidth() #Creamo inoltre una finestra con larghezza
A= original.getHeight() #ed altezza dove compariranno le immagini modificate
finestra= cImage.ImageWin("GokuBaby", L*3, A*2) #Il riquadro avrà il doppio dell'area
original.draw(finestra) #dell'immagine

Goku_Rosso=cImage.EmptyImage(L,A)#| creamo le 5 immagini
Goku_Verde= cImage.EmptyImage(L,A) #| da modificare
Goku_Blu= cImage.EmptyImage(L,A) 
Goku_Grigio= cImage.EmptyImage(L,A) 
Goku_Seppia= cImage.EmptyImage(L,A) 


for i in range(A):
    for j in range(L):
        p= original.getPixel(i,j)
        rosso=p.getRed() #prendiamo le componenti RGB della foto
        verde=p.getGreen()
        blu= p.getBlue()
        grigio=(rosso + verde + blu)//3
        seppia=(rosso + verde + blu)//6 #colore seppia

        ComponentiRosse= cImage.Pixel(rosso, verde*0, blu*0) #Ora ho modificato i
        ComponentiVerdi= cImage.Pixel(rosso*0, verde, blu*0) #parametri RGB di ogni
        ComponentiBlu= cImage.Pixel(rosso*0, verde*0, blu) #pixel
        ComponentiGrigio= cImage.Pixel(grigio, grigio, grigio)
        ComponentiSeppia= cImage.Pixel(seppia+20, seppia+30, seppia)


        Goku_Rosso.setPixel(i, j, ComponentiRosse)            #|Ora abbiamo alterato      
        Goku_Verde.setPixel(i, j, ComponentiVerdi)            #|le varie immagini con i     
        Goku_Blu.setPixel(i, j, ComponentiBlu)                #|valori modificati
        Goku_Grigio.setPixel(i, j, ComponentiGrigio)  
        Goku_Seppia.setPixel(i, j, ComponentiSeppia)

Goku_Rosso.setPosition(L+1,0) #stabiliamo la posizione di ROSSO
Goku_Verde.setPosition(0, A+1) #stabiliamo la posizione di VERDE
Goku_Blu.setPosition(L+1, A+1) #stabiliamo la posizione di BLU
Goku_Grigio.setPosition(L*2+1, 0) #stabiliamo la posizione di GRIGIO
Goku_Seppia.setPosition(L*2+1, A+1) #stabiliamo la posizione di SEPPIA

while True:
    print("""
Benvenuti nel mio programma per modificare il piccolo Goku...
Ora avrai la possibilità di fargli fare quello che vuoi!

Premi 1 per farlo diventare tutto rosso
Premi 2 per farlo diventare tutto verde
Premi 3 per farlo diventare tutto blu
Premi 4 per farlo diventare tutto grigio
Premi 5 per farlo diventare seppia
Premi 6 per uscire dal programma.""")

    colore= int(input("Come vuoi farlo diventare..."))

    if colore==1:
        Goku_Rosso.draw(finestra)
    elif colore==2:
        Goku_Verde.draw(finestra)
    elif colore==3:
        Goku_Blu.draw(finestra)
    elif colore==4:
        Goku_Grigio.draw(finestra)
    elif colore== 5:
        Goku_Seppia.draw(finestra)
    elif colore==6:
        print("\"Goku: Alla prossima!!\"")
        break

finestra.exitOnClick()
        
