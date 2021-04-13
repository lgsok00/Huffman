# <허프만 코드(Huffman Coding)>
  : 트리를 이용해 문자열을 2진수로 압축하는 알고리즘
  
## 방법


1. 알파벳과 알파벳의 빈도수를 저장할 Node 클래스 생성

2. 알파벳의 빈도수를 count 한 후, 최소힙에 빈도수와 해당 알파벳을 Node로 만들어 저장

3. 최소 힙에서 Node 두 개를 꺼낸다.

4. 부모 Node(3에서 꺼낸 두 Node의 부모 Node)를 만든 후 최소 힙에 넣는다.
  (이때 부모 Node의 빈도 수는 자식 Node들의 합)

5. 최소 힙이 빌(empty)때까지 1~4를 반복한다.

  
  
## 과정
:*(1,2,3,4,5,6)의 빈도수를 가진 요소들의 허프만 트리 과정*

  
![컴알 (2)](https://user-images.githubusercontent.com/80369805/114546059-b2482780-9c97-11eb-8375-53d47d2b06b2.png)





## 실행결과

### 입력

![컴알 입력](https://user-images.githubusercontent.com/80369805/114552603-bc6e2400-9c9f-11eb-99ce-3906985e8470.png)

### 결과

![컴알 결과](https://user-images.githubusercontent.com/80369805/114552635-c55ef580-9c9f-11eb-931f-e6021f7a0514.png)


## 허프만 트리 제작의 시간 복잡도
1.최소 값 추출에 걸리는 시간 : log N
2.원소의 개수 : N

*시간복잡도 : log N x N = *O(NlogN)*


![허프만 시간복자도](https://user-images.githubusercontent.com/80369805/114535798-62fbfa00-9c8b-11eb-92bb-e6e41ee39f42.png)


