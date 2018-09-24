# Server

JDK: 10



1. __[Blocking Server](./java/server/blocking-server)__
2. __[Non Blocking Server](./java/server/non-blocking-server)__

---

동기 비동기와 블로킹 논블로킹은 다른 개념이다.

일반적으로 블로킹과 논블로킹은 소켓의 동작방식을 나타내는 말이며, 동기와 비동기는 서비스나 함수의 동작 방식을 나타내는 말이다.



* 동기: 순서가 정해져 있다.
* 비동기: 순서가 정해져있지 않다.
* 블로킹: 결과가 올 때 까지 함수를 반환하지 않는다.
* 논블로킹: 결과가 올 때 까지 기다리지 않고 함수를 바로 반환한다.



![server](./server.gif)