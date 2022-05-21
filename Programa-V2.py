# -*- coding: utf-8 -*-
"""
Created on Sat May 21 15:19:45 2022

@author: jpcor
"""

import matplotlib.pyplot as plt
import networkx as nx
g = nx.DiGraph()

def printgraph():
    nx.draw_networkx(g)
    plt.draw()
    plt.show()





with open("guategrafo.txt") as file:
    for line in file:
        edge = line.split()
        g.add_edge(edge[0],edge[1],weight=float(edge[2]))

salir = False
while salir == False:
    opcion = input("\nIngrese el número acorde a la acción que desea realizar:"
                        +"\n1) Mostrar grafo"
                        +"\n2) Mostrar ruta más corta (NO DISPONIBLE)"
                        +"\n3) Mostrar centro (NO DISPONIBLE)"
                        +"\n4) Agregar interrupción entre ciudades"
                        +"\n5) Indicar nueva ruta entre ciudades"
                        +"\n6) Salir\n")
    if (opcion=="1"):
        printgraph()

    elif (opcion=="2"):
        origen = input("Ingresar la ciudad de origen\n")
        destino = input("Ingresar la ciudad de destino\n")
        
            
    elif (opcion =="3"):
        regresar=0
    elif (opcion =="4"):
        origen = input("Ingresar la ciudad de origen\n")
        destino = input("Ingresar la ciudad de destino\n")
        g.remove_edge(origen,destino)
        printgraph()
    elif (opcion =="5"):
        origen = input("Ingresar la ciudad de origen\n")
        destino = input("Ingresar la ciudad de destino\n")
        distancia = float(input("Ingresar la ciudad distancia\n"))
        g.add_edge(origen,destino,weight=distancia)
        printgraph()    
    elif (opcion =="6"):
        print("Saliendo del programa")
        salir = True
    else:
        print("Seleccionar opcion valida")
