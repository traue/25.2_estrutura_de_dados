# Atividade 0 Filas


1)	No programa apresentado na aula, implemente a funcionalidade de localização da posição em que se encontra o primeiro elemento de um determinado valor. 
Não se trata do índice do vetor, mas sim da sua posição na fila (o primeiro da fila é o número 1).


2)	Implemente um programa de teste para simular o comportamento de uma fila de requisições em um computador servidor de forma que:
a.	O algoritmo deve executar diversas vezes consecutivas, chamadas ciclos, para simular o tráfego de requisições ao longo do tempo (laço for ou while). 
b.	A fila deve ser acondicionada em um vetor de objetos com dados diversos (podemos simplificar a simulação utilizando um vetor de inteiros). 
c.	O servidor tem diversos processadores paralelos.
d.	A cada ciclo de simulação:
i.	O servidor atende tantas requisições quantos forem os seus processadores (tempo de atendimento igual para todas as requisições). 
ii.	Chegam de 0 a N novas requisições na fila. 


O objetivo é poder analisar a probabilidade de perda de requisições (a fila está cheia por ocasião da chegada de uma nova requisição) em diversas situações/configurações:

- Quantidade máxima de requisições por ciclo (0 a max)
- Tamanho do vetor
- Quantidade de processadores do servidor.