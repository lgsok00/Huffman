# <허프만 코드(Huffman Coding)>
  : 트리를 이용해 문자열을 2진수로 압축하는 알고리즘
  
## 허프만 트리 만들기


1. 알파벳과 알파벳의 빈도수를 저장할 Node 클래스 생성

2. 알파벳의 빈도수를 count 한 후, 최소힙에 빈도수와 해당 알파벳을 Node로 만들어 저장

3. 최소 힙에서 Node 두 개를 꺼낸다.

4. 부모 Node(3에서 꺼낸 두 노드가 이 노드의 자식노드들이고
  이때 *부모 Node의 빈도 수=자식 Node들의 빈도수 합*)를 만든 후 최소 힙에 넣는다.
  
5.최소 힙이 빌(empty)때까지 1~4를 반복한다.


*(1,2,3,4,5,6)의 빈도수를 가진 요소들의 허프만 트리 과정*
![Com al](https://user-images.githubusercontent.com/80369805/114534955-7e1a3a00-9c8a-11eb-898a-21d63dc7fac8.jpg)

## 방법



## 허프만 트리 제작의 시간 복잡도
최소 값 추출에 걸리는 시간 : log N

N개의 원소 개수를 이용하므로


![허프만 시간복자도](https://user-images.githubusercontent.com/80369805/114535798-62fbfa00-9c8b-11eb-92bb-e6e41ee39f42.png)

시간복잡도 : log N x N = *O(NlogN)*

