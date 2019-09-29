# Annotation Processor

Annotation Processor는 소스코드 레벨에서 AST를 읽어 컴파일러가 컴파일 타임에 
새로운 소스코드를 생성하거나 수정할 수 있다. (수정은 권장하지 않음.)

즉 클래스, 바이트 코드 등 새로운 리소스들을 생성할 수 있다.

## 예시
 
@Override, Lombok 등이 있다.

## 장점

1. 런타임 비용이 없다.

## 단점 

1. 기존의 코드를 수정할 수 있는 API는 아직 없다.
(Lombok는 일종의 해킹)

